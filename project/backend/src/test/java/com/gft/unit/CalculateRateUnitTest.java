package com.gft.unit;

import com.gft.dto.model.Rate;
import com.gft.services.unit.RateCalculator;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamu on 2016-10-10.
 */
public class CalculateRateUnitTest {

    private static final Logger LOG = LoggerFactory.getLogger(CalculateRateUnitTest.class);

    //Lista ofert sprzedaży
    List<Rate> rates;

    @Test
    public void calculateRateTest() throws ParseException {
        rates = prepare();
        Assume.assumeNotNull(rates);
        RateCalculator rateCalculator = new RateCalculator(rates);
        Assume.assumeNotNull(rateCalculator);

        BigDecimal result = new RateCalculator.Builder().setDtoList(rates).build().calculate();
        Assert.assertEquals(result, new BigDecimal("0"));
    }


    private static List<Rate> prepare() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        List<Rate> rates = new ArrayList<Rate>();
        rates.add(new Rate(new BigDecimal("956.52"), simpleDateFormat.parse("2016-10-10 11:00") ));
        rates.add(new Rate(new BigDecimal("1100"),   simpleDateFormat.parse("2016-10-10 11:01") ));
        rates.add(new Rate(new BigDecimal("1000"),   simpleDateFormat.parse("2016-10-10 12:15") ));
        rates.add(new Rate(new BigDecimal("1000"),   simpleDateFormat.parse("2016-10-10 12:19") ));
        rates.add(new Rate(new BigDecimal("1000"),   simpleDateFormat.parse("2016-10-10 13:15") ));

        return rates;
    }
}
