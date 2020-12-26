package com.curtisnewbie.dao;

import com.curtisnewbie.config.PropertiesLoader;
import com.curtisnewbie.config.PropertyConstants;
import com.curtisnewbie.entity.*;
import com.curtisnewbie.util.IOUtil;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author yongjie.zhuang
 */
public final class DBFactory implements MapperFactory {

    public static final DBFactory INSTANCE = new DBFactory();
    private final Connection connection;

    private DBFactory() {
        try {
            String rootPath = System.getProperty("user.home") + File.separator + "petty";
            new File(rootPath).mkdir();
            connection = DriverManager.getConnection("jdbc:sqlite:" + rootPath + File.separator + "petty.db");
            initTablesIfNotExists(connection);
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

    @Override
    public Mapper getMapper(MapperType type) {
        if (type == null)
            throw new IllegalArgumentException("MapperType should not be null");
        if (type.equals(MapperType.HOSPITAL_RECORD))
            return new HospitalRecordMapperImpl(this.connection);
        else if (type.equals(MapperType.HOSPITAL_RECORD_DETAIL))
            return new HospitalRecordDetailMapperImpl(this.connection);
        else if (type.equals(MapperType.MED_DETAIL))
            return new MedDetailMapperImpl(this.connection);
        else if (type.equals(MapperType.POO_DETAIL))
            return new PooDetailMapperImpl(this.connection);
        else if (type.equals(MapperType.WEIGHT_DETAIL))
            return new WeightDetailMapperImpl(this.connection);
        else
            throw new UnsupportedOperationException("MapperType not supported");
    }

    private static class HospitalRecordMapperImpl implements HospitalRecordMapper {

        private final Connection conn;

        private HospitalRecordMapperImpl(Connection conn) {
            this.conn = conn;
        }

        @Override
        public Optional<Integer> insert(HospitalRecordEntity entity) {
            try (PreparedStatement preparedStatement = conn
                    .prepareStatement("INSERT INTO hospital_record (date) VALUES (?)", Statement.RETURN_GENERATED_KEYS);) {
                preparedStatement.setDate(1, toSqlDate(entity.getDate()));
                preparedStatement.executeUpdate();
                ResultSet set = preparedStatement.getGeneratedKeys();
                if (set.next())
                    return Optional.of(set.getInt(1));
                return Optional.empty();
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        @Override
        public boolean deleteById(HospitalRecordEntity entity) {
            return deleteById(entity.getId());
        }

        @Override
        public boolean deleteById(int id) {
            try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM hospital_record WHERE id = ?")) {
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean updateById(HospitalRecordEntity entity) {
            try (PreparedStatement preparedStatement = conn.prepareStatement("UPDATE hospital_record SET date = ? WHERE id = ?")) {
                preparedStatement.setDate(1, toSqlDate(entity.getDate()));
                preparedStatement.setInt(2, entity.getId());
                return preparedStatement.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public List<HospitalRecordEntity> findAll() {
            List<HospitalRecordEntity> list = new ArrayList<>();
            try (PreparedStatement stmt = conn.prepareStatement("SELECT id, date FROM hospital_record");) {
                ResultSet resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    HospitalRecordEntity entity = new HospitalRecordEntity();
                    entity.setId(resultSet.getInt(1));
                    entity.setDate(resultSet.getDate(2));
                    list.add(entity);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return list;
        }
    }

    private static class HospitalRecordDetailMapperImpl implements HospitalRecordDetailMapper {

        private final Connection conn;

        private HospitalRecordDetailMapperImpl(Connection conn) {
            this.conn = conn;
        }

        @Override
        public Optional<Integer> insert(HospitalRecordDetailEntity entity) {
            try (PreparedStatement preparedStatement = conn
                    .prepareStatement("INSERT INTO hospital_record_detail (hospital_record_pk, type, date,  remarks) VALUES (?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);) {
                preparedStatement.setInt(1, entity.getHospitalRecordPk());
                preparedStatement.setInt(2, entity.getType());
                preparedStatement.setDate(3, toSqlDate(entity.getDate()));
                preparedStatement.setString(4, entity.getRemarks());
                preparedStatement.executeUpdate();
                ResultSet set = preparedStatement.getGeneratedKeys();
                if (set.next())
                    return Optional.of(set.getInt(1));
                return Optional.empty();
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        @Override
        public boolean deleteById(HospitalRecordDetailEntity entity) {
            return deleteById(entity.getId());
        }

        @Override
        public boolean deleteById(int id) {
            try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM hospital_record_detail WHERE id = ?",
                    Statement.RETURN_GENERATED_KEYS);) {
                preparedStatement.setInt(1, id);
                return preparedStatement.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean updateById(HospitalRecordDetailEntity entity) {
            try (PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE hospital_record_detail SET hospital_record_pk = ?, type = ?, date = ?, remarks = ? WHERE id = ?",
                            Statement.RETURN_GENERATED_KEYS);) {
                preparedStatement.setInt(1, entity.getHospitalRecordPk());
                preparedStatement.setInt(2, entity.getType());
                preparedStatement.setDate(3, toSqlDate(entity.getDate()));
                preparedStatement.setString(4, entity.getRemarks());
                preparedStatement.setInt(5, entity.getId());
                return preparedStatement.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private static class MedDetailMapperImpl implements MedDetailMapper {

        private final Connection conn;

        private MedDetailMapperImpl(Connection conn) {
            this.conn = conn;
        }

        @Override
        public Optional<Integer> insert(MedDetailEntity entity) {
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO med_detail (date, remarks) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setDate(1, toSqlDate(entity.getDate()));
                stmt.setString(2, entity.getRemarks());
                ResultSet set = stmt.getGeneratedKeys();
                if (set.next())
                    return Optional.of(set.getInt(1));
                return Optional.empty();
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        @Override
        public boolean deleteById(int id) {
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM med_detail WHERE id = ?")) {
                stmt.setInt(1, id);
                return stmt.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean deleteById(MedDetailEntity entity) {
            return deleteById(entity.getId());
        }

        @Override
        public boolean updateById(MedDetailEntity entity) {
            try (PreparedStatement stmt = conn.prepareStatement("UPDATE med_detail SET date = ?, remarks = ? WHERE id = ?")) {
                stmt.setDate(1, toSqlDate(entity.getDate()));
                stmt.setString(2, entity.getRemarks());
                stmt.setInt(3, entity.getId());
                return stmt.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private static class PooDetailMapperImpl implements PooDetailMapper {

        private final Connection conn;

        private PooDetailMapperImpl(Connection conn) {
            this.conn = conn;
        }

        @Override
        public Optional<Integer> insert(PooDetailEntity entity) {
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO poo_detail (date, remarks) VALUES (?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setDate(1, toSqlDate(entity.getDate()));
                stmt.setString(2, entity.getRemarks());
                ResultSet set = stmt.getGeneratedKeys();
                if (set.next())
                    return Optional.of(set.getInt(1));
                return Optional.empty();
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        @Override
        public boolean deleteById(PooDetailEntity entity) {
            return deleteById(entity.getId());
        }

        @Override
        public boolean deleteById(int id) {
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM poo_detail WHERE id = ?")) {
                stmt.setInt(1, id);
                return stmt.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean updateById(PooDetailEntity entity) {
            try (PreparedStatement stmt = conn.prepareStatement("UPDATE poo_detail SET date = ?, remarks = ? WHERE id = ?")) {
                stmt.setDate(1, toSqlDate(entity.getDate()));
                stmt.setString(2, entity.getRemarks());
                stmt.setInt(3, entity.getId());
                return stmt.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    private static class WeightDetailMapperImpl implements WeightDetailMapper {

        private final Connection conn;

        private WeightDetailMapperImpl(Connection conn) {
            this.conn = conn;
        }

        @Override
        public Optional<Integer> insert(WeightDetailEntity entity) {
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO weight_detail (date, remarks, weight) VALUES (?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS)) {
                stmt.setDate(1, toSqlDate(entity.getDate()));
                stmt.setString(2, entity.getRemarks());
                stmt.setDouble(3, entity.getWeight());
                ResultSet set = stmt.getGeneratedKeys();
                if (set.next())
                    return Optional.of(set.getInt(1));
                return Optional.empty();
            } catch (SQLException e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }

        @Override
        public boolean deleteById(WeightDetailEntity entity) {
            return deleteById(entity.getId());
        }

        @Override
        public boolean deleteById(int id) {
            try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM weight_detail WHERE id = ?")) {
                stmt.setInt(1, id);
                return stmt.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public boolean updateById(WeightDetailEntity entity) {
            try (PreparedStatement stmt = conn.prepareStatement("UPDATE weight_detail SET date = ?, remarks = ?, weight = ? WHERE id = ?")) {
                stmt.setDate(1, toSqlDate(entity.getDate()));
                stmt.setString(2, entity.getRemarks());
                stmt.setDouble(3, entity.getWeight());
                stmt.setInt(4, entity.getId());
                return stmt.executeUpdate() == 1;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }


    /**
     * Convert java.util.Date to java.sql.Date
     *
     * @param date java.util.Date
     * @return java.sql.Date
     */
    private static java.sql.Date toSqlDate(java.util.Date date) {
        return new java.sql.Date(date.toInstant().toEpochMilli());
    }
}
