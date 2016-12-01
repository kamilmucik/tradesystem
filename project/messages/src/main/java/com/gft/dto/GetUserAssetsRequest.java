package com.gft.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by kamu on 2016-09-06.
 */
public class GetUserAssetsRequest extends Parent implements Serializable {

    private String login;

    private Long page;

    private Long size;

    private List<String> sort;

    private String direction;

    public GetUserAssetsRequest(String login, Long page, Long size, List<String> sort, String direction, Integer notificationCode){
        this.login = login;
        this.page = page;
        this.size = size;
        this.sort = sort;
        this.direction = direction;
        this.notificationCode = notificationCode;
    }

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

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
        return "GetUserAssetsRequest [login=" + login
                + ", page=" + page
                + ", size=" + size
                + ", sort=" + sort
                + ", direction=" + direction
                + ", notificationCode=" + notificationCode
                + ", correlationId=" + correlationId + "]";
    }
}
