package per.cuizhen.githubtest.utils;

import java.io.File;

import per.cuizhen.githubtest.application.GitHubApp;

/**
 * 缓存辅助类
 *
 * @author CuiZhen
 * @date 2019/6/15
 * QQ: 302833254
 * E-mail: goweii@163.com
 * GitHub: https://github.com/goweii
 */
public class CacheUtils {

    /**
     * 获取系统默认缓存文件夹
     * 优先返回SD卡中的缓存文件夹
     */
    public static String getCacheDir() {
        File cacheFile = null;
        if (FileUtils.isSDCardAlive()) {
            cacheFile = GitHubApp.getAppContext().getExternalCacheDir();
        }
        if (cacheFile == null) {
            cacheFile = GitHubApp.getAppContext().getCacheDir();
        }
        return cacheFile.getAbsolutePath();
    }

    /**
     * 获取系统默认缓存文件夹内的缓存大小
     */
    public static String getTotalCacheSize() {
        long cacheSize = FileUtils.getSize(GitHubApp.getAppContext().getCacheDir());
        if (FileUtils.isSDCardAlive()) {
            cacheSize += FileUtils.getSize(GitHubApp.getAppContext().getExternalCacheDir());
        }
        return FileUtils.formatSize(cacheSize);
    }

    /**
     * 清除系统默认缓存文件夹内的缓存
     */
    public static void clearAllCache() {
        FileUtils.delete(GitHubApp.getAppContext().getCacheDir());
        if (FileUtils.isSDCardAlive()) {
            FileUtils.delete(GitHubApp.getAppContext().getExternalCacheDir());
        }
    }

}