package cn.andyjee.smartdev.dao;

import cn.andyjee.smartdev.po.UserPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 系统用户
 *
 * @author AndyJee
 */
@Mapper
public interface UserDao extends IBaseDao<UserPo> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    UserPo queryByUserName(String userName);

}
