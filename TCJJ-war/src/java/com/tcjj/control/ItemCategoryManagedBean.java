/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.control;

import com.tcjj.ejb.ItemCategoryBean;
import com.tcjj.entity.ItemCategory;
import com.tcjj.lazy.ItemCategoryModel;
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
public class ItemCategoryManagedBean extends SuperOperateBean<ItemCategory> {

    @EJB
    private ItemCategoryBean itemCategoryBean;

    /**
     * Creates a new instance of ItemCategoryManagedBean
     */
    public ItemCategoryManagedBean() {
        super(ItemCategory.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        setEntityList(itemCategoryBean.findAll());
        if (this.entityList != null && !this.entityList.isEmpty()) {
            jab = itemCategoryBean.createJsonArrayBuilder(this.entityList);
            this.buildJsonFile(jab.build(), getAppDataPath(), "category.json");
        }
    }

    @Override
    public void init() {
        setSuperEJB(itemCategoryBean);
        setModel(new ItemCategoryModel(itemCategoryBean, this.userManagedBean));
    }

    @Override
    public void push() {
        super.push(); //To change body of generated methods, choose Tools | Templates.       
    }

}
