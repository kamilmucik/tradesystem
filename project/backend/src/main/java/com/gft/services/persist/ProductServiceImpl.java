package com.gft.services.persist;

import com.gft.dao.ProductDao;
import com.gft.dao.ProductRateDao;
import com.gft.dto.*;
import com.gft.dto.model.TransactionType;
import com.gft.model.Product;
import com.gft.model.ProductRate;
import com.gft.services.unit.RateCalculator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by kamu on 8/23/2016.
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    static final Logger LOG = LoggerFactory.getLogger(ProductServiceImpl.class);

    private static final int ERROR_BACKEND_CODE = 2;

    @Autowired
    private ProductDao dao;

    @Autowired
    private ProductRateDao productRateDao;

    @Override
    public Page<Product> findAll(int page, int size, List<String> sort, String direction) {
        return dao.findAll(
                new PageRequest(
                        page,
                        size,
                        Sort.Direction.fromString(direction),
                        sort.toArray(new String[0])
                )
        );
    }

    @Override
    public List<Product> findAllToMatch() {
        Optional<List<Product>> possible = dao.findForMatch();

        if (possible.isPresent()) {
            return possible.get();
        }

        return new ArrayList<>(0);
    }


    @Override
    public List<com.gft.dto.model.Product> findAll() {
        RateCalculator calculator;
        BigDecimal price = null;
        List<com.gft.dto.model.Product> products = new ArrayList<com.gft.dto.model.Product>();
        for (com.gft.model.Product p : dao.findAll()) {
            Optional<List<ProductRate>> pr =productRateDao.findProductRate(p, new Sort(Sort.Direction.ASC, "created"));
            if (pr.isPresent()){
                p.getRates().addAll(pr.get());
            }
            calculator = new RateCalculator.Builder().setModelList(p.getRates()).build();

            if (p.getRates().size() > 0){
                price = p.getRates().get(p.getRates().size()-1).getRate();
            } else {
                price = new BigDecimal(0);
            }

            products.add(new com.gft.dto.model.Product(p.getId(), p.getName(),price,calculator.calculate() ));
        }

        return products;
    }

    @Override
    public Map<Long, String> getSupportedProduct(String envName) {
        Map<Long, String> supportedProductMap = new HashMap<>();
        String[] products =  envName.split(",");
        Set<String> params = new HashSet<String>();
        Arrays.stream(products).forEach(v ->{
            params.add(v);
        });
        return supportedProductMap;
    }

    @Override
    public GetProductListResponse getAll(GetProductListRequest request) {
        Page<Product> products = dao.findAll(
                new PageRequest(
                        request.getPage().intValue(),
                        request.getSize().intValue(),
                        Sort.Direction.fromString(request.getDirection()),
                        request.getSort().toArray(new String[0])
                )
        );
        ArrayList<com.gft.dto.model.Product> list = new ArrayList<>();
        products.forEach( o->{
            list.add(new com.gft.dto.model.Product(
                    o.getId(),
                    o.getName(),
                    o.getRates().get(0).getRate(),
                    dao.procedure3(o.getId())
            ));
        });
        return new GetProductListResponse(
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
    public GetProductDetailsResponse getDetails(GetProductDetailsRequest request) {
        Product p = dao.find(request.getId());
        return new GetProductDetailsResponse(p.getId(),p.getRates().get(0).getRate());
    }

    public void setProductDao(ProductDao dao) {
        this.dao = dao;
    }

    public void setProductRateDao(ProductRateDao productRateDao) {this.productRateDao = productRateDao;}
}
