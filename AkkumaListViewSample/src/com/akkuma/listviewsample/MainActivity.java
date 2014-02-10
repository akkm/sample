package com.akkuma.listviewsample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
    
    ArrayList<ListViewData> list = new ArrayList<ListViewData>();
    ListViewAdapter adapter;
    
    View.OnClickListener buttonOnClickListener = new View.OnClickListener() {
        
        /**
         * ボタンをおした時に呼ばれるメソッド
         */
        @Override
        public void onClick(View v) {
            // 新しいデータを作成
            ListViewData data = new ListViewData();

            // editText1の文字列を新しいデータ内にコピーする
            EditText editText1 = (EditText) findViewById(R.id.editText1);
            data.first = editText1.getText().toString();
            
            // editText2の文字列を新しいデータ内にコピーする
            EditText editText2 = (EditText) findViewById(R.id.editText2);
            data.second = editText2.getText().toString();
            
            // リストに新しいデータを追加する
            list.add(data);
            
            // アダプターにリストの変化を伝える
            adapter.notifyDataSetChanged();
        }
    };

    /**
     * 画面起動時に呼ばれるメソッド
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // ファイルからリストを読み込むメソッドを呼ぶ
        read();

        // リストビューにアダプターをセットする
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        adapter = new ListViewAdapter(this, R.layout.list_item, R.id.textView1, list);
        listView1.setAdapter(adapter);
        
        // ボタンにリスナーをセットする
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(buttonOnClickListener);
        
        
    }
    
    /**
     * 画面終了時に呼ばれるメソッド
     */
    @Override
    protected void onDestroy() {
        write();
        super.onDestroy();
    }

    /**
     * ファイルにリストを保存する
     */
    public void write() {

        ObjectOutputStream objectOut = null;
        try {

            // DATAというファイルで保存する
            FileOutputStream fileOut = openFileOutput("DATA", Activity.MODE_PRIVATE);
            objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(list);
            fileOut.getFD().sync();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (objectOut != null) {
                try {
                    objectOut.close();
                } catch (IOException e) {}
            }
        }
    }

    /**
     * ファイルからリストを読み込む
     */
    public void read() {

        ObjectInputStream objectIn = null;
        try {

            // DATAというファイルを開く
            FileInputStream fileIn = openFileInput("DATA");
            objectIn = new ObjectInputStream(fileIn);
            list = (ArrayList<ListViewData>) objectIn.readObject();

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (objectIn != null) {
                try {
                    objectIn.close();
                } catch (IOException e) {}
            }
        }

    }
}
