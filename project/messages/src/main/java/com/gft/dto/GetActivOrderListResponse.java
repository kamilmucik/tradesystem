package com.gft.dto;

import com.gft.dto.model.ActiveOrder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kamu on 2016-09-20.
 */
public class GetActivOrderListResponse extends Parent implements Serializable {

    private ArrayList<ActiveOrder> list = new ArrayList<ActiveOrder>(0);

    private Long page;

    private Long size;

    private List<String> sort;

    private String direction;

    private Long total;

    public GetActivOrderListResponse(ArrayList<ActiveOrder> list, Long page, Long size, List<String> sort, String direction, Long total, Integer notificationCode){
        this.list = list;
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.direction = direction;
        this.notificationCode = notificationCode;
        this.total = total;
    }

    public GetActivOrderListResponse(Integer notificationCode){
        this.notificationCode = notificationCode;
    }

    public ArrayList<ActiveOrder> getAssetList() {return list;}

    public void setAssetList(ArrayList<ActiveOrder> list) {this.list = list;}

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
        return "GetActivOrderListResponse [list=" + list.size()
                + ", page=" + page
                + ", size=" + size
                + ", sort=" + sort
                + ", direction=" + direction
                + ", total=" + total
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
