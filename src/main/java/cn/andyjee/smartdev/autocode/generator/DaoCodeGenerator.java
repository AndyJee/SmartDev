package cn.andyjee.smartdev.autocode.generator;

import cn.andyjee.smartdev.autocode.bean.PoData;
import com.xiaoleilu.hutool.io.FileUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成代码工具
 */
public class DaoCodeGenerator extends BaseCodeGenerator {

    /**
     * 生成PO
     *
     * @param codeBasePath  代码生成目录
     * @param poInExcelList PO对象列表
     */
    public static void generatorCode(String codeBasePath, List<PoData> poInExcelList) {


        for (PoData poInExcel : poInExcelList) {
            DaoCodeGenerator.generatorDaoCode(codeBasePath, poInExcel);
        }
    }

    /**
     * 生成PO
     *
     * @param codeBasePath 代码生成目录
     * @param poInExcel    PO对象
     */
    public static void generatorDaoCode(String codeBasePath, PoData poInExcel) {

        String codePackage = poInExcel.getCodePackage();

        /* 1-创建文件*/
        File codeFile = createCodeFile(codeBasePath, poInExcel.getCodePackage(), poInExcel.getEntityNameEn(), AutoCodeFileType.DAO);

        /* 2-编写文件内容 */
        List<String> codeLines = new ArrayList();

        //package
        codeLines.add("package " + codePackage + ".dao;");
        codeLines.add("");

        //import枚举
        codeLines.add("import cn.andyjee.smartdev.dao.IBaseDao;");
        codeLines.add("import " + codePackage + ".po." + poInExcel.getEntityNameEn() + "Po;");
        codeLines.add("import org.apache.ibatis.annotations.Mapper;");
        codeLines.add("");

        //class
        codeLines.add("/**");
        codeLines.add(" * " + poInExcel.getEntityNameCn() + "DAO");
        codeLines.add(" *");
        codeLines.add(" * @author SmartDev AutoCode v1.0");
        codeLines.add(" */");
        codeLines.add("@Mapper");
        codeLines.add("public interface I" + poInExcel.getEntityNameEn() + "Dao extends IBaseDao<" + poInExcel.getEntityNameEn() + "Po> {");
        codeLines.add("");
        codeLines.add("}");


        /* 3- 写入文件 */
        FileUtil.appendUtf8Lines(codeLines, codeFile);
    }
}