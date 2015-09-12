/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.control;

import com.tcjj.ejb.ItemCategoryBean;
import com.tcjj.ejb.ItemKindBean;
import com.tcjj.ejb.ItemMasterBean;
import com.tcjj.ejb.ItemPropertyBean;
import com.tcjj.ejb.ItemTypeBean;
import com.tcjj.entity.ItemCategory;
import com.tcjj.entity.ItemKind;
import com.tcjj.entity.ItemMaster;
import com.tcjj.entity.ItemProperty;
import com.tcjj.entity.ItemType;
import com.tcjj.lazy.ItemMasterModel;
import com.tcjj.web.SuperOperateBean;
import java.math.BigDecimal;
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
public class ItemMasterManagedBean extends SuperOperateBean<ItemMaster> {

    @EJB
    private ItemCategoryBean itemCategoryBean;
    @EJB
    private ItemTypeBean itemTypeBean;
    @EJB
    private ItemPropertyBean itemPropertyBean;
    @EJB
    private ItemKindBean itemKindBean;
    @EJB
    private ItemMasterBean itemMasterBean;

    private List<ItemCategory> itemCategoryList;
    private List<ItemType> itemTypeList;
    private List<ItemProperty> itemPropertyList;
    private List<ItemKind> itemKindList;

    /**
     * Creates a new instance of ItemMasterManagedBean
     */
    public ItemMasterManagedBean() {
        super(ItemMaster.class);
    }

    @Override
    protected void buildJsonArray() {
        JsonArrayBuilder jab;
        String name;
        if (itemCategoryList == null || itemCategoryList.isEmpty()) {
            itemCategoryList = itemCategoryBean.findAll();
        }
        if (itemKindList == null || itemKindList.isEmpty()) {
            itemKindList = itemKindBean.findAll();
        }
        for (ItemCategory category : itemCategoryList) {
            setEntityList(null);
            setEntityList(itemMasterBean.findByCategoryId(category.getId()));
            if (!entityList.isEmpty()) {
                jab = itemMasterBean.createJsonArrayBuilder(entityList);
                name = category.getClassname() + ".json";
                buildJsonFile(jab.build(), getAppDataPath(), name);
            }
        }
        for (ItemKind kind : itemKindList) {
            setEntityList(null);
            setEntityList(itemMasterBean.findByKindId(kind.getId()));
            if (!entityList.isEmpty()) {
                jab = itemMasterBean.createJsonArrayBuilder(entityList);
                name = kind.getClassname() + ".json";
                buildJsonFile(jab.build(), getAppDataPath(), name);
            }
        }
        setEntityList(null);
        setEntityList(itemMasterBean.findFavorite(this.getFavoriteCount()));
        if (!entityList.isEmpty()) {
            jab = itemMasterBean.createJsonArrayBuilder(entityList);
            name = "favorite.json";
            buildJsonFile(jab.build(), getAppDataPath(), name);
        }
        setEntityList(null);
        setEntityList(itemMasterBean.findNewest(this.getNewestCount()));
        if (!entityList.isEmpty()) {
            jab = itemMasterBean.createJsonArrayBuilder(entityList);
            name = "newest.json";
            buildJsonFile(jab.build(), getAppDataPath(), name);
        }
    }

    @Override
    public void create() {
        super.create();
        this.newEntity.setPrice(BigDecimal.ZERO);
        this.newEntity.setDisc(BigDecimal.valueOf(100.00));
        this.newEntity.setHot(0);
        this.newEntity.setIdx(0);
    }

    @Override
    public void init() {
        setSuperEJB(itemMasterBean);
        setModel(new ItemMasterModel(itemMasterBean, this.userManagedBean));
        setItemCategoryList(itemCategoryBean.findAll());
        setItemTypeList(itemTypeBean.findAll());
        setItemPropertyList(itemPropertyBean.findAll());
        setItemKindList(itemKindBean.findAll());
    }

    public void onItemTypeChangedNew() {
        if (newEntity != null && newEntity.getItemType().getId() != 0) {
            onItemTypeChanged(newEntity.getItemType().getId());
        } else {
            onItemTypeChanged(0);
        }
    }

    public void onItemTypeChangedEdit() {
        if (currentEntity != null && currentEntity.getItemType().getId() != 0) {
            onItemTypeChanged(currentEntity.getItemType().getId());
        } else {
            onItemTypeChanged(0);
        }
    }

    private void onItemTypeChanged(int typeid) {
        setItemPropertyList(itemPropertyBean.findByType(typeid));
    }

    /**
     * @return the itemCategoryList
     */
    public List<ItemCategory> getItemCategoryList() {
        return itemCategoryList;
    }

    /**
     * @param itemCategoryList the itemCategoryList to set
     */
    public void setItemCategoryList(List<ItemCategory> itemCategoryList) {
        this.itemCategoryList = itemCategoryList;
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

    /**
     * @return the itemPropertyList
     */
    public List<ItemProperty> getItemPropertyList() {
        return itemPropertyList;
    }

    /**
     * @param itemPropertyList the itemPropertyList to set
     */
    public void setItemPropertyList(List<ItemProperty> itemPropertyList) {
        this.itemPropertyList = itemPropertyList;
    }

    /**
     * @return the itemKindList
     */
    public List<ItemKind> getItemKindList() {
        return itemKindList;
    }

    /**
     * @param itemKindList the itemKindList to set
     */
    public void setItemKindList(List<ItemKind> itemKindList) {
        this.itemKindList = itemKindList;
    }

}
