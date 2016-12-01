package com.gft.services.unit;

import com.gft.dto.model.Asset;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Class represnt mechanism to macht two site of contrahents and find best way to create transaction
 * Created by kamu on 2016-09-13.
 */
public class AssetMatcher {

    static final Logger LOG = LoggerFactory.getLogger(AssetMatcher.class);

    List<Asset> assets = new ArrayList<>();

    public AssetMatcher(List<Asset> assets) {
        this.assets = assets;
    }


    public Optional<Asset> findOldestByProduct(Long productId){
        Optional optional = Optional.empty();
        Collections.sort(assets);

        List<Asset> result = assets.stream()
                .filter(asset -> productId. equals (asset.getProductId()))
                .collect(Collectors.toList());

        if (result.size() > 0)
            optional = Optional.of( result.get(0) );

        return optional;

    }
}
