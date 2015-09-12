/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.entity;

import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "itemmaster")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemMaster.getRowCount", query = "SELECT COUNT(i) FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.findAll", query = "SELECT i FROM ItemMaster i"),
    @NamedQuery(name = "ItemMaster.findById", query = "SELECT i FROM ItemMaster i WHERE i.id = :id"),
    @NamedQuery(name = "ItemMaster.findByCategoryId", query = "SELECT i FROM ItemMaster i  WHERE i.itemCategory.id = :categoryid ORDER BY i.idx DESC "),
    @NamedQuery(name = "ItemMaster.findByTypeId", query = "SELECT i FROM ItemMaster i WHERE i.itemType.id = :typeid"),
    @NamedQuery(name = "ItemMaster.findByPropertyId", query = "SELECT i FROM ItemMaster i WHERE i.itemProperty.id = :propertyid"),
    @NamedQuery(name = "ItemMaster.findByKindId", query = "SELECT i FROM ItemMaster i WHERE i.itemKind.id = :kindid ORDER BY i.idx DESC "),
    @NamedQuery(name = "ItemMaster.findByItemno", query = "SELECT i FROM ItemMaster i WHERE i.itemno = :itemno"),
    @NamedQuery(name = "ItemMaster.findByStatus", query = "SELECT i FROM ItemMaster i WHERE i.status = :status"),
    @NamedQuery(name = "ItemMaster.findFavorite", query = "SELECT i FROM ItemMaster i ORDER BY i.idx DESC "),
    @NamedQuery(name = "ItemMaster.findNewest", query = "SELECT i FROM ItemMaster i ORDER BY i.hot DESC")})
public class ItemMaster extends BaseOperateEntity {

    @JoinColumn(name = "categoryid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemCategory itemCategory;
    @JoinColumn(name = "typeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemType itemType;
    @JoinColumn(name = "propertyid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ItemProperty itemProperty;
    @JoinColumn(name = "kindid", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private ItemKind itemKind;
    @Size(max = 20)
    @Column(name = "itemno")
    private String itemno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "itemdesc")
    private String itemdesc;
    @Size(max = 200)
    @Column(name = "itemspec")
    private String itemspec;
    @Size(max = 45)
    @Column(name = "brand")
    private String brand;
    @Size(max = 45)
    @Column(name = "batch")
    private String batch;
    @Size(max = 45)
    @Column(name = "sn")
    private String sn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "unit")
    private String unit;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "disc")
    private BigDecimal disc;
    @Column(name = "hot")
    private Integer hot;
    @Column(name = "idx")
    private Integer idx;
    @Size(max = 45)
    @Column(name = "logo1")
    private String logo1;
    @Size(max = 45)
    @Column(name = "logo2")
    private String logo2;

    public ItemMaster() {

    }

    public String getItemno() {
        return itemno;
    }

    public void setItemno(String itemno) {
        this.itemno = itemno;
    }

    public String getItemdesc() {
        return itemdesc;
    }

    public void setItemdesc(String itemdesc) {
        this.itemdesc = itemdesc;
    }

    public String getItemspec() {
        return itemspec;
    }

    public void setItemspec(String itemspec) {
        this.itemspec = itemspec;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDisc() {
        return disc;
    }

    public void setDisc(BigDecimal disc) {
        this.disc = disc;
    }

    public Integer getHot() {
        return hot;
    }

    public void setHot(Integer hot) {
        this.hot = hot;
    }

    public Integer getIdx() {
        return idx;
    }

    public void setIdx(Integer idx) {
        this.idx = idx;
    }

    public String getLogo1() {
        return logo1;
    }

    public void setLogo1(String logo1) {
        this.logo1 = logo1;
    }

    public String getLogo2() {
        return logo2;
    }

    public void setLogo2(String logo2) {
        this.logo2 = logo2;
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
        if (!(object instanceof ItemMaster)) {
            return false;
        }
        ItemMaster other = (ItemMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.tcjj.entity.ItemMaster[ id=" + id + " ]";
    }

    /**
     * @return the itemCategory
     */
    public ItemCategory getItemCategory() {
        return itemCategory;
    }

    /**
     * @param itemCategory the itemCategory to set
     */
    public void setItemCategory(ItemCategory itemCategory) {
        this.itemCategory = itemCategory;
    }

    /**
     * @return the itemType
     */
    public ItemType getItemType() {
        return itemType;
    }

    /**
     * @param itemType the itemType to set
     */
    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    /**
     * @return the itemProperty
     */
    public ItemProperty getItemProperty() {
        return itemProperty;
    }

    /**
     * @param itemProperty the itemProperty to set
     */
    public void setItemProperty(ItemProperty itemProperty) {
        this.itemProperty = itemProperty;
    }

    /**
     * @return the itemKind
     */
    public ItemKind getItemKind() {
        return itemKind;
    }

    /**
     * @param itemKind the itemKind to set
     */
    public void setItemKind(ItemKind itemKind) {
        this.itemKind = itemKind;
    }

}
