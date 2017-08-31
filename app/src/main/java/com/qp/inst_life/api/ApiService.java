package com.qp.inst_life.api;

import com.qp.inst_life.bean.Headlines;
import com.qp.inst_life.bean.NewJoke;
import com.qp.inst_life.bean.QueryNews;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/7 0007.
 */

public interface ApiService {
    /**
     * 获取最新笑话数据
     * @param key
     * @param page
     * @param pagesize
     * @return
     */
  @FormUrlEncoded
  @POST("content/text.from")
  Observable<NewJoke> getNewJokeData(@Field("key") String key, @Field("page") int page, @Field("pagesize") int pagesize);

    /**
     * 获取新闻头条
     * @param key
     * @param type
     * @return
     */
    @FormUrlEncoded
    @POST("toutiao/index")
    Observable<Headlines> getHeadLinesData(@Field("key") String key,@Field("type") String type);

  @FormUrlEncoded
  @POST("news/query")
  Observable<QueryNews> getQueryNewsData(@Field("key") String key, @Field("q") String q);

}
