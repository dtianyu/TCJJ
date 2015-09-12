/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.ejb;

import com.tcjj.comm.SuperEJB;
import com.tcjj.entity.ItemProperty;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.Query;

/**
 *
 * @author kevindong
 */
@Stateless
@LocalBean
public class ItemPropertyBean extends SuperEJB<ItemProperty> {

    public ItemPropertyBean() {
        super(ItemProperty.class);
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(ItemProperty entity) {
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

    public List<ItemProperty> findByType(int id) {
        Query query;
        query = em.createNamedQuery("ItemProperty.findByTypeId");
        query.setParameter("typeid", id);
        return query.getResultList();
    }

}
