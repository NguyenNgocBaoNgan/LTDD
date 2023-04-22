package com.example.gk;

import java.io.Serializable;

public class SinhVien implements Serializable {

    Integer id;
    String name, date, id_class;

    public SinhVien() {
    }

    public SinhVien(Integer id, String name, String date, String id_class) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.id_class = id_class;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId_class() {
        return id_class;
    }

    public void setId_class(String id_class) {
        this.id_class = id_class;
    }
}
