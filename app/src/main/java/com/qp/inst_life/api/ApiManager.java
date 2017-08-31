package com.qp.inst_life.api;

import com.qp.inst_life.bean.Headlines;
import com.qp.inst_life.bean.NewJoke;
import com.qp.inst_life.bean.QueryNews;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/12 0012.
 */

public class ApiManager {
    private static final String BASE_URL = "http://japi.juhe.cn/joke/";  //笑话
    private static final String BASEURL_HEALDLINES = "http://v.juhe.cn/";  //新闻头条
    public static final String JOKE_KEY = "997632fe87a0b2cba461208d29f9e938";
    public static final String HEADLINES_KEY = "e5628eea4c9f9f8a8dd9c8cd91029474";
    public static final String BASE_QUERYNEWS = "http://op.juhe.cn/onebox/";//新闻检索
    public static final String QUERYNEWS_KEY = "62d18e8ba57ec5e6e097dfd0cf54b104";


    /**
     * 笑话的retrofit
     */
    private static final Retrofit sRetrofit_joke = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();
    private static final ApiService apiManagerService_joke = sRetrofit_joke.create(ApiService.class);
    /**
     * 新闻头条笑话的retrofit
     */
    private static final Retrofit sRetrofit_Head = new Retrofit.Builder()
            .baseUrl(BASEURL_HEALDLINES)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();
    private static final ApiService apiManagerService_head = sRetrofit_Head.create(ApiService.class);
    /**
     * 检索新闻的新闻头条笑话的retrofit
     */
    private static final Retrofit sRetrofit_Query = new Retrofit.Builder()
            .baseUrl(BASE_QUERYNEWS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create()) // 使用RxJava作为回调适配器
            .build();
    private static final ApiService apiManagerService_query = sRetrofit_Query.create(ApiService.class);


    /**
     * 获取天气数据
     *
     * @param
     * @return
     */
    public static Observable<NewJoke> getJokeData(String key, int page, int pagesize) {
        return apiManagerService_joke.getNewJokeData(key, page, pagesize);
    }

    /**
     * 获取新闻头条
     *
     * @param key
     * @param type
     * @return
     */
    public static Observable<Headlines> getHeadLinesData(String key, String type) {

        return apiManagerService_head.getHeadLinesData(key, type);
    }

    /**
     * 获取检索新闻
     */
    public static Observable<QueryNews> getQueryNewsData(String key, String q) {

        return apiManagerService_query.getQueryNewsData(key, q);

    }
}