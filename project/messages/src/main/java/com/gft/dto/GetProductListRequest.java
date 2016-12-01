package com.gft.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Request represent all active product
 * Created by kamu on 2016-09-07.
 */
public class GetProductListRequest extends Parent implements Serializable {

    private Long page;

    private Long size;

    private List<String> sort;

    private String direction;

    public GetProductListRequest(Long page, Long size, List<String> sort, String direction, Integer notificationCode){
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.direction = direction;
        this.notificationCode = notificationCode;
    }

    public Long getPage() {return page;}

    public void setPage(Long page) {this.page = page;}

    public Long getSize() {return size;}

    public void setSize(Long size) {this.size = size;}

    public List<String> getSort() {return sort;}

    public void setSort(List<String> sort) {this.sort = sort;}

    public String getDirection() {return direction;}

    public void setDirection(String direction) {this.direction = direction;}

    @Override
    public String toString() {
        return "GetProductListRequest [page=" + page
                + ", size=" + size
                + ", sort=" + sort
                + ", direction=" + direction
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
