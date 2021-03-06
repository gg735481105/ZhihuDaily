package com.azheng.hami.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.azheng.hami.R;
import com.azheng.hami.activity.ZhihuDetailActivity;
import com.azheng.hami.bean.CollectCfg;
import com.azheng.hami.config.Config;

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

    public CollectAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType){
            /*case PROGRESS_TYPE:
                break;*/
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
            /*case PROGRESS_TYPE:
                break;*/
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
        final String id = mCollectList.get(position).getId();
        final String title = mCollectList.get(position).getTitle();
        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, ZhihuDetailActivity.class);
                Log.d("azheng","id:" + id + "title:" + title);
                intent.putExtra("id", id);
                intent.putExtra("title", title);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCollectList.size();
    }

    @Override
    public int getItemViewType(int position) {
        /*if (position == 0){
            return PROGRESS_TYPE;
        } else {
            return DISPLAY_TYPE;
        }*/
        return DISPLAY_TYPE;
    }

    /*
    * 收藏详细信息
    */
    private class CollectViewHolder extends RecyclerView.ViewHolder {
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

    public void resetData(List<CollectCfg> list){
        this.mCollectList = list;
        notifyDataSetChanged();
    }
}
