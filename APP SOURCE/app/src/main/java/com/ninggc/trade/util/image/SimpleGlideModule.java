package com.ninggc.trade.util.image;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * Created by Ning on 10/30/2017 0030.
 */

public class SimpleGlideModule implements GlideModule {
    private static final int DISK_CACHE_SIZE = 100 * 1024 * 1024;
    public static final int MAX_MEMORY_CACHE_SIZE = 10 * 1024 * 1024;

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //设置磁盘缓存的路径 path
        String path = context.getExternalCacheDir().getPath();
        final File cacheDir = new File(path);
        builder.setDiskCache(new DiskCache.Factory() {
            @Override
            public DiskCache build() {
                return DiskLruCacheWrapper.get(cacheDir, DISK_CACHE_SIZE);
            }
        });
        //设置内存缓存大小，一般默认使用glide内部的默认值
        builder.setMemoryCache(new LruResourceCache(MAX_MEMORY_CACHE_SIZE));
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
