package com.example.lastproject

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(var context:Context) : SQLiteOpenHelper(context, "memberDB", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var tableCreSql = "create table memberTBL (memId varchar(20) primary key, password varchar(20), name varchar(20), phone varchar(20), email varchar(20) );"
        db!!.execSQL(tableCreSql)
        db.execSQL("insert into memberTBL(memId, password, name, phone, email) values('admin01', 'admin01', '정지운', '010-1234-1234', 'admin@gmail.com'); ")
    }

    fun getLogin(memId : String, password : String) : Boolean {
        var db : SQLiteDatabase = readableDatabase
        var result: String = ""

        var cursor : Cursor = db.rawQuery("select memId, password from memberTBL", null)
        while (cursor.moveToNext()){
            result = (cursor.getString(0))
            if (result.equals(memId)) {
                if(cursor.getString(1).equals(password)){
                    return true
                    break
                }
                else {
                    return false
                }
            }
            else {
            }
        }
        return false
    }

    fun getId(memId: String) : Boolean {
        var db : SQLiteDatabase = readableDatabase
        var result: String = ""

        var cursor : Cursor = db.rawQuery("select memId from memberTBL", null)
        while (cursor.moveToNext()){
            result = (cursor.getString(0))
            if (result.equals(memId)) {
                var idt = cursor.getString(0)
                return true
                break
            }
            else {
            }
        }
        return false
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db!!.execSQL("drop table if exists memberTBL")
        onCreate(db)
    }
}