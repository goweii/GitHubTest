package per.cuizhen.githubtest.http;

import java.util.List;

import io.reactivex.Observable;
import per.cuizhen.githubtest.mvp.model.SearchUserBean;
import per.cuizhen.githubtest.mvp.model.UserReposBean;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface GitHubApi {

    @GET("search/users")
    Observable<SearchUserBean> searchUser(@Query("q") String key,
                                          @Query("page") int page,
                                          @Query("per_page") int perPage);

    @GET("users/{username}/repos")
    Observable<List<UserReposBean>> userRepos(@Path("username") String username);

}
