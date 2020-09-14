package com.example.project_1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<SiteInfo> mData = null;
    ListView mListView = null;
    Base_Adapter mAdapter = null;

    final static int ACTIVITY_EDIT = 0;
    final static int ACTIVITY_NEW = 1;

    int index;

    static final String mFILENAME = "myInformation.txt";

    Button new_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new_btn = (Button) findViewById(R.id.btn_new);

        display(); // 파일에 있는 데이터

        // 어댑터를 생성하고 데이터 설정
        mAdapter = new Base_Adapter(this, mData);

        // 리스트뷰에 어댑터 설정
        mListView = (ListView) findViewById(R.id.lt_listview);
        mListView.setAdapter(mAdapter);

        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, /*final*/ int position, long id) {
                index = position;

                DialogInterface.OnClickListener mDlgListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0: // 수정하기
                                Intent intent = new Intent(getApplicationContext(), NewEditActivity.class);
                                intent.putExtra("sitename", mData.get(/*position*/ index).site_name);
                                intent.putExtra("url", mData.get(/*position*/ index).url);
                                intent.putExtra("user", mData.get(/*position*/ index).user);
                                startActivityForResult(intent, ACTIVITY_EDIT);
                                break;

                            case 1: // 삭제하기
                                mAdapter.delete(index);

                                deleteFile(mFILENAME); // 파일 삭제

                                FileOutputStream fos = null;
                                BufferedOutputStream bos = null;
                                DataOutputStream dos = null;

                                try {
                                    fos = openFileOutput(mFILENAME, Context.MODE_APPEND);
                                    bos = new BufferedOutputStream(fos);
                                    dos = new DataOutputStream(bos);

                                    for (int i = 0; i < mData.size(); i++) {
                                        dos.writeUTF(mData.get(i).site_name);
                                        dos.writeUTF(mData.get(i).url);
                                        dos.writeUTF(mData.get(i).user);
                                        dos.writeUTF(mData.get(i).pwd);
                                        dos.writeUTF(mData.get(i).pwd_check);
                                    }

                                    dos.flush();
                                }
                                catch (FileNotFoundException fnfe) {
                                    return;
                                }
                                catch (IOException ioe) {   }
                                finally {
                                    try {
                                        if (dos != null) dos.close();
                                        if (bos != null) bos.close();
                                        if (fos != null) fos.close();
                                    }
                                    catch (IOException ioe) {   }
                                }
                                break;

                            case AlertDialog.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("이 사이트에 대해")
                        .setIcon(R.drawable.ic_launcher_foreground)
                        .setItems(new String[] { "수정하기", "삭제하기" }, mDlgListener)
                        .setNegativeButton("취소", mDlgListener)
                        .show();
                return false;
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(parent.getContext(), mData.get(position).pwd, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBClick(View v) {
        switch (v.getId()) {
            case R.id.btn_new: // 새 계정 등록
                Intent intent = new Intent(this, NewEditActivity.class);
                startActivityForResult(intent, ACTIVITY_NEW);
                break;

            case R.id.btn_close: // 종료
                finish();
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case ACTIVITY_NEW: // 새 계정 등록 -> 등록
                if (resultCode == RESULT_OK) {
                    SiteInfo siteInfo = new SiteInfo();

                    siteInfo.site_name = data.getStringExtra("new_sitename");
                    siteInfo.url = data.getStringExtra("new_url");
                    siteInfo.user = data.getStringExtra("new_user");
                    siteInfo.pwd = data.getStringExtra("new_pwd");
                    siteInfo.pwd_check = data.getStringExtra("new_pwd_check");

                    mAdapter.add(mData.size(), siteInfo);

                    // 파일에 새 계정 추가
                    FileOutputStream fos = null;
                    BufferedOutputStream bos = null;
                    DataOutputStream dos = null;

                    try {
                        fos = openFileOutput(mFILENAME, Context.MODE_APPEND);
                        bos = new BufferedOutputStream(fos);
                        dos = new DataOutputStream(bos);

                        dos.writeUTF(siteInfo.site_name);
                        dos.writeUTF(siteInfo.url);
                        dos.writeUTF(siteInfo.user);
                        dos.writeUTF(siteInfo.pwd);
                        dos.writeUTF(siteInfo.pwd_check);

                        dos.flush();
                    }
                    catch (FileNotFoundException fnfe) {
                        return;
                    }
                    catch (IOException ioe) {   }
                    finally {
                        try {
                            if (dos != null) dos.close();
                            if (bos != null) bos.close();
                            if (fos != null) fos.close();
                        }
                        catch (IOException ioe) {   }
                    }
                }
                break;

            case ACTIVITY_EDIT: // 수정하기 -> 등록
                if (resultCode == RESULT_OK) {
                    SiteInfo siteInfo = new SiteInfo();

                    siteInfo.site_name = data.getStringExtra("new_sitename");
                    siteInfo.url = data.getStringExtra("new_url");
                    siteInfo.user = data.getStringExtra("new_user");
                    siteInfo.pwd = data.getStringExtra("new_pwd");
                    siteInfo.pwd_check = data.getStringExtra("new_pwd_check");

                    mAdapter.update(index, siteInfo);

                    deleteFile(mFILENAME); // 파일 삭제

                    FileOutputStream fos = null;
                    BufferedOutputStream bos = null;
                    DataOutputStream dos = null;

                    try {
                        fos = openFileOutput(mFILENAME, Context.MODE_APPEND);
                        bos = new BufferedOutputStream(fos);
                        dos = new DataOutputStream(bos);

                        for (int i = 0; i < mData.size(); i++) {
                            dos.writeUTF(mData.get(i).site_name);
                            dos.writeUTF(mData.get(i).url);
                            dos.writeUTF(mData.get(i).user);
                            dos.writeUTF(mData.get(i).pwd);
                            dos.writeUTF(mData.get(i).pwd_check);
                        }

                        dos.flush();
                    }
                    catch (FileNotFoundException fnfe) {
                        return;
                    }
                    catch (IOException ioe) {   }
                    finally {
                        try {
                            if (dos != null) dos.close();
                            if (bos != null) bos.close();
                            if (fos != null) fos.close();
                        }
                        catch (IOException ioe) {   }
                    }
                }
                break;
        }
    }

    private void display() {
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        DataInputStream dis = null;

        mData = new ArrayList<SiteInfo>();
        int id = 0;

        try {
            fis = openFileInput(mFILENAME);
            bis = new BufferedInputStream(fis);
            dis = new DataInputStream(bis);

            while (dis.available() > 0) {
                String str_site_name = dis.readUTF();
                String str_url = dis.readUTF();
                String str_user = dis.readUTF();
                String str_pwd = dis.readUTF();
                String str_pwd_check = dis.readUTF();

                SiteInfo sif = new SiteInfo();

                sif.site_name = str_site_name;
                sif.url = str_url;
                sif.user = str_user;
                sif.pwd = str_pwd;
                sif.pwd_check = str_pwd_check;

                mData.add(id, sif);

                id = id + 1;
            }
        }
        catch (FileNotFoundException fnfe) {
            return;
        }
        catch (IOException ioe) {   }
        finally {
            try {
                if (dis != null) dis.close();
                if (bis != null) bis.close();
                if (fis != null) fis.close();
            }
            catch (IOException ioe) {   }
        }
    }
}
