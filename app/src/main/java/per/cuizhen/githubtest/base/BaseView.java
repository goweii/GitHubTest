package per.cuizhen.githubtest.base;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public interface BaseView extends MvpView {
    void showLoading();
    void dismissLoading();
    void clearLoading();
}
