package com.bionic.university.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 * Created by Sergiy Seleznyov on 03.08.2015.
 */

//user backing bean
@ManagedBean
@SessionScoped
public class UserBB {

    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index.html?faces-redirect=true";
    }
}
