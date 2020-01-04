package com.example.ailatrieuphu.Class;

public class CauHoi {
    private int id;
    private String noiDung;
    private int linhVucID;
    private String daA;
    private String daB;
    private String daC;
    private String daD;
    private String dapAn;

    public CauHoi(){
        id =0;
        noiDung ="";
        linhVucID=0;
        daA="";
        daB="";
        daC="";
        daD="";
        dapAn="";
    }
    public CauHoi(int id,int linhVucID,String noiDung,String daA,String daB, String daC,String daD,String dapAn){
        this.id=id;
        this.linhVucID=linhVucID;
        this.noiDung=noiDung;
        this.daA=daA;
        this.daB=daB;
        this.daC=daC;
        this.daD=daD;
        this.dapAn=dapAn;
    }
    public CauHoi(String noiDung,String daA,String daB, String daC,String daD,String dapAn){
        this.noiDung=noiDung;
        this.daA=daA;
        this.daB=daB;
        this.daC=daC;
        this.daD=daD;
        this.dapAn = dapAn;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }



    public String getDaA() {
        return daA;
    }

    public void setDaA(String daA) {
        this.daA = daA;
    }

    public String getDaB() {
        return daB;
    }

    public void setDaB(String daB) {
        this.daB = daB;
    }

    public String getDaC() {
        return daC;
    }

    public void setDaC(String daC) {
        this.daC = daC;
    }

    public String getDaD() {
        return daD;
    }

    public void setDaD(String daD) {
        this.daD = daD;
    }

    public String getDapAn() {
        return dapAn;
    }

    public void setDapAn(String dapAn) {
        this.dapAn = dapAn;
    }

    public int getLinhVucID() {
        return linhVucID;
    }

    public void setLinhVucID(int linhVucID) {
        this.linhVucID = linhVucID;
    }
}
