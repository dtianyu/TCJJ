/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.lazy;

import com.tcjj.comm.SuperEJB;
import com.tcjj.control.UserManagedBean;
import com.tcjj.entity.BaseEntity;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author kevindong Ï
 * @param <T>
 */
public abstract class BaseModel<T extends BaseEntity> extends LazyDataModel<T> {

    protected SuperEJB superEJB;
    protected List<T> dataList;
    protected UserManagedBean userManagedBean;

    @Override
    public void setRowIndex(int rowIndex) {
        /*
         * The following is in ancestor (LazyDataModel):
         * this.rowIndex = rowIndex == -1 ? rowIndex : (rowIndex % pageSize);
         */
        if (rowIndex == -1 || getPageSize() == 0) {
            super.setRowIndex(-1);
        } else {
            super.setRowIndex(rowIndex % getPageSize());
        }
    }

    @Override
    public T getRowData(String rowKey) {
        for (T entity : dataList) {
            if (entity.getId().toString().equals(rowKey)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public Object getRowKey(T t) {
        return t.getId();
    }

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        if (userManagedBean != null) {

            setDataList(superEJB.findAll(first, pageSize));
            setRowCount(superEJB.getRowCount());
                
        }
        return this.dataList;
    }

    /**
     * @return the dataList
     */
    public List<T> getDataList() {
        return dataList;
    }

    /**
     * @param dataList the dataList to set
     */
    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }

}
