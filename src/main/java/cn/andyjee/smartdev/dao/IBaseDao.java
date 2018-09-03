package cn.andyjee.smartdev.dao;

import cn.andyjee.smartdev.po.IBasePo;
import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * DAO接口
 *
 * @author AndyJee
 */
public interface IBaseDao<T extends IBasePo> extends BaseMapper<T> {

}