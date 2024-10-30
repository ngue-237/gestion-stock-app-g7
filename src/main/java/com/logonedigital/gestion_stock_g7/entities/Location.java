package com.logonedigital.gestion_stock_g7.entities;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "locations")
public class Location {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer locationId;
    private String town;
    private Integer postalCode;
    private String street;
    @Temporal(TemporalType.DATE)
    private Instant createdAt;
    @Temporal(TemporalType.DATE)
    private Instant updatedAt;

    public Location() {
    }

    public Location(Integer locationId, String town, Integer postalCode, String street,
                    Instant createdAt, Instant updatedAt) {
        this.locationId = locationId;
        this.town = town;
        this.postalCode = postalCode;
        this.street = street;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public Integer getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
