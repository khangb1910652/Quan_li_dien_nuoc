package com.example.quan_li_dien_nuoc;

public class DienNuocModel {
    private String maDay;
    private String maPhong;
    private String ngayGhi;
    private String chiSoDien;
    private String chiSoNuoc;
    private int maDienNuoc;

    // creating getter and setter methods
    public String layNgayGhi() { return ngayGhi; }
    public void datNgayGhi(String ngayGhi)
    {
        this.ngayGhi = ngayGhi;
    }

    public String layChiSoDien() { return chiSoDien; }
    public void datChiSoDien(String chiSoDien)
    {
        this.chiSoDien = chiSoDien;
    }

    public String layChiSoNuoc() { return chiSoNuoc; }
    public void datChiSoNuoc(String chiSoNuoc)
    {
        this.chiSoNuoc = chiSoNuoc;
    }

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

    public int layMaDienNuoc() { return maDienNuoc; }
    public void datMaDienNuoc(int maDienNuoc) { this.maDienNuoc = maDienNuoc; }
    // constructor
    public DienNuocModel(String maDay, String maPhong, String ngayGhi, String chiSoDien, String chiSoNuoc, Integer maDienNuoc)
    {
        this.maDay = maDay;
        this.maPhong = maPhong;
        this.ngayGhi = ngayGhi;
        this.chiSoDien = chiSoDien;
        this.chiSoNuoc = chiSoNuoc;
        this.maDienNuoc = maDienNuoc;
    }
}
