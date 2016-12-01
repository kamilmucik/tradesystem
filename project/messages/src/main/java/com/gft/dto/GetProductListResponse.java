package com.gft.dto;

import com.gft.dto.model.Product;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Response represent all active product
 * Created by kamu on 2016-09-07.
 */
public class GetProductListResponse extends Parent implements Serializable {

    private ArrayList<Product> list = new ArrayList<Product>(0);

    private Long page;

    private Long size;

    private List<String> sort;

    private String direction;

    private Long total;

    public GetProductListResponse(ArrayList<Product> list, Long page, Long size, List<String> sort, String direction, Long total, Integer notificationCode){
        this.list = list;
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.direction = direction;
        this.notificationCode = notificationCode;
        this.total = total;
    }

    public ArrayList<Product> getAssetList() {return list;}

    public void setAssetList(ArrayList<Product> assetList) {this.list = assetList;}

    public Long getPage() {return page;}

    public void setPage(Long page) {this.page = page;}

    public Long getSize() {return size;}

    public void setSize(Long size) {this.size = size;}

    public List<String> getSort() {return sort;}

    public void setSort(List<String> sort) {this.sort = sort;}

    public String getDirection() {return direction;}

    public void setDirection(String direction) {this.direction = direction;}

    public Long getTotal() {return total;}

    public void setTotal(Long total) {this.total = total;}

    @Override
    public String toString() {
        return "GetProductListResponse [list=" + list.size()
                + ", page=" + page
                + ", size=" + size
                + ", sort=" + sort
                + ", direction=" + direction
                + ", total=" + total
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }

}
