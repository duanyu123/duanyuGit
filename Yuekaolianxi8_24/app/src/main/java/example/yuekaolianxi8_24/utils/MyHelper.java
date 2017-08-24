package example.yuekaolianxi8_24.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 段昱 on 2017/8/24.
 */

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context) {
        super(context,"user.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
db.execSQL("create table lx(id Integer primary key autoincrement,title varchar(20),image varchar(20) )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
