package com.curtisnewbie.controller;

import com.curtisnewbie.dao.DBFactory;
import com.curtisnewbie.dao.HospitalRecordDetailMapper;
import com.curtisnewbie.dao.HospitalRecordMapper;
import com.curtisnewbie.dao.MapperType;
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

    private DBFactory dbFactory = DBFactory.INSTANCE;

    // TODO implement this
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HospitalRecordMapper hospitalRecordMapper = (HospitalRecordMapper) dbFactory.getMapper(MapperType.HOSPITAL_RECORD);
    }
}


