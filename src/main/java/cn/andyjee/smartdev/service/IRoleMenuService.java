package cn.andyjee.smartdev.service;

import cn.andyjee.smartdev.po.RoleMenuPo;

import java.util.List;


/**
 * 角色与菜单对应关系
 *
 * @author AndyJee
 */
public interface IRoleMenuService extends IBaseService<RoleMenuPo> {

    void saveOrUpdate(Long roleId, List<Long> menuIdList);

    /**
     * 根据角色ID，获取菜单ID列表
     */
    List<Long> queryMenuIdList(Long roleId);

    /**
     * 根据角色ID数组，批量删除
     */
    int deleteBatch(Long[] roleIds);

}
