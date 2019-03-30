package com.example.swipedemo;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.view.LayoutInflater;
import android.graphics.Color;
import java.util.Random;

public class SlideAdapter extends PagerAdapter {
    Context context;
    LayoutInflater inflater;
    Random rnd = new Random();

    //  List of images
    public int[] lst_images = {
            R.drawable.rest_1,
            R.drawable.rest_2,
            R.drawable.rest_3,
            R.drawable.rest_4
    };

    //  List of titles
    public String[] lst_title = {
            "Short Order",
            "Cedar Creek Grille",
            "Kricket",
            "Taverna Opa"
    };

    //   List of descriptions
    public String[] lst_description = {
            "Jazz fusion restaurant. Live pianist. Happy hour 3-6 Daily.",
            "Upscale restaurant. Serves Mediterranean dishes cooked to perfection",
            "Small, but cute. Delicious steaks, wine, and ambiance.",
            "Serves the best Spanish-Fusion food around."
    };

    //  List of background colors
    public int[] lst_backgroundcolor = {
//            Color.rgb(55, 55, 55),
//            Color.rgb(239, 85, 85),
//            Color.rgb(110, 49, 89),
//            Color.rgb(1, 188, 212),
            Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)),
            Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)),
            Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)),
            Color.rgb(rnd.nextInt(255), rnd.nextInt(255), rnd.nextInt(255)),
    };


    public SlideAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return lst_title.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return (view == (LinearLayout)o);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.slide, container, false);
        LinearLayout layoutslide = view.findViewById(R.id.slidelinearlayout);
        ImageView imgslide = (ImageView) view.findViewById(R.id.slideimg);
        TextView txttitle  = (TextView) view.findViewById(R.id.txttitle);
        TextView description = (TextView) view.findViewById(R.id.txtDescription);
        layoutslide.setBackgroundColor(lst_backgroundcolor[position]);
        imgslide.setImageResource(lst_images[position]);
        txttitle.setText(lst_title[position]);
        description.setText(lst_description[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((LinearLayout)object);
    }
}
