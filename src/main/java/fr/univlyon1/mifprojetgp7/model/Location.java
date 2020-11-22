package fr.univlyon1.mifprojetgp7.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Location")
public class Location {

    @Id
    private String coordinates;

    private String roadName;
    private String complement;
    private int postalCode;
    private String cityName;
    private String countryName;
    private String categoryName;

    public String getCategoryName() {
        return categoryName; }

    public void setCategoryName(final String categoryName) {
        this.categoryName = categoryName; }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(final String coordinates) {
        this.coordinates = coordinates;
    }

    public String getRoadName() {
        return roadName;
    }

    public void setRoadName(final String roadName) {
        this.roadName = roadName;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(final String complement) {
        this.complement = complement;
    }

    public int getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(final int postalCode) {
        this.postalCode = postalCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(final String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(final String countryName) {
        this.countryName = countryName;
    }

    /**
     * Test if the location is equal the the coordinates.
     * @param o
     * @return True if the location is equal to the coordinates or false if not
     */
    public boolean equals(final Object o) {
        if (o instanceof Location) {
            return ((Location) o).getCoordinates().equals(coordinates);
        }
        return false;
    }

    /**
     * Hashed coordinates.
     * @return hashed coordinates
     */
    public int hashCode() {
        return coordinates.hashCode();
    }
}
