package per.cuizhen.githubtest.activity;

import android.support.annotation.Nullable;
import android.view.View;

import butterknife.OnClick;
import per.cuizhen.githubtest.R;
import per.cuizhen.githubtest.base.BaseActivity;
import per.cuizhen.githubtest.base.MvpPresenter;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class MainActivity extends BaseActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Nullable
    @Override
    protected MvpPresenter initPresenter() {
        return null;
    }

    @Override
    protected void initView() {
    }

    @Override
    protected void loadData() {
    }

    @OnClick({R.id.btn_search_user_sync, R.id.btn_search_user_async})
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    protected void onClick2(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn_search_user_sync:
                SearchUserActivity.start(getContext());
                break;
            case R.id.btn_search_user_async:
                SearchUserLazyLanguageActivity.start(getContext());
                break;
        }
    }
}
