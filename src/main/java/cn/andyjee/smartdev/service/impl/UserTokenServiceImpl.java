package cn.andyjee.smartdev.service.impl;

import cn.andyjee.smartdev.dao.UserTokenDao;
import cn.andyjee.smartdev.po.UserTokenPo;
import cn.andyjee.smartdev.service.IUserTokenService;
import cn.andyjee.smartdev.vo.R;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;


@Service
public class UserTokenServiceImpl extends BaseService<UserTokenDao, UserTokenPo> implements IUserTokenService {

    private static final long USER_TOKEN_EXPIRE = 3600 * 12 * 1000;

    @Override
    public R createToken(long userId) {
        //生成一个token
        String token = this.generateToken();
        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + USER_TOKEN_EXPIRE);

        //判断是否已存在过token
        UserTokenPo tokenEntity = this.selectById(userId);
        if (tokenEntity == null) {
            tokenEntity = new UserTokenPo();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            this.insert(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            this.updateById(tokenEntity);
        }

        R r = R.ok()
                .put("token", token)
                .put("expire", USER_TOKEN_EXPIRE);

        return r;
    }

    @Override
    public void changeToken(long userId) {
        //生成一个token
        String token = this.generateToken();

        //修改token
        UserTokenPo tokenEntity = new UserTokenPo();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }

    @Override
    public UserTokenPo queryByToken(String token) {
        return null;
    }

    @Override
    public void logout(Long userId) {

    }

    /**
     * 生成Token字符串的算法
     *
     * @return
     */
    private String generateToken() {
        //直接使用UUID工具
        return UUID.randomUUID().toString();
    }

}
