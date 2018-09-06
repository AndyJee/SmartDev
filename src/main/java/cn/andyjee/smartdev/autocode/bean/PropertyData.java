package cn.andyjee.smartdev.autocode.bean;

import lombok.Data;

/**
 * 属性列表
 *
 * @author AndyJee
 */
@Data
public class PropertyData {

    /**
     * 序号
     */
    private Integer id;

    /**
     * 字段类型
     */
    private String type;

    /**
     * 字段类型长度
     */
    private String typeLength;

    /**
     * 字段英文名称
     */
    private String nameEn;

    /**
     * 字段中文名称
     */
    private String nameCn;

    /**
     * 是否为关键字字段
     */
    private Boolean isKey;

    /**
     * 是否可以为空
     */
    private Boolean canNull;

    /**
     * 默认值
     */
    private String defaultValue;
}
