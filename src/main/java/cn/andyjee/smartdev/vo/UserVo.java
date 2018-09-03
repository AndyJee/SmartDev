package cn.andyjee.smartdev.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用于给客户展示的用户的对象
 *
 * @author xulifan
 */
@Data
@ApiModel(description = "用于展示的用户对象，再AgentVo和CustomerVo中出现")
public class UserVo {

    /**
     * 用户ID
     */
    @ApiModelProperty(value = "用户ID")
    private Long userId;

    /**
     * 用户名
     * 此用户名登录使用
     */
    @ApiModelProperty(value = "用户名，此用户名登录使用")
    private String userName;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;

    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
    private String email;

    /**
     * 手机号
     */
    @ApiModelProperty(value = "手机号")
    private String mobile;

    /**
     * 状态
     * 0:禁用;1:正常
     */
    @ApiModelProperty(value = "状态。0:禁用;1:正常")
    private Integer status;

    /**
     * 创建者ID
     */
    @ApiModelProperty(value = "创建者ID")
    private Long createUserId;
}
