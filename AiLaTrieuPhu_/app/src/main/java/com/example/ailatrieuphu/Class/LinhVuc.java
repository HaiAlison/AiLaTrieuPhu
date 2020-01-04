package com.example.ailatrieuphu.Class;

public class LinhVuc {
    private int id;
    private String tenLinhVuc;
    private boolean xoa;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLinhVuc() {
        return tenLinhVuc;
    }

    public void setTenLinhVuc(String tenLinhVuc) {
        this.tenLinhVuc = tenLinhVuc;
    }

    public boolean isXoa() {
        return xoa;
    }

    public void setXoa(boolean xoa) {
        this.xoa = xoa;
    }

    public LinhVuc() {
    }

    public LinhVuc(int id, String tenLinhVuc, boolean xoa) {
        this.id = id;
        this.tenLinhVuc = tenLinhVuc;
        this.xoa = xoa;
    }
}
