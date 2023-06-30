package com.shopmeowmeow.presentation.managedBeans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;

@Named
@SessionScoped
public class AuthorizationBean implements Serializable {

    private boolean loggedIn;

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public void checkLoggedIn() {
        if (!loggedIn) {
                // Benutzer ist nicht eingeloggt, daher Weiterleitung zur Login-Seite
                try {
                    ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
                    externalContext.redirect(externalContext.getRequestContextPath() + "/views/adminlogin.xhtml");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
    }
}
