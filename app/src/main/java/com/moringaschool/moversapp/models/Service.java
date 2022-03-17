package com.moringaschool.moversapp.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Service implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("noOfBedRooms")
    @Expose
    private String noOfBedRooms;
    @SerializedName("photoLink")
    @Expose
    private String photoLink;
    @SerializedName("price")
    @Expose
    private Integer price;

    /**
     * No args constructor for use in serialization
     *
     */
    public Service() {
    }

    /**
     *
     * @param noOfBedRooms
     * @param price
     * @param id
     * @param photoLink
     */
    public Service(Integer id, String noOfBedRooms, String photoLink, Integer price) {
        super();
        this.id = id;
        this.noOfBedRooms = noOfBedRooms;
        this.photoLink = photoLink;
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNoOfBedRooms() {
        return noOfBedRooms;
    }

    public void setNoOfBedRooms(String noOfBedRooms) {
        this.noOfBedRooms = noOfBedRooms;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
