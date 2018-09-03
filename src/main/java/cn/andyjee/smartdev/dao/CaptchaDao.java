package cn.andyjee.smartdev.dao;

import cn.andyjee.smartdev.po.CaptchaPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 验证码
 *
 * @author AndyJee
 */
@Mapper
public interface CaptchaDao extends IBaseDao<CaptchaPo> {

}
