package cn.andyjee.smartdev.controller;

import cn.andyjee.smartdev.po.UserPo;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Controller抽象类
 *
 * @author AndyJee
 */
public abstract class BaseController {

    /**
     * 系统日志
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 获取用户对象
     *
     * @return 用户对象
     */
    protected UserPo getUser() {
        return (UserPo) SecurityUtils.getSubject().getPrincipal();
    }

    /**
     * 获取当前登录用户ID
     *
     * @return 有登录时返回ID，否则返回null
     */
    protected Long getUserId() {
        UserPo userPo = getUser();
        return userPo == null ? null : userPo.getUserId();
    }

}
