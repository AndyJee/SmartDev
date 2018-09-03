package cn.andyjee.smartdev.po;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.util.Date;


/**
 * 用户令牌，用于表明用户登录在一段时间内的有效性
 * 在用户登录时创建新令牌，登出时变更令牌
 *
 * @author AndyJee
 */
@Data
@TableName("um_user_token")
public class UserTokenPo implements IBasePo {

    /**
     * 用户ID
     */
    @TableId(type = IdType.INPUT)
    private Long userId;

    /**
     * 用户Token值
     * 使用唯一字符即可UUID
     */
    private String token;

    /**
     * 更新时间，创建令牌的时间
     */
    private Date updateTime;

    /**
     * 过期时间，超过此时间的令牌失效
     */
    private Date expireTime;

}
