package app.mvp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.mvp.R;

public class UserProfileAdapter extends BaseAdapter {

    private Context context;
    private List<KeyItem> list;

    public class KeyItem {
        String key;
        String value;
    }

    public UserProfileAdapter(Context context){
        this.context = context;
        list = new ArrayList<>();
    }

    public void addItem(String key, String value){
        KeyItem item = new KeyItem();
        item.key = key;
        item.value = value;
        list.add(item);
    }

    public void clean(){
        list.clear();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = (convertView == null)
                ? LayoutInflater.from(context).inflate(R.layout.list_row_profile, null)
                : convertView;
        ((TextView)convertView.findViewById(R.id.textView_key)).setText(list.get(position).key);
        ((TextView)convertView.findViewById(R.id.textView_value)).setText(list.get(position).value);
        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public KeyItem getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
