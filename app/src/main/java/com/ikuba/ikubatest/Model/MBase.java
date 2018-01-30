package com.ikuba.ikubatest.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AFARHAN-PC on 1/23/2018.
 */

public class MBase {
    @SerializedName("status")
    boolean status;
    @SerializedName("message")
    String message;

    public boolean isStatus() {return status;}

    public void setStatus(boolean status){this.status = status;}

    public String getMessage(){return  message;}

    public void setMessage(String message){this.message = message;}
}
