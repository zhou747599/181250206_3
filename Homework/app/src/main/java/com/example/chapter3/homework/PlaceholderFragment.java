package com.example.chapter3.homework;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class PlaceholderFragment extends Fragment {
    ListView simpleList;
    LottieAnimationView loading;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View rootView =  inflater.inflate(R.layout.fragment_placeholder, container, false);
        loading = (LottieAnimationView) rootView.findViewById(R.id.ex3_loading);
        simpleList = (ListView) rootView.findViewById(R.id.ex3_simple_list);
        ArrayList<String> items = new ArrayList<>();
        for(int i=0;i<20;i++){
            items.add("item" + i);
        }
        ArrayAdapter<String> simpleAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, items);
        simpleList.setAdapter(simpleAdapter);
        simpleList.setVisibility(View.INVISIBLE);
        return rootView;
        //return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {

                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                loading.animate().alpha(0f).setDuration(500).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation, boolean isReverse) {
                        loading.setVisibility(View.GONE);
                    }
                });
                simpleList.setVisibility(View.VISIBLE);
                AlphaAnimation a2 = new AlphaAnimation(0,1);
                a2.setDuration(500);
                simpleList.setAnimation(a2);
            }
        }, 5000);
    }
}
