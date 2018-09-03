package cn.andyjee.smartdev.po;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 用户与角色对应关系
 *
 * @author AndyJee
 */
@Data
@TableName("um_user_role")
public class UserRolePo implements IBasePo {

    @TableId
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}
