package cn.andyjee.smartdev.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 登录对象
 *
 * @author AndyJee
 */
@Data
@ApiModel(description = "登录对象")
public class LoginVo {

    /**
     * 登录名
     */
    @ApiModelProperty(value = "登录名", required = true)
    private String username;

    /**
     * 密码MD5
     */
    @ApiModelProperty(value = "密码MD5", required = true)
    private String password;

    /**
     * 验证码
     */
    @ApiModelProperty(value = "验证码", required = true)
    private String captcha;

    /**
     * 与验证码配套的UUID
     */
    @ApiModelProperty(value = "与验证码配套的UUID", required = true)
    private String uuid;
}
