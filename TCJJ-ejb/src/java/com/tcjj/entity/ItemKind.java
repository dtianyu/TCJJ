/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.entity;

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
@Table(name = "itemkind")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemKind.getRowCount", query = "SELECT COUNT(i) FROM ItemKind i"),
    @NamedQuery(name = "ItemKind.findAll", query = "SELECT i FROM ItemKind i"),
    @NamedQuery(name = "ItemKind.findById", query = "SELECT i FROM ItemKind i WHERE i.id = :id"),
    @NamedQuery(name = "ItemKind.findByName", query = "SELECT i FROM ItemKind i WHERE i.name = :name"),
    @NamedQuery(name = "ItemKind.findByStatus", query = "SELECT i FROM ItemKind i WHERE i.status = :status")})
public class ItemKind extends BaseOperateEntity {

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

    public ItemKind() {
    }

    public ItemKind(Integer id) {
        this.id = id;
    }

    public ItemKind(Integer id, String name, String status) {
        this.id = id;
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
        if (!(object instanceof ItemKind)) {
            return false;
        }
        ItemKind other = (ItemKind) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tcjj.entity.ItemKind[ id=" + id + " ]";
    }
    
}
