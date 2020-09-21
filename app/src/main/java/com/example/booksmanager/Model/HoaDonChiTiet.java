package com.example.booksmanager.Model;

public class HoaDonChiTiet {
    private String maHoaDonChiTiet;
    private String maHoaDon;
    private String maSanPham;
    private String soLuongMua;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(String maHoaDonChiTiet, String maHoaDon, String maSanPham, String soLuongMua) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.maHoaDon = maHoaDon;
        this.maSanPham = maSanPham;
        this.soLuongMua = soLuongMua;
    }

    public String getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(String maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(String soLuongMua) {
        this.soLuongMua = soLuongMua;
    }
}
