package com.example.rami.picturetester;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ThingListAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Thing> thingsList;

    public ThingListAdapter(Context context, int layout, ArrayList<Thing> thingsList) {
        this.context = context;
        this.layout = layout;
        this.thingsList = thingsList;
    }

    @Override
    public int getCount() {
        return thingsList.size();
    }

    @Override
    public Object getItem(int i) {
        return thingsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtName;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View row = view;
        ViewHolder holder = new ViewHolder();
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.imageView = (ImageView) row.findViewById(R.id.imgThing);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Thing thing = thingsList.get(i);
        holder.txtName.setText(thing.getName());
        byte[] thingImage = thing.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(thingImage, 0, thingImage.length);
        holder.imageView.setImageBitmap(bitmap);
        return row;
    }
}
