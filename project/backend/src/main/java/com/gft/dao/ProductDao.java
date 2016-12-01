package com.gft.dao;

import com.gft.model.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Created by kamu on 8/23/2016.
 */
@Repository
public interface ProductDao extends PagingAndSortingRepository<Product, Long> {

    Optional<List<Product>> findByEnvNameIn(Set<String> envName);

    @Query("SELECT new com.gft.model.Product(p.id, p.name, p.envName) FROM Product p ")
    Optional<List<Product>> findAllWithRate();

    @Query("SELECT p FROM Product p")
    Optional<List<Product>> findForMatch();

    @Query("SELECT p FROM Product p WHERE p.id = :paramId")
    Product find(@Param("paramId") Long id);

    @Procedure("PROCEDURE3")
    BigDecimal procedure3(Long arg);

    //TODO: Napisać procedurę, która wraca gotowy obiekt z SELEKT FROM z wyliczoną stawką zmiany kursu, dialekt 10g nie obsługuje procedur z zwrotką obiektu
    //@Procedure
    //List<Product> findProductsViaProcedure();



}
