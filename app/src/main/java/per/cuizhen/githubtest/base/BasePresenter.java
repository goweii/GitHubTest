package per.cuizhen.githubtest.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class BasePresenter<V extends BaseView> extends MvpPresenter<V> {

    private CompositeDisposable mCompositeDisposable = null;

    @Override
    protected void onCreate(V baseView) {
        super.onCreate(baseView);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeDisposable != null && !mCompositeDisposable.isDisposed()) {
            mCompositeDisposable.dispose();
        }
    }

    public void addDisposable(Disposable disposable) {
        if (mCompositeDisposable == null || mCompositeDisposable.isDisposed()) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void showLoading() {
        if (isAttachView()) {
            getBaseView().showLoading();
        }
    }

    public void dismissLoading() {
        if (isAttachView()) {
            getBaseView().dismissLoading();
        }
    }
}
