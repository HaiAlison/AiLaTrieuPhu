package com.example.ailatrieuphu.Class;

public class CauHoi {
    private int id;
    private String noidung;
    private  int linhvucID;
    private String PhuonganA;
    private String PhuonganB;
    private String PhuonganC;
    private String PhuonganD;
    private String DapAn;
    private Boolean xoa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public int getLinhvucID() {
        return linhvucID;
    }

    public void setLinhvucID(int linhvucID) {
        this.linhvucID = linhvucID;
    }

    public String getPhuonganA() {
        return PhuonganA;
    }

    public void setPhuonganA(String phuonganA) {
        PhuonganA = phuonganA;
    }

    public String getPhuonganB() {
        return PhuonganB;
    }

    public void setPhuonganB(String phuonganB) {
        PhuonganB = phuonganB;
    }

    public String getPhuonganC() {
        return PhuonganC;
    }

    public void setPhuonganC(String phuonganC) {
        PhuonganC = phuonganC;
    }

    public String getPhuonganD() {
        return PhuonganD;
    }

    public void setPhuonganD(String phuonganD) {
        PhuonganD = phuonganD;
    }

    public String getDapAn() {
        return DapAn;
    }

    public void setDapAn(String dapAn) {
        DapAn = dapAn;
    }

    public Boolean getXoa() {
        return xoa;
    }

    public void setXoa(Boolean xoa) {
        this.xoa = xoa;
    }

    public CauHoi() {
    }

    public CauHoi(int id, String noidung, int linhvucID, String phuonganA, String phuonganB, String phuonganC, String phuonganD, String dapAn, Boolean xoa) {
        this.id = id;
        this.noidung = noidung;
        this.linhvucID = linhvucID;
        PhuonganA = phuonganA;
        PhuonganB = phuonganB;
        PhuonganC = phuonganC;
        PhuonganD = phuonganD;
        DapAn = dapAn;
        this.xoa = xoa;
    }
}
