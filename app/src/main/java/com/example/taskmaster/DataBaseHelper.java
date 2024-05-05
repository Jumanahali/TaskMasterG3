package com.example.taskmaster;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.SQLException;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String CLIENT_TABLE = "CLIENT_Table";
    public static final String COLUMN_CLIENT_NAME = "CLIENT_NAME";
    public static final String COLUMN_CLIENT_ID = "CLIENT_ID";
    public static final String COLUMN_CLIENT_EMAIL = "ClIENT_EMAIL";
    public static final String COLUMN_CLIENT_PASSWORD = "CLIENT_PASSWORD";

    //service table
    public static final String SERVICE_TABLE = "SERVICE_Table";
    public static final String COLUMN_CATEGORY_NAME = "CATEGORY_NAME";
    public static final String COLUMN_SERVICE_ID = "SERVICE_ID";
    public static final String COLUMN_SUBCATEGORY_NAME = "SUBCATEGORY_NAME";
    public static final String COLUMN_PRICE = "PRICE";
    public static final String COLUMN_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_IMAGE = "IMAGE";

    //order table
    public static final String ORDER_TABLE = "ORDER_Table";
    public static final String COLUMN_ORDER_ID = "ORDER_ID";
    //service id and client id as foreign key
    public static final String COLUMN_LOCATION = "LOCATION";
    public static final String COLUMN_RATE = "RATE";
    public static final String COLUMN_TIME = "TIME";
    public static final String COLUMN_WORKER_NAME = "WORKER_NAME";
    public static final String COLUMN_ORDER_STATUS = "ORDER_STATUS";
    public static final String COLUMN_WORKER_PHONE = "WORKER_PHONE";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "TaskMaster.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "Create TABLE " + CLIENT_TABLE + " (" + COLUMN_CLIENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CLIENT_NAME + " TEXT, " + COLUMN_CLIENT_EMAIL + " TEXT, " + COLUMN_CLIENT_PASSWORD + " TEXT )";
        sqLiteDatabase.execSQL(createTableStatement);
        String createTable2Statement = "CREATE TABLE " + SERVICE_TABLE + " (" +
                COLUMN_SERVICE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY_NAME + " TEXT, " +
                COLUMN_SUBCATEGORY_NAME + " TEXT, " +
                COLUMN_PRICE + " INTEGER, " +
                COLUMN_DESCRIPTION + " TEXT, " + COLUMN_IMAGE + " TEXT )";
        sqLiteDatabase.execSQL(createTable2Statement);
        String createTable3Statement = "CREATE TABLE " + ORDER_TABLE + " (" +
                COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_SERVICE_ID + " INTEGER, " +
                COLUMN_CLIENT_ID + " TEXT,  " +
                COLUMN_LOCATION + " TEXT, " +
                COLUMN_RATE + " INTEGER, " +
                COLUMN_TIME + " TEXT, " +
                COLUMN_WORKER_NAME + " TEXT, " +
                COLUMN_WORKER_PHONE + " TEXT, " +
                COLUMN_ORDER_STATUS + " TEXT, " +
                "FOREIGN KEY (" + COLUMN_CLIENT_ID + ") REFERENCES " + CLIENT_TABLE + " ('ClIENT_EMAIL'), " +
                "FOREIGN KEY (" + COLUMN_SERVICE_ID + ") REFERENCES " + SERVICE_TABLE + " ('SERVICE_ID')) ";
        sqLiteDatabase.execSQL(createTable3Statement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public boolean addClient(clientmod clientmod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CLIENT_NAME, clientmod.getcName());
        cv.put(COLUMN_CLIENT_EMAIL, clientmod.getEmail());
        cv.put(COLUMN_CLIENT_PASSWORD, clientmod.getPassword());
        long insert = db.insert(CLIENT_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }
    
        public Boolean checkUsername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + CLIENT_TABLE + " where " + COLUMN_CLIENT_EMAIL + " = ?", new
                String[]{username});
        if (cursor.getCount() > 0) return true;
        return false;
    }


    public boolean addService(servicemod servicemod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATEGORY_NAME, servicemod.getCategoryName());
        cv.put(COLUMN_SUBCATEGORY_NAME, servicemod.getSubcategory());
        cv.put(COLUMN_PRICE, servicemod.getPrice());
        cv.put(COLUMN_DESCRIPTION, servicemod.getDescription());
        cv.put(COLUMN_IMAGE, servicemod.getImageUrl());

        long insert = db.insert(SERVICE_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addOrder(ordermod ordermod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SERVICE_ID, ordermod.getServiceID());
        cv.put(COLUMN_CLIENT_ID, ordermod.getClientID());
        cv.put(COLUMN_LOCATION, ordermod.getLocation());
        cv.put(COLUMN_RATE, ordermod.getRate());
        cv.put(COLUMN_TIME, ordermod.getTime());
        cv.put(COLUMN_WORKER_NAME, ordermod.getWorkerName());
        cv.put(COLUMN_WORKER_PHONE, ordermod.getWorkerPhone());
        cv.put(COLUMN_ORDER_STATUS, ordermod.getOrderStatus());
        long insert = db.insert(ORDER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean DeleteClient(clientmod clientmod) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "Delete From " + CLIENT_TABLE + " WHERE " +
                COLUMN_CLIENT_ID + " = " + clientmod.getClientID();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DeleteService(servicemod servicemod) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "Delete From " + SERVICE_TABLE + " WHERE " +
                COLUMN_SERVICE_ID + " = " + servicemod.getServiceID();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean DeleteOrder(ordermod ordermod) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "Delete From " + ORDER_TABLE + " WHERE " +
                COLUMN_ORDER_ID + " = " + ordermod.getOrderID();
        Cursor cursor = db.rawQuery(queryString, null);
        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }


    public Boolean checkUsernamePassword(String username, String password) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from " + CLIENT_TABLE + " where " + COLUMN_CLIENT_EMAIL + " = ? and " + COLUMN_CLIENT_PASSWORD + " = ?", new String[]{username, password});
        if (cursor.getCount() > 0)
            return true;
        return false;
    }




    @SuppressLint("Range")
    public ordermod getOrderById(int orderId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ordermod order = null;


        String query = "SELECT * FROM " + ORDER_TABLE + " WHERE " + COLUMN_ORDER_ID + " = ?";


        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(orderId)});


        if (cursor != null && cursor.moveToFirst()) {

            order = new ordermod();
            order.setOrderID(cursor.getInt(cursor.getColumnIndex(COLUMN_ORDER_ID)));
            order.setServiceID(cursor.getInt(cursor.getColumnIndex(COLUMN_SERVICE_ID)));
            order.setClientID(cursor.getString(cursor.getColumnIndex(COLUMN_CLIENT_ID)));
            order.setLocation(cursor.getString(cursor.getColumnIndex(COLUMN_LOCATION)));
            order.setRate(cursor.getInt(cursor.getColumnIndex(COLUMN_RATE)));
            order.setTime(cursor.getString(cursor.getColumnIndex(COLUMN_TIME)));
            order.setWorkerName(cursor.getString(cursor.getColumnIndex(COLUMN_WORKER_NAME)));
            order.setWorkerPhone(cursor.getString(cursor.getColumnIndex(COLUMN_WORKER_PHONE)));
            order.setOrderStatus(cursor.getString(cursor.getColumnIndex(COLUMN_ORDER_STATUS)));
        }


        if (cursor != null) {
            cursor.close();
        }
        db.close();

        return order;
    }


    @SuppressLint("Range")
    public servicemod getserviceById(int serviceId) {
        SQLiteDatabase db = this.getReadableDatabase();
        servicemod service = null;

        Cursor cursor = db.query(SERVICE_TABLE, null, COLUMN_SERVICE_ID + " = ?",
                new String[]{String.valueOf(serviceId)}, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {

            service = new servicemod();
            service.setServiceID(cursor.getInt(cursor.getColumnIndex(COLUMN_SERVICE_ID)));
            service.setCategoryName(cursor.getString(cursor.getColumnIndex(COLUMN_CATEGORY_NAME)));
            service.setSubcategory(cursor.getString(cursor.getColumnIndex(COLUMN_SUBCATEGORY_NAME)));
            service.setPrice(cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE)));
            service.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_DESCRIPTION)));
            service.setImageUrl(cursor.getString(cursor.getColumnIndex(COLUMN_IMAGE)));

            cursor.close();
        }

        db.close();

        return service;
    }

    public boolean updateOrder(ordermod ordermod) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_SERVICE_ID, ordermod.getServiceID());
        cv.put(COLUMN_CLIENT_ID, ordermod.getClientID());
        cv.put(COLUMN_LOCATION, ordermod.getLocation());
        cv.put(COLUMN_RATE, ordermod.getRate());
        cv.put(COLUMN_TIME, ordermod.getTime());
        cv.put(COLUMN_WORKER_NAME, ordermod.getWorkerName());
        cv.put(COLUMN_WORKER_PHONE, ordermod.getWorkerPhone());
        cv.put(COLUMN_ORDER_STATUS, ordermod.getOrderStatus());


        String whereClause = COLUMN_ORDER_ID + " = ?";
        String[] whereArgs = {String.valueOf(ordermod.getOrderID())};


        int updatedRows = db.update(ORDER_TABLE, cv, whereClause, whereArgs);

        return updatedRows > 0;
    }

    public boolean deleteOrderById(int orderId) {
        SQLiteDatabase db = this.getWritableDatabase();


        String whereClause = COLUMN_ORDER_ID + " = ?";
        String[] whereArgs = {String.valueOf(orderId)};


        int deletedRows = db.delete(ORDER_TABLE, whereClause, whereArgs);


        db.close();
        return deletedRows > 0;
    }


    public synchronized void cancelOrder(int orderId) {
        SQLiteDatabase db = null;
        try {
            db = this.getWritableDatabase();
            db.beginTransaction();

            String queryString = "UPDATE " + ORDER_TABLE +
                    " SET " + COLUMN_ORDER_STATUS + " = 'cancelled'" +
                    " WHERE " + COLUMN_ORDER_ID + " = " + orderId;
            db.execSQL(queryString);

            db.setTransactionSuccessful();
        } catch (RuntimeException e) {

            e.printStackTrace();
        } finally {
            if (db != null) {
                db.endTransaction();
                db.close();
            }
        }
    }

    }

