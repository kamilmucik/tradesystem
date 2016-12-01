package com.gft.db;

import com.gft.ConfigApp;
import com.gft.dao.ProductDao;
import com.gft.dao.UserAssetsDao;
import com.gft.dao.UserDao;
import com.gft.dto.model.TransactionType;
import com.gft.model.User;
import com.gft.model.UserAsset;
import com.gft.model.UserSeparatedAsset;
import org.junit.Assume;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by kamu on 2016-10-04.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ConfigApp.class)
public class UserAssetsIntegrationTest {

    private static final Logger LOG = LoggerFactory.getLogger(UserAssetsIntegrationTest.class);

    @Autowired
    ProductDao productDao;

    @Autowired
    UserDao userDao;

    @Autowired
    UserAssetsDao userAssetsDao;

    @Test
    @Transactional
    @Rollback(false)
    public void updateBatch(){
        Assume.assumeNotNull("UserAssetsDao is null" ,userAssetsDao);
        Assume.assumeNotNull("ProductDao is null" ,productDao);

        com.gft.model.Product product = productDao.findOne(1l);
        UserAsset buy  = userAssetsDao.findActiveAssets(product, TransactionType.BUY, new Sort(Sort.Direction.DESC, "price")).get(0);
        UserAsset sale = userAssetsDao.findActiveAssets(product, TransactionType.SALE, new Sort(Sort.Direction.ASC, "price")).get(0);

        buy.setDone(true);
        sale.setDone(true);

        buy.setContractor2(sale.getContractor1());
        sale.setContractor2(buy.getContractor1());

        userAssetsDao.save(buy);
        userAssetsDao.save(sale);
    }

    @Test
    @Transactional
    @Rollback(false)
    public void findAssetToMatch(){
        Assume.assumeNotNull("UserAssetsDao is null" ,userAssetsDao);
        Assume.assumeNotNull("ProductDao is null" ,productDao);

        com.gft.model.Product product = productDao.findOne(1l);
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
                    //wolumen jest równy
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
