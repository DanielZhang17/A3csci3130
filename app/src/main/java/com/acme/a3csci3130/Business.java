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

    public Business(long businessNumber, String name, String primaryBusiness, String address, String province,String uid) {
        this.businessNumber = businessNumber;
        this.name = name;
        this.primaryBusiness = primaryBusiness;
        this.address = address;
        this.province = province;
        this.uid = uid;
    }

    public Business() {
        // Default constructor required for calls to DataSnapshot.getValue
    }

    public long getBusinessNumber() {
        return businessNumber;
    }

    public String getName() {
        return name;
    }

    public String getPrimaryBusiness() {
        return primaryBusiness;
    }

    public String getAddress() {
        return address;
    }

    public String getProvince() {
        return province;
    }
    public  String getUID(){
        return uid;
    }

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
