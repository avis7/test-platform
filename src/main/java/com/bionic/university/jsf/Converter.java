package com.bionic.university.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 * Created by c2612 on 27.07.2015.
 */

@FacesConverter("com/bionic/university/jsf/Converter.java")
public class Converter implements javax.faces.convert.Converter {

    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object o) {
        return null;
    }
}
