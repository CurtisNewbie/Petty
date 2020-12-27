package com.curtisnewbie.controller;

import com.curtisnewbie.dao.*;
import com.curtisnewbie.entity.HospitalRecordEntity;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.*;

/**
 * <p>
 * Controller for UI
 * </p>
 *
 * @author yongjie.zhuang
 */
public class Controller implements Initializable {

    /** DB Connection and factory for Mappers */
    private MapperFactory mapperFactory = DBFactory.INSTANCE;

    /** Mapper for table hospital_record */
    private HospitalRecordMapper hospitalRecordMapper = (HospitalRecordMapper) mapperFactory.getMapper(MapperType.HOSPITAL_RECORD);
    /** Mapper for table hospital_record_detail */
    private HospitalRecordDetailMapper hospitalRecordDetailMapper = (HospitalRecordDetailMapper) mapperFactory.getMapper(MapperType.HOSPITAL_RECORD_DETAIL);
    /** Mapper for table med_detail */
    private MedDetailMapper medDetailMapper = (MedDetailMapper) mapperFactory.getMapper(MapperType.MED_DETAIL);
    /** Mapper for table weight_detail */
    private WeightDetailMapper weightDetailMapper = (WeightDetailMapper) mapperFactory.getMapper(MapperType.WEIGHT_DETAIL);
    /** Mapper for table poo_detail */
    private PooDetailMapper pooDetailMapper = (PooDetailMapper) mapperFactory.getMapper(MapperType.POO_DETAIL);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO implement this
    }
}


