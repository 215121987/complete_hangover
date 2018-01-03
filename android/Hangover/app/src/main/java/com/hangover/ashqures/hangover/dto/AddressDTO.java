package com.hangover.ashqures.hangover.dto;


/**
 * Created by ashqures on 8/28/16.
 */
public class AddressDTO extends BaseDTO{

    private String address;
    private String street;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private String latitude;
    private String longitude;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public  String getCompleteAddress(){
        StringBuilder builder = new StringBuilder();
        builder.append(this.address).append(", ").append(this.street)
                .append(", ").append(this.city).append(", ")
                .append(this.state).append(", ").append(this.country).append(", ")
                .append(this.zipCode);
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AddressDTO)) return false;

        AddressDTO that = (AddressDTO) o;

        if (getAddress() != null ? !getAddress().equals(that.getAddress()) : that.getAddress() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(that.getStreet()) : that.getStreet() != null)
            return false;
        if (getCity() != null ? !getCity().equals(that.getCity()) : that.getCity() != null)
            return false;
        if (getState() != null ? !getState().equals(that.getState()) : that.getState() != null)
            return false;
        if (getCountry() != null ? !getCountry().equals(that.getCountry()) : that.getCountry() != null)
            return false;
        if (getZipCode() != null ? !getZipCode().equals(that.getZipCode()) : that.getZipCode() != null)
            return false;
        if (getLatitude() != null ? !getLatitude().equals(that.getLatitude()) : that.getLatitude() != null)
            return false;
        return getLongitude() != null ? getLongitude().equals(that.getLongitude()) : that.getLongitude() == null;

    }

    @Override
    public int hashCode() {
        int result = getAddress() != null ? getAddress().hashCode() : 0;
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getCity() != null ? getCity().hashCode() : 0);
        result = 31 * result + (getState() != null ? getState().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        result = 31 * result + (getZipCode() != null ? getZipCode().hashCode() : 0);
        result = 31 * result + (getLatitude() != null ? getLatitude().hashCode() : 0);
        result = 31 * result + (getLongitude() != null ? getLongitude().hashCode() : 0);
        return result;
    }
}
