package ca.gbc.mobile.dgy.personalrestaurantguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;


/*********************************
 *    Students:                  *
 * David Olano - ID: 100847924   *
 * Yafan Zhang - ID: 100816652   *
 * Gary  Chan  - ID: 100882663   *
 *                               *
 ********************************/
public class MyAdapter extends ArrayAdapter<Restaurant> implements Filterable {

    public MyAdapter(Context context, Restaurant[] restaurants) {
        super(context, R.layout.row_layout,restaurants);

    }
    @Override
    public View getView(int position,
                        View convertView,
                        ViewGroup parent)
    {
        Restaurant restaurant=getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
        }


        TextView rest_name =
                (TextView)convertView.findViewById(R.id.textView);
        ImageView rest_image =
                (ImageView)convertView.findViewById(R.id.imageView);
        RatingBar ratingBar=
                (RatingBar)convertView.findViewById(R.id.ratingBar);
        ratingBar.setRating(5);

        rest_name.setText(restaurant.name);
        rest_image.setBackgroundResource(restaurant.image);
        ratingBar.setRating(restaurant.rate);
        return convertView;
    }
}
