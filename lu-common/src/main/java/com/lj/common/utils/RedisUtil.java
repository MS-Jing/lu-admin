package com.lj.common.utils;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import io.lettuce.core.RedisClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author luojing
 * @version 1.0
 * @date 2021/11/1 17:27
 */
@Component
@ConditionalOnClass({RedisClient.class})
@Slf4j
public class RedisUtil {

    @Autowired
    private StringRedisTemplate redisTemplate;

    // =============================common============================

    /**
     * java对象转json字符串
     *
     * @param value Java对象
     * @return json字符串
     */
    private String toJson(Object value) {
        return JSONUtil.toJsonStr(value);
    }

    /**
     * json字符串换Java对象
     *
     * @param value json字符串
     * @param type  java对象类型
     * @param <T>   类型
     * @return Java对象
     */
    private <T> T toObject(String value, Class<T> type) {
        return JSONUtil.toBean(value, type);
    }

    /**
     * 给指定key设置失效时间
     *
     * @param key  键
     * @param time 默认为秒
     * @return 状态
     */
    public boolean expire(String key, long time) {
        return expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 给指定key设置失效时间
     *
     * @param key  键
     * @param time 时间
     * @param unit 时间单位
     * @return 状态
     */
    public boolean expire(String key, long time, TimeUnit unit) {
        try {
            if (time > 0) {
                redisTemplate.expire(key, time, unit);
            }
            return true;
        } catch (Exception e) {
            log.warn("设置失效时间失败:", e);
            return false;
        }
    }

    /**
     * 获取key的过期时间
     *
     * @param key  键
     * @param unit 时间单位
     * @return 时间
     */
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return 状态
     */
    public boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除一个或多个key
     *
     * @param keys 键
     */
    public void delete(String... keys) {
        if (keys != null && keys.length > 0) {
            if (keys.length == 1) {
                redisTemplate.delete(keys[0]);
            } else {
                redisTemplate.delete(Arrays.asList(keys));
            }
        }
    }

    // =============================String============================

    /**
     * 放入一个值
     *
     * @param key   键
     * @param value 值
     * @return 状态
     */
    public boolean set(String key, Object value) {
        try {
            //key为空会报 non null key required
            redisTemplate.opsForValue().set(key, toJson(value));
            return true;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return false;
        }
    }

    /**
     * 添加key时 设置过期时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间
     * @param unit  时间单位
     * @return 状态
     */
    public boolean set(String key, Object value, long time, TimeUnit unit) {
        try {
            // 过期时间小于 0会报   ERR invalid expire time in setex
            redisTemplate.opsForValue().set(key, toJson(value), time, unit);
            return true;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return false;
        }
    }

    /**
     * 获取一个key的值
     *
     * @param key 键
     * @return 值
     */
    public <T> T get(String key, Class<T> aClass) {
        try {
            String value;
            if (key == null || (value = redisTemplate.opsForValue().get(key)) == null) {
                return null;
            }
            return toObject(value, aClass);
        } catch (Exception e) {
            log.warn("失败: ", e);
        }
        return null;
    }

    /**
     * 获取一个key的值
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return key == null ? null : redisTemplate.opsForValue().get(key);
    }

    /**
     * 递增或递减
     *
     * @param key  键
     * @param date 大于0增加  小于0减少
     * @return 操作后的值
     */
    public Long incrOrDecr(String key, long date) {
        return redisTemplate.opsForValue().increment(key, date);
    }

    // =============================Map============================

    /**
     * 为一个hash放入一个值
     *
     * @param key 键
     * @param hk  hk
     * @param hv  hv
     * @return 状态
     */
    public boolean putHash(String key, String hk, Object hv) {
        try {
            redisTemplate.opsForHash().put(key, hk, toJson(hv));
            return true;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return false;
        }
    }

    /**
     * 批量put Hash
     *
     * @param key 键
     * @param map 值
     * @return 状态
     */
    public boolean putMultiHash(String key, Map<String, Object> map) {
        try {
            Map<String, String> temp = new HashMap<>();
            map.forEach((k, v) -> temp.put(k, toJson(v)));
            redisTemplate.opsForHash().putAll(key, temp);
            return true;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return false;
        }
    }

    /**
     * 获取hash的一个值
     *
     * @param key 键
     * @param hk  hk
     * @return 值
     */
    public Object getHash(String key, String hk) {
        return redisTemplate.opsForHash().get(key, hk);
    }

    public <T> T getHash(String key, String hk, Class<T> aClass) {
        try {
            String hv = (String) redisTemplate.opsForHash().get(key, hk);
            if (hv == null) {
                return null;
            }
            return toObject(hv, aClass);
        } catch (Exception e) {
            log.warn("失败: ", e);
        }
        return null;
    }

    /**
     * 批量获取key对应的hash的所有键值
     *
     * @param key 键
     * @return 值
     */
    public Map<Object, Object> getMultiHash(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 判断是否存在hk
     *
     * @param key 键
     * @param hk  hk
     * @return 状态
     */
    public boolean hasHashHk(String key, String hk) {
        return redisTemplate.opsForHash().hasKey(key, hk);
    }

    /**
     * 删除hash的hk
     *
     * @param key 键
     * @param hks hks
     */
    public void delHashHks(String key, Object... hks) {
        redisTemplate.opsForHash().delete(key, hks);
    }

    /**
     * 递增或递减
     *
     * @param key  键
     * @param hk   hk
     * @param date 大于0递增，小于0递减
     * @return 操作完成后当前的量
     */
    public long hashIncrOrDecr(String key, String hk, long date) {
        return redisTemplate.opsForHash().increment(key, hk, date);
    }

    // ============================set=============================

    /**
     * 添加set值
     *
     * @param key    键
     * @param values 值
     * @return 添加成功的个数
     */
    public long addSet(String key, Object... values) {
        try {
            String[] temp = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                temp[i] = toJson(values[i]);
            }
            return redisTemplate.opsForSet().add(key, temp);
        } catch (Exception e) {
            log.warn("失败: ", e);
            return 0;
        }
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return 值
     */
    public <T> Set<T> getAllSet(String key, Class<T> aClass) {
        try {
            Set<String> members = redisTemplate.opsForSet().members(key);
            if (CollUtil.isEmpty(members)) {
                return Collections.emptySet();
            }
            Set<T> objects = new HashSet<>();
            for (String member : members) {
                objects.add(toObject(member, aClass));
            }
            return objects;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return Collections.emptySet();
        }
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return 值
     */
    public Set<String> getAllSet(String key) {
        try {
            return redisTemplate.opsForSet().members(key);
        } catch (Exception e) {
            log.warn("失败: ", e);
            return Collections.emptySet();
        }
    }

    /**
     * 判断value是否在key的set中
     *
     * @param key   键
     * @param value 值
     * @return 判断状态
     */
    public boolean hasKeySet(String key, Object value) {
        try {
            return redisTemplate.opsForSet().isMember(key, toJson(value));
        } catch (Exception e) {
            log.warn("失败: ", e);
            return false;
        }
    }

    /**
     * 删除set的值
     *
     * @param key    键
     * @param values 值
     * @return 删除的个数
     */
    public long removeSet(String key, Object... values) {
        try {
            String[] temp = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                temp[i] = toJson(values[i]);
            }
            return redisTemplate.opsForSet().remove(key, temp);
        } catch (Exception e) {
            log.warn("失败: ", e);
            return 0;
        }
    }

    /**
     * 获取set的大小
     *
     * @param key 键
     * @return 大小
     */
    public long getSetSize(String key) {
        try {
            return redisTemplate.opsForSet().size(key);
        } catch (Exception e) {
            log.warn("失败: ", e);
            return 0;
        }
    }

    // ===============================list=================================

    /**
     * 获取list列表的值
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return 值
     */
    public List<String> getList(String key, long start, long end) {
        try {
            return redisTemplate.opsForList().range(key, start, end);
        } catch (Exception e) {
            log.warn("失败: ", e);
            return Collections.emptyList();
        }
    }

    /**
     * 获取list列表的值
     *
     * @param key   键
     * @param start 开始下标
     * @param end   结束下标
     * @return 值
     */
    public <T> List<T> getList(String key, long start, long end, Class<T> aClass) {
        try {
            List<String> range = redisTemplate.opsForList().range(key, start, end);
            if (CollUtil.isEmpty(range)){
                return Collections.emptyList();
            }
            List<T> objects = new ArrayList<>();
            for (String s : range) {
                objects.add(toObject(s, aClass));
            }
            return objects;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return Collections.emptyList();
        }
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return 长度
     */
    public long getListSize(String key) {
        try {
            return redisTemplate.opsForList().size(key);
        } catch (Exception e) {
            log.warn("失败: ", e);
            return 0;
        }
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 大于0 从第一个开始 小于0 从最后一个开始
     * @return 值
     */
    public <T> T getListIndex(String key, long index, Class<T> aClass) {
        try {
            String index1 = redisTemplate.opsForList().index(key, index);
            return toObject(index1, aClass);
        } catch (Exception e) {
            log.warn("失败: ", e);
            return null;
        }
    }

    /**
     * 将值从list右边放入
     *
     * @param key   键
     * @param value 值的列表
     * @return 放入状态
     */
    public boolean listRightPush(String key, Object value) {
        try {
            redisTemplate.opsForList().rightPush(key, toJson(value));
            return true;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return false;
        }
    }

    /**
     * 批量放入
     *
     * @param key   键
     * @param value 值的列表
     * @return 放入状态
     */
    public boolean listRightPushAll(String key, List<Object> value) {
        try {
            List<String> temp = new ArrayList<>();
            for (Object o : value) {
                temp.add(toJson(o));
            }
            redisTemplate.opsForList().rightPushAll(key, temp);
            return true;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return false;
        }
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     * @return 更新状态
     */
    public boolean updateListByIndex(String key, long index, Object value) {
        try {
            redisTemplate.opsForList().set(key, index, toJson(value));
            return true;
        } catch (Exception e) {
            log.warn("失败: ", e);
            return false;
        }
    }

    /**
     * 移除count个值为value 的元素
     *
     * @param key   键
     * @param count 移除个数
     * @param value 值
     * @return 移除个数
     */
    public long listRemoveCount(String key, long count, Object value) {
        try {
            return redisTemplate.opsForList().remove(key, count, toJson(value));
        } catch (Exception e) {
            log.warn("失败: ", e);
            return 0;
        }
    }

}
