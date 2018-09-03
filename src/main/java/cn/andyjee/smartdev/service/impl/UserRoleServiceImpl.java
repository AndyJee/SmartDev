package cn.andyjee.smartdev.service.impl;

import cn.andyjee.smartdev.dao.UserRoleDao;
import cn.andyjee.smartdev.po.UserRolePo;
import cn.andyjee.smartdev.service.IUserRoleService;
import cn.andyjee.smartdev.utils.MapPlus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 用户与角色对应关系
 *
 * @author AndyJee
 */
@Service
public class UserRoleServiceImpl extends BaseService<UserRoleDao, UserRolePo> implements IUserRoleService {

    @Override
    public void saveOrUpdate(Long userId, List<Long> roleIdList) {
        //先删除用户与角色关系
        this.deleteByMap(MapPlus.newMap().put("user_id", userId));

        if (roleIdList == null || roleIdList.size() == 0) {
            return;
        }

        //保存用户与角色关系
        List<UserRolePo> list = new ArrayList<>(roleIdList.size());
        for (Long roleId : roleIdList) {
            UserRolePo sysUserRoleEntity = new UserRolePo();
            sysUserRoleEntity.setUserId(userId);
            sysUserRoleEntity.setRoleId(roleId);

            list.add(sysUserRoleEntity);
        }
        this.insertBatch(list);
    }

    @Override
    public List<Long> queryRoleIdList(Long userId) {
        return baseMapper.queryRoleIdList(userId);
    }

    @Override
    public int deleteBatch(Long[] roleIds) {
        return baseMapper.deleteBatch(roleIds);
    }
}
