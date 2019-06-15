package per.cuizhen.githubtest.mvp.view;

import java.util.List;

import per.cuizhen.githubtest.base.BaseView;
import per.cuizhen.githubtest.mvp.model.SearchUserListBean;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface SearchUserLazyLanguageView extends BaseView {
    void searchUserSuccess(List<SearchUserListBean> searchUserListBeanList);
    void searchUserFail();
    void userLanguageSuccess(SearchUserListBean searchUserListBean);
    void userLanguageFail();
}
