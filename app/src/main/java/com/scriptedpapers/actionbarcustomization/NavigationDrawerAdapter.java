package com.scriptedpapers.actionbarcustomization;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by mahes on 27/1/15.
 */
public class NavigationDrawerAdapter extends ArrayAdapter<String> {

    public static String[] screenTitles = { "Screen 1", "Screen 1",
            "Screen 1", "Screen 1", "Screen 1" };
    private Activity activityObject;

    public NavigationDrawerAdapter(Activity activityObject) {
        super(activityObject, R.layout.navigation_drawer_list_item, screenTitles);
        this.activityObject = activityObject;
    }

    class ViewHolder {
        TextView menuName;
        ImageView menuImage;

        LinearLayout buttonLayout;

        ImageView img_banner;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (convertView == null) {
            LayoutInflater inflator = activityObject.getLayoutInflater();
            view = inflator.inflate(R.layout.navigation_drawer_list_item, null);
        } else
            view = convertView;

        final ViewHolder viewHolder = new ViewHolder();

        viewHolder.menuName = (TextView) view.findViewById(R.id.menuName);
        viewHolder.menuImage = (ImageView) view.findViewById(R.id.menuImage);

        viewHolder.buttonLayout = (LinearLayout) view
                .findViewById(R.id.buttonLayout);
        viewHolder.img_banner = (ImageView) view.findViewById(R.id.img_banner);

        if (position != 0) {

            viewHolder.menuName.setText(screenTitles[position]);

            viewHolder.img_banner.setVisibility(View.GONE);
            viewHolder.buttonLayout.setVisibility(View.VISIBLE);
        } else {

            viewHolder.img_banner.setVisibility(View.VISIBLE);
            viewHolder.buttonLayout.setVisibility(View.GONE);
        }

        view.setTag(viewHolder);

        return view;
    }
}
