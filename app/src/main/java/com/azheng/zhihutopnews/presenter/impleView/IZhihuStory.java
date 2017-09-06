package com.azheng.zhihutopnews.presenter.impleView;


import com.azheng.zhihutopnews.bean.ZhihuStory;

public interface IZhihuStory {

    void showError(String error);

    void showZhihuStory(ZhihuStory zhihuStory);

}
