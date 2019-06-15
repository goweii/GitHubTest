package per.cuizhen.githubtest.http;

import com.google.gson.Gson;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import per.cuizhen.githubtest.BuildConfig;
import per.cuizhen.githubtest.common.Config;
import per.cuizhen.githubtest.utils.CacheUtils;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class GitHubRequest {

    private final Retrofit mRetrofit;

    private static class Holder {
        private static final GitHubRequest INSTANCE = new GitHubRequest();
    }

    private static GitHubRequest getInstance() {
        return Holder.INSTANCE;
    }

    private static <T> T getService(Class<T> clazz) {
        return getInstance().mRetrofit.create(clazz);
    }

    public static GitHubApi api() {
        return GitHubRequest.getService(GitHubApi.class);
    }

    private GitHubRequest() {
        mRetrofit = create();
    }

    private Retrofit create() {
        return new Retrofit.Builder()
                .client(createOkHttpClient())
                .baseUrl(Config.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    /**
     * 创建OkHttpClient实例
     *
     * @return OkHttpClient
     */
    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        // 设置调试模式打印日志
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(logging);
        }
        // 设置缓存
        builder.cache(createCache());
        // 设置3个超时时长
        final long timeout = Config.TIMEOUT;
        builder.connectTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.readTimeout(timeout, TimeUnit.MILLISECONDS);
        builder.writeTimeout(timeout, TimeUnit.MILLISECONDS);
        return builder.build();
    }

    /**
     * 创建缓存
     *
     * @return Cache
     */
    private Cache createCache() {
        File cacheFile = new File(CacheUtils.getCacheDir(), Config.HTTP_CACHE_DIR_NAME);
        if (!cacheFile.exists()) {
            cacheFile.mkdirs();
        }
        return new Cache(cacheFile, Config.HTTP_CACHE_SIZE);
    }

}
