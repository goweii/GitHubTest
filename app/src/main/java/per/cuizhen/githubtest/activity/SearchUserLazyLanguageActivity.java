package per.cuizhen.githubtest.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import per.cuizhen.githubtest.R;
import per.cuizhen.githubtest.adapter.SearchUserLazyLanguageAdapter;
import per.cuizhen.githubtest.base.BaseActivity;
import per.cuizhen.githubtest.common.Config;
import per.cuizhen.githubtest.mvp.model.SearchUserListBean;
import per.cuizhen.githubtest.mvp.presenter.SearchUserLazyLanguagePresenter;
import per.cuizhen.githubtest.mvp.view.SearchUserLazyLanguageView;

/**
 * 先加载用户列表，再查询每个用户的偏好语言
 *
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class SearchUserLazyLanguageActivity extends BaseActivity<SearchUserLazyLanguagePresenter> implements SearchUserLazyLanguageView {

    @BindView(R.id.tb)
    Toolbar tb;
    @BindView(R.id.et_search)
    EditText etSearch;
    @BindView(R.id.rv)
    RecyclerView rv;

    private SearchUserLazyLanguageAdapter mAdapter;

    private int currPage = Config.LIST_START_PAGE;

    public static void start(Context context){
        Intent intent = new Intent(context, SearchUserLazyLanguageActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_search_user;
    }

    @Nullable
    @Override
    protected SearchUserLazyLanguagePresenter initPresenter() {
        return new SearchUserLazyLanguagePresenter();
    }

    @Override
    protected void initView() {
        setSupportActionBar(tb);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(manager);
        mAdapter = new SearchUserLazyLanguageAdapter();
        rv.setAdapter(mAdapter);
        mAdapter.closeLoadAnimation();
        mAdapter.setEnableLoadMore(false);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                search();
            }
        }, rv);
    }

    @Override
    protected void loadData() {
    }

    @OnClick({R.id.tv_search})
    @Override
    public void onClick(View v) {
        super.onClick(v);
    }

    @Override
    protected void onClick2(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.tv_search:
                currPage = Config.LIST_START_PAGE;
                search();
                break;
        }
    }

    private void search(){
        String key = etSearch.getText().toString();
        presenter.searchUser(key, currPage);
    }

    @Override
    public void searchUserSuccess(List<SearchUserListBean> searchUserListBeanList) {
        if (currPage == Config.LIST_START_PAGE) {
            mAdapter.setNewData(searchUserListBeanList);
            mAdapter.setEnableLoadMore(true);
        } else {
            mAdapter.addData(searchUserListBeanList);
            mAdapter.loadMoreComplete();
        }
        currPage++;
        if (searchUserListBeanList.size() < Config.LIST_PER_PAGE) {
            mAdapter.loadMoreEnd();
        }
        for (SearchUserListBean searchUserListBean : searchUserListBeanList) {
            if (searchUserListBean.getLanguage() == null) {
                presenter.userLanguage(searchUserListBean.getName());
            }
        }
    }

    @Override
    public void searchUserFail() {
        if (currPage > Config.LIST_START_PAGE) {
            mAdapter.loadMoreFail();
        }
        Toast.makeText(getContext(), "加载失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void userLanguageSuccess(SearchUserListBean searchUserListBean) {
        List<SearchUserListBean> datas = mAdapter.getData();
        for (int i = 0; i < datas.size(); i++) {
            SearchUserListBean data = datas.get(i);
            if(TextUtils.equals(data.getName(), searchUserListBean.getName())){
                data.setLanguage(searchUserListBean.getLanguage());
                mAdapter.notifyItemChanged(i);
                break;
            }
        }
    }

    @Override
    public void userLanguageFail(String name) {
        List<SearchUserListBean> datas = mAdapter.getData();
        for (int i = 0; i < datas.size(); i++) {
            SearchUserListBean data = datas.get(i);
            if(TextUtils.equals(data.getName(), name)){
                data.setLanguage("");
                mAdapter.notifyItemChanged(i);
                break;
            }
        }
    }
}
