/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.control;

import com.tcjj.comm.Lib;
import com.tcjj.ejb.SystemUserBean;
import com.tcjj.entity.SystemUser;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "userManagedBean")
@SessionScoped
public class UserManagedBean implements Serializable {

    @EJB
    private SystemUserBean systemUserBean;

    private SystemUser currentUser;
    private String userid;
    private String mobile;
    private String email;
    private String pwd;
    private String newpwd;
    private String secpwd;
    private boolean status;

    public UserManagedBean() {
        status = false;
    }

    public boolean checkUser() {
        return true;
    }

    public String login() {
        if (getUserid().length() == 0 || getPwd().length() == 0) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "请输入用户名和密码"));
        }
        try {
            secpwd = Lib.securityMD5(getPwd());
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            SystemUser u = systemUserBean.findByUserIdAndPwd(getUserid(), getSecpwd());
            if (u != null) {
                currentUser = u;
                status = true;
                mobile = u.getUserid();
                updateLoginTime();
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "用户名或密码错误"));
                status = false;
                return "";
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", "用户名或密码不正确！"));
            status = false;
            return "login";
        }
        return "home";
    }

    public String logout() {
        if (status) {
            currentUser = null;
            status = false;
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.invalidate();
            return "login";
        } else {
            return "home";
        }
    }

    public void update() {
        if (currentUser != null) {
            if (mobile!=null && !mobile.equals("") && !mobile.equals(currentUser.getUserid())) {
                currentUser.setUserid(mobile);
            }
            if (email != null && !email.equals("") && !email.equals(currentUser.getEmail())) {
                currentUser.setEmail(email);
            }
            try {
                systemUserBean.update(currentUser);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新成功"));
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "更新失败！"));
            }           
        }
    }

    public void updateLoginTime() {
        if (currentUser != null) {
            currentUser.setLastlogin(getDate());
            update();
        }
    }

    public void updatePwd() {
        try {
            secpwd = Lib.securityMD5(getPwd());
            SystemUser u = systemUserBean.findByUserIdAndPwd(getUserid(), getSecpwd());
            if (u != null) {
                secpwd = Lib.securityMD5(newpwd);
                currentUser.setPassword(secpwd);
                update();
                pwd = "";
                newpwd = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "更新密码成功"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warn", "原密码错误"));
            }
        } catch (UnsupportedEncodingException e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal", e.getMessage()));
        }
    }

    public Date getDate() {
        return Calendar.getInstance().getTime();
    }

    /**
     * @return the currentUser
     */
    public SystemUser getCurrentUser() {
        return currentUser;
    }

    public boolean getStatus() {
        return status;
    }

    /**
     * @return the userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid the userid to set
     */
    public void setUserid(String userid) {
        this.userid = userid;
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
     * @return the newpwd
     */
    public String getNewpwd() {
        return newpwd;
    }

    /**
     * @param newpwd the newpwd to set
     */
    public void setNewpwd(String newpwd) {
        this.newpwd = newpwd;
    }

    /**
     * @return the secpwd
     */
    public String getSecpwd() {
        return secpwd;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

}
