package com.gft.bean;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by kamu on 8/18/2016.
 */
public class InventoryBuilder  {

    static final Logger LOG = LoggerFactory.getLogger(InventoryBuilder.class);

    private String id;
    private int returnCode;
    private String comment;
    private String objClass;
    private String objClassName = "";
    private String objService = "";
    private Object obj;
    private String method;
    private String javaType ="";

    private List<CriteriaField> criteria = new ArrayList<CriteriaField>();
    private int criteriaOrder = 0;

    private ObjectMapper mapper = new ObjectMapper();

    public InventoryBuilder(){
        this.id = "";
        this.returnCode = 0;
        this.comment = "";
        this.objClass = "";
        this.obj = "";
        this.method = "";
    }

    public InventoryBuilder(String id ){
        this.id = id;
        this.returnCode = 0;
        this.comment = "";
        this.objClass = "";
        this.obj = "";
        this.method = "";
    }

    public InventoryBuilder setId(String id) {
        this.id = id;
        return this;
    }

    public InventoryBuilder setReturnCode(int returnCode) {
        this.returnCode = returnCode;
        return this;
    }

    public InventoryBuilder setComment(String comment) {
        this.comment = comment;
        return this;
    }

    private InventoryBuilder setObjClass(String objClass) {
        this.objClass = objClass;
        String[] parts = objClass.split("\\.");
        this.objClassName = parts[parts.length -1];
        this.objService = objClassName.toLowerCase().concat("Service");
        return this;
    }

    public InventoryBuilder setObj(Object obj) {
        this.obj = obj;
        return setObjClass(obj.getClass().getName());
    }

    public InventoryBuilder setMethod(String method) {
        this.method = method;
        return this;
    }

    public InventoryBuilder addCriteria(String key, Object value) {
        //this.criteria.put(key, value);
        criteria.add(new CriteriaField(key, value.getClass().getTypeName() ,criteriaOrder,value));
        criteriaOrder++;
        return this;
    }

    public InventoryBuilder setCriteria(List<CriteriaField> criteria) {
        this.criteria = criteria;
        return this;
    }

    public InventoryBuilder setJavaType(String javaType) {
        this.javaType = javaType;
        return this;
    }

    public Inventory build() {
        String objInJSON = null;
        String criteriaInJSON = null;
        try {
            objInJSON = mapper.writeValueAsString(obj);
            criteriaInJSON = mapper.writeValueAsString(criteria);
        } catch (JsonProcessingException e) {
           LOG.error(e.getOriginalMessage());
        }

        return new Inventory(id,returnCode, comment,objClass, objClassName, objService , objInJSON, method, javaType, criteriaInJSON);
    }

    public InventoryResource deserialize(Inventory inventory) {
        InventoryResource inventoryResource = new InventoryResource();
        try {
            inventoryResource.setInventory(inventory);
            inventoryResource.setId(inventory.getId());
            inventoryResource.setClz(Class.forName(inventory.getObjClass()));
            TypeReference<List<CriteriaField>> typeRef = new TypeReference<List<CriteriaField>>(){};

            List<Object> finalCriteriaFieldList = new ArrayList<Object>();

            try {
                for (CriteriaField cf : (List<CriteriaField>) mapper.readValue(inventory.getCriteria(), typeRef)) {
                    Class<?> cl = Class.forName(cf.getType());
                    Object object= null;
                    Constructor<?> ctor = null;
                    try {
                        ctor = cl.getConstructors()[0];
                        object=ctor.newInstance(new Object[]{cf.getValue()});
                        cl.getDeclaredMethods()[0].invoke(object,cf.getValue());
                    } catch (java.lang.IllegalArgumentException e ){
                        ctor = cl.getConstructor(cl);
                        object = ctor.newInstance(cf.getValue());
                    }

                    if (object != null) {
                        finalCriteriaFieldList.add(object);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            inventoryResource.getCriteria().addAll(finalCriteriaFieldList);
            if (inventory.getReturnCode() != 0 && "findAll".equals(inventoryResource.getInventory().getMethod())){
                Class<?> cl = Class.forName(inventory.getJavaType());
                JavaType listType = mapper.getTypeFactory().constructCollectionType(List.class, cl);
                inventoryResource.setObj(mapper.readValue(inventory.getObj(), listType));
            } else {
                inventoryResource.setObj(mapper.readValue(inventory.getObj(),inventoryResource.getClz()));
            }
        } catch (Exception e){
            LOG.error(e.getMessage());
        }

        return inventoryResource;
    }
}
