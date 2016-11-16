package com.gft.unittest;
/**
 * Created by kamu on 2016-08-25.
 */

import com.gft.bean.Inventory;
import com.gft.bean.InventoryBuilder;
import com.gft.bean.InventoryResource;
import com.gft.dto.User;
import org.junit.*;

import java.util.UUID;

public class BuilderTest {

    @Test
    public void testBuilder() {
        Inventory inventory = new InventoryBuilder(UUID.randomUUID().toString())
                .setObj(new User())
                .setMethod("findAll")
                .setJavaType("List")
                .addCriteria("page", new Long("0"))
                .addCriteria("size", new String("10"))
                .addCriteria("total", new Integer("20"))
                .build();

        InventoryResource inventoryResource = new InventoryBuilder().deserialize(inventory);
        Assert.assertEquals(inventoryResource.getCriteria().get(0), new Long("0"));
    }
}
