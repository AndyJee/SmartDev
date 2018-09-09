package cn.andyjee.smartdev.vo;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * 返回数据
 *
 * @author AndyJee
 */
public class R extends JSONObject {
    private static final long serialVersionUID = 1L;

    public R() {
        put("code", 0);
        put("msg", "success");
    }

    public static R error() {
        return error("未知异常，请联系管理员");
    }

    public static R error(String msg) {
        return error(-1, msg);
    }

    public static R error(int code, String msg) {
        R r = new R();
        r.put("code", code);
        r.put("msg", msg);
        return r;
    }

    public static R ok(String msg) {
        R r = new R();
        r.put("msg", msg);
        return r;
    }

    public static R ok(Map<String, Object> map) {
        R r = new R();
        r.putAll(map);
        return r;
    }

    public static R ok() {
        return new R();
    }

    /**
     * 增加指定关键字的值
     *
     * @param key   指定关键字。请与前台约定好。
     * @param value 值
     * @return 已增加值对的本对象
     */
    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * 增加关键字为data的值。一般推荐用于简单返回一个对象。
     *
     * @param value 值
     * @return 已增加值对的本对象
     */
    public R putData(Object value) {
        super.put("data", value);
        return this;
    }
}
