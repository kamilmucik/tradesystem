package com.gft.bean;

import java.io.Serializable;
import java.util.HashMap;

/**
 * Created by kamu on 8/22/2016.
 */
public class Inventory implements Serializable {

    private String id = "";
    private int returnCode = 0;
    private String comment = "";
    private String objClass = "";
    private String objClassName = "";
    private String objService = "";
    private String javaType ="";
    /**
     * Object in json
     */
    private String obj = "";
    private String method = "";
    /**
     * List<CriteriaField> in json
     */
    private String criteria ="";

    public Inventory(String id,int returnCode,String comment, String objClass, String objClassName, String objService, String obj,String method, String javaType, String criteria ){
        this.id = id;
        this.returnCode = returnCode;
        this.comment = comment;
        this.objClass = objClass;
        this.obj = obj;
        this.method = method;
        this.criteria = criteria;
        this.objClassName = objClassName;
        this.objService = objService;
        this.javaType = javaType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(int returnCode) {
        this.returnCode = returnCode;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getObjClass() {
        return objClass;
    }

    public void setObjClass(String objClass) {
        this.objClass = objClass;
    }

    public String getObj() {
        return obj;
    }

    public void setObj(String obj) {
        this.obj = obj;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCriteria() {
        return criteria;
    }

    public String getObjClassName() {return objClassName;}

    public String getObjService() {return objService;}

    public String getJavaType() {return javaType;}

    public void setJavaType(String javaType) {this.javaType = javaType;}

    public void setCriteria(String criteria) {this.criteria = criteria;}

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", returnCode=" + returnCode + ", comment=" + comment + ", method=" + method + ", objClass=" + objClass + ", obj=" + obj + ", criteria=" + criteria + ", javaType=" + javaType + "]";
    }
}
