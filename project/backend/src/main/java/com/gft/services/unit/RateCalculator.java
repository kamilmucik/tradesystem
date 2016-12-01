package com.gft.services.unit;

import com.gft.dto.model.Rate;
import com.gft.model.ProductRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by kamu on 2016-10-10.
 */
public class RateCalculator {

    static final Logger LOG = LoggerFactory.getLogger(RateCalculator.class);

    List<Rate> rates = new ArrayList<Rate>();

    final int MILLI_TO_HOUR = 1000 * 60 * 60;

    public RateCalculator() {}

    public RateCalculator(List<Rate> rates) {
        this.rates = rates;
    }

    public BigDecimal calculate(){
        BigDecimal result = new BigDecimal(0);
        BigDecimal tmp = null;

        // obliczanie z dwóch ostatnih watości
        for (int it = 0; it < rates.size(); it++){
            if ( it > 0) {
                Rate r1 = rates.get(it-1);
                Rate r2 = rates.get(it);

                int tmpHr = (int) ((new Date()).getTime() - r2.getAddDate().getTime()) / MILLI_TO_HOUR;

                tmp = r2.getRate()
                        .multiply(BigDecimal.valueOf(100))
                        .divide(r1.getRate(),1, RoundingMode.DOWN)
                        .setScale(0,BigDecimal.ROUND_DOWN)
                        .subtract(new BigDecimal(100));

                if (!tmp.equals(new BigDecimal(0))){
                    result = tmp;
                }

                //jesli nie miesci sie w jednej godzinie
                if (tmpHr != 0 ){
                    result = new BigDecimal(0);
                }
            }
        }
        rates.clear();
        return result;
    }


    public static class Builder {

        List<Rate> rates = new ArrayList<Rate>(0);

        public Builder() {
        }

        public Builder setModelList(List<ProductRate> rates) {
            for (ProductRate rate :  rates){
                this.rates.add(new Rate(rate.getId().intValue(), rate.getRate(), rate.getCreated()));
            }
            return this;
        }

        public Builder setDtoList(List<Rate> rates) {
            this.rates = rates;
            return this;
        }

        public RateCalculator build() {
            return new RateCalculator(rates);
        }

    }

}
