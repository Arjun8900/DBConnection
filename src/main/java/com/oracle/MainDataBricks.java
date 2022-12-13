package com.oracle;

import com.dbmananger.DataBricksManager;

public class MainDataBricks {
    public static void main(String[] args) {
        DataBricksManager dataBricksManager = new DataBricksManager();
        System.out.println(dataBricksManager.getTables());
    }
}
