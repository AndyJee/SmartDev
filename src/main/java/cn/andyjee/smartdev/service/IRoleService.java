package cn.andyjee.smartdev.service;

import cn.andyjee.smartdev.po.RolePo;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;


/**
 * 角色
 *
 * @author AndyJee
 */
public interface IRoleService extends IBaseService<RolePo> {

    Page<RolePo> queryPage(String roleName, Long createUserId, int current, int size);

    void save(RolePo role);

    void update(RolePo role);

    void deleteBatch(Long[] roleIds);

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
