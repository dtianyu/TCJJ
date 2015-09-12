/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.web;

import com.tcjj.entity.BaseOperateEntity;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author KevinDong
 * @param <T>
 */
public abstract class SuperOperateBean<T extends BaseOperateEntity> extends SuperManagedBean<T> {

    /**
     * Creates a new instance of SuperOperateBean
     *
     * @param entityClass
     */
    public SuperOperateBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void create() {
        if (getNewEntity() == null) {
            T entity;
            try {
                entity = entityClass.newInstance();
                entity.setStatus("N");
                entity.setCreator(getUserManagedBean().getCurrentUser().getUserid());
                entity.setCredate(getUserManagedBean().getDate());
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperOperateBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String edit(String path) {
        if (currentEntity != null) {
            return path;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "没有选择编辑数据！"));
            return "";
        }
    }

    public String edit(T entity, String path) {
        edit(entity);
        return edit(path);
    }

    public String persist(String path) {
        try {
            persist();
            return path;
        } catch (Exception e) {
            return "";
        }      
    }
    
    public String save(String path) {
        try {
            save();
            return path;
        } catch (Exception e) {
            return "";
        }      
    }
    
    public void verify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setStatus("V");
                currentEntity.setCfmuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setCfmdate(getUserManagedBean().getDate());
                save();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public void unverify() {
        if (null != getCurrentEntity()) {
            try {
                currentEntity.setStatus("M");
                currentEntity.setOptuser(getUserManagedBean().getCurrentUser().getUserid());
                currentEntity.setOptdate(getUserManagedBean().getDate());
                currentEntity.setCfmuser(null);
                currentEntity.setCfmdate(null);
                save();
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, e.getMessage()));
            }
        }
    }

    public String view(String path) {
        if (currentEntity != null) {
            return path;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(null, "没有选择查看数据！"));
            return "";
        }
    }

    public String view(T entity, String path) {
        view(entity);
        return view(path);
    }

    public void onRowSelect(SelectEvent event) {
        setCurrentEntity((T) event.getObject());
    }

    public void onRowUnselect(UnselectEvent event) {
        setCurrentEntity(null);
    }

}
