package cn.andyjee.smartdev.service;

import cn.andyjee.smartdev.po.UserRolePo;

import java.util.List;


/**
 * 用户与角色对应关系
 *
 * @author AndyJee
 */
public interface IUserRoleService extends IBaseService<UserRolePo> {

    void saveOrUpdate(Long userId, List<Long> roleIdList);

    /**
     * 根据用户ID，获取角色ID列表
     */
    List<Long> queryRoleIdList(Long userId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);
}
