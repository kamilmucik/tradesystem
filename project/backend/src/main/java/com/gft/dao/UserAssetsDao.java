package com.gft.dao;

import com.gft.dto.model.TransactionType;
import com.gft.model.Product;
import com.gft.model.UserAsset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by kamu on 2016-09-06.
 */
@Repository
public interface UserAssetsDao extends PagingAndSortingRepository<UserAsset, Long> {

    @Query("SELECT ua FROM UserAsset ua WHERE LOWER(ua.contractor1.email) = LOWER(:email)")
    Page<UserAsset> findAllByUser(@Param("email") String email, Pageable pageable);

    @Query("SELECT ua FROM UserAsset ua WHERE " +
            "ua.isDone = true AND " +
            "(( " +
            "LOWER(ua.contractor1.email) = LOWER(:email) AND ua.transactionType = :type " +
            ") OR (" +
            "LOWER(ua.contractor2.email) = LOWER(:email) AND ua.transactionType <> :type" +
            "))")
    List<UserAsset> findAllByUser(@Param("email") String email, @Param("type")TransactionType type);

    @Query("SELECT ua FROM UserAsset ua WHERE ua.isDone = false AND ua.product = :product AND ua.transactionType = :type")
    List<UserAsset> findActiveAssets(@Param("product")Product product, @Param("type")TransactionType type, Sort sort);

    @Query("SELECT ua FROM UserAsset ua WHERE ua.isDone = false")
    Page<UserAsset> findActiveAssets(Pageable pageable);

    @Query("SELECT ua FROM UserAsset ua WHERE ua.isDone = false ORDER BY ua.id ASC")
    List<UserAsset> findActiveAssets();
}
