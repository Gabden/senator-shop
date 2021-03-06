package ru.gabdulindv.senatorshop.model.product;

import java.util.List;

public class Filter {
    private List<String> selectedCategories;
    private List<String> selectedTypes;
    private String selectedCountry;
    private String selectedManufacturer;
    private int minPrice;
    private int maxPrice;

    public Filter() {
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public List<String> getSelectedCategories() {
        return selectedCategories;
    }

    public void setSelectedCategories(List<String> selectedCategories) {
        this.selectedCategories = selectedCategories;
    }

    public List<String> getSelectedTypes() {
        return selectedTypes;
    }

    public void setSelectedTypes(List<String> selectedTypes) {
        this.selectedTypes = selectedTypes;
    }

    public String getSelectedCountry() {
        return selectedCountry;
    }

    public void setSelectedCountry(String selectedCountry) {
        this.selectedCountry = selectedCountry;
    }

    public String getSelectedManufacturer() {
        return selectedManufacturer;
    }

    public void setSelectedManufacturer(String selectedManufacturer) {
        this.selectedManufacturer = selectedManufacturer;
    }

    @Override
    public String toString() {
        return "Filter{" +
                "selectedCategories=" + selectedCategories +
                ", selectedTypes=" + selectedTypes +
                ", selectedCountry='" + selectedCountry + '\'' +
                ", selectedManufacturer='" + selectedManufacturer + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                '}';
    }
}
