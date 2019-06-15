package per.cuizhen.githubtest.mvp.presenter;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import per.cuizhen.githubtest.base.BasePresenter;
import per.cuizhen.githubtest.common.Config;
import per.cuizhen.githubtest.http.GitHubRequest;
import per.cuizhen.githubtest.mvp.model.SearchUserBean;
import per.cuizhen.githubtest.mvp.model.SearchUserListBean;
import per.cuizhen.githubtest.mvp.model.UserReposBean;
import per.cuizhen.githubtest.mvp.view.SearchUserView;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class SearchUserPresenter extends BasePresenter<SearchUserView> {

    public void searchUser(final String key, final int page) {
        GitHubRequest.api().searchUser(key, page, Config.LIST_PER_PAGE)
                .flatMap(new Function<SearchUserBean, ObservableSource<List<SearchUserListBean>>>() {
                    @Override
                    public ObservableSource<List<SearchUserListBean>> apply(SearchUserBean searchUserBean) throws Exception {
                        final List<SearchUserListBean> searchUserListBeanList = new ArrayList<>();
                        List<Observable<List<UserReposBean>>> os = new ArrayList<>(searchUserBean.getItems().size());
                        for (SearchUserBean.ItemsBean item : searchUserBean.getItems()) {
                            SearchUserListBean searchUserListBean = new SearchUserListBean();
                            searchUserListBean.setName(item.getLogin());
                            searchUserListBean.setIcon(item.getAvatar_url());
                            searchUserListBeanList.add(searchUserListBean);
                            os.add(GitHubRequest.api().userRepos(item.getLogin()));
                        }
                        return Observable.zip(os, new Function<Object[], List<SearchUserListBean>>() {
                            @Override
                            public List<SearchUserListBean> apply(Object[] objects) throws Exception {
                                for (Object object : objects) {
                                    List<UserReposBean> userReposBeans = (List<UserReposBean>) object;
                                    if (userReposBeans != null && userReposBeans.size() > 0) {
                                        String owner = userReposBeans.get(0).getOwner().getLogin();
                                        String language = findPreferenceLanguage(userReposBeans);
                                        for (SearchUserListBean searchUserListBean : searchUserListBeanList) {
                                            if (TextUtils.equals(searchUserListBean.getName(), owner)) {
                                                searchUserListBean.setLanguage(language);
                                                break;
                                            }
                                        }
                                    }
                                }
                                return searchUserListBeanList;
                            }
                        });
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<SearchUserListBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposable(d);
                        if (page == Config.LIST_START_PAGE) {
                            showLoading();
                        }
                    }

                    @Override
                    public void onNext(List<SearchUserListBean> searchUserListBeanList) {
                        if (isAttachView()) {
                            getBaseView().searchUserSuccess(searchUserListBeanList);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (page == Config.LIST_START_PAGE) {
                            dismissLoading();
                        }
                        if (isAttachView()) {
                            getBaseView().searchUserFail();
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (page == Config.LIST_START_PAGE) {
                            dismissLoading();
                        }
                    }
                });
    }

    private String findPreferenceLanguage(List<UserReposBean> userReposBeans) {
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < userReposBeans.size(); i++) {
            UserReposBean userReposBean = userReposBeans.get(i);
            final String language = userReposBean.getLanguage();
            Integer count = map.get(language);
            if (count != null) {
                map.put(language, count + 1);
            } else {
                map.put(language, 1);
            }
        }
        Collection<Integer> count = map.values();
        int maxCount = Collections.max(count);
        String preferenceLanguage = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (maxCount == entry.getValue()) {
                preferenceLanguage = entry.getKey();
            }
        }
        return preferenceLanguage;
    }

}
