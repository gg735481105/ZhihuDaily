package com.azheng.zhihutopnews.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.azheng.zhihutopnews.R;
import com.azheng.zhihutopnews.adapter.CollectAdapter;
import com.azheng.zhihutopnews.adapter.ZhihuAdapter;
import com.azheng.zhihutopnews.bean.CollectCfg;
import com.azheng.zhihutopnews.config.Config;
import com.azheng.zhihutopnews.uitls.DBUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class CollectFragment extends BaseFragment {
    @BindView(R.id.recycle_collect)
    RecyclerView mRecycleCollect;
    @BindView(R.id.prograss_collect)
    ProgressBar mProgress;

    private Context mContext;
    private CollectAdapter mCollectAdapter;
    private List<CollectCfg> mCollectList = new ArrayList<>();

    public CollectFragment() {
        // Required empty public constructor
    }

    public CollectFragment(Context context) {
        // Required empty public constructor
        this.mContext = context;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //将数据库里的值保存到list
        mCollectList = DBUtils.getDB(mContext).getCollect(Config.COLLECT);
        for (int i = 0;i < mCollectList.size();i++){
            Log.d("azheng",mCollectList.get(i).getTime());
        }
        mCollectAdapter = new CollectAdapter(mContext, mCollectList);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecycleCollect.setAdapter(mCollectAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
