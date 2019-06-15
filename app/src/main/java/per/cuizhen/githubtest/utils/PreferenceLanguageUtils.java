package per.cuizhen.githubtest.utils;

import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import per.cuizhen.githubtest.mvp.model.UserReposBean;

/**
 * 从用户仓库列表中获取偏好语言
 *
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class PreferenceLanguageUtils {

    /**
     * 从用户仓库列表中获取偏好语言
     * 出现最多次数的语言
     *
     * @param userReposBeans 仓库列表
     * @return 空字符则没有偏好语言
     */
    @NonNull
    public static String findPreferenceLanguage(@NonNull List<UserReposBean> userReposBeans) {
        HashMap<String, Integer> map = new HashMap<>(userReposBeans.size());
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
