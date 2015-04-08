package ca.cybermentor.android.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ca.cybermentor.android.app.R;
import ca.cybermentor.android.app.model.view.DrawerItem;

public class DrawerListAdapter extends ArrayAdapter {

    private ArrayList<DrawerItem> drawerItems;

    public DrawerListAdapter(Context context, ArrayList<DrawerItem> drawerItems) {
        super(context, R.layout.drawer_item, drawerItems);
        this.drawerItems = drawerItems;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(R.layout.drawer_item, parent, false);
        }

        TextView recipientName = (TextView) convertView.findViewById(R.id.recipient_name);
        recipientName.setText(drawerItems.get(position).recipient.name);

        return convertView;
    }
}
