package com.hangover.ashqures.hangover.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by ashqures on 8/22/16.
 */
public class DBResourceCleanup {

    protected void dbResourceCleanup(Object... resources){
        for(Object resource :  resources){
            if(null != resource && resource instanceof Cursor){
                Cursor cursor = (Cursor)resource;
                if(!cursor.isClosed())
                    cursor.close();
            }else if(null!= resource && resource instanceof SQLiteDatabase){
                SQLiteDatabase db = (SQLiteDatabase)resource;
                if(db.isOpen())
                    db.close();
            }
        }
    }
}
