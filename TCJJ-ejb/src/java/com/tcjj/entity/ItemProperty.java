/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.entity;

import com.lightshell.comm.BaseEntityWithOperate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kevindong
 */
@Entity
@Table(name = "itemproperty")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemProperty.getRowCount", query = "SELECT COUNT(i) FROM ItemProperty i"),
    @NamedQuery(name = "ItemProperty.findAll", query = "SELECT i FROM ItemProperty i"),
    @NamedQuery(name = "ItemProperty.findById", query = "SELECT i FROM ItemProperty i WHERE i.id = :id"),
    @NamedQuery(name = "ItemProperty.findByName", query = "SELECT i FROM ItemProperty i WHERE i.name = :name"),  
    @NamedQuery(name = "ItemProperty.findByTypeId", query = "SELECT i FROM ItemProperty i WHERE i.typeid = :typeid"),
    @NamedQuery(name = "ItemProperty.findByStatus", query = "SELECT i FROM ItemProperty i WHERE i.status = :status")})
public class ItemProperty extends BaseEntityWithOperate {
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "typeid")
    private int typeid;
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

    public ItemProperty() {
    }

    public ItemProperty(String name, String status) {
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
        if (!(object instanceof ItemProperty)) {
            return false;
        }
        ItemProperty other = (ItemProperty) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tcjj.entity.ItemProperty[ id=" + id + " ]";
    }

    /**
     * @return the typeid
     */
    public int getTypeid() {
        return typeid;
    }

    /**
     * @param typeid the typeid to set
     */
    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }
    
}
