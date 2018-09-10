package cn.andyjee.smartdev.autocode.generator;

import cn.andyjee.smartdev.autocode.bean.EnumData;
import com.xiaoleilu.hutool.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成代码工具
 */
public class EnumCodeGenerator extends BaseCodeGenerator {

    /**
     * 生成Enum
     *
     * @param codeBasePath 代码生成目录
     * @param enumData     枚举对象
     */
    public static void generatorCode(String codeBasePath, EnumData enumData) {

        String codePackage = enumData.getCodePackage();

        /* 1-创建文件*/
        File codeFile = createCodeFile(codeBasePath, codePackage, enumData.getEntityNameEn(), AutoCodeFileType.ENUM);


        /* 2-编写文件内容 */
        List<String> codeLines = new ArrayList();

        //package
        codeLines.add("package " + codePackage + ".enums;");
        codeLines.add("");

        //import枚举
        codeLines.add("import com.baomidou.mybatisplus.enums.IEnum;");
        codeLines.add("");
        codeLines.add("import java.io.Serializable;");
        codeLines.add("import java.util.*;");
        codeLines.add("");

        //class
        codeLines.add("/**");
        codeLines.add(" * " + enumData.getEntityNameCn());
        codeLines.add(" *");
        codeLines.add(" * @author SmartDev AutoCode v1.0");
        codeLines.add(" */");
        codeLines.add("public enum " + enumData.getEntityNameEn() + " implements IEnum {");
        codeLines.add("");
        codeLines.add("    //TODO SystemLogTypeEnum的属性值");
        codeLines.add("    DEFAULT(-1, \"默认值\"),");
        codeLines.add("    ;");
        codeLines.add("");
        codeLines.add("    /**");
        codeLines.add("     * 代码，一般推荐非0整数");
        codeLines.add("     */");
        codeLines.add("    private Integer code;");
        codeLines.add("");
        codeLines.add("    /**");
        codeLines.add("     * 描述");
        codeLines.add("     */");
        codeLines.add("    private String memo;");
        codeLines.add("");
        codeLines.add("    " + enumData.getEntityNameEn() + "(Integer code, String memo) {");
        codeLines.add("        this.code = code;");
        codeLines.add("        this.memo = memo;");
        codeLines.add("    }");
        codeLines.add("");
        codeLines.add("    @Override");
        codeLines.add("    public Serializable getValue() {");
        codeLines.add("        return this.name();");
        codeLines.add("    }");
        codeLines.add("");
        codeLines.add("}");

        /* 3- 写入文件 */
        FileUtil.appendUtf8Lines(codeLines, codeFile);
    }

}