package com.shopmeowmeow.presentation.managedBeans;

import com.shopmeowmeow.businessLogic.managers.CatManager;
import com.shopmeowmeow.model.CatColor;
import com.shopmeowmeow.model.CatGender;
import com.shopmeowmeow.model.CatHairstyle;
import com.shopmeowmeow.persistence.CatDAO;
import com.shopmeowmeow.transfer.Cat;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named()
@ApplicationScoped
public class CatBean implements Serializable {
    private final CatManager catManager;
    private List<Cat> cats;

    private Cat selectedCat;

    private Cat newCat = new Cat();
    public Cat getNewCat() {
        return newCat;
    }

    public void setNewCat(Cat newCat) {
        this.newCat = newCat;
    }

    public CatBean() {
        this.catManager = new CatManager();
    }

    public List<Cat> getCats() {
        if (cats == null) {
            cats = catManager.getAllCats();
        }
        return cats;
    }

    public void setCats(List<Cat> cats) {
        this.cats = cats;
    }

    public Cat getSelectedCat() {
        return selectedCat;
    }

    public void setSelectedCat(Cat selectedCat) {
        this.selectedCat = selectedCat;
    }

    public String prepareEditCat(Cat cat) {
        if (cat == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Ungültige Katze"));
            return null;
        }
        setSelectedCat(cat);
        return "goToEditCat";
    }

    public String prepareDeleteCat(Cat cat) {
        if (cat == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Ungültige Katze"));
            return null;
        }
        setSelectedCat(cat);
        return "goToDeleteCat";
    }

    public String updateCat() throws IOException {
        catManager.updateCat(selectedCat);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                "Cat updated successfully", null));
        return "saveEdit";
    }

    public String addCat() {
        try {
            // Überprüfe, ob alle erforderlichen Felder ausgefüllt sind
            if (newCat.getGender() == null || newCat.getName() == null || newCat.getAge() <= 0) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Please fill in all required fields"));
                return null;
            }

            // Füge die neue Katze zur Datenbank oder Liste hinzu
            catManager.addCat(newCat);

            cats = catManager.getAllCats();

            // Erfolgreiche Nachricht anzeigen
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Success", "Cat added successfully"));

            // Navigiere zurück zum Admin-Dashboard
            return "successAdd";
        } catch (Exception e) {
            // Fehlerbehandlung
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "An error occurred"));
            return null;
        }
    }

    public List<CatGender> getGenderOptions() {
        return Arrays.asList(CatGender.values());
    }

    public List<CatHairstyle> getHairstyleOptions() {
        return Arrays.asList(CatHairstyle.values());
    }

    public List<CatColor> getColorOptions() {
        return Arrays.asList(CatColor.values());
    }

    public String deleteCat() {
        catManager.removeCat(selectedCat);
        cats = catManager.getAllCats();
        return "confirmDelete";
    }

    public List<Cat> getMostRecent() {
        return catManager.getMostRecent();
    }

    public List<Cat> getCatsWhereEquals(String attribute, Object value) {
        System.out.println(value.getClass().getName());
        return catManager.getCatsWhereEquals(attribute, value);
    }

    public Object getEnumValue(String category, String value) {
        try {
            System.out.println(category);
            System.out.println(value);
            if("color".equals(category)) {
                return CatColor.valueOf(value);
            }
            if("gender".equals(category)) {
                return CatGender.valueOf(value);
            }
            if("hairstyle".equals(category)) {
                return CatHairstyle.valueOf(value);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
        return null;
    }
}
