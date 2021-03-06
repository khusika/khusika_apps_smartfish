package com.khusika.smartfish.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.khusika.smartfish.R;
import com.khusika.smartfish.activities.FaqActivity;
import com.khusika.smartfish.activities.FishmartActivity;
import com.khusika.smartfish.activities.MainActivity;
import com.khusika.smartfish.activities.ProfileActivity;
import com.khusika.smartfish.activities.TpiActivity;

import java.util.HashMap;

/**
 * Created by khusika on 11/02/17.
 */

public class MainFragment extends Fragment implements ViewPagerEx.OnPageChangeListener {

    private MainActivity main;
    private Toolbar toolbar;
    private SliderLayout mDemoSlider;
    private ImageView imageView;

    public MainFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        main = (MainActivity)activity;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        toolbar = (Toolbar)view.findViewById(R.id.fragment_main_toolbar);
        setupToolbar();
        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);

        HashMap<String,Integer> file_maps = new HashMap<>();
        file_maps.put("KUB Samudra Jaya",R.drawable.slider_5);
        file_maps.put("TPI Sedati",R.drawable.slider_4);
        file_maps.put("Nelayan Sedati",R.drawable.slider_3);
        file_maps.put("Desa Tambakcemandi",R.drawable.slider_2);
        file_maps.put("SMARTFISH",R.drawable.slider_1);

        for(String name : file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);

            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.ZoomOutSlide);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(4000);
        mDemoSlider.addOnPageChangeListener(this);

        imageView = (ImageView)view.findViewById(R.id.home_tpi);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent tpi = new Intent(view.getContext(), TpiActivity.class);
                startActivity(tpi);
            }
        });
        imageView = (ImageView)view.findViewById(R.id.home_market);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent market = new Intent(view.getContext(), FishmartActivity.class);
                startActivity(market);
            }
        });
        imageView = (ImageView)view.findViewById(R.id.home_profile);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent profile = new Intent(view.getContext(), ProfileActivity.class);
                startActivity(profile);
            }
        });
        imageView = (ImageView)view.findViewById(R.id.home_faq);
        imageView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent faq = new Intent(view.getContext(), FaqActivity.class);
                startActivity(faq);
            }
        });

        return view;
    }

    @Override
    public  void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        main.setupNavigationDrawer(toolbar);
    }

    private  void setupToolbar(){
        toolbar.setTitle(getString(R.string.app_name));
        main.setSupportActionBar(toolbar);
    }

    @Override
    public void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        mDemoSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}
}
