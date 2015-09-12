/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.ejb;

import com.tcjj.comm.SuperEJB;
import com.tcjj.entity.ItemType;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemTypeBean extends SuperEJB<ItemType> {

    public ItemTypeBean(){
        super(ItemType.class);
    }

}
