package per.cuizhen.githubtest.base;

import android.support.v7.app.AppCompatActivity;
import android.util.SparseArray;
import android.view.View;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public abstract class CacheActivity extends AppCompatActivity {

    private SparseArray<View> mViewCaches = null;
    private View mActivityContent = null;

    @Override
    public <T extends View> T findViewById(int id) {
        if (mViewCaches == null) {
            mViewCaches = new SparseArray<>();
        }
        View view = mViewCaches.get(id);
        if (view == null) {
            view = getActivityContent().findViewById(id);
            mViewCaches.put(id, view);
        }
        return (T) view;
    }

    public View getActivityContent() {
        if (mActivityContent == null) {
            mActivityContent = getWindow().getDecorView().findViewById(android.R.id.content);
        }
        return mActivityContent;
    }
}
