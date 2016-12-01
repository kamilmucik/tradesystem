package com.gft.messaging;

import com.gft.dto.*;
import com.gft.dto.model.*;
import com.gft.socket.rate.RateTimer;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by kamu on 2016-08-31.
 */
@Controller
public class MiddletierHandler extends MessageListenerAdapter {

    static final Logger LOG = LoggerFactory.getLogger(MiddletierHandler.class);

    @Autowired
    MessageBase messageBase;

    public void handleMessage(RegisterBackendRequest request) {
        LOG.debug("size[1]: {}", messageBase.getCustomers().size());

        request.getProducts().forEach((k,v)->{
            LOG.debug("product[{}]: {}", k,v);

        });

        //JMSCustomer cus = new JMSCustomer(request.getInstance(),request.getProducts());

        //LOG.debug("size[1]: {}", messageBase.getJmsCustomers().contains(cus));


        /*if (!messageBase.getJmsCustomers().contains(cus)){
            messageBase.getJmsCustomers().add(new JMSCustomer(request.getInstance(),request.getProducts()));
        }
*/
        //LOG.debug("size[2]: {}", messageBase.getJmsCustomers().size());
    }

    public void handleMessage(GetUserAssetsResponse response) {
        if(messageBase.getFutures().keySet().contains(response.getCorrelationId())) {
           Page<UserAsset> imp = new PageImpl<>(
                    response.getAssetList(),
                    new PageRequest(
                            response.getPage().intValue(),
                            response.getSize().intValue(),
                            new Sort(Sort.Direction.fromString(response.getDirection()), response.getSort().get(0))),
                            response.getTotal());
            messageBase.getFutures().get(response.getCorrelationId()).complete(imp);
            messageBase.getFutures().remove(response.getCorrelationId());
        }
    }

    public void handleMessage(GetProductListResponse response) {
        if(messageBase.getFutures().keySet().contains(response.getCorrelationId())) {
            Page<Product> imp = new PageImpl<>(
                    response.getAssetList(),
                    new PageRequest(
                            response.getPage().intValue(),
                            response.getSize().intValue(),
                            new Sort(Sort.Direction.fromString(response.getDirection()),
                                    response.getSort().get(0))),
                    response.getTotal());
            messageBase.getFutures().get(response.getCorrelationId()).complete(imp);
            messageBase.getFutures().remove(response.getCorrelationId());
        }
    }

    public void handleMessage(LoginUserResponse response) {
        if(messageBase.getFutures().keySet().contains(response.getCorrelationId())) {
            User user = null;
            if (response.getNotificationCode() == 211) {
                user = new User();
                user.setLogin(response.getLogin());
                messageBase.getFutures().get(response.getCorrelationId()).complete(user);
            } else {
                MiddletierException me = new MiddletierException("kod ","wiadomosc");
                messageBase.getFutures().get(response.getCorrelationId()).completeExceptionally(me);
            }
            messageBase.getFutures().remove(response.getCorrelationId());
        }
    }

    public void handleMessage(GetUserDetailsResponse response) {
        if(messageBase.getFutures().keySet().contains(response.getCorrelationId())) {
            UserDetails userDetails =
                    new UserDetails(
                            response.getUserName(),
                            response.getLastName(),
                            response.getFreeResources(),
                            response.getWalletValue(),
                            response.getShareholderReturn());
            messageBase.getFutures().get(response.getCorrelationId()).complete(userDetails);
            messageBase.getFutures().remove(response.getCorrelationId());
        }
    }

    public void handleMessage(GetProductDetailsResponse response) {
        if(messageBase.getFutures().keySet().contains(response.getCorrelationId())) {
            messageBase.getFutures().get(response.getCorrelationId()).complete(new Product(response.getId(), response.getPrice(), new BigDecimal("0.01")));
            messageBase.getFutures().remove(response.getCorrelationId());
        }
    }

    public void handleMessage(CreateOrderResponse response) {
        if(messageBase.getFutures().keySet().contains(response.getCorrelationId())) {

            if (response.getNotificationCode() == 211) {
                messageBase.getFutures().get(response.getCorrelationId()).complete(new ResponseEntity<Void>(HttpStatus.OK));
            } else {
                messageBase.getFutures().get(response.getCorrelationId()).complete(new ResponseEntity<Void>(HttpStatus.BAD_REQUEST));
            }
            messageBase.getFutures().remove(response.getCorrelationId());
        }
    }

