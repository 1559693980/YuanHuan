package com.hzc.yuanhuan.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
@Component
public class RedisTemplateUtil {
    private static final Logger logger = LoggerFactory.getLogger(RedisTemplateUtil.class);
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    public RedisTemplateUtil() {
    }

    public Set<String> keys(String keys) {
        try {
            return this.redisTemplate.keys(keys);
        } catch (Exception var3) {
            logger.error("keys", var3);
            return null;
        }
    }

    public boolean expire(String key, long timeout) {
        try {
            if (timeout > 0L) {
                this.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
            }

            return true;
        } catch (Exception var5) {
            logger.error("expire", var5);
            return false;
        }
    }

    public long getExpire(String key) {
        return this.redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    public boolean hasKey(String key) {
        try {
            return this.redisTemplate.hasKey(key);
        } catch (Exception var3) {
            logger.error("hasKey", var3);
            return false;
        }
    }

    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                this.redisTemplate.delete(key[0]);
            } else {
                this.redisTemplate.delete((Collection<String>) CollectionUtils.arrayToList(key));
            }
        }

    }

    public String get(String key) {
        if (key == null) {
            return null;
        } else {
            Object result = this.redisTemplate.opsForValue().get(key);
            return result != null ? result.toString() : null;
        }
    }

    public boolean set(String key, Object value) {
        try {
            this.redisTemplate.opsForValue().set(key, value);
            return true;
        } catch (Exception var4) {
            logger.error("set(String key, Object value)", var4);
            return false;
        }
    }

    public boolean set(String key, Object value, long timeout) {
        try {
            if (timeout > 0L) {
                this.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
            } else {
                this.set(key, value);
            }

            return true;
        } catch (Exception var6) {
            logger.error("set(String key, Object value, long timeout)", var6);
            return false;
        }
    }

    public long incr(String key, long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递增因子必须大于0");
        } else {
            return this.redisTemplate.opsForValue().increment(key, delta);
        }
    }

    public long decr(String key, long delta) {
        if (delta < 0L) {
            throw new RuntimeException("递减因子必须大于0");
        } else {
            return this.redisTemplate.opsForValue().increment(key, -delta);
        }
    }

    public Object hget(String key, String item) {
        return this.redisTemplate.opsForHash().get(key, item);
    }

    public Map<Object, Object> hmget(String key) {
        return this.redisTemplate.opsForHash().entries(key);
    }

    public boolean hmset(String key, Map<String, Object> map) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception var4) {
            logger.error("hmset(String key, Map<String, Object> map)", var4);
            return false;
        }
    }

    public boolean hmset(String key, Map<String, Object> map, long timeout) {
        try {
            this.redisTemplate.opsForHash().putAll(key, map);
            if (timeout > 0L) {
                this.expire(key, timeout);
            }

            return true;
        } catch (Exception var6) {
            logger.error("hmset(String key, Map<String, Object> map, long timeout)", var6);
            return false;
        }
    }

    public boolean hset(String key, String item, Object value) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception var5) {
            logger.error("hset(String key, String item, Object value)", var5);
            return false;
        }
    }

    public boolean hset(String key, String item, Object value, long timeout) {
        try {
            this.redisTemplate.opsForHash().put(key, item, value);
            if (timeout > 0L) {
                this.expire(key, timeout);
            }

            return true;
        } catch (Exception var7) {
            logger.error("hset(String key, String item, Object value, long timeout)", var7);
            return false;
        }
    }

    public void hdel(String key, Object... item) {
        this.redisTemplate.opsForHash().delete(key, item);
    }

    public boolean hHasKey(String key, String item) {
        return this.redisTemplate.opsForHash().hasKey(key, item);
    }

    public double hincr(String key, String item, double by) {
        return this.redisTemplate.opsForHash().increment(key, item, by);
    }

    public double hdecr(String key, String item, double by) {
        return this.redisTemplate.opsForHash().increment(key, item, -by);
    }

    public Set<Object> sGet(String key) {
        try {
            return this.redisTemplate.opsForSet().members(key);
        } catch (Exception var3) {
            logger.error("sGet(String key)", var3);
            return null;
        }
    }

    public boolean sHasKey(String key, Object value) {
        try {
            return this.redisTemplate.opsForSet().isMember(key, value);
        } catch (Exception var4) {
            logger.error("sHasKey(String key, Object value)", var4);
            return false;
        }
    }

    public long sSet(String key, Object... values) {
        try {
            return this.redisTemplate.opsForSet().add(key, values);
        } catch (Exception var4) {
            logger.error("sSet(String key, Object... values)", var4);
            return 0L;
        }
    }

    public long sSetAndTime(String key, long timeout, Object... values) {
        try {
            Long count = this.redisTemplate.opsForSet().add(key, values);
            if (timeout > 0L) {
                this.expire(key, timeout);
            }

            return count;
        } catch (Exception var6) {
            logger.error("sSetAndTime(String key, long timeout, Object... values)", var6);
            return 0L;
        }
    }

    public long sGetSetSize(String key) {
        try {
            return this.redisTemplate.opsForSet().size(key);
        } catch (Exception var3) {
            logger.error("sGetSetSize(String key)", var3);
            return 0L;
        }
    }

    public long setRemove(String key, Object... values) {
        try {
            Long count = this.redisTemplate.opsForSet().remove(key, values);
            return count;
        } catch (Exception var4) {
            logger.error("setRemove(String key, Object... values)", var4);
            return 0L;
        }
    }

    public List<Object> lGet(String key, long start, long end) {
        try {
            return this.redisTemplate.opsForList().range(key, start, end);
        } catch (Exception var7) {
            logger.error("lGet(String key, long start, long end)", var7);
            return null;
        }
    }

    public long lGetListSize(String key) {
        try {
            return this.redisTemplate.opsForList().size(key);
        } catch (Exception var3) {
            logger.error("lGetListSize(String key)", var3);
            return 0L;
        }
    }

    public Object lGetIndex(String key, long index) {
        try {
            return this.redisTemplate.opsForList().index(key, index);
        } catch (Exception var5) {
            logger.error("lGetIndex(String key, long index)", var5);
            return null;
        }
    }

    public boolean rPush(String key, Object value) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            return true;
        } catch (Exception var4) {
            logger.error("rPush(String key, Object value)", var4);
            return false;
        }
    }

    public boolean rPush(String key, Object value, long timeout) {
        try {
            this.redisTemplate.opsForList().rightPush(key, value);
            if (timeout > 0L) {
                this.expire(key, timeout);
            }

            return true;
        } catch (Exception var6) {
            logger.error("rPush(String key, Object value, long timeout)", var6);
            return false;
        }
    }

    public boolean rPushAll(String key, List<Object> value) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            return true;
        } catch (Exception var4) {
            logger.error("rPushAll(String key, List<Object> value)", var4);
            return false;
        }
    }

    public boolean rPushAll(String key, List<Object> value, long timeout) {
        try {
            this.redisTemplate.opsForList().rightPushAll(key, value);
            if (timeout > 0L) {
                this.expire(key, timeout);
            }

            return true;
        } catch (Exception var6) {
            logger.error("rPushAll(String key, List<Object> value, long timeout)", var6);
            return false;
        }
    }

    public boolean lPush(String key, Object value) {
        try {
            this.redisTemplate.opsForList().leftPush(key, value);
            return true;
        } catch (Exception var4) {
            logger.error("lPush(String key, Object value)", var4);
            return false;
        }
    }

    public boolean lPush(String key, Object value, long timeout) {
        try {
            this.redisTemplate.opsForList().leftPush(key, value);
            if (timeout > 0L) {
                this.expire(key, timeout);
            }

            return true;
        } catch (Exception var6) {
            logger.error("lPush(String key, Object value, long timeout)", var6);
            return false;
        }
    }

    public boolean lPushAll(String key, List<Object> value) {
        try {
            this.redisTemplate.opsForList().leftPushAll(key, value);
            return true;
        } catch (Exception var4) {
            logger.error("(String key, List<Object> value)", var4);
            return false;
        }
    }

    public boolean lPushAll(String key, List<Object> value, long timeout) {
        try {
            this.redisTemplate.opsForList().leftPushAll(key, value);
            if (timeout > 0L) {
                this.expire(key, timeout);
            }

            return true;
        } catch (Exception var6) {
            logger.error("lPushAll(String key, List<Object> value, long timeout)", var6);
            return false;
        }
    }

    public boolean lUpdateIndex(String key, long index, Object value) {
        try {
            this.redisTemplate.opsForList().set(key, index, value);
            return true;
        } catch (Exception var6) {
            logger.error("lUpdateIndex(String key, long index, Object value)", var6);
            return false;
        }
    }

    public long lRemove(String key, long count, Object value) {
        try {
            Long remove = this.redisTemplate.opsForList().remove(key, count, value);
            return remove;
        } catch (Exception var6) {
            logger.error("lRemove(String key, long count, Object value)", var6);
            return 0L;
        }
    }

    public Object lPop(String key) {
        try {
            return this.redisTemplate.opsForList().leftPop(key);
        } catch (Exception var3) {
            logger.error("lPop(String key)", var3);
            return null;
        }
    }

    public Object lPop(String key, long timeout) {
        try {
            return this.redisTemplate.opsForList().leftPop(key, timeout, TimeUnit.SECONDS);
        } catch (Exception var5) {
            logger.error("lPop(String key, long timeout)", var5);
            return null;
        }
    }

    public Object rPop(String key) {
        try {
            return this.redisTemplate.opsForList().rightPop(key);
        } catch (Exception var3) {
            logger.error("rPop(String key)", var3);
            return null;
        }
    }

    public Object rPop(String key, long timeout) {
        try {
            return this.redisTemplate.opsForList().rightPop(key, timeout, TimeUnit.SECONDS);
        } catch (Exception var5) {
            logger.error("rPop(String key, long timeout)", var5);
            return null;
        }
    }

    public Object bLPop(final String key, final int timeout) {
        try {
            Object obj = this.redisTemplate.executePipelined(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.bLPop(timeout, new byte[][]{key.getBytes()});
                }
            }, new StringRedisSerializer());
            return obj;
        } catch (Exception var4) {
            logger.error("bLPop(String key, int timeout)", var4);
            return null;
        }
    }

    public Object bRPop(final String key, final int timeout) {
        try {
            Object obj = this.redisTemplate.executePipelined(new RedisCallback<Object>() {
                public Object doInRedis(RedisConnection connection) throws DataAccessException {
                    return connection.bRPop(timeout, new byte[][]{key.getBytes()});
                }
            }, new StringRedisSerializer());
            return obj;
        } catch (Exception var4) {
            logger.error("bRPop(String key, int timeout)", var4);
            return null;
        }
    }

    public Object getCacheObject(final String key){
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
}
