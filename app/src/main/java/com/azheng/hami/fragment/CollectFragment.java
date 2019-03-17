package com.azheng.hami.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azheng.hami.R;
import com.azheng.hami.adapter.CollectAdapter;
import com.azheng.hami.bean.CollectCfg;
import com.azheng.hami.config.Config;
import com.azheng.hami.uitls.DBUtils;
import com.azheng.hami.view.GridItemDividerDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class CollectFragment extends BaseFragment {
    @BindView(R.id.recycle_collect)
    RecyclerView mRecycleCollect;
    /*@BindView(R.id.prograss_collect)
    ProgressBar mProgress;*/

    private Context mContext;
    private View view = null;
    private LinearLayoutManager linearLayoutManager;
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
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        view = inflater.inflate(R.layout.fragment_collect, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mCollectAdapter = new CollectAdapter(mContext);

        linearLayoutManager = new LinearLayoutManager(mContext);
        mRecycleCollect.setLayoutManager(linearLayoutManager);
        mRecycleCollect.setHasFixedSize(true);//设定固定大小
        mRecycleCollect.addItemDecoration(new GridItemDividerDecoration(
                mContext, R.dimen.divider_height, R.color.divider));//设置item分割线
        mRecycleCollect.setItemAnimator(new DefaultItemAnimator());
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
        view.setBackgroundColor(getResources().getColor(Config.isNight ? R.color.background_dark : R.color.background_light));
        initData();
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

    /**
     * 日间夜间模式切换时更新UI
     */
    public void refreshUI() {
        view.setBackgroundColor(getResources().getColor(Config.isNight ? R.color.background_dark : R.color.background_light));
        mCollectAdapter.notifyDataSetChanged();

    }

    public void initData(){
        mCollectList = DBUtils.getDB(mContext).getCollect(Config.COLLECT);
        mCollectAdapter.resetData(mCollectList);
    }
}

