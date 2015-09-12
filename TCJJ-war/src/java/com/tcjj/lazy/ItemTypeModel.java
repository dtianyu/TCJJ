/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.lazy;

import com.tcjj.comm.SuperEJB;
import com.tcjj.control.UserManagedBean;
import com.tcjj.entity.ItemType;

/**
 *
 * @author kevindong
 */
public class ItemTypeModel extends BaseModel<ItemType> {

    public ItemTypeModel(SuperEJB sessionBean, UserManagedBean userManagedBean) {
        this.superEJB = sessionBean;
        this.userManagedBean = userManagedBean;
    }

}
