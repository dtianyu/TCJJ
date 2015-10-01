/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.control;

import com.lightshell.comm.BaseLib;
import com.lightshell.comm.GraphicCode;
import com.tcjj.ejb.SystemUserBean;
import com.tcjj.entity.SystemUser;
import com.tcjj.lazy.SystemUserModel;
import com.tcjj.web.SuperOperateBean;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.Pattern;
import org.primefaces.event.FlowEvent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author C0160
 */
@ManagedBean
@SessionScoped
public class SystemUserManagedBean extends SuperOperateBean<SystemUser> {

    @EJB
    private SystemUserBean systemUserBean;
    private GraphicCode graphicCode;
    private String mobile;
    private String username;
    @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message = "电子邮件无效")
    private String mail;
    private String pwd;
    private String verifyCode;
    private String verifyInput;
    private StreamedContent graphicContent;
    private String graphicString;
    private int count;//验证码发送次数

    public SystemUserManagedBean() {
        super(SystemUser.class);
    }

    @Override
    public void create() {
        if (getNewEntity() == null) {
            SystemUser entity;
            try {
                entity = entityClass.newInstance();
                entity.setStatusToNew();
                entity.setCreatorToSystem();
                entity.setCredateToNow();
                entity.setOptuser(null);
                entity.setOptdate(null);
                setNewEntity(entity);
            } catch (InstantiationException | IllegalAccessException ex) {
                Logger.getLogger(SuperOperateBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    protected boolean doBeforePersist() {

        try {
            newEntity.setUserid(mobile);
            newEntity.setUsername(username);
            newEntity.setEmail(mail);
            newEntity.setPassword(BaseLib.securityMD5(pwd));
            newEntity.setSuperuser(Boolean.FALSE);
            newEntity.setOwnstore(Boolean.FALSE);
            newEntity.setLocked(Boolean.FALSE);
            return true;
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(SystemUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }

    @Override
    public void init() {
        setSuperEJB(systemUserBean);
        setModel(new SystemUserModel(systemUserBean));
         try {
            graphicCode = new GraphicCode();
            graphicCode.build();
            graphicContent = graphicCode.getContent();
        } catch (IOException ex) {
            Logger.getLogger(SystemUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String onFlowProcess(FlowEvent event) {
        if (verifyCode.equals(verifyInput)) {
            return event.getNewStep();
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "验证码错误"));
        }
        return event.getOldStep();
    }

    public void sendGraphicCode() {
        if (graphicCode == null) {
            graphicCode = new GraphicCode();
        }
        try {
            graphicCode.build();
            graphicContent = graphicCode.getContent();
        } catch (IOException ex) {
            Logger.getLogger(SystemUserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @return the verifyInput
     */
    public String getVerifyInput() {
        return verifyInput;
    }

    /**
     * @param verifyInput the verifyInput to set
     */
    public void setVerifyInput(String verifyInput) {
        this.verifyInput = verifyInput;
    }

    /**
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the mail
     */
    public String getMail() {
        return mail;
    }

    /**
     * @param mail the mail to set
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * @return the count
     */
    public int getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return the graphicContent
     */
    public StreamedContent getGraphicContent() {
        return graphicContent;
    }

    /**
     * @return the graphicString
     */
    public String getGraphicString() {
        return graphicString;
    }

    @Override
    protected void buildJsonObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void buildJsonArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
