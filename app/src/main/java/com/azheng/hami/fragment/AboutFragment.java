package com.azheng.hami.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.azheng.hami.R;
import com.azheng.hami.config.Config;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 阿正 on 2017/4/28 0028.
 */

public class AboutFragment extends BaseFragment {
    private Context mcontext;

    @BindView(R.id.fragmeng_about)
    LinearLayout aboutFragment;
    @BindView(R.id.about_app)
    TextView aboutApp;
    @BindView(R.id.about_me)
    TextView aboutMe;
    @BindView(R.id.about_function)
    TextView aboutFunction;
    @BindView(R.id.about_thank)
    TextView aboutThanks;

    @Override
    public void onAttach(Context context) {
        this.mcontext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //使用setRetainInstance方法. 设置该方法为true后，可以让fragment在activity被重建时保持实例不变
        setRetainInstance(true);
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        refreshUI();
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
    /**
     * 日间夜间模式切换时更新UI
     */
    public void refreshUI() {
        aboutFragment.setBackgroundColor(getResources().getColor(Config.isNight ? R.color.background_dark : R.color.background_light));
        aboutApp.setTextColor(
                Config.isNight ? mcontext.getResources().getColor(R.color.text_primary_dark) : mcontext.getResources().getColor(R.color.text_light));
        aboutMe.setTextColor(
                Config.isNight ? mcontext.getResources().getColor(R.color.text_primary_dark) : mcontext.getResources().getColor(R.color.text_light));
        aboutFunction.setTextColor(
                Config.isNight ? mcontext.getResources().getColor(R.color.text_primary_dark) : mcontext.getResources().getColor(R.color.text_light));
        aboutThanks.setTextColor(
                Config.isNight ? mcontext.getResources().getColor(R.color.text_primary_dark) : mcontext.getResources().getColor(R.color.text_light));
    }
}
