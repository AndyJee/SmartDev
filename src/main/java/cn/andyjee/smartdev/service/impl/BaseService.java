package cn.andyjee.smartdev.service.impl;

import cn.andyjee.smartdev.dao.IBaseDao;
import cn.andyjee.smartdev.po.IBasePo;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 抽象服务
 *
 * @author AndyJee
 */

public abstract class BaseService<S extends IBaseDao<T>, T extends IBasePo> extends ServiceImpl<S, T> {

    /**
     * 系统日志
     */
    protected Logger logger = LoggerFactory.getLogger(getClass());


    public Logger getLogger() {
        return logger;
    }

}
