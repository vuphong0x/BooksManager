package com.example.booksmanager.Model;

public class Sach {
    private String maSach;
    private String maTheLoai;
    private String tieuDe;
    private String tacGia;
    private String soLuongTonKho;
    private String nhaXuatBan;

    public Sach() {
    }

    public Sach(String maSach, String maTheLoai, String tieuDe, String tacGia, String soLuongTonKho, String nhaXuatBan) {
        this.maSach = maSach;
        this.maTheLoai = maTheLoai;
        this.tieuDe = tieuDe;
        this.tacGia = tacGia;
        this.soLuongTonKho = soLuongTonKho;
        this.nhaXuatBan = nhaXuatBan;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getTacGia() {
        return tacGia;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }

    public String getSoLuongTonKho() {
        return soLuongTonKho;
    }

    public void setSoLuongTonKho(String soLuongTonKho) {
        this.soLuongTonKho = soLuongTonKho;
    }

    public String getNhaXuatBan() {
        return nhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        this.nhaXuatBan = nhaXuatBan;
    }
}
