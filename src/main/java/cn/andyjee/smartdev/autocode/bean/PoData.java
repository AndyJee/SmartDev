package cn.andyjee.smartdev.autocode.bean;

import lombok.Data;

import java.util.List;

/**
 * Excel中描述的 实体
 */
@Data
public class PoData {

    /**
     * 代码所在业务包（此包下将生成po,dao等子包）
     */
    private String codePackage;

    /**
     * 实体英文名称
     */
    private String entityNameEn;

    /**
     * 实体中文名称
     */
    private String entityNameCn;

    /**
     * 数据库表名
     */
    private String tableName;

    /**
     * 属性列表
     */
    private List<PropertyData> propertyList;

}
