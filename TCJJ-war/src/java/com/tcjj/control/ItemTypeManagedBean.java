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
    public void init() {
        setSuperEJB(itemTypeBean);
        setModel(new ItemTypeModel(itemTypeBean,this.userManagedBean));
    }
    
    
    
}