package cn.andyjee.smartdev.po;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * 菜单
 *
 * @author AndyJee
 */
@Data
@TableName("um_menu")
public class MenuPo implements IBasePo {

    /**
     * 菜单ID
     */
    @TableId
    private Long menuId;

    /**
     * 父菜单ID，一级菜单为0
     */
    @NotBlank(message = "父菜单ID不能为空")
    private Long parentId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    private String menuName;

    /**
     * 菜单URL
     */
    private String url;

    /**
     * 授权(多个用逗号分隔，如：user:list,user:create)
     */
    private String perms;

    /**
     * 类型     0：目录   1：菜单   2：按钮
     */
    private Integer type;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 排序
     */
    private Integer orderNum;

    /**
     * 父菜单名称
     */
    @TableField(exist = false)
    private String parentName;

//    /**
//     * ztree属性
//     */
//    @TableField(exist = false)
//    private Boolean open;
//
    /**
     * 存放树形结构时
     */
    @TableField(exist = false)
    private List<?> list;
}
