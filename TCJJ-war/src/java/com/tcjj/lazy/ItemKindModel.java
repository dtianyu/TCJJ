/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.lazy;

import com.lightshell.comm.BaseLazyModel;
import com.lightshell.comm.SuperEJB;
import com.tcjj.entity.ItemKind;

/**
 *
 * @author kevindong
 */
public class ItemKindModel extends BaseLazyModel<ItemKind> {

    public ItemKindModel(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

}
