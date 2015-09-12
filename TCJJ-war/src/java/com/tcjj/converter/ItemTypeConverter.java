/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tcjj.converter;

import com.tcjj.ejb.ItemTypeBean;
import com.tcjj.entity.ItemType;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author kevindong
 */
@FacesConverter("itemTypeConverter")
public class ItemTypeConverter implements Converter  {
    
    @EJB
    private ItemTypeBean itemTypeBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
         if (value.trim().equals("")) {
            return null;
        } else {
            try {
                ItemType entity = itemTypeBean.findById(Integer.parseInt(value));
                if (entity != null) {
                    return entity;
                } else {
                    return null;
                }
            } catch (ConverterException exception) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid entity"));
            }
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || value.equals("")) {
            return "";
        } else {
            return ((ItemType) value).getId().toString();
        }
    }
    
}
