/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.ejb;

import com.tcjj.comm.SuperEJB;
import com.tcjj.entity.ItemKind;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.json.Json;
import javax.json.JsonObjectBuilder;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemKindBean extends SuperEJB<ItemKind> {

    public ItemKindBean() {
        super(ItemKind.class);
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(ItemKind entity) {
        JsonObjectBuilder job;
        job = Json.createObjectBuilder();
        if (entity != null) {
            job.add("id", entity.getId())
                    .add("name", entity.getName());
            if (entity.getClassname() != null) {
                job.add("classname", entity.getClassname());
            } else {
                job.addNull("classname");
            }
            if (entity.getItemcount() != null) {
                job.add("itemcount", entity.getItemcount());
            } else {
                job.add("itemcount", 0);
            }
        }
        return job;
    }

}
