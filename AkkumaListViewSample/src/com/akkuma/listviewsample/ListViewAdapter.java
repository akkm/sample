package com.akkuma.listviewsample;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListViewAdapter extends ArrayAdapter<ListViewData> {

    public ListViewAdapter(Context context, int resource, int textViewResourceId, List<ListViewData> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    /**
     * ListView１列分のビューを作るメソッド
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        // position番目のデータを取得する
        ListViewData data = getItem(position);

        // 1番目の文字列をTextViewにセットする
        TextView textView1 = (TextView) view.findViewById(R.id.textView1);
        textView1.setText(data.first);

        // 2番目の文字列をTextViewにセットする
        TextView textView2 = (TextView) view.findViewById(R.id.textView2);
        textView2.setText(data.second);

        return view;
    }
}
