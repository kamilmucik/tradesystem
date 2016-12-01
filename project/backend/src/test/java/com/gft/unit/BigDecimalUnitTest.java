package com.gft.unit;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

/**
 * Created by kamu on 2016-10-04.
 */
public class BigDecimalUnitTest {

    private static final Logger LOG = LoggerFactory.getLogger(BigDecimalUnitTest.class);

    @Test
    public void compareTest() {
        BigDecimal x = new BigDecimal("1");
        BigDecimal y = new BigDecimal("1.00");
        Assert.assertFalse(x.equals(y));
        Assert.assertTrue(x.compareTo(y) == 0 ? true: false);
    }

    @Test
    public void higerTest() {
        BigDecimal bg1, bg2;
        bg1 = new BigDecimal("10");
        bg2 = new BigDecimal("10.01");
        int res= bg1.compareTo(bg2); // compare bg1 with bg2

        Assert.assertTrue(res == -1);

        String str1 = "Both values are equal ";
        String str2 = "First Value is greater ";
        String str3 = "Second value is greater";

        if( res == 0 )
            LOG.debug("{}" ,str1 );
        else if( res == 1 )
            LOG.debug("{}" ,str2 );
        else if( res == -1 )
            LOG.debug("{}" ,str3 );
    }

    @Test
    public void multiplyTest() {
        BigDecimal bg1, bg2, bg3;

        bg1 = new BigDecimal("3");
        bg2 = new BigDecimal("4.0");
        bg3 = bg1.multiply(bg2);
        Assert.assertEquals(bg3, new BigDecimal("12.0"));
    }

    @Test
    public void divideTest() {
        BigDecimal bg1, bg2, bg3;

        bg1 = new BigDecimal("12");
        bg2 = new BigDecimal("4.0");
        bg3 = bg1.divide(bg2);
        Assert.assertEquals(bg3, new BigDecimal("3"));
    }

    @Test
    public void subtractTest() {
        BigDecimal bg1, bg2, bg3;

        bg1 = new BigDecimal("3");
        bg2 = new BigDecimal("4.0");
        bg3 = bg1.subtract(bg2);
        Assert.assertEquals(bg3, new BigDecimal("-1.0"));
    }

    @Test
    public void addTest() {
        BigDecimal bg1, bg2, bg3;
        bg1 = new BigDecimal("3");
        bg2 = new BigDecimal("4.0");
        bg3 = bg1.add(bg2);
        Assert.assertEquals(bg3, new BigDecimal("7.0"));
    }
}
