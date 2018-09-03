package cn.andyjee.smartdev.service;

import cn.andyjee.smartdev.po.IBasePo;
import com.baomidou.mybatisplus.service.IService;

/**
 * 服务接口
 * 包装MyBatisPlus
 *
 * @param <T> 基类为BasePo的实体类
 * @author AndyJee
 */
public interface IBaseService<T extends IBasePo> extends IService<T> {

}
