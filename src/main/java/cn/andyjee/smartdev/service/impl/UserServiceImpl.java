package cn.andyjee.smartdev.service.impl;

import cn.andyjee.smartdev.dao.UserDao;
import cn.andyjee.smartdev.po.UserPo;
import cn.andyjee.smartdev.service.IUserRoleService;
import cn.andyjee.smartdev.service.IUserService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * 用户管理服务
 *
 * @author AndyJee
 */
@Service
public class UserServiceImpl extends BaseService<UserDao, UserPo> implements IUserService {

    @Resource
    private IUserRoleService iUserRoleService;


    @Override
    public List<String> queryAllPerms(Long userId) {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public UserPo queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(UserPo user) {
        user.setCreateTime(new Date());
        //MD5加密
        user.setPassword(SecureUtil.md5(user.getPassword()));
        this.insert(user);

        //保存用户与角色关系
        iUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserPo save(String userName, String password, String email, String mobile, Integer status, Long userCreatorId, ArrayList<Long> roleList) {

        UserPo user = new UserPo();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setStatus(status);
        user.setCreateUserId(userCreatorId);
        user.setRoleIdList(roleList);
        user.setCreateTime(new Date());
        //MD5加密
        user.setPassword(SecureUtil.md5(user.getPassword()));
        this.insert(user);

        //保存用户与角色关系
        iUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(UserPo user) {
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(SecureUtil.md5(user.getPassword()));
        }
        this.updateById(user);

        //保存用户与角色关系
        iUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userId) {
        this.deleteBatchIds(Arrays.asList(userId));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        UserPo userEntity = new UserPo();
        userEntity.setPassword(SecureUtil.md5(newPassword));
        return this.update(userEntity,
                new EntityWrapper<UserPo>()
                        .eq("user_id", userId)
                        .eq("password", SecureUtil.md5(password)));
    }

}
