package example.yuekaolianxi8_24.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import example.yuekaolianxi8_24.DataLX;

/**
 * Created by 段昱 on 2017/8/24.
 */

public class dao {
    private SQLiteDatabase db;

    public dao(Context context) {
        MyHelper helper = new MyHelper(context);
        db = helper.getWritableDatabase();
    }

    public void insert(String title, String image) {
        ContentValues values = new ContentValues();
        values.put("tilte", title);
        values.put("image", image);
    }

    public List<DataLX> findAll() {

        Cursor cursor = db.query("lx", null, null, null, null, null, null);

        //StringBuffer sb = new StringBuffer();

        List<DataLX> persons = new ArrayList<DataLX>();

        while (cursor.moveToNext()) {

            String name = cursor.getString(cursor.getColumnIndex("title"));
            String phone = cursor.getString(cursor.getColumnIndex("image"));

            //创建对象
            DataLX dataLX=new DataLX();
            dataLX.setName(name);
            dataLX.setImge(phone);
            //sb.append("姓名 : "+name+"   电话 : "+phone+"\n");
            //添加到集合
            persons.add(dataLX);


        }


        return persons;


    }
}