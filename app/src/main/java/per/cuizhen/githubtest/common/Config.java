package per.cuizhen.githubtest.common;

/**
 * 公共配置
 *
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class Config {

    /**
     * Retrofit的BaseUrl
     */
    public static final String BASE_URL = "https://api.github.com/";

    /**
     * OkHttp超时
     */
    public static final long TIMEOUT = 5000;

    /**
     * OkHttp默认缓存目录名
     */
    public static final String HTTP_CACHE_DIR_NAME = "http-cache";

    /**
     * OkHttp默认缓存大小
     */
    public static final long HTTP_CACHE_SIZE = 10 * 1024 * 1024;

    /**
     * 列表每页返回条目个数
     */
    public static final int LIST_PER_PAGE = 10;

    /**
     * 列表起始页码
     */
    public static final int LIST_START_PAGE = 1;

}
