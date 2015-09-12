/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.ejb;

import com.tcjj.comm.SuperEJB;
import com.tcjj.entity.ItemMaster;
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
public class ItemMasterBean extends SuperEJB<ItemMaster> {

    public ItemMasterBean() {
        super(ItemMaster.class);
    }

    @Override
    public JsonObjectBuilder createJsonObjectBuilder(ItemMaster entity) {
        JsonObjectBuilder job;
        job = Json.createObjectBuilder();
        if (entity != null) {

            job.add("id", entity.getId());
            if (entity.getItemCategory() != null) {
                job.add("categoryid", entity.getItemCategory().getId());
                job.add("category", entity.getItemCategory().getName());
                if(entity.getItemCategory().getClassname()!=null){
                    job.add("categoryclass",entity.getItemCategory().getClassname());
                }else{
                    job.addNull("categoryclass");
                }
            } else {
                job.addNull("categoryid");
                job.addNull("category");
                job.addNull("categoryclass");
            }
            if (entity.getItemType() != null) {
                job.add("typeid", entity.getItemType().getId());
                job.add("type", entity.getItemType().getName());
                 if(entity.getItemType().getClassname()!=null){
                    job.add("typeclass",entity.getItemType().getClassname());
                }else{
                    job.addNull("typeclass");
                }
            } else {
                job.addNull("typeid");
                job.addNull("type");
                job.addNull("typeclass");
            }
            if (entity.getItemProperty() != null) {
                job.add("propertyid", entity.getItemProperty().getId());
                job.add("property", entity.getItemProperty().getName());
            } else {
                job.addNull("propertyid");
                job.addNull("property");
            }
            if (entity.getItemKind() != null) {
                job.add("kindid", entity.getItemKind().getId());
                job.add("kind", entity.getItemKind().getName());
                 if(entity.getItemKind().getClassname()!=null){
                    job.add("kindclass",entity.getItemKind().getClassname());
                }else{
                    job.addNull("kindclass");
                }
            } else {
                job.addNull("kindid");
                job.addNull("kind");
                job.addNull("kindclass");
            }
            if (entity.getItemno() != null) {
                job.add("itemno", entity.getItemno());
            } else {
                job.addNull("itemno");
            }
            if (entity.getItemdesc() != null) {
                job.add("itemdesc", entity.getItemdesc());
            } else {
                job.addNull("itemdesc");
            }
            if (entity.getItemspec() != null) {
                job.add("itemspec", entity.getItemspec());
            } else {
                job.addNull("itemspec");
            }
            if (entity.getBrand() != null) {
                job.add("brand", entity.getBrand());
            } else {
                job.addNull("brand");
            }
            if (entity.getBatch() != null) {
                job.add("batch", entity.getBatch());
            } else {
                job.addNull("batch");
            }
            if (entity.getSn() != null) {
                job.add("sn", entity.getSn());
            } else {
                job.addNull("sn");
            }
            job.add("unit", entity.getUnit())
                    .add("price", entity.getPrice());
            if (entity.getLogo1() != null) {
                job.add("logo1", entity.getLogo1());
            } else {
                job.addNull("logo1");
            }
            if (entity.getLogo2() != null) {
                job.add("logo2", entity.getLogo2());
            } else {
                job.addNull("logo2");
            }

        }
        return job;
    }

    public List<ItemMaster> findByCategoryId(int id) {
        Query query = em.createNamedQuery("ItemMaster.findByCategoryId");
        query.setParameter("categoryid", id);
        return query.getResultList();
    }

    public List<ItemMaster> findByTypeId(int id) {
        Query query = em.createNamedQuery("ItemMaster.findByTypeId");
        query.setParameter("typeid", id);
        return query.getResultList();
    }

    public List<ItemMaster> findByPropertyId(int id) {
        Query query = em.createNamedQuery("ItemMaster.findByPropertyId");
        query.setParameter("propertyid", id);
        return query.getResultList();
    }
    
    public List<ItemMaster> findByKindId(int id) {
        Query query = em.createNamedQuery("ItemMaster.findByKindId");
        query.setParameter("kindid", id);
        return query.getResultList();
    }
       
    public List<ItemMaster> findFavorite(int pagesize) {
        Query query = em.createNamedQuery("ItemMaster.findFavorite").setFirstResult(0).setMaxResults(pagesize);
        return query.getResultList();
    }
       
    public List<ItemMaster> findNewest(int pagesize) {
        Query query = em.createNamedQuery("ItemMaster.findNewest").setFirstResult(0).setMaxResults(pagesize);;
        return query.getResultList();
    }

}
