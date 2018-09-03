package cn.andyjee.smartdev.service;

import cn.andyjee.smartdev.po.UserPo;

import java.util.ArrayList;
import java.util.List;


/**
 * 系统用户
 *
 * @author AndyJee
 */
public interface IUserService extends IBaseService<UserPo> {

    /**
     * 查询用户的所有权限
     *
     * @param userId 用户ID
     */
    List<String> queryAllPerms(Long userId);

    /**
     * 查询用户的所有菜单ID
     */
    List<Long> queryAllMenuId(Long userId);

    /**
     * 根据用户名，查询系统用户
     */
    UserPo queryByUserName(String username);

    /**
     * 保存用户
     */
    void save(UserPo user);

    /**
     * 保存用户
     *
     * @param userName
     * @param password
     * @param email
     * @param mobile
     * @param status
     * @param userCreatorId
     * @param roleList
     * @return
     */
    UserPo save(String userName, String password, String email, String mobile, Integer status, Long userCreatorId, ArrayList<Long> roleList);

    /**
     * 修改用户
     */
    void update(UserPo user);

    /**
     * 删除用户
     */
    void deleteBatch(Long[] userIds);

    /**
     * 修改密码
     *
     * @param userId      用户ID
     * @param password    原密码
     * @param newPassword 新密码
     * @return 是否成功
     */
    boolean updatePassword(Long userId, String password, String newPassword);
}
