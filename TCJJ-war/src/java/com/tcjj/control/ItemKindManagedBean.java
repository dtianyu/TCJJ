/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.control;

import com.tcjj.ejb.ItemKindBean;
import com.tcjj.entity.ItemKind;
import com.tcjj.lazy.ItemKindModel;
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
public class ItemKindManagedBean extends SuperOperateBean<ItemKind> {

    @EJB
    private ItemKindBean itemKindBean;

    /**
     * Creates a new instance of ItemKindManagedBean
     */
    public ItemKindManagedBean() {
        super(ItemKind.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        setEntityList(itemKindBean.findAll());
        if (entityList != null && !entityList.isEmpty()) {
            jab = itemKindBean.createJsonArrayBuilder(entityList);
            buildJsonFile(jab.build(), getAppDataPath(), "kind.json");
        }
    }

    @Override
    public void init() {
        setSuperEJB(itemKindBean);
        setModel(new ItemKindModel(itemKindBean, this.userManagedBean));
    }

}
