package cn.andyjee.smartdev.service;

import cn.andyjee.smartdev.po.UserTokenPo;
import cn.andyjee.smartdev.vo.R;

/**
 * 用户Token
 *
 * @author
 */
public interface IUserTokenService extends IBaseService<UserTokenPo> {

    /**
     * 为指定用户创建token
     *
     * @param userId 用户ID
     */
    R createToken(long userId);

    /**
     * 改变token值，用于如取消登录后
     *
     * @param userId 用户ID
     */
    void changeToken(long userId);


    UserTokenPo queryByToken(String token);

    void logout(Long userId);
}
