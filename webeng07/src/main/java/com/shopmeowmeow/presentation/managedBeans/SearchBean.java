package com.shopmeowmeow.presentation.managedBeans;

import com.shopmeowmeow.businessLogic.managers.CatManager;
import com.shopmeowmeow.transfer.Cat;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class SearchBean implements Serializable {

    @Inject
    private CatManager catManager;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    private String searchTerm;
    private List<Cat> searchResults;

    public List<Cat> getSearchResults() {
        return searchResults;
    }

    public void setSearchResults(List<Cat> searchResults) {
        this.searchResults = searchResults;
    }

    public void searchListener() {
        if (searchTerm != null && !searchTerm.isEmpty()) {
            searchResults = catManager.getCatsWhereSubstring("name", searchTerm);
        } else {
            searchResults = new ArrayList<>();
        }
    }
}
