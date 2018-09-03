package cn.andyjee.smartdev.service;

import cn.andyjee.smartdev.po.MenuPo;

import java.util.List;


/**
 * 菜单管理
 *
 * @author AndyJee
 */
public interface IMenuService extends IBaseService<MenuPo> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId   父菜单ID
     * @param menuIdList 用户菜单ID
     */
    List<MenuPo> queryListParentId(Long parentId, List<Long> menuIdList);

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<MenuPo> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<MenuPo> queryNotButtonList();

    /**
     * 获取用户菜单列表
     */
    List<MenuPo> getUserMenuList(Long userId);

    /**
     * 删除
     */
    void delete(Long menuId);
}
