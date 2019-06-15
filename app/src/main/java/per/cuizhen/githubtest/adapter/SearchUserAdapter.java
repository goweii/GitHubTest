package per.cuizhen.githubtest.adapter;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import de.hdodenhof.circleimageview.CircleImageView;
import per.cuizhen.githubtest.R;
import per.cuizhen.githubtest.mvp.model.SearchUserListBean;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class SearchUserAdapter extends BaseQuickAdapter<SearchUserListBean, BaseViewHolder> {

    public SearchUserAdapter() {
        super(R.layout.rv_item_user);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchUserListBean item) {
        helper.setText(R.id.tv_user_name, item.getName());
        CircleImageView civUserIcon = helper.getView(R.id.civ_user_icon);
        Glide.with(civUserIcon)
                .asBitmap()
                .load(item.getIcon())
                .into(civUserIcon);
        TextView tvLanguage = helper.getView(R.id.tv_language);
        if (TextUtils.isEmpty(item.getLanguage())) {
            tvLanguage.setVisibility(View.GONE);
        } else {
            tvLanguage.setVisibility(View.VISIBLE);
            tvLanguage.setText(item.getLanguage());
        }
    }
}
