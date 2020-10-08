package com.pricillatan.app;

import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.util.TimeZone;

public class Constant {

    private Constant() {
    }

    public static final TimeZone UTC_TIMEZONE = TimeZone.getTimeZone("UTC");
    public static final String FORMAT = "yyyyMMdd";
    public static final String FORMAT_DATE_TIME = "dd/MM/yyyy HH:mm:ss";
    public static final String FORMAT_DATE_ISO = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String MSG_TYPE_EMAIL = "EMAIL";
    public static final String MSG_TYPE_SMS = "SMS";

    public static final String PATH_SEPARATOR = "/";

    public static final Marker LOG_MARKER_CRITICAL = MarkerFactory.getMarker("CRITICAL");
    public static final Marker LOG_MARKER_FATAL = MarkerFactory.getMarker("FATAL");
    public static final Marker LOG_MARKER_WARN = MarkerFactory.getMarker("WARN");

    public static final String ERR_CODE_SYSTEM_ERROR = "ERR_SYSTEM_ERROR";
    public static final String ERR_CODE_BAD_REQUEST = "ERR_BAD_REQUEST";
    public static final String ERR_CODE_NOT_FOUND = "ERR_NOT_FOUND";
    public static final String ERR_CODE_FILE_NOT_FOUND = "ERR_CODE_FILE_NOT_FOUND";

    public static final String ERR_CODE_FORBIDDEN = "ERR_FORBIDDEN";
    public static final String ERR_CODE_MANDATORY_FIELD = "ERR_MANDATORY_FIELD";
    public static final String ERR_CODE_DATA_IN_TXN = "ERR_DATA_IN_TXN";
    public static final String ERR_CODE_DUPLICATE = "ERR_DUPLICATE";
    public static final String ERR_CODE_BC_SAVE_DOC = "ERR_BC_SAVE_DOC";
    public static final String ERR_CODE_RFID_EXIST = "ERR_RFID_EXIST";
    public static final String ERR_CODE_STATUS_CONFLICT = "ERR_STATUS_CONFLICT";

    public static final long BLE_TAG = 1;
    public static final long BLE_READER = 3;

}
