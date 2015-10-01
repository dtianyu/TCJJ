/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.web;

import com.lightshell.comm.SuperManagedBean;
import com.lightshell.comm.BaseEntityWithOperate;
import com.tcjj.control.UserManagedBean;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

/**
 *
 * @author KevinDong
 * @param <T>
 */
public abstract class SuperOperateBean<T extends BaseEntityWithOperate> extends SuperManagedBean<T> {

    private String appDataPath;
    private String appImgPath;
    private int favoriteCount;
    private int newestCount;

    @ManagedProperty(value = "#{userManagedBean}")
    private UserManagedBean userManagedBean;

    /**
     * @param entityClass
     */
    public SuperOperateBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public void construct() {
        super.construct();
        appDataPath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.tcjj.web.appdatapath");
        appImgPath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.tcjj.web.appimgpath");
        favoriteCount = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.tcjj.web.favorite"));
        newestCount = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.tcjj.web.newest"));
    }

    @Override
    public void create() {
        if (getNewEntity() == null) {
            T entity;
            try {
                entity = entityClass.newInstance();
                entity.setStatus("N");
                entity.setCreator(getUserManagedBean().getCurrentUser().getUserid());
                entity.setCredateToNow();
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

    public String persist(String path) {
        try {
            persist();
            return path;
        } catch (Exception e) {
            return "";
        }
    }

    public String update(String path) {
        try {
            update();
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
                currentEntity.setCfmdateToNow();
                update();
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
                currentEntity.setOptdateToNow();
                currentEntity.setCfmuser(null);
                currentEntity.setCfmdate(null);
                update();
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

    /**
     * @return the userManagedBean
     */
    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    /**
     * @param userManagedBean the userManagedBean to set
     */
    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }

    @Override
    public String getAppDataPath() {
        return this.appDataPath;
    }

    @Override
    public String getAppImgPath() {
        return this.appImgPath;
    }

    /**
     * @return the favoriteCount
     */
    public int getFavoriteCount() {
        return favoriteCount;
    }

    /**
     * @return the newestCount
     */
    public int getNewestCount() {
        return newestCount;
    }

    @Override
    public void push() {
        buildJsonArray();
    }

}
