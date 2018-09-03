package cn.andyjee.smartdev.service.impl;

import cn.andyjee.smartdev.dao.RoleDao;
import cn.andyjee.smartdev.exception.SmartDevException;
import cn.andyjee.smartdev.po.RolePo;
import cn.andyjee.smartdev.service.IRoleMenuService;
import cn.andyjee.smartdev.service.IRoleService;
import cn.andyjee.smartdev.service.IUserRoleService;
import cn.andyjee.smartdev.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author AndyJee
 */
@Service
public class RoleServiceImpl extends BaseService<RoleDao, RolePo> implements IRoleService {

    @Resource
    private IRoleMenuService iRoleMenuService;
    @Resource
    private IUserService iUserService;
    @Resource
    private IUserRoleService iUserRoleService;

    @Override
    public Page<RolePo> queryPage(String roleName, Long createUserId, int current, int size) {
        Page<RolePo> page = this.selectPage(
                new Page(current, size),
                new EntityWrapper<RolePo>()
                        .like(StrUtil.isNotBlank(roleName), "role_name", roleName)
                        .eq(createUserId != null, "create_user_id", createUserId)
        );

        return page;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(RolePo role) {
        role.setCreateTime(new Date());
        this.insert(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        iRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(RolePo role) {
        this.updateById(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        iRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.deleteBatchIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        iRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        iUserRoleService.deleteBatch(roleIds);
    }


    @Override
    public List<Long> queryRoleIdList(Long createUserId) {
        return baseMapper.queryRoleIdList(createUserId);
    }

    /**
     * 检查权限是否越权
     */
    private void checkPrems(RolePo role) {

        //查询用户所拥有的菜单列表
        List<Long> menuIdList = iUserService.queryAllMenuId(role.getCreateUserId());

        //判断是否越权
        if (!menuIdList.containsAll(role.getMenuIdList())) {
            throw new SmartDevException("新增角色的权限，已超出你的权限范围");
        }
    }
}
