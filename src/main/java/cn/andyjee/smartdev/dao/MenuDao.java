package cn.andyjee.smartdev.dao;

import cn.andyjee.smartdev.po.MenuPo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 菜单管理
 *
 * @author AndyJee
 */
@Mapper
public interface MenuDao extends IBaseDao<MenuPo> {

    /**
     * 根据父菜单，查询子菜单
     *
     * @param parentId 父菜单ID
     */
    List<MenuPo> queryListParentId(Long parentId);

    /**
     * 获取不包含按钮的菜单列表
     */
    List<MenuPo> queryNotButtonList();

}
