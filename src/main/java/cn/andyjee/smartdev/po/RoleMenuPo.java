package cn.andyjee.smartdev.po;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

/**
 * 角色与菜单对应关系
 *
 * @author AndyJee
 */
@Data
@TableName("um_role_menu")
public class RoleMenuPo implements IBasePo {

    @TableId
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID
     */
    private Long menuId;

}
