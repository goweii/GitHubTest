package per.cuizhen.githubtest.base;

import android.content.Context;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public abstract class MvpPresenter<V extends MvpView> {
    private Context context;
    private V baseView;

    protected void onCreate(V baseView) {
        this.baseView = baseView;
        context = baseView.getContext();
    }

    protected void onDestroy() {
        baseView = null;
        context = null;
    }

    public V getBaseView() {
        return baseView;
    }

    public boolean isAttachView() {
        return baseView != null;
    }

    public Context getContext() {
        return context;
    }
}
