package cn.andyjee.smartdev.dao;

import cn.andyjee.smartdev.po.UserRolePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户与角色对应关系
 *
 * @author AndyJee
 */
@Mapper
public interface UserRoleDao extends IBaseDao<UserRolePo> {

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);


    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
