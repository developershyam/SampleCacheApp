/**
package com.sample.coherence;

import org.springframework.stereotype.Service;

import com.tangosol.net.CacheFactory;
import com.tangosol.net.NamedCache;

// @Service
public class CoherenceCacheService {

	private NamedCache cache = null;
	private static final String USER_MAP = "mycache"; // "user-map";
	private static final long LOCK_TIMEOUT = -1;

	public CoherenceCacheService() {
		cache = CacheFactory.getCache(USER_MAP);
		cache.addMapListener(new UserMapListener());
	}

	public NamedCache getCache() {
		return cache;
	}

	public void setCache(NamedCache cache) {
		this.cache = cache;
	}

	public void addToCache(Object key, Object value) {
		// key is locked
		cache.lock(key, LOCK_TIMEOUT);
		try {
			// application logic
			cache.put(key, value);
		} finally {
			// key is unlocked
			cache.unlock(key);
		}
	}

	public void deleteFromCache(Object key) {
		// key is locked
		cache.lock(key, LOCK_TIMEOUT);
		try {
			// application logic
			cache.remove(key);
		} finally {
			// key is unlocked
			cache.unlock(key);
		}
	}
}

**/