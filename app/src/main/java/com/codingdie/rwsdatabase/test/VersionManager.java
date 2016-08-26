package com.codingdie.rwsdatabase.test;

import com.codingdie.rwsdatabase.connection.WritableConnection;

/**
 * Created by xupen on 2016/8/26.
 */
public class VersionManager {
    public void  createDatabase(WritableConnection db){
        db.execWriteSQL("DROP TABLE IF EXISTS `Class`");
        db.execWriteSQL("CREATE TABLE `Class` ( `classId`  INTEGER PRIMARY KEY  ,`className`  TEXT)");
    }
    public void  createDatabaseFromVersion2(WritableConnection db){
        db.execWriteSQL("DROP TABLE IF EXISTS `Class`");
        db.execWriteSQL("CREATE TABLE `Class` ( `classId`  INTEGER PRIMARY KEY  ,`className`  TEXT)");
        db.execWriteSQL("DROP TABLE IF EXISTS `Student`");
        db.execWriteSQL("CREATE TABLE `Student` ( `classId`  INTEGER PRIMARY KEY  ,`studentId`  INTEGER ,`studentName`  TEXT)");
        db.execWriteSQL("DROP TABLE IF EXISTS `Teacher`");
        db.execWriteSQL("CREATE TABLE `Teacher` ( `classId`  INTEGER PRIMARY KEY  ,`teacherId`  INTEGER ,`teacherName`  TEXT)");
    }

    public void  version1ToVersion2(WritableConnection db){
        db.execWriteSQL("DROP TABLE IF EXISTS `Student`");
        db.execWriteSQL("CREATE TABLE `Student` ( `classId`  INTEGER PRIMARY KEY  ,`studentId`  INTEGER ,`studentName`  TEXT)");
        db.execWriteSQL("DROP TABLE IF EXISTS `Teacher`");
        db.execWriteSQL("CREATE TABLE `Teacher` ( `classId`  INTEGER PRIMARY KEY  ,`teacherId`  INTEGER ,`teacherName`  TEXT)");

    }
}


