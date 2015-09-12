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
@Table(name = "itemtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemType.getRowCount", query = "SELECT COUNT(i) FROM ItemType i"),
    @NamedQuery(name = "ItemType.findAll", query = "SELECT i FROM ItemType i"),
    @NamedQuery(name = "ItemType.findById", query = "SELECT i FROM ItemType i WHERE i.id = :id"),
    @NamedQuery(name = "ItemType.findByName", query = "SELECT i FROM ItemType i WHERE i.name = :name"),
    @NamedQuery(name = "ItemType.findByClassname", query = "SELECT i FROM ItemType i WHERE i.classname = :classname"),
    @NamedQuery(name = "ItemType.findByTitle", query = "SELECT i FROM ItemType i WHERE i.title = :title"),
    @NamedQuery(name = "ItemType.findByItemcount", query = "SELECT i FROM ItemType i WHERE i.itemcount = :itemcount"),
    @NamedQuery(name = "ItemType.findByStatus", query = "SELECT i FROM ItemType i WHERE i.status = :status")})
public class ItemType extends BaseOperateEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Size(max = 20)
    @Column(name = "classname")
    private String classname;
    @Size(max = 20)
    @Column(name = "title")
    private String title;
    @Column(name = "itemcount")
    private Integer itemcount;
 
    public ItemType() {
    }

    public ItemType(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getItemcount() {
        return itemcount;
    }

    public void setItemcount(Integer itemcount) {
        this.itemcount = itemcount;
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
        if (!(object instanceof ItemType)) {
            return false;
        }
        ItemType other = (ItemType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tcjj.entity.ItemType[ id=" + id + " ]";
    }
    
}
