package com.gft.services;

import com.gft.dao.UserAssetsDao;
import com.gft.dto.model.TransactionType;
import com.gft.model.Product;
import com.gft.model.User;
import com.gft.model.UserAsset;
import com.gft.model.UserSeparatedAsset;
import com.gft.services.persist.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by kamu on 2016-10-03.
 */
@Component
public class MatcherScheduledTasks {

    private static final Logger LOG = LoggerFactory.getLogger(MatcherScheduledTasks.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    ProductService productService;

    @Autowired
    UserAssetsDao userAssetsDao;

    @Scheduled(fixedRate = 90000)
    @Transactional(readOnly=false,propagation= Propagation.REQUIRES_NEW,rollbackFor=Exception.class)
    public void reportCurrentTime() {
        LOG.info("The time is now {}", dateFormat.format(new Date()));

        List<Product> productList = productService.findAllToMatch();
        for (Product product : productList) {
            List<UserAsset> buyList  = userAssetsDao.findActiveAssets(product, TransactionType.BUY, new Sort(Sort.Direction.DESC, "price"));
            List<UserAsset> saleList = userAssetsDao.findActiveAssets(product, TransactionType.SALE, new Sort(Sort.Direction.ASC, "price"));

            BigDecimal finalPrice = null;
            UserSeparatedAsset childUserAsset = null;

            for (UserAsset sale : saleList){
                for (UserAsset buy : buyList){
                    int res= buy.getPrice().compareTo(sale.getPrice());
                    if (res >= 0 ){
                        User seller = sale.getContractor1();
                        User buyer = buy.getContractor1();

                        if (seller.equals(buyer))
                            continue;

                        if (sale.getVolume() > buy.getVolume()){
                            // sprzedaje więcej niż chcę kupić
                            sale.setVolume(sale.getVolume() - buy.getVolume());
                            childUserAsset = new UserSeparatedAsset(sale);
                            userAssetsDao.save(childUserAsset);
                        } else if (sale.getVolume() < buy.getVolume()){
                            // sprzedaje mniej niż chce kupić
                            Long div = Math.abs(buy.getVolume() - sale.getVolume());
                            sale.setVolume(0l);
                            buy.setVolume(div);
                            childUserAsset = new UserSeparatedAsset(buy);
                            userAssetsDao.save(childUserAsset);
                        }

                        finalPrice = sale.getPrice().multiply(BigDecimal.valueOf(sale.getVolume().intValue()));
                        seller.setAmount(seller.getAmount().add(finalPrice));
                        buyer.setAmount(buyer.getAmount().subtract(finalPrice));

                        sale.setDone(true);
                        buy.setDone(true);
                        buy.setContractor2(seller);
                        sale.setContractor2(buyer);

                        userAssetsDao.save(buy);
                        userAssetsDao.save(sale);
                    }
                }
            }
        }
    }



}
