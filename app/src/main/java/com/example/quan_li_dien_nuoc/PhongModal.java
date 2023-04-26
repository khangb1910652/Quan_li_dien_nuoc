package com.example.quan_li_dien_nuoc;

public class PhongModal {
    private String maDay;
    private String maPhong;

    // creating getter and setter methods
    public String layMaDay() { return maDay; }

    public void datMaDay(String maDay)
    {
        this.maDay = maDay;
    }

    public String layMaPhong() { return maPhong; }

    public void datMaPhong(String maPhong)
    {
        this.maPhong = maPhong;
    }

    // constructor
    public PhongModal(String maDay, String maPhong)
    {
        this.maDay = maDay;
        this.maPhong = maPhong;
    }

}







