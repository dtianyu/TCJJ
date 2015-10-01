/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.control;

import com.tcjj.ejb.ItemTypeBean;
import com.tcjj.entity.ItemType;
import com.tcjj.lazy.ItemTypeModel;
import com.tcjj.web.SuperOperateBean;
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
public class ItemTypeManagedBean extends SuperOperateBean<ItemType> {

    @EJB
    private ItemTypeBean itemTypeBean;

    /**
     * Creates a new instance of ItemTypeManagedBean
     */
    public ItemTypeManagedBean() {
        super(ItemType.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        setEntityList(itemTypeBean.findAll());
        if (entityList != null && !entityList.isEmpty()) {
            jab = itemTypeBean.createJsonArrayBuilder(entityList);
            buildJsonFile(jab.build(), getAppDataPath(), "type.json");
        }
    }

    @Override
    protected void buildJsonObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        setSuperEJB(itemTypeBean);
        setModel(new ItemTypeModel(itemTypeBean));
    }

}
