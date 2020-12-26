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
    private DBFactory dbFactory = DBFactory.INSTANCE;

    /** Mapper for table hospital_record */
    private HospitalRecordMapper hospitalRecordMapper = (HospitalRecordMapper) dbFactory.getMapper(MapperType.HOSPITAL_RECORD);
    /** Mapper for table hospital_record_detail */
    private HospitalRecordDetailMapper hospitalRecordDetailMapper = (HospitalRecordDetailMapper) dbFactory.getMapper(MapperType.HOSPITAL_RECORD_DETAIL);
    /** Mapper for table med_detail */
    private MedDetailMapper medDetailMapper = (MedDetailMapper) dbFactory.getMapper(MapperType.MED_DETAIL);
    /** Mapper for table weight_detail */
    private WeightDetailMapper weightDetailMapper = (WeightDetailMapper) dbFactory.getMapper(MapperType.WEIGHT_DETAIL);
    /** Mapper for table poo_detail */
    private PooDetailMapper pooDetailMapper = (PooDetailMapper) dbFactory.getMapper(MapperType.POO_DETAIL);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO implement this
    }
}


