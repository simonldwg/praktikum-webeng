package com.shopmeowmeow.presentation.managedBeans;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;

@Named()
@ApplicationScoped
public class AdminLoginBean implements Serializable {

    private String username;
    private String password;

    @Inject
    AuthorizationBean auth;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login() {
        if (username.equals("admin") && password.equals("admin123")) {
            // Erfolgreicher Login
            auth.setLoggedIn(true);
            return "success";
        } else {
            // Login fehlgeschlagen
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login failed", "Invalid username or password"));
            return null;
        }
    }

    public String logout() {
        //Zurücksetzen der Sitzungsvariablen
        auth.setLoggedIn(false);
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        externalContext.invalidateSession(); // Sitzung ungültig machen

        // Erfolgsmeldung anzeigen
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Logout successful", null));

        return "logout";
    }

}
