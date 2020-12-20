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
public final class DBFactory implements MapperFactory {

    public static final DBFactory INSTANCE = new DBFactory();
    private static final Connection conn;

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

    private DBFactory() {
    }

    private static void initTablesIfNotExists(Connection conn) {
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(IOUtil.readResourceAsString(PropertiesLoader.getInstance().get(PropertyConstants.APP_INIT_SQL_SCRIPT)));
        } catch (Exception e) {
            throw new RuntimeException("Cannot initialize tables", e);
        }
    }

    @Override
    public Mapper getMapper(MapperType type) {
        if (type == null)
            throw new IllegalArgumentException("MapperType should not be null");
        if (type.equals(MapperType.HOSPITAL_RECORD))
            return new HospitalRecordMapperImpl(this.conn);
        else if (type.equals(MapperType.HOSPITAL_RECORD_DETAIL))
            return new HospitalRecordDetailMapperImpl(this.conn);
        else if (type.equals(MapperType.MED_DETAIL))
            return new MedDetailMapperImpl(this.conn);
        else if (type.equals(MapperType.POO_DETAIL))
            return new PooDetailMapperImpl(this.conn);
        else if (type.equals(MapperType.WEIGHT_DETAIL))
            return new WeightDetailMapperImpl(this.conn);
        else
            throw new UnsupportedOperationException("MapperType not supported");
    }

    private static class HospitalRecordMapperImpl implements HospitalRecordMapper {
        private final Connection conn;

        private HospitalRecordMapperImpl(Connection conn) {
            this.conn = conn;
        }
    }

    private static class HospitalRecordDetailMapperImpl implements HospitalRecordDetailMapper {

        private final Connection conn;

        private HospitalRecordDetailMapperImpl(Connection conn) {
            this.conn = conn;
        }

    }

    private static class MedDetailMapperImpl implements MedDetailMapper {

        private final Connection conn;

        private MedDetailMapperImpl(Connection conn) {
            this.conn = conn;
        }

    }

    private static class PooDetailMapperImpl implements PooDetailMapper {

        private final Connection conn;

        private PooDetailMapperImpl(Connection conn) {
            this.conn = conn;
        }

    }

    private static class WeightDetailMapperImpl implements WeightDetailMapper {

        private final Connection conn;

        private WeightDetailMapperImpl(Connection conn) {
            this.conn = conn;
        }
    }
}
