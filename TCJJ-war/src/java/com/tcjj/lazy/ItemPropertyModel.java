/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.lazy;

import com.tcjj.comm.SuperEJB;
import com.tcjj.control.UserManagedBean;
import com.tcjj.entity.ItemProperty;

/**
 *
 * @author kevindong
 */
public class ItemPropertyModel extends BaseModel<ItemProperty> {
    
    public ItemPropertyModel(SuperEJB superEJB,UserManagedBean userManagedBean){
        this.superEJB = superEJB;
        this.userManagedBean = userManagedBean;
    }
    
}
