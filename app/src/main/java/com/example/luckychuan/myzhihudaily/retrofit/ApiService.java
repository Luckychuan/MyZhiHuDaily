package com.example.luckychuan.myzhihudaily.retrofit;

import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.bean.News;
import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.bean.Theme;
import com.example.luckychuan.myzhihudaily.bean.ThemeContent;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by Luckychuan on 2017/4/20.
 */
public interface ApiService {

    //    最新消息
//    URL: http://news-at.zhihu.com/api/4/news/latest
    @GET("api/4/news/latest")
    Observable<LatestData> getLatestData();


    //        过往消息
//    URL: http://news-at.zhihu.com/api/4/news/before/20131119
//    若果需要查询 11 月 18 日的消息，before 后的数字应为 20131119
//    知乎日报的生日为 2013 年 5 月 19 日，若 before 后数字小于 20130520 ，只会接收到空消息
//    输入的今日之后的日期仍然获得今日内容，但是格式不同于最新消息的 JSON 格式
    @GET("api/4/news/before/{date}")
    Observable<News> getOldData(@Path("date") String date);


    //    主题日报列表查看
//    URL: http://news-at.zhihu.com/api/4/themes
    @GET("api/4/themes")
    Observable<Theme> getTheme();


    //    主题日报内容查看
//    URL: http://news-at.zhihu.com/api/4/theme/11
//    使用在 主题日报列表查看 中获得需要查看的主题日报的 id，拼接在 http://news-at.zhihu.com/api/4/theme/ 后，得到对应主题日报 JSON 格式的内容
    @GET("api/4/theme/{id}")
    Observable<ThemeContent> getThemeContent(@Path("id") int id);


    //    消息内容获取与离线下载
//    URL: http://news-at.zhihu.com/api/4/news/3892357
//    使用在 最新消息 中获得的 id，拼接在 http://news-at.zhihu.com/api/4/news/ 后，得到对应消息 JSON 格式的内容
    @GET("api/4/news/{id}")
    Observable<StoryContent> getStoryContent(@Path("id") int id);


}
