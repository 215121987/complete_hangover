package com.hangover.ashqures.hangover.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.hangover.ashqures.hangover.entity.CartItemEntity;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * Created by ashqures on 8/21/16.
 */
public class CartItemDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "hangover.db";
    public static final String TABLE_NAME = "cart_item";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_ITEM_ID = "item_id";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_ITEM_DETAIL_ID = "item_detail_id";
    public static final String COLUMN_ITEM_SIZE = "item_size";
    public static final String COLUMN_ITEM_QUANTITY = "item_quantity";
    public static final String COLUMN_ITEM_PRICE = "item_price";
    public static final String COLUMN_ITEM_IMAGE = "item_image_url";

    private DBResourceCleanup dbResourceCleanup;

    @Inject
    public CartItemDBHelper(Context context){
        super(context, DATABASE_NAME , null, 1);
        dbResourceCleanup = new DBResourceCleanup();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL(
                "create table "+TABLE_NAME +
                        "(id integer primary key, item_id integer, item_name text, item_image_url text, item_detail_id integer, item_size text, item_quantity integer, item_price real, cart_id integer)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    public boolean insert(CartItemEntity cart){
        SQLiteDatabase db = null;
        try{
            db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(COLUMN_ITEM_ID, cart.getId());
            contentValues.put(COLUMN_ITEM_NAME, cart.getItemName());
            contentValues.put(COLUMN_ITEM_DETAIL_ID, cart.getItemDetailId());
            contentValues.put(COLUMN_ITEM_SIZE, cart.getItemSize());
            contentValues.put(COLUMN_ITEM_QUANTITY, cart.getItemQuantity());
            contentValues.put(COLUMN_ITEM_PRICE, cart.getItemPrice());
            contentValues.put(COLUMN_ITEM_IMAGE, cart.getImageUrl());
            db.insert(TABLE_NAME, null, contentValues);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //dbResourceCleanup.dbResourceCleanup(db);
        }
        return true;
    }

    public CartItemEntity getData(int id){
        SQLiteDatabase db = null;
        Cursor res = null;
        CartItemEntity cart = null;
        try{
            db = this.getReadableDatabase();
            res =  db.rawQuery( "select * from "+TABLE_NAME+" where "+COLUMN_ID+"="+id+"", null );
            res.moveToFirst();
            cart = new CartItemEntity();
            cart.setId(res.getLong(res.getColumnIndex(COLUMN_ID)));
            cart.setItemId(res.getLong(res.getColumnIndex(COLUMN_ITEM_ID)));
            cart.setItemName(res.getString(res.getColumnIndex(COLUMN_ITEM_NAME)));
            cart.setImageUrl(res.getString(res.getColumnIndex(COLUMN_ITEM_IMAGE)));
            cart.setItemDetailId(res.getLong(res.getColumnIndex(COLUMN_ITEM_DETAIL_ID)));
            cart.setItemSize(res.getString(res.getColumnIndex(COLUMN_ITEM_SIZE)));
            cart.setItemQuantity(res.getInt(res.getColumnIndex(COLUMN_ITEM_QUANTITY)));
            cart.setItemId(res.getLong(res.getColumnIndex(COLUMN_ITEM_ID)));
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbResourceCleanup.dbResourceCleanup(res);
        }
        return cart;
    }

    public int numberOfRows(){
        SQLiteDatabase db=null;
        int numRows = 0;
        try{
            db = this.getReadableDatabase();
            numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbResourceCleanup.dbResourceCleanup(db);
        }
        return numRows;
    }

    public boolean update(CartItemEntity cart){
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ITEM_ID, cart.getId());
        contentValues.put(COLUMN_ITEM_NAME, cart.getItemName());
        contentValues.put(COLUMN_ITEM_DETAIL_ID, cart.getItemDetailId());
        contentValues.put(COLUMN_ITEM_SIZE, cart.getItemSize());
        contentValues.put(COLUMN_ITEM_QUANTITY, cart.getItemQuantity());
        contentValues.put(COLUMN_ITEM_PRICE, cart.getItemPrice());
        contentValues.put(COLUMN_ITEM_IMAGE, cart.getImageUrl());
        SQLiteDatabase db = null;
        Integer result = null;
        try{
            db = this.getWritableDatabase();
            result =  db.update(TABLE_NAME, contentValues, COLUMN_ID+" = ? ", new String[] { Long.toString(cart.getId()) } );
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbResourceCleanup.dbResourceCleanup(db);
        }
        return null!=result && result ==0;
    }

    public Integer delete(Integer id){
        SQLiteDatabase db = null;
        Integer result = null;
        try{
            db = this.getWritableDatabase();
           result = db.delete(TABLE_NAME,
                    COLUMN_ID+" = ? ",
                    new String[] { Integer.toString(id) });
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbResourceCleanup.dbResourceCleanup(db);
        }
        return result;
    }

    public ArrayList<String> getAll(){
        ArrayList<String> array_list = new ArrayList<String>();
        SQLiteDatabase db =null;
        Cursor res = null;
        try{
            db = this.getReadableDatabase();
            res =  db.rawQuery( "select * from "+ TABLE_NAME, null );
            res.moveToFirst();
            while(!res.isAfterLast()){
                array_list.add(res.getString(res.getColumnIndex(COLUMN_ITEM_ID)));
                res.moveToNext();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            dbResourceCleanup.dbResourceCleanup(res, db);
        }
        return array_list;
    }
}
