package com.azheng.hami.presenter.impleView;

import com.azheng.hami.bean.ZhihuDaily;

public interface IZhihuFragment extends IBaseFragment {
    void updateList(ZhihuDaily zhihuDaily);//获取最新新闻
    void getTopStory(ZhihuDaily zhihuDaily);//获取头条新闻
}
