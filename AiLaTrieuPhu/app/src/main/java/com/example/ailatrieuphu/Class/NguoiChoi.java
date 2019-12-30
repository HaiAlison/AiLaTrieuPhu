package com.example.ailatrieuphu.Class;

public class NguoiChoi {
    private int id;
    private String tenTaiKhoan;
    private String MatKhau;
    private String email;
    private String hinhDaiDien;
    private int diemCaoNhat;
    private int credit;
    private boolean xoa;


    public NguoiChoi(){

    }
    public NguoiChoi(int id, String tenTaiKhoan,String MatKhau, String email,String hinhDaiDien,int diemCaoNhat,int credit,boolean xoa){
        this.id=id;
        this.tenTaiKhoan=tenTaiKhoan;
        this.MatKhau=MatKhau;
        this.email=email;
        this.hinhDaiDien=hinhDaiDien;
        this.diemCaoNhat=diemCaoNhat;
        this.credit=credit;
        this.xoa=xoa;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        this.hinhDaiDien = hinhDaiDien;
    }

    public int getDiemCaoNhat() {
        return diemCaoNhat;
    }

    public void setDiemCaoNhat(int diemCaoNhat) {
        this.diemCaoNhat = diemCaoNhat;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }
}
