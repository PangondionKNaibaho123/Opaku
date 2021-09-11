package com.dionkn.opaku.User;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {

    public String email_user;
    public String password;
    public String nama_lengkap;
    public String phone_number;
    public int jenis_user;
    public String alamat;

    public User(){

    }

    public User(String email_user, String password, String nama_lengkap,String phone_number,  int jenis_user, String alamat){
        this.email_user = email_user;
        this.password = password;
        this.nama_lengkap = nama_lengkap;
        this.phone_number = phone_number;
        this.jenis_user = jenis_user;
        this.alamat = alamat;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public int getJenis_user() {
        return jenis_user;
    }

    public void setJenis_user(int jenis_user) {
        this.jenis_user = jenis_user;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

}
