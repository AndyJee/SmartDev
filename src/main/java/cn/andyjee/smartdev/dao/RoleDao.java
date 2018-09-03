package cn.andyjee.smartdev.dao;

import cn.andyjee.smartdev.po.RolePo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色管理
 *
 * @author AndyJee
 */
@Mapper
public interface RoleDao extends IBaseDao<RolePo> {

    /**
     * 查询用户创建的角色ID列表
     */
    List<Long> queryRoleIdList(Long createUserId);
}
