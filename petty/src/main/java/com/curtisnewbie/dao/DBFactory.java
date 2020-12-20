package com.curtisnewbie.dao;

import com.curtisnewbie.config.PropertiesLoader;
import com.curtisnewbie.config.PropertyConstants;
import com.curtisnewbie.util.IOUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author yongjie.zhuang
 */
public final class DBFactory {

    public static final DBFactory INSTANCE = new DBFactory();
    private static final Connection conn;

    private DBFactory() {
    }

    static {
        try {
            String rootPath = System.getProperty("user.home") + File.separator + "petty";
            new File(rootPath).mkdir();
            conn = DriverManager.getConnection("jdbc:sqlite:" + rootPath + File.separator + "petty.db");
            initTablesIfNotExists(conn);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot connect to database", e);
        }
    }

    private static void initTablesIfNotExists(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(IOUtil.readResourceAsString(PropertiesLoader.getInstance().get(PropertyConstants.APP_INIT_SQL_SCRIPT)));
        } catch (Exception e) {
            throw new RuntimeException("Cannot initialize tables", e);
        }
    }
}
