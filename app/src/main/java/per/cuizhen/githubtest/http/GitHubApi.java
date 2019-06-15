package per.cuizhen.githubtest.http;

import java.util.List;

import io.reactivex.Observable;
import per.cuizhen.githubtest.mvp.model.SearchUserBean;
import per.cuizhen.githubtest.mvp.model.UserReposBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 接口声明
 *
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface GitHubApi {

    /**
     * 搜索用户
     *
     * @param key     关键字
     * @param page    页码
     * @param perPage 每页个数
     */
    @GET("search/users")
    Observable<SearchUserBean> searchUser(@Query("q") String key,
                                          @Query("page") int page,
                                          @Query("per_page") int perPage);

    /**
     * 获取用户仓库列表
     *
     * @param username 用户名
     */
    @GET("users/{username}/repos")
    Observable<List<UserReposBean>> userRepos(@Path("username") String username);

}
