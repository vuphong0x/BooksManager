package com.example.booksmanager.Model;

public class HoaDonChiTiet {
    private int maHoaDonChiTiet;
    private HoaDon hoaDon;
    private Sach sach;
    private int soLuongMua;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHoaDonChiTiet, HoaDon hoaDon, Sach sach, int soLuongMua) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
        this.hoaDon = hoaDon;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }

    public int getMaHoaDonChiTiet() {
        return maHoaDonChiTiet;
    }

    public void setMaHoaDonChiTiet(int maHoaDonChiTiet) {
        this.maHoaDonChiTiet = maHoaDonChiTiet;
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHoaDonChiTiet=" + maHoaDonChiTiet +
                ", hoaDon=" + hoaDon +
                ", sach=" + sach +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
