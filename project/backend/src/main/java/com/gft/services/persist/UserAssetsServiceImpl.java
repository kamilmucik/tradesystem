package com.gft.services.persist;

import com.gft.dao.ProductDao;
import com.gft.dao.ProductRateDao;
import com.gft.dao.UserAssetsDao;
import com.gft.dao.UserDao;
import com.gft.dto.*;
import com.gft.dto.model.TransactionType;
import com.gft.model.Product;
import com.gft.model.ProductRate;
import com.gft.model.User;
import com.gft.model.UserAsset;
import com.gft.services.unit.RateCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

/**
 * Created by kamu on 2016-09-06.
 */
@Service("assetService")
public class UserAssetsServiceImpl implements UserAssetsService {

    static final Logger LOG = LoggerFactory.getLogger(UserAssetsServiceImpl.class);

    @Autowired
    private UserAssetsDao dao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductRateDao productRateDao;

    @Transactional(readOnly = true)
    @Override
    public GetUserAssetsResponse getAssetsByUserEmail(GetUserAssetsRequest request) {

        Page<UserAsset> products = dao.findAllByUser(
                request.getLogin(),
                new PageRequest(
                        request.getPage().intValue(),
                        request.getSize().intValue(),
                        Sort.Direction.fromString(request.getDirection()),
                        request.getSort().toArray(new String[0])
                )
        );
        ArrayList<com.gft.dto.model.UserAsset> list = new ArrayList<>();
        products.forEach( o->{
            list.add(new com.gft.dto.model.UserAsset(
                    o.getId(),
                    o.getProduct().getName(),
                    o.getVolume(),
                    o.getPrice(),
                    new BigDecimal(0)
            ));

        });
        return new GetUserAssetsResponse(
                list,
                request.getPage(),
                request.getSize(),
                request.getSort(),
                request.getDirection(),
                products.getTotalElements(),
                211
        );
    }

    @Transactional
    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        CreateOrderResponse response = new CreateOrderResponse();
        Product product = null;
        UserAsset userAsset = null;
        User user = userDao.findOne(request.getUserId());
        if (user != null) {
            userAsset = new UserAsset();
            userAsset.setContractor1(user);
            userAsset.setVolume(request.getVolume());
            userAsset.setPrice(request.getPrice());
            userAsset.setTransactionType(request.getType());

            product = productDao.find(request.getProductId());
        }

        if (product != null && userAsset != null && user != null ) {
            userAsset.setProduct(product);
            dao.save(userAsset);

            if (userAsset.getTransactionType().equals(TransactionType.SALE)) {
                ProductRate productRate = new ProductRate();
                productRate.setCreated(new Date());
                productRate.setRate(userAsset.getPrice());
                productRate.setProduct(product);
                productRateDao.save(productRate);
                product.getRates().add(productRate);

                if (!productRate.getRate().equals(product.getPrice())){
                    product.setPrice(productRate.getRate());
                    productDao.save(product);
                }
            }

            response.setId(userAsset.getId());
            response.setNotificationCode(Parent.MESSAGE_CODE_CORRECT);
        } else {
            response.setNotificationCode(Parent.MESSAGE_CODE_INCORRECT);
        }

        response.setCorrelationId(request.getCorrelationId());
        return response;
    }

    @Override
    public GetActivOrderListResponse findAllActiveAssets(GetActivOrderListRequest request) {
        Page<UserAsset> products = dao.findActiveAssets(
                new PageRequest(
                        request.getPage().intValue(),
                        request.getSize().intValue(),
                        Sort.Direction.fromString(request.getDirection()),
                        request.getSort().toArray(new String[0])
                )
        );
        ArrayList<com.gft.dto.model.ActiveOrder> list = new ArrayList<>();
        products.forEach( o->{
            list.add(new com.gft.dto.model.ActiveOrder(
                    o.getId(),
                    o.getProduct().getName(),
                    o.getVolume(),
                    o.getPrice(),
                    new BigDecimal(0),
                    o.getTransactionType()
            ));

        });
        return new GetActivOrderListResponse(
                list,
                request.getPage(),
                request.getSize(),
                request.getSort(),
                request.getDirection(),
                products.getTotalElements(),
                211
        );
    }

    @Override
    public List<com.gft.dto.model.ActiveOrder> findAllActiveAssets() {
        List<com.gft.dto.model.ActiveOrder> assets = new ArrayList<com.gft.dto.model.ActiveOrder>();
        for (com.gft.model.UserAsset a : dao.findActiveAssets()) {
            assets.add(new com.gft.dto.model.ActiveOrder(
                    a.getId(),
                    a.getProduct().getName(),
                    a.getVolume(),
                    a.getPrice(),
                    a.getPrice().multiply(new BigDecimal(a.getVolume())).setScale(2),
                    a.getTransactionType(),
                    a.getCreatedDate()
            ));
        }
        return assets;
    }

    @Transactional(readOnly = true)
    @Override
    public List<com.gft.dto.model.UserAsset> getAssetsByUserEmail(String login) {
        List<com.gft.dto.model.UserAsset> assets = new ArrayList<com.gft.dto.model.UserAsset>();
        List<UserAsset> products = dao.findAllByUser(login, TransactionType.BUY);

        products.forEach( o->{
            BigDecimal profit = o.getPrice()
                    .multiply(new BigDecimal(100))
                    .divide(o.getProduct().getPrice(),2, RoundingMode.HALF_DOWN)
                    .setScale(2)
                    .subtract(new BigDecimal(100))
                    .multiply(new BigDecimal(-1));

            assets.add(new com.gft.dto.model.UserAsset(
                    o.getId(),
                    o.getProduct().getName(),
                    o.getVolume(),
                    o.getPrice(),
                    o.getPrice()
                            .multiply(new BigDecimal(o.getVolume()))
                            .setScale(2),
                    profit
            ));

        });
        return assets;
    }

    @Override
    public Map<Date, BigDecimal> getAssetsChartData(String login) {
        Map<Date, BigDecimal> map = new HashMap<Date, BigDecimal>();
        //TODO: testowe dane do wykresu
        for (int i =1; i <= 10; i++){
            BigDecimal max = new BigDecimal((i * 20) + ".0");
            BigDecimal randFromDouble = new BigDecimal(Math.random());
            BigDecimal actualRandomDec = randFromDouble.divide(max,4, RoundingMode.HALF_DOWN).multiply(new BigDecimal(100)).setScale(2);

            LocalDate yesterday = LocalDate.now().minusDays(i);

            map.put(Date.from(yesterday.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()),actualRandomDec );
        }

        return map;
    }

    private Sort orderBy() {
        return new Sort(Sort.Direction.DESC, "created");
    }

    public void setDao(UserAssetsDao dao) {
        this.dao = dao;
    }
}

