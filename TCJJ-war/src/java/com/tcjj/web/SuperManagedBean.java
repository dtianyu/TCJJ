/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.web;


import com.tcjj.comm.Lib;
import com.tcjj.comm.SuperEJB;
import com.tcjj.control.UserManagedBean;
import com.tcjj.entity.BaseEntity;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.json.JsonStructure;
import javax.servlet.http.HttpServletRequest;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author KevinDong
 * @param <T>
 */
public abstract class SuperManagedBean<T extends BaseEntity> implements Serializable {

    private String appDataPath;
    private String appImgPath;
    private int favoriteCount;
    private int newestCount;

    protected Class<T> entityClass;
    protected SuperEJB superEJB;

    @ManagedProperty(value = "#{userManagedBean}")
    protected UserManagedBean userManagedBean;

    protected T currentEntity;
    protected T newEntity;
    protected List<T> entityList;
    protected LazyDataModel model;

    protected String fileName;
    protected UploadedFile file;

    public SuperManagedBean() {

    }

    public SuperManagedBean(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @PostConstruct
    public void construct() {
        if (!FacesContext.getCurrentInstance().isPostback()) {
            create();
            init();
        }
        appDataPath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.tcjj.web.appdatapath");
        appImgPath = FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.tcjj.web.appimgpath");
        favoriteCount = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.tcjj.web.favorite"));
        newestCount = Integer.parseInt(FacesContext.getCurrentInstance().getExternalContext().getInitParameter("com.tcjj.web.newest"));
    }

    @PreDestroy
    public void destory() {
        if (getEntityList() != null) {
            getEntityList().clear();
            setEntityList(null);
        }
        setCurrentEntity(null);
        setNewEntity(null);
    }

    protected void buildJsonFile(JsonStructure value, String filePath, String fileName) {

        try {
            File dir = new File(filePath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            fileName = filePath + "//" + fileName;
            Lib.buildJson(value, fileName);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "发布成功"));
        } catch (IOException ex) {
            Logger.getLogger(SuperManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", ex.getMessage()));
        }

    }

    protected void buildJsonObject() {

    }

    protected void buildJsonArray() {

    }

    public void create() {
        if (getNewEntity() == null) {
            try {
                T entity = entityClass.newInstance();
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperManagedBean.class.getName()).log(Level.SEVERE, null, ex);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", ex.getMessage()));
            }
        }
    }

    public String create(String path) {
        try {
            create();
            return path;
        } catch (Exception e) {
            return "404";
        }
    }

    protected boolean doAfterDelete() {
        return true;
    }

    protected boolean doAfterPersist() {
        return true;
    }

    protected boolean doAfterSave() {
        return true;
    }

    protected boolean doBeforeDelete() {
        return true;
    }

    protected boolean doBeforePersist() {
        return true;
    }

    protected boolean doBeforeSave() {
        return true;
    }

    public void delete() {
        if (currentEntity != null) {
            delete(currentEntity);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可删除数据！"));
        }
    }

    public void delete(T entity) {
        if (entity != null) {
            try {
                getSuperEJB().delete(entity);
                init();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "删除成功！"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", e.getMessage()));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可删除数据！"));
        }
    }

    public void edit(T entity) {
        if (entity != null) {
            setCurrentEntity(entity);
        }
    }

    public void init() {
        setEntityList(retrieve());
        if (!getEntityList().isEmpty()) {
            setCurrentEntity(getEntityList().get(0));
        } else {
            setCurrentEntity(getNewEntity());
        }
    }

    public void persist() throws Exception {
        if (doBeforePersist()) {
            if (getNewEntity() != null) {
                try {
                    getSuperEJB().persist(getNewEntity());
                    setNewEntity(null);
                    create();
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
                } catch (Exception e) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", e.getMessage()));
                    throw new Exception();
                }
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据！"));
            }
        }
    }

    public void push() {
        if (userManagedBean.getCurrentUser() != null) {
            if (userManagedBean.getCurrentUser().getSuperuser()) {
                buildJsonArray();
            } else {
                buildJsonObject();
            }
        }
    }

    public List<T> retrieve() {
        setEntityList(getSuperEJB().findAll());
        return entityList;
    }

    public void save() throws Exception {
        if (currentEntity != null) {
            try {
                getSuperEJB().update(currentEntity);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功！"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", e.toString()));
                throw new Exception();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "没有可更新数据！"));
        }
    }

    public void sendNotification(T entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void view(T entity) {
        if (entity != null) {
            setCurrentEntity(entity);
        }
    }

    /**
     * @return the currentEntity
     */
    public T getCurrentEntity() {
        return currentEntity;
    }

    /**
     * @param currentEntity the currentEntity to set
     */
    public void setCurrentEntity(T currentEntity) {
        this.currentEntity = currentEntity;
    }

    /**
     * @return the newEntity
     */
    public T getNewEntity() {
        return newEntity;
    }

    /**
     * @param newEntity the newEntity to set
     */
    public void setNewEntity(T newEntity) {
        this.newEntity = newEntity;
    }

    /**
     * @return the entityList
     */
    public List<T> getEntityList() {
        return entityList;
    }

    /**
     * @param entityList the entityList to set
     */
    public void setEntityList(List<T> entityList) {
        this.entityList = entityList;
    }

    /**
     * @return the superEJB
     */
    public SuperEJB getSuperEJB() {
        return superEJB;
    }

    /**
     * @param superEJB the superEJB to set
     */
    public void setSuperEJB(SuperEJB superEJB) {
        this.superEJB = superEJB;
    }

    /**
     * @return the model
     */
    public LazyDataModel getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(LazyDataModel model) {
        this.model = model;
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

    public void handleFileUpload(FileUploadEvent event) {
        file = event.getFile();
        if (file != null && getCurrentEntity() != null) {
            try {
                fileName = file.getFileName();
                upload();
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", getFileName() + " is uploaded."));
            } catch (Exception e) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", e.toString());
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "文件或实体对象不存在"));
        }
    }

    protected void upload() throws IOException {
        try {

            InputStream in = file.getInputstream();
            HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
            request.setCharacterEncoding("UTF-8");

            File dir = new File(getAppImgPath());
            if (!dir.exists()) {
                dir.mkdirs();
            }

            OutputStream out = new FileOutputStream(new File(dir.getAbsolutePath() + "//" + getFileName()));
            int read = 0;
            byte[] bytes = new byte[1024];
            while (true) {
                read = in.read(bytes);
                if (read < 0) {
                    break;
                }
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", e.toString());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public Date getDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return the appDataPath
     */
    public String getAppDataPath() {
        return appDataPath;
    }

    /**
     * @return the appImgPath
     */
    public String getAppImgPath() {
        return appImgPath;
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



}
