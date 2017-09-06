package com.azheng.zhihutopnews.api;



import com.azheng.zhihutopnews.bean.ZhihuDaily;
import com.azheng.zhihutopnews.bean.ZhihuStory;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;


public interface ZhihuApi {

    /*
    * 当日最新消息以及top消息
    */
    @GET("/api/4/news/latest")
    Observable<ZhihuDaily> getLastDaily();

    /*
    * 过往消息，如需要知道今日前的消息，data的职位今日(20170429)
    */
    @GET("/api/4/news/before/{date}")
    Observable<ZhihuDaily> getTheDaily(@Path("date") String date);

    /*
    *具体的消息内容，通过id获取
    */
    @GET("/api/4/news/{id}")
    Observable<ZhihuStory> getZhihuStory(@Path("id") String id);



}
