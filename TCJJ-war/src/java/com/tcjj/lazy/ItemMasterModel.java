/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.lazy;

import com.tcjj.comm.SuperEJB;
import com.tcjj.control.UserManagedBean;
import com.tcjj.entity.ItemMaster;

/**
 *
 * @author kevindong
 */
public class ItemMasterModel extends BaseModel<ItemMaster>{
    
    public ItemMasterModel(SuperEJB superEJB,UserManagedBean userManagedBean){
        this.superEJB = superEJB;
        this.userManagedBean = userManagedBean;
    }
    
    
}
