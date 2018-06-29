package com.acme.a3csci3130;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that defines how the data will be stored in the
 * Firebase databse. This is converted to a JSON format
 */
public class Business implements Serializable {

    private  long businessNumber;
    private  String name;
    private  String primaryBusiness;
    private  String address;
    private  String province;
    private String uid;

    /**
     * Instantiates a new Business.
     *
     * @param businessNumber  the business number
     * @param name            the name
     * @param primaryBusiness the primary business
     * @param address         the address
     * @param province        the province
     * @param uid             the uid
     */
    public Business(long businessNumber, String name, String primaryBusiness, String address, String province,String uid) {
        this.businessNumber = businessNumber;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
        this.uid = uid;
    }

    /**
     * Instantiates a new Business.
     */
    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    /**
     * Gets business number.
     *
     * @return the business number
     */
    public long getBusinessNumber() {
        return businessNumber;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets primary business.
     *
     * @return the primary business
     */
    public String getPrimaryBusiness() {
        return primaryBusiness;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Gets province.
     *
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * Get uid string.
     *
     * @return the string
     */
    public  String getUID(){
        return uid;
    }

    /**
     * To map map.
     *
     * @return the map
     */
    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("businessNumber", businessNumber);
        result.put("name", name);
        result.put("primaryBusiness", primaryBusiness);
        result.put("address", address);
        result.put("province", province);
        result.put("uid", uid);
        return result;
    }
}
