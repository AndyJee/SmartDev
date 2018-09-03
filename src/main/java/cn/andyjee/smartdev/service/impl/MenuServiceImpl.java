package cn.andyjee.smartdev.service.impl;

import cn.andyjee.smartdev.dao.MenuDao;
import cn.andyjee.smartdev.po.MenuPo;
import cn.andyjee.smartdev.service.IMenuService;
import cn.andyjee.smartdev.service.IRoleMenuService;
import cn.andyjee.smartdev.service.IUserService;
import cn.andyjee.smartdev.utils.MapPlus;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单管理
 *
 * @author AndyJee
 */
@Service
public class MenuServiceImpl extends BaseService<MenuDao, MenuPo> implements IMenuService {

    @Resource
    private IUserService iUserService;
    @Resource
    private IRoleMenuService iRoleMenuService;

    @Override
    public List<MenuPo> queryListParentId(Long parentId, List<Long> menuIdList) {
        List<MenuPo> menuList = queryListParentId(parentId);
        if (menuIdList == null) {
            return menuList;
        }

        List<MenuPo> userMenuList = new ArrayList<>();
        for (MenuPo menu : menuList) {
            if (menuIdList.contains(menu.getMenuId())) {
                userMenuList.add(menu);
            }
        }
        return userMenuList;
    }

    @Override
    public List<MenuPo> queryListParentId(Long parentId) {
        return baseMapper.queryListParentId(parentId);
    }

    @Override
    public List<MenuPo> queryNotButtonList() {
        return baseMapper.queryNotButtonList();
    }

    @Override
    public List<MenuPo> getUserMenuList(Long userId) {
        //用户菜单列表
        List<Long> menuIdList = iUserService.queryAllMenuId(userId);
        return this.selectBatchIds(menuIdList);
    }

    @Override
    public void delete(Long menuId) {
        //删除菜单
        this.deleteById(menuId);
        //删除菜单与角色关联
        iRoleMenuService.deleteByMap(MapPlus.newMap().put("menu_id", menuId));
    }

}
