package com.jmpergar.awesometext;

import android.support.v4.util.LruCache;
import android.text.style.ImageSpan;

class AwesomeTextViewCache {
    private final static int MAXIMUM_IN_MEMORY_CACHE_SIZE_IN_BYTES = 2 * 1024 * 1024;
    private LruCache<String, ImageSpan> lruCache;

    private static class SingletonHelper {
        private static final AwesomeTextViewCache INSTANCE = new AwesomeTextViewCache();
    }

    public static AwesomeTextViewCache getInstance() {
        return SingletonHelper.INSTANCE;
    }

    private AwesomeTextViewCache() {
        lruCache = new LruCache<>(MAXIMUM_IN_MEMORY_CACHE_SIZE_IN_BYTES);
    }

    public ImageSpan getCachedImageSpan(String text) {
        return lruCache.get(text);
    }

    public void cacheSpan(String text, ImageSpan span) {
        lruCache.put(text, span);
    }
}
