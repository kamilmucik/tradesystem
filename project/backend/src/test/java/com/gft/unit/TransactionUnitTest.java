package com.gft.unit;

import com.gft.dto.model.Asset;
import com.gft.services.unit.AssetMatcher;
import junit.framework.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by kamu on 2016-09-13.
 */
public class TransactionUnitTest {

    private static final Logger LOG = LoggerFactory.getLogger(TransactionUnitTest.class);

    private List<Asset> assets;

    private AssetMatcher matcher;

    private Asset asset = null;

    @Before
    public void setUp() throws InterruptedException {

    }

    @Test
    public void testMatch() {
        assets = prepare();
        matcher = new AssetMatcher(assets);
        Assume.assumeNotNull("Transactions list is null" ,assets);
        Assert.assertEquals(3,assets.size());
        Assert.assertNotNull("AssetMatcher is null" ,matcher);

        Optional<Asset> optional = matcher.findOldestByProduct(1l);

        if (optional.isPresent())
            asset = optional.get();

        Assert.assertNotNull("Asset is null", asset);
        Assert.assertEquals(7, asset.getVolume().intValue());
    }

    @Test
    public void testNotMatch() {
        assets = prepare();
        matcher = new AssetMatcher(assets);
        Assert.assertNotNull("Transactions list is null" ,assets);
        Assert.assertEquals(3,assets.size());
        Assert.assertNotNull("AssetMatcher is null" ,matcher);

        Optional<Asset> optional = matcher.findOldestByProduct(21l);
        if (optional.isPresent())
            asset = optional.get();

        Assert.assertNull("Asset isn't null", asset);
    }

    private static List<Asset> prepare(){
        List<Asset> assets = new ArrayList<Asset>();
        assets.add(new Asset(2l,1l,new BigDecimal(10),-10));
        assets.add(new Asset(1l,2l,new BigDecimal(10),7));
        assets.add(new Asset(1l,3l,new BigDecimal(20),5));

        return assets;
    }
}
