/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Squash
 */
public class Address {
    
    @JsonProperty("street")
    public String street;
    @JsonProperty("city")
    public String city;
    @JsonProperty("state")
    public String state;
    @JsonProperty("postcode")
    public String postcode;
    @JsonProperty("country")
    public String country;
}