    public void handleMessage(GetActivOrderListResponse response) {
        if(messageBase.getFutures().keySet().contains(response.getCorrelationId())) {
            Page<ActiveOrder> imp = new PageImpl<>(
                    response.getAssetList(),
                    new PageRequest(
                            response.getPage().intValue(),
                            response.getSize().intValue(),
                            new Sort(Sort.Direction.fromString(response.getDirection()), response.getSort().get(0))),
                    response.getTotal());
            messageBase.getFutures().get(response.getCorrelationId()).complete(imp);
            messageBase.getFutures().remove(response.getCorrelationId());
        }
    }

    public void handleMessage(GetSocketPackageResponse response) throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        response.getUsers().forEach((k,v) ->{
            UserComplex userComplex = v;
            BigDecimal userProfit = new BigDecimal(0);
            StringBuilder userAssetsSb = new StringBuilder();
            for (Iterator<UserAsset> iterator = userComplex.getAssetList().iterator(); iterator.hasNext();) {
                UserAsset userAsset = iterator.next();
                userProfit = userProfit.add(userAsset.getBuyValue());
                userAssetsSb.append(String.format("{id: %d, name: '%s', BuyPrice: '%s', volume: '%s', BuyValue: '%s', profit: '%s'}",
                        userAsset.getId(),
                        userAsset.getName(),
                        userAsset.getBuyPrice(),
                        userAsset.getVolume(),
                        userAsset.getBuyValue(),
                        userAsset.getProfit()

                ));
                if (iterator.hasNext()) {
                    userAssetsSb.append(',');
                }
            }
            userComplex.getUserDetails().setWalletValue(userProfit);
            JSONObject userDetails = new JSONObject(userComplex.getUserDetails());
            try {
                RateTimer.send(k,
                        String.format("{'type': 'update-user', 'data' :  {user : %s , assets : [%s]} }",
                                userDetails.toString(),
                                userAssetsSb.toString()
                        ));
            } catch (Exception e) {
                e.printStackTrace();
            }

            LOG.debug("");
            StringBuilder chartLegendSb = new StringBuilder();
            StringBuilder chartDataSb = new StringBuilder();
            for (Iterator<Map.Entry<Date,BigDecimal>> iterator = userComplex.getChart().entrySet().iterator(); iterator.hasNext();) {
                Map.Entry pair = (Map.Entry)iterator.next();
                chartLegendSb.append( "'" + dateFormat.format(pair.getKey()) + "'" );
                chartDataSb.append(pair.getValue());
                if (iterator.hasNext()) {
                    chartLegendSb.append(',');
                    chartDataSb.append(',');
                }
            }
            try {
                RateTimer.send(k,
                        String.format("{'type': 'update-chart', 'data' :  {label : [%s] , data : [%s]} }",
                                chartLegendSb.toString(),
                                chartDataSb.toString()
                        ));
            } catch (Exception e) {
                e.printStackTrace();
            }


        });

        StringBuilder productsSb = new StringBuilder();
        for (Iterator<Product> iterator = response.getProductList().iterator(); iterator.hasNext();) {
            Product product = iterator.next();
            productsSb.append(String.format("{id: %d, name: '%s', rate: '%s', ratechanges: '%s'}",
                    product.getId(),
                    product.getName(),
                    product.getPrice(),
                    product.getRate()
            ));
            if (iterator.hasNext()) {
                productsSb.append(',');
            }
        }

        StringBuilder activeOrdersSb = new StringBuilder();
        for (Iterator<ActiveOrder> iterator = response.getActiveOrderList().iterator(); iterator.hasNext();) {
            ActiveOrder order = iterator.next();
            activeOrdersSb.append(String.format("{te: '%s', name: '%s', volume: '%s', price: '%s', valuation: '%s',rate: 0, type: '%s'}",
                    order.getId(),
                    order.getName(),
                    order.getVolumen(),
                    order.getPrice(),
                    order.getValuation(),
                    order.getType().toString()
            ));

            if (iterator.hasNext()) {
                activeOrdersSb.append(',');
            }
        }
        RateTimer.broadcast(String.format("{'type': 'update', 'data' :  {products : [%s], activeorders : [%s]} }", productsSb, activeOrdersSb ));
    }

}
