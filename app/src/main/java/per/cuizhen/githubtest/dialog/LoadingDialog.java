package per.cuizhen.githubtest.dialog;

import android.animation.Animator;
import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import per.cuizhen.githubtest.R;
import per.goweii.anylayer.AnimHelper;
import per.goweii.anylayer.AnyLayer;
import per.goweii.anylayer.LayerManager;

/**
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class LoadingDialog {

    private static final long ANIM_DURATION = 200;
    private final Context context;
    private AnyLayer mAnyLayer;
    private int count = 0;

    private LoadingDialog(Context context) {
        this.context = context;
    }

    public static LoadingDialog with(Context context) {
        return new LoadingDialog(context);
    }

    public void show() {
        if (count <= 0) {
            count = 0;
            mAnyLayer = AnyLayer.with(context)
                    .contentView(R.layout.dialog_loading)
                    .backgroundColorInt(Color.TRANSPARENT)
                    .cancelableOnClickKeyBack(false)
                    .cancelableOnTouchOutside(false)
                    .gravity(Gravity.CENTER)
                    .contentAnim(new LayerManager.IAnim() {
                        @Override
                        public Animator inAnim(View target) {
                            return AnimHelper.createZoomInAnim(target).setDuration(ANIM_DURATION);
                        }

                        @Override
                        public Animator outAnim(View target) {
                            return AnimHelper.createZoomOutAnim(target).setDuration(ANIM_DURATION);
                        }
                    });
            mAnyLayer.show();
        }
        count++;
    }

    public void dismiss() {
        count--;
        if (count <= 0) {
            clear();
        }
    }

    public void clear() {
        if (mAnyLayer != null) {
            mAnyLayer.dismiss();
            mAnyLayer = null;
        }
        count = 0;
    }
}
