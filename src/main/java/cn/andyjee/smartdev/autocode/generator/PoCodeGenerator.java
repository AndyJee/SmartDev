package cn.andyjee.smartdev.autocode.generator;

import cn.andyjee.smartdev.autocode.bean.EnumData;
import cn.andyjee.smartdev.autocode.bean.PoData;
import cn.andyjee.smartdev.autocode.bean.PropertyData;
import com.xiaoleilu.hutool.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成代码工具
 */
public class PoCodeGenerator extends BaseCodeGenerator {

    /**
     * 生成PO
     *
     * @param codeBasePath  代码生成目录
     * @param poInExcelList PO对象列表
     */
    public static void generatorCode(String codeBasePath, List<PoData> poInExcelList) {
        for (PoData poInExcel : poInExcelList) {
            PoCodeGenerator.generatorPoCode(codeBasePath, poInExcel);
        }
    }

    /**
     * 生成PO
     *
     * @param codeBasePath 代码生成目录
     * @param poInExcel    PO对象
     */
    public static void generatorPoCode(String codeBasePath, PoData poInExcel) {

        List<PropertyData> propertyList = poInExcel.getPropertyList();
        String codePackage = poInExcel.getCodePackage();

        /* 1-创建文件*/
        File codeFile = createCodeFile(codeBasePath, codePackage, poInExcel.getEntityNameEn(), AutoCodeFileType.PO);

        /* 2-编写文件内容 */
        List<String> codeLines = new ArrayList();

        //package
        codeLines.add("package " + codePackage + ".po;");
        codeLines.add("");

        //import枚举
        for (PropertyData propertyData : propertyList) {
            if (propertyData.getType().endsWith("Enum")) {

                //创建Enum文件
                EnumData enumData = new EnumData();
                enumData.setCodePackage(codePackage);
                enumData.setEntityNameEn(propertyData.getType());
                enumData.setEntityNameCn("枚举" + propertyData.getType());
                EnumCodeGenerator.generatorCode(codeBasePath, enumData);

                codeLines.add("import " + codePackage + ".enums." + propertyData.getType() + ";");
            }
        }

        codeLines.add("import cn.andyjee.smartdev.po.IBasePo;");
        codeLines.add("import com.baomidou.mybatisplus.annotations.*;");
        codeLines.add("import lombok.Data;");
        codeLines.add("");
        codeLines.add("import javax.validation.constraints.*;");
        codeLines.add("import java.util.*;");
        codeLines.add("");

        //class
        codeLines.add("/**");
        codeLines.add(" * " + poInExcel.getEntityNameCn());
        codeLines.add(" *");
        codeLines.add(" * @author SmartDev AutoCode v1.0");
        codeLines.add(" */");
        codeLines.add("@Data");
        codeLines.add("@TableName(\"" + poInExcel.getTableName() + "\")");
        codeLines.add("public class " + poInExcel.getEntityNameEn() + "Po implements IBasePo {");
        codeLines.add("");

        //po
        for (PropertyData propertyData : propertyList) {

            codeLines.add("    /**");
            codeLines.add("     * " + propertyData.getNameCn());
            codeLines.add("     */");

            if (propertyData.getIsKey()) {
                codeLines.add("    @TableId");
            }
            if (!propertyData.getCanNull()) {
                codeLines.add("    @NotBlank");
            }
            codeLines.add("    private " + propertyData.getType() + " " + propertyData.getNameEn() + ";");
            codeLines.add("");
        }

        codeLines.add("}");


        /* 3- 写入文件 */
        FileUtil.appendUtf8Lines(codeLines, codeFile);
    }
}