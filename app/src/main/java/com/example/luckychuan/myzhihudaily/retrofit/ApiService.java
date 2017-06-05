package com.example.luckychuan.myzhihudaily.retrofit;

import com.example.luckychuan.myzhihudaily.bean.Comment;
import com.example.luckychuan.myzhihudaily.bean.Comments;
import com.example.luckychuan.myzhihudaily.bean.LatestData;
import com.example.luckychuan.myzhihudaily.bean.News;
import com.example.luckychuan.myzhihudaily.bean.StoryContent;
import com.example.luckychuan.myzhihudaily.bean.StoryExtra;
import com.example.luckychuan.myzhihudaily.bean.Theme;
import com.example.luckychuan.myzhihudaily.bean.ThemeContent;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 知乎日报API，感谢原作者https://github.com/izzyleung/ZhihuDailyPurify/wiki/%E7%9F%A5%E4%B9%8E%E6%97%A5%E6%8A%A5-API-%E5%88%86%E6%9E%90
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
    Observable<ThemeContent> getThemeContent(@Path("id") String id);


    //    消息内容获取与离线下载
//    URL: http://news-at.zhihu.com/api/4/news/3892357
//    使用在 最新消息 中获得的 id，拼接在 http://news-at.zhihu.com/api/4/news/ 后，得到对应消息 JSON 格式的内容
    @GET("api/4/news/{id}")
    Observable<StoryContent> getStoryContent(@Path("id") String id);

    //    新闻额外信息
//    URL:  http://news-at.zhihu.com/api/4/story-extra/3892357
//    输入新闻的ID，获取对应新闻的额外信息，如评论数量，所获的『赞』的数量。
    @GET("api/4/story-extra/{id}")
    Observable<StoryExtra> getStoryExtra(@Path("id") String id);


    //    新闻对应长评论查看
//    •URL:  http://news-at.zhihu.com/api/4/story/8997528/long-comments
//    // •使用在  最新消息  中获得的  id ，在  http://news-at.zhihu.com/api/4/story/#{id}/long-comments  中将  id  替换为对应的  id ，得到长评论 JSON 格式的内容
    @GET("api/4/story/{id}/long-comments")
    Observable<Comments> getLongComments(@Path("id") String id);

    @GET("api/4/story/{id}/short-comments")
    Observable<Comments> getShortComments(@Path("id") String id);


}
