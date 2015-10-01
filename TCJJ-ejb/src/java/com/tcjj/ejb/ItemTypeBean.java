/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.ejb;

import com.lightshell.comm.SuperEJB;
import com.tcjj.entity.ItemType;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemTypeBean extends SuperEJB<ItemType> {

    @PersistenceContext(unitName = "TCJJ-ejbPU")
    protected EntityManager em;

    public ItemTypeBean() {
        super(ItemType.class);
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(ItemType entity) {
        JsonObjectBuilder job = Json.createObjectBuilder();
        if (entity != null) {
            job.add("id", entity.getId())
                    .add("name", entity.getName());
            if (entity.getClassname() != null) {
                job.add("classname", entity.getClassname());
            } else {
                job.addNull("classname");
            }
        }
        return job;
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
