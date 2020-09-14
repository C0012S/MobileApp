package com.example.fileiotest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_phone;
    private TextView tv_display;

    //private SharedPreferences mPref;
    private ContactsDBHelper mHelper;

    //static final String mFILENAME ="myContacts.txt"; //내부, 외부 저장소 입출력 파일
    //static final String mFILENAME = "myContatcts"; //프레퍼렌스로 입출력되는 파일
    static final String mFILENAME = "myContacts.db"; //SQLite

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main); //내부, 외부 저장소, 프레퍼렌스, sqlite
        setContentView(R.layout.sqlite_test); //sqlite ListView

        et_name = (EditText) findViewById(R.id.et_name);
        et_phone = (EditText) findViewById(R.id.et_phone);
        tv_display = (TextView) findViewById(R.id.tv_display);

        //mPref = getSharedPreferences(mFILENAME, Context.MODE_PRIVATE); //프레퍼렌스
        mHelper = new ContactsDBHelper(this, mFILENAME, null, 2); //1); //DBHelper 객체 생성 //표준 커서를 사용할 것이면 null을 전달 //자바 버전은 1
        //2 버전으로 바꿔서 drop table 한 다음, 다시 create table 하는 형태가 된다 //이후 create table 하는 과정을 통해서 한 100 여개의 dummy data들을 만들어 내서 데이터베이스에 추가해 놓은 상태 //이를 단순하게 CursorAdapter를 통해서 접근 가능

        //displayContacts(); //프로그램을 제일 처음에 수행했다면, 이전에 저장되었던 내용을 같이 출력할 수 있도록 하기 위해서 displayContacts 메소드 호출 //내부, 외부 저장소, 프레퍼렌스, sqlite
        mInitListView(); //sqlite ListView
    }

    private void mInitListView() {
        SQLiteDatabase db = mHelper.getReadableDatabase(); //Helper 객체로부터 읽어낼 수 있는 데이터베이스 객체를 얻어낸다

        String sql = "select * from mycontacts"; //데이터베이스 객체를 기반으로, select 구문을 수행해서 mycontacts 테이블 안에 있는 모든 레코드들을 읽어낸다
        Cursor cursor = db.rawQuery(sql, null);

//        startManagingCursor(cursor);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, //그 내용을 SimpleCursorAdapter 객체와 같이 연결시켜서 이름과 전화번호를 출력할 수 있도록 구성
                android.R.layout.simple_list_item_2,
                cursor,
                new String[] {"name", "phone"},
                new int[] { android.R.id.text1, android.R.id.text2 },
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER
        );

        ListView list = (ListView) findViewById(R.id.lv_display); //구성된 SimpleCurosorAdapter를 전개된 ListView 객체의 Adapter 객체로 설정해 주면 동작된다
        list.setAdapter(adapter);
    }

    public void mOnInsertClick(View v) {
/*      내부 저장소 파일 입출력
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;

        try {
            fos = openFileOutput(mFILENAME, Context.MODE_APPEND);
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);

            String name = et_name.getText().toString();
            String phone = et_phone.getText().toString();

            dos.writeUTF(name);
            dos.writeUTF(phone);

            dos.flush();
        }
        catch (FileNotFoundException fnfe) {
            return;
        }
        catch (IOException ioe) {

        }
        finally {
            try {
                if (dos != null) dos.close();
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            }
            catch (IOException ioe) {   }
        }

        displayContacts();

        et_name.setText("");
        et_phone.setText("");
*/
/*
        //외부 저장소 파일 입출력
        if (!isExternalStorageWirtable())
            return;     // 외부메모리를 사용하지 못하면 끝냄

        requestPermission(); //잘 마운트가 되어 있다면 //적절한 접근 권한 요청

        // 공용
        //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //File f = new File(path, mFILENAME); // 경로, 파일명

        // 전용
        File f = new File(getExternalFilesDir(null), mFILENAME);

        FileOutputStream fos = null;
        BufferedOutputStream bos = null;
        DataOutputStream dos = null;

        try {
            fos = new FileOutputStream(f, true); //append 모드로 FileOutputStream 객체를 생성
            bos = new BufferedOutputStream(fos);
            dos = new DataOutputStream(bos);

            String name = et_name.getText().toString();
            String phone = et_phone.getText().toString();

            dos.writeUTF(name);
            dos.writeUTF(phone);

            dos.flush();
        }
        catch (FileNotFoundException fnfe) {
            return;
        }
        catch (IOException ioe) {

        }
        finally {
            try {
                if (dos != null) dos.close();
                if (bos != null) bos.close();
                if (fos != null) fos.close();
            }
            catch (IOException ioe) {   }
        }

        displayContacts();

        et_name.setText("");
        et_phone.setText("");
*/
/*
        //프레퍼렌스 입출력
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();

        SharedPreferences.Editor editor = mPref.edit();

        int maxId = mPref.getInt("maxId", 0) + 1;

        editor.putString("name" + maxId, name);
        editor.putString("phone" + maxId, phone);
        editor.putInt("maxId", maxId);
        editor.commit();

        displayContacts();

        et_name.setText("");
        et_phone.setText("");
*/
/*
        //SQLite
        String name = et_name.getText().toString();
        String phone = et_phone.getText().toString();

        String sql = "";
        sql += "insert into mycontacts values ( ";
        sql += "null" + ", ";
        sql += "'" + name + "', ";
        sql += "'" + phone + "'";
        sql += " )";

        Log.i("MainActivity", sql);

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL(sql);

        displayContacts();

        et_name.setText("");
        et_phone.setText("");
*/
    }

    public void mOnDeleteClick(View v) { //파일 자체를 삭제하도록 구성 //원래는 특정 항목 삭제
/*      내부 저장소에서 파일 삭제
        if (deleteFile(mFILENAME)) //deleteFile()은 내부 메모리에 지정된 경로 상에서 이 이름의 파일을 삭제하는 일을 수행
            tv_display.setText("delete success");
        else
            tv_display.setText("delete failed");
*/
/*
        // 공용 //외부 저장소에서 파일 삭제
        //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //File f = new File(path, mFILENAME); // 경로, 파일명

        // 전용
        File f = new File(getExternalFilesDir(null), mFILENAME);

        if (f.delete())
            tv_display.setText("delete success");
        else
            tv_display.setText("delete failed");
*/
/*
        //프레퍼렌스 입출력
        String target = et_name.getText().toString();
        int maxId = mPref.getInt("maxId", 0);

        for (int i = 1; i <= maxId; i++) {
            String name = mPref.getString("name" + i, "");
            if (name.equals(target)) {
                SharedPreferences.Editor editor = mPref.edit();
                editor.putString("phone" + i, "");
                editor.commit();
            }
        }

        displayContacts();

        et_name.setText("");
        et_phone.setText("");
*/
/*
        //SQLite
        String target = et_name.getText().toString();

        String sql = "delete from mycontacts where name=" + "'" + target + "'";
        Log.i("MainActivity", sql);

        SQLiteDatabase db = mHelper.getWritableDatabase();
        db.execSQL(sql);

        displayContacts();

        et_name.setText("");
        et_phone.setText("");
*/
    }

    public void mOnUpdateClick(View v) { //수정 //특정 항목만 수정하는 것을 현재 과정에서는 파일로부터 데이터를 모두 읽어서 TextView에 설정 //TextView에 있는 값들을 찾아 수정하고, 그 내용을 다시 파일에 저장해 주는 처리를 해 주면 된다

    }

    private void displayContacts() { //현재 액티비티 내부에서만 사용할 것이라 private 도메인에 두었다
/*      내부 저장소 파일 입출력
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        try {
            fis = openFileInput(mFILENAME);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            String str = "";
            while (dis.available() > 0) {
                String name = dis.readUTF();
                String phone = dis.readUTF();

                str += name + " | " + phone + "\n";
            }
            tv_display.setText(str);
        }
        catch (FileNotFoundException fnfe) {
            return;
        }
        catch (IOException ioe) {

        }
        finally {
            try {
                if (dis != null) dis.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            }
            catch (IOException ioe) {   }
        }
*/
/*
        //외부 저장소 파일 입출력
        if (!isExternalStorageWirtable())
            return;     // 외부메모리를 사용하지 못하면 끝냄

        requestPermission();

        // 공용 //외부 저장소에서 파일 입출력?
        //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        //File f = new File(path, mFILENAME); // 경로, 파일명

        //외부 저장소에 저장되긴 하나 내부 저장소에 파일을 저장한다고 하면?
        // 전용
        File f = new File(getExternalFilesDir(null), mFILENAME); //2 개의 작업을 하나의 문장에 처리

        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        try {
            fis = new FileInputStream(f);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            String str = "";
            while (dis.available() > 0) {
                String name = dis.readUTF();
                String phone = dis.readUTF();

                str += name + " | " + phone + "\n";
            }
            tv_display.setText(str);
        }
        catch (FileNotFoundException fnfe) {
            return;
        }
        catch (IOException ioe) {

        }
        finally {
            try {
                if (dis != null) dis.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            }
            catch (IOException ioe) {   }
        }
*/
/*
        //프레퍼렌스 입출력
        int maxId = mPref.getInt("maxId", 0);

        String str = "";
        for (int i = 1; i <= maxId; i++) {
            String name = mPref.getString("name" + i, "");
            if (name.length() == 0) continue;

            String phone = mPref.getString("phone" + i, "");
            if (phone.length() == 0) continue;

            str += "" + i + " | ";
            str += name + " | ";
            str += phone + "\n";
        }

        tv_display.setText(str);
*/
/*
        //SQLite
        SQLiteDatabase db = mHelper.getReadableDatabase();

        String sql = "select * from mycontacts";
        Cursor cursor = db.rawQuery(sql, null);

        String str = "";
        while (cursor.moveToNext()) {
            String id = cursor.getString(0);
            String name = cursor.getString(1);
            String phone = cursor.getString(2);

            str += id + " | " + name + " | " + phone + "\n";
        }

        tv_display.setText(str);
*/
    }

    public boolean isExternalStorageWirtable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            Log.i("MainActivity", "외부메모리 읽기 쓰기 모두 가능");
            return true;
        }
        return false;
    }

    public void requestPermission() { //Permission이 제대로 할당되어 있는지 확인 //할당되어 있지 않으면 ~ 접근 권한이 필요하다는 대화상자를 통해서 필요한 접근 권한을 할당받도록 구성
        final int REQUEST_EXTERNAL_STORAGE = 1;
        String[] PERMISSIONS_STORAGE = {
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int permission = ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}
