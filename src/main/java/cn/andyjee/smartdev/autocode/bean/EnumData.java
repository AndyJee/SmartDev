package cn.andyjee.smartdev.autocode.bean;

import lombok.Data;

/**
 * Excel中描述的 枚举
 */
@Data
public class EnumData {

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

}
