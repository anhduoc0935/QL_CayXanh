package com.example.ql_cayxanh.model;

import java.io.Serializable;

public class CayXanh implements Serializable {
    private String id;
    private String TenKhoaHoc;
    private String TenThuongGoi;
    private String DacTinh;
    private String MauLa;


    @Override
    public String toString() {
        return "CayXanh{" +
                "id='" + id + '\'' +
                ", TenKhoaHoc='" + TenKhoaHoc + '\'' +
                ", TenThuongGoi='" + TenThuongGoi + '\'' +
                ", DacTinh='" + DacTinh + '\'' +
                ", MauLa='" + MauLa + '\'' +

                '}';
    }

    public CayXanh() {
    }

    public CayXanh( String tenKhoaHoc, String tenThuongGoi, String dacTinh, String mauLa) {

        TenKhoaHoc = tenKhoaHoc;
        TenThuongGoi = tenThuongGoi;
        DacTinh = dacTinh;
        MauLa = mauLa;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenKhoaHoc() {
        return TenKhoaHoc;
    }

    public void setTenKhoaHoc(String tenKhoaHoc) {
        TenKhoaHoc = tenKhoaHoc;
    }

    public String getTenThuongGoi() {
        return TenThuongGoi;
    }

    public void setTenThuongGoi(String tenThuongGoi) {
        TenThuongGoi = tenThuongGoi;
    }

    public String getDacTinh() {
        return DacTinh;
    }

    public void setDacTinh(String dacTinh) {
        DacTinh = dacTinh;
    }

    public String getMauLa() {
        return MauLa;
    }

    public void setMauLa(String mauLa) {
        MauLa = mauLa;
    }


}
