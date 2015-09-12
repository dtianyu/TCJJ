/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.control;

import com.tcjj.ejb.ItemPropertyBean;
import com.tcjj.ejb.ItemTypeBean;
import com.tcjj.entity.ItemProperty;
import com.tcjj.entity.ItemType;
import com.tcjj.lazy.ItemTypeModel;
import com.tcjj.web.SuperOperateBean;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author kevindong
 */
@ManagedBean
@SessionScoped
public class ItemPropertyManagedBean extends SuperOperateBean<ItemProperty> {

    @EJB
    private ItemTypeBean itemTypeBean;

    @EJB
    private ItemPropertyBean itemPropertyBean;

    private List<ItemType> itemTypeList;

    /**
     * Creates a new instance of ItemPropertyManagedBean
     */
    public ItemPropertyManagedBean() {
        super(ItemProperty.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        setEntityList(itemPropertyBean.findAll());
        if (entityList != null && !entityList.isEmpty()) {
            jab = itemPropertyBean.createJsonArrayBuilder(entityList);
            buildJsonFile(jab.build(), getAppDataPath(), "property.json");
        }
    }

    @Override
    public void init() {
        setSuperEJB(itemPropertyBean);
        setModel(new ItemTypeModel(itemPropertyBean, this.userManagedBean));
        setItemTypeList(itemTypeBean.findAll());
    }  

    /**
     * @return the itemTypeList
     */
    public List<ItemType> getItemTypeList() {
        return itemTypeList;
    }

    /**
     * @param itemTypeList the itemTypeList to set
     */
    public void setItemTypeList(List<ItemType> itemTypeList) {
        this.itemTypeList = itemTypeList;
    }

}
