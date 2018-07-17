package com.biminds.framework.cache.redis;

/**
 * @author Kevin Lv
 * @version V1.0
 * @Title RedisClient</   p>
 * @Project runlin-framework
 * @Package com.runlin.framework.cache.redis
 * @date 2016年8月15日 下午4:24:11
 */
public interface RedisClient {

    /**
     * @param @param  key
     * @param @param  value
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: set
     * @Description: do redis set
     */
    public String set(String key, String value);

    /**
     * @param @param  key
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: get
     * @Description: do redis get
     */
    public String get(String key);

    /**
     * @param @param  hkey
     * @param @param  key
     * @param @return 设定文件
     * @return String    返回类型
     * @throws
     * @Title: hget
     * @Description: do redis hget
     */
    public String hget(String hkey, String key);

    /**
     * @param @param  hkey
     * @param @param  key
     * @param @param  value
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: hset
     * @Description: do redis hset
     */
    public long hset(String hkey, String key, String value);

    /**
     * @param @param  hkey
     * @param @param  key
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: hdel
     * @Description: do redis hdel
     */
    public long hdel(String hkey, String key);

    /**
     * @param @param  key
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: incr
     * @Description: do redis increment
     */
    public String incr(String key);

    /**
     * @param @param  key
     * @param @param  second
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: expire
     * @Description: do redis expire time, unit is second
     */
    public long expire(String key, int second);

    /**
     * @param @param  key
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: ttl
     * @Description: do redis ttl
     */
    public long ttl(String key);

    /**
     * @param @param  key
     * @param @return 设定文件
     * @return long    返回类型
     * @throws
     * @Title: del
     * @Description: do redis del by key
     */
    public long del(String key);

}
