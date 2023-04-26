package com.example.quan_li_dien_nuoc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "QLDienNuoc";
    private static final int DB_VERSION = 1;

    private static final String TABLE_PHONG = "phong";
    private static final String MA_DAY = "maDay";
    private static final String MA_PHONG = "maPhong";

    private static final String TABLE_DIEN_NUOC = "dienNuoc";
    private static final String MA_DIEN_NUOC = "maDienNuoc";
    private static final String NGAY_GHI = "ngayGhi";
    private static final String CHI_SO_DIEN = "chiSoDien";
    private static final String CHI_SO_NUOC = "chiSoNuoc";

    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_PHONG + " ("
                + MA_DAY + " TEXT,"
                + MA_PHONG + " TEXT,"
                + "PRIMARY KEY (" + MA_DAY + ", " + MA_PHONG + "))";
        db.execSQL(query);
        String query1 = "CREATE TABLE " + TABLE_DIEN_NUOC + " ("
                + MA_DIEN_NUOC + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + NGAY_GHI + " TEXT,"
                + CHI_SO_DIEN + " TEXT,"
                + CHI_SO_NUOC + " TEXT,"
                + MA_DAY + " TEXT, "
                + MA_PHONG + " TEXT, "
                + "FOREIGN KEY (" + MA_DAY + ", " + MA_PHONG + ") REFERENCES " + TABLE_PHONG + " (" + MA_DAY + ", " + MA_PHONG + ") ON DELETE CASCADE ON UPDATE CASCADE)";
        db.execSQL(query1);
    }
    public boolean themMoiPhong(String maDay, String maPhong) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!kiemTraPhongTonTai(maDay, maPhong)) {
            ContentValues values = new ContentValues();
            values.put(MA_DAY, maDay);
            values.put(MA_PHONG, maPhong);
            db.insert(TABLE_PHONG, null, values);
            db.close();
            return true;
        } else {
            db.close();
            return false;
        }
    }

    public ArrayList<PhongModal> danhSachPhong() {
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor cursorPhong = db.rawQuery("SELECT DISTINCT " + MA_DAY + ", (SELECT GROUP_CONCAT(" + MA_PHONG + ", ', ') FROM " + TABLE_PHONG + " WHERE " + TABLE_PHONG + "." + MA_DAY + " = " + MA_DAY + ") AS maPhong FROM " + TABLE_PHONG, null);
        Cursor cursorPhong = db.rawQuery("SELECT * FROM " + TABLE_PHONG + " ORDER BY " + MA_DAY + ", " + MA_PHONG + " ASC;", null);
        ArrayList<PhongModal> phongModalArrayList = new ArrayList<>();
        if (cursorPhong.moveToFirst()) {
            do {
                phongModalArrayList.add(new PhongModal(cursorPhong.getString(0),
                        cursorPhong.getString(1)));
            } while (cursorPhong.moveToNext());
        }
        cursorPhong.close();
        return phongModalArrayList;
    }
    public List<String> layDSDay() {
        List<String> day = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT DISTINCT " + MA_DAY + " FROM " + TABLE_PHONG, null);
        if (cursor.moveToFirst()) {
            do {
                String maDay = cursor.getString(cursor.getColumnIndex(MA_DAY));
                day.add(maDay);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return day;
    }
    public List<String> layDSPhong(String maDay) {
        List<String> phong = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + MA_PHONG + " FROM " + TABLE_PHONG + " WHERE " + TABLE_PHONG + "." + MA_DAY + " = ?", new String[] {maDay});
        if (cursor.moveToFirst()) {
            do {
                String maPhong = cursor.getString(cursor.getColumnIndex(MA_PHONG));
                phong.add(maPhong);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return phong;
    }
    public void xoaPhong(String maDay, String maPhong) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PHONG, MA_DAY + "=? AND " + MA_PHONG + "=?", new String[]{maDay,maPhong});
        db.close();
    }
    public boolean kiemTraPhongTonTai(String maDay, String maPhong) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_PHONG + " WHERE " + MA_DAY + " = ? AND " + MA_PHONG + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{maDay, maPhong});
        boolean exists = cursor.moveToFirst();
        cursor.close();
        return exists;
    }
    public boolean suaPhong(String maDayCu, String maPhongCu, String maDayMoi, String maPhongMoi) {
        SQLiteDatabase db = this.getWritableDatabase();
        if (!kiemTraPhongTonTai(maDayMoi, maPhongMoi)) {
            ContentValues values = new ContentValues();
            values.put(MA_DAY, maDayMoi);
            values.put(MA_PHONG, maPhongMoi);
            int rowsUpdated = db.update(TABLE_PHONG, values, MA_DAY + " = ? AND " + MA_PHONG + " = ?", new String[]{maDayCu, maPhongCu});
            db.close();
            return rowsUpdated > 0;
        } else {
            db.close();
            return false;
        }
    }

    public void themMoiDienNuoc(String ngayGhi, String chiSoDien, String chiSoNuoc, String maDay, String maPhong) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NGAY_GHI, ngayGhi);
        values.put(CHI_SO_DIEN, chiSoDien);
        values.put(CHI_SO_NUOC, chiSoNuoc);
        values.put(MA_DAY, maDay);
        values.put(MA_PHONG, maPhong);
        db.insert(TABLE_DIEN_NUOC, null, values);
        db.close();
    }
    public ArrayList<DienNuocModel> danhSachDienNuoc(String maDay, String maPhong) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_DIEN_NUOC + " WHERE " + MA_DAY + " = ? AND " + MA_PHONG + " = ? ORDER BY " + NGAY_GHI + " DESC;", new String[] {maDay, maPhong});
        ArrayList<DienNuocModel> dienNuocModalArrayList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                dienNuocModalArrayList.add(new DienNuocModel(cursor.getString(4),
                        cursor.getString(5),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getInt(0)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return dienNuocModalArrayList;
    }
    public void suaDienNuoc(String ngayGhi, String maDay, String maPhong, String chiSoDienMoi, String chiSoNuocMoi) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(CHI_SO_DIEN, chiSoDienMoi);
        values.put(CHI_SO_NUOC, chiSoNuocMoi);
        int rowsUpdated = db.update(TABLE_DIEN_NUOC, values, MA_DAY + " = ? AND " + MA_PHONG + " = ? AND " + NGAY_GHI + " = ?", new String[]{maDay, maPhong, ngayGhi});
        db.close();
    }
    public void xoaDienNuoc(Integer maDienNuoc) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_DIEN_NUOC, MA_DIEN_NUOC + "=?", new String[]{String.valueOf(maDienNuoc)});
        db.close();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PHONG);
        onCreate(db);
    }
}
