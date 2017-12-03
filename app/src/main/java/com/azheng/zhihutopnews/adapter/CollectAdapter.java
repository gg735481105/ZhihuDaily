package com.azheng.zhihutopnews.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azheng.zhihutopnews.R;
import com.azheng.zhihutopnews.activity.ZhihuDetailActivity;
import com.azheng.zhihutopnews.bean.CollectCfg;
import com.azheng.zhihutopnews.config.Config;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 阿正 on 2017/11/29 0029.
 */

public class CollectAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int PROGRESS_TYPE = -1;
    private static final int DISPLAY_TYPE = 1;
    private Context mContext;
    private List<CollectCfg> mCollectList = new ArrayList<>();

    public CollectAdapter(Context context, List<CollectCfg> list) {
        this.mContext = context;
        this.mCollectList = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            case PROGRESS_TYPE:
                break;
            case DISPLAY_TYPE:
                return new CollectViewHolder(LayoutInflater.from(mContext).inflate(R.layout.collect_recycleview_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int type = getItemViewType(position);
        switch (type){
            case DISPLAY_TYPE:
                bindViewHolderCollect((CollectViewHolder) holder,position);
                break;
            case PROGRESS_TYPE:
                break;
        }
    }

    private void bindViewHolderCollect(CollectViewHolder holder, int position) {
        holder.title.setTextColor(
                Config.isNight ? mContext.getResources().getColor(R.color.text_primary_dark) : mContext.getResources().getColor(R.color.text_light));
        holder.time.setTextColor(
                Config.isNight ? mContext.getResources().getColor(R.color.text_primary_dark) : mContext.getResources().getColor(R.color.text_light));
        holder.cardview.setCardBackgroundColor(
                Config.isNight ? mContext.getResources().getColor(R.color.cardview_background_dark) : mContext.getResources().getColor(R.color.cardview_background_light));
        //显示数据
        holder.title.setText(mCollectList.get(position).getTitle());
        holder.time.setText(mCollectList.get(position).getTime());
        //按键监听
        toDetailActivity(holder,position);
    }

    private void toDetailActivity(CollectViewHolder holder,int position) {
        Intent intent = new Intent(mContext, ZhihuDetailActivity.class);
        intent.putExtra("id", mCollectList.get(position).getTime());
        intent.putExtra("title", mCollectList.get(position).getTitle());
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (position > 0){
            return DISPLAY_TYPE;
        }
        return PROGRESS_TYPE;
    }

    /*
    * 收藏详细信息
    */
    class CollectViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView time;
        CardView cardview;
        public CollectViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.item_title);
            time = (TextView) itemView.findViewById(R.id.item_time);
            cardview = (CardView) itemView.findViewById(R.id.collect_item_carview);
        }
    }

}
