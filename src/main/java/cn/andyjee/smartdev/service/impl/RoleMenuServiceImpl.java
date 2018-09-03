package cn.andyjee.smartdev.service.impl;

import cn.andyjee.smartdev.dao.RoleMenuDao;
import cn.andyjee.smartdev.po.RoleMenuPo;
import cn.andyjee.smartdev.service.IRoleMenuService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


/**
 * 角色与菜单对应关系
 *
 * @author AndyJee
 */
@Service
public class RoleMenuServiceImpl extends BaseService<RoleMenuDao, RoleMenuPo> implements IRoleMenuService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveOrUpdate(Long roleId, List<Long> menuIdList) {
        //先删除角色与菜单关系
        deleteBatch(new Long[]{roleId});

        if (menuIdList.size() == 0) {
            return;
        }

        //保存角色与菜单关系
        List<RoleMenuPo> list = new ArrayList<>(menuIdList.size());
        for (Long menuId : menuIdList) {
            RoleMenuPo sysRoleMenuEntity = new RoleMenuPo();
            sysRoleMenuEntity.setMenuId(menuId);
            sysRoleMenuEntity.setRoleId(roleId);

            list.add(sysRoleMenuEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryMenuIdList(Long roleId) {
        return baseMapper.queryMenuIdList(roleId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }

}
