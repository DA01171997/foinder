package com.example.mainactivity;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;

    //list of images
    public int[] lst_images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
    };
    // list of Restaurant Names

    public String [] lst_names = {
            "7 Leaves",
            "Omomo Tea",
            "Ding Tea"
    };
    // list of description

    public String [] lst_description = {
            "OOOOOLONGGG MILK TEA",
            "Dank Jasmine Green",
            "Ems Crystal Boba"
    };
    //list of background colors

    public int [] lst_backgroundColors = {
            Color.rgb(55,55,55),
            Color.rgb(239,85,85),
            Color.rgb(1,188,212)
    };

    public SlideAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_names.length;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide,container,false);
        LinearLayout layoutslide =(LinearLayout) view.findViewById(R.id.slideLinearLayout);
        ImageView imgslide = (ImageView) view.findViewById(R.id.slideimg);
        TextView txtname = (TextView) view.findViewById(R.id.slidenames);
        TextView txtdescription = (TextView) view.findViewById(R.id.restaurantDescription);
        layoutslide.setBackgroundColor(lst_backgroundColors[position]);
        imgslide.setImageResource(lst_images[position]);
        txtname.setText(lst_names[position]);
        txtdescription.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return (view==(LinearLayout)object);
    }
}
