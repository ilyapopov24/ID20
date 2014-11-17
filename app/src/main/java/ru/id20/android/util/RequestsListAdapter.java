package ru.id20.android.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import ru.id20.android.R;

/**
 * Created by Dre on 29.09.2014.
 */
public class RequestsListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> items;
    private LayoutInflater inflater;

    public RequestsListAdapter(Context context, ArrayList<String> items) {
        this.context = context;
        this.items = items;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        view = inflater.inflate(R.layout.item_request_list, parent, false);

        //String item = items.get(position);
        //((TextView) view.findViewById(R.id.calculatorItemTextView)).setText(item);

        return view;
    }
}
