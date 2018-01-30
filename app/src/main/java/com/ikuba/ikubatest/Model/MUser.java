package com.ikuba.ikubatest.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by AFARHAN-PC on 1/23/2018.
 */

public class MUser extends MBase {
    public User data;

    public User getData(){return data;}

    public void setData(User data){this.data = data;}

    public static class User{
        private String id;
        @SerializedName("name")
        private String name;
        @SerializedName("username")
        private String username;
        @SerializedName("password")
        private String password;
        @SerializedName("no_telp")
        private String no_telp;
        @SerializedName("email")
        private String email;
        @SerializedName("user_token")
        private String user_token;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getNo_telp() {
            return no_telp;
        }

        public void setNo_telp(String no_telp) {
            this.no_telp = no_telp;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getUser_token() {
            return user_token;
        }

        public void setUser_token(String user_token) {
            this.user_token = user_token;
        }
    }
}
