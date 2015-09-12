/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "itemimg")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemImg.findAll", query = "SELECT i FROM ItemImg i"),
    @NamedQuery(name = "ItemImg.findById", query = "SELECT i FROM ItemImg i WHERE i.id = :id"),
    @NamedQuery(name = "ItemImg.findByItemid", query = "SELECT i FROM ItemImg i WHERE i.itemid = :itemid"),
    @NamedQuery(name = "ItemImg.findByFilename", query = "SELECT i FROM ItemImg i WHERE i.filename = :filename"),
    @NamedQuery(name = "ItemImg.findByStatus", query = "SELECT i FROM ItemImg i WHERE i.status = :status"),
    @NamedQuery(name = "ItemImg.findByCreator", query = "SELECT i FROM ItemImg i WHERE i.creator = :creator"),
    @NamedQuery(name = "ItemImg.findByCredate", query = "SELECT i FROM ItemImg i WHERE i.credate = :credate"),
    @NamedQuery(name = "ItemImg.findByOptuser", query = "SELECT i FROM ItemImg i WHERE i.optuser = :optuser"),
    @NamedQuery(name = "ItemImg.findByOptdate", query = "SELECT i FROM ItemImg i WHERE i.optdate = :optdate"),
    @NamedQuery(name = "ItemImg.findByCfmuser", query = "SELECT i FROM ItemImg i WHERE i.cfmuser = :cfmuser"),
    @NamedQuery(name = "ItemImg.findByCfmdate", query = "SELECT i FROM ItemImg i WHERE i.cfmdate = :cfmdate")})
public class ItemImg implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "itemid")
    private int itemid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "filename")
    private String filename;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "status")
    private String status;
    @Size(max = 20)
    @Column(name = "creator")
    private String creator;
    @Column(name = "credate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date credate;
    @Size(max = 20)
    @Column(name = "optuser")
    private String optuser;
    @Column(name = "optdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date optdate;
    @Size(max = 20)
    @Column(name = "cfmuser")
    private String cfmuser;
    @Column(name = "cfmdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cfmdate;

    public ItemImg() {
    }

    public ItemImg(Integer id) {
        this.id = id;
    }

    public ItemImg(Integer id, int itemid, String filename, String status) {
        this.id = id;
        this.itemid = itemid;
        this.filename = filename;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCredate() {
        return credate;
    }

    public void setCredate(Date credate) {
        this.credate = credate;
    }

    public String getOptuser() {
        return optuser;
    }

    public void setOptuser(String optuser) {
        this.optuser = optuser;
    }

    public Date getOptdate() {
        return optdate;
    }

    public void setOptdate(Date optdate) {
        this.optdate = optdate;
    }

    public String getCfmuser() {
        return cfmuser;
    }

    public void setCfmuser(String cfmuser) {
        this.cfmuser = cfmuser;
    }

    public Date getCfmdate() {
        return cfmdate;
    }

    public void setCfmdate(Date cfmdate) {
        this.cfmdate = cfmdate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItemImg)) {
            return false;
        }
        ItemImg other = (ItemImg) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tcjj.entity.ItemImg[ id=" + id + " ]";
    }
    
}
