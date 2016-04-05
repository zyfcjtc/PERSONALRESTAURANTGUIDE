package ca.gbc.mobile.dgy.personalrestaurantguide;

import com.google.android.gms.maps.model.LatLng;

/*********************************
 *    Students:                  *
 * David Olano - ID: 100847924   *
 * Yafan Zhang - ID: 100816652   *
 * Gary  Chan  - ID: 100882663   *
 *                               *
 ********************************/
public class Restaurant {
    int rest_id;
    String name;
    String address;
    String phone;
    String tag;
    String description;
    float rate;
    String website;
    int image;
    double latitude;
    double longitude;



    public Restaurant() {
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public Restaurant(int id,String name, String address, String phone, String tag, String description, float rate, String website, int image,double latitude,double longitude) {
        this.rest_id=id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.tag = tag;
        this.description = description;
        this.rate = rate;
        this.website = website;
        this.image = image;

        this.latitude=latitude;
        this.longitude=longitude;
    }
    //id,name,address,phone,tag,description,rate,website,pic,la,lo
    public Restaurant(String name, String address, String phone, String tag, String description, float rate, String website, int image,double latitude,double longitude) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.tag = tag;
        this.description = description;
        this.rate = rate;
        this.website = website;
        this.image = image;

        this.latitude=latitude;
        this.longitude=longitude;
    }

    //get method
    public int getRest_id() {
        return rest_id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getTag() {
        return tag;
    }

    public String getDescription() {
        return description;
    }

    public float getRate() {
        return rate;
    }

    public String getWebsite() {
        return website;
    }

    public int getImage() {
        return image;
    }
    //set method
    public void setRest_id(int rest_id) {
        this.rest_id = rest_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
