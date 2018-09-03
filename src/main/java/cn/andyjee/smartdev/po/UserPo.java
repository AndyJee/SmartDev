package cn.andyjee.smartdev.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户
 *
 * @author AndyJee
 */
@Data
@TableName("um_user")
public class UserPo implements IBasePo {

    /**
     * 用户ID
     */
    @TableId
    private Long userId;

    /**
     * 用户名
     * 此用户名登录使用
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * 邮箱
     */
    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空")
    private String mobile;

    /**
     * 状态
     * 0:禁用;1:正常
     */
    private Integer status;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色ID列表
     * 不作为数据库字段
     */
    @TableField(exist = false)
    private List<Long> roleIdList = new ArrayList<>();

    /**
     * 角色ID列表
     * 不作为数据库字段
     */
    @TableField(exist = false)
    private List<String> permsList = new ArrayList<>();

}
