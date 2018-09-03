package cn.andyjee.smartdev.po;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * 角色
 *
 * @author AndyJee
 */
@Data
@TableName("um_role")
public class RolePo implements IBasePo {

    /**
     * 角色ID
     */
    @TableId
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    private String roleName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建者ID
     */
    private Long createUserId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 菜单列表
     */
    @TableField(exist = false)
    private List<Long> menuIdList;

}
