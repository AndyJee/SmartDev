package cn.andyjee.smartdev.dao;

import cn.andyjee.smartdev.po.UserTokenPo;
import org.apache.ibatis.annotations.Mapper;

/**
 * 系统用户Token
 *
 * @author AndyJee
 */
@Mapper
public interface UserTokenDao extends IBaseDao<UserTokenPo> {

    UserTokenPo queryByToken(String token);

}
