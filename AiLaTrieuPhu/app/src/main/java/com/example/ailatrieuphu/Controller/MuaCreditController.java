package com.example.ailatrieuphu.Controller;

public class MuaCreditController {
    private static final String TABLE_GOI_CREDIT= "goicredit";
    private static final String COLUMN_ID= "id";
    private static final String COLUMN_TEN_GOI= "ten_goi";
    private static final String COLUMN_CREDIT = "credit";
    private static final String COLUMN_SO_TIEN = "so_tien";
    private static final String COLUMN_XOA = "xoa";
    private static final String SELECT_ALL = "SELECT * FROM "+TABLE_GOI_CREDIT +" WHERE " + COLUMN_XOA +" = 0 ";
}
