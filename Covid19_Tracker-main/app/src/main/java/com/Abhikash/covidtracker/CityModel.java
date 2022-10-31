package com.Abhikash.covidtracker;

public class CityModel {
private String district, active, confirm,
        deceased, recovered ;

public CityModel(String dist
, String active, String confirmed, String deceased
, String recovered){
    this.district =dist
;
    this.active = active;
    this.confirm = confirmed;
    this.deceased = deceased;
    this.recovered = recovered;

}
    public void setDistrict(String district) {this.district = district; }

    public void setActive(String active) {
        this.active = active;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }

    public void setDeceased(String deceased) {
        this.deceased = deceased;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getDistrict() {
        return district;
    }

    public String getActive() {
        return active;
    }

    public String getConfirm() {
        return confirm;
    }

    public String getDeceased() {
        return deceased;
    }

    public String getRecovered() {
        return recovered;
    }
}