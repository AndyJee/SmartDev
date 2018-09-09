package cn.andyjee.smartdev.autocode.generator;

import cn.andyjee.smartdev.autocode.bean.PoData;
import cn.andyjee.smartdev.autocode.bean.PropertyData;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成代码工具
 */
public class DaoCodeGenerator {

    /**
     * 生成PO
     *
     * @param codeBasePath  代码生成目录
     * @param poInExcelList PO对象列表
     */
    public static void generatorCode(String codeBasePath, List<PoData> poInExcelList) {


        for (PoData poInExcel : poInExcelList) {
            DaoCodeGenerator.generatorPoCode(codeBasePath, poInExcel);
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

        /* 1-创建文件*/
        String codePackage = poInExcel.getCodePackage();
        String codeChildPackageDir = codePackage.replaceAll("\\.", "\\" + File.separator);
        String fileName = StrUtil.upperFirst(poInExcel.getEntityNameEn());
        File javaPoFile = new File(codeBasePath + File.separator + codeChildPackageDir + File.separator + "dao" + File.separator + "I" + fileName + "Dao.java");


        /* 2-编写文件内容 */
        List<String> codeLines = new ArrayList();

        //package
        codeLines.add("package " + codePackage + ".dao;");
        codeLines.add("");

        //import枚举
        codeLines.add("import cn.andyjee.smartdev.po.MenuPo;");
        codeLines.add("import org.apache.ibatis.annotations.Mapper;");
        codeLines.add("");

        //class
        codeLines.add("/**");
        codeLines.add(" * " + poInExcel.getEntityNameCn() + "DAO");
        codeLines.add(" *");
        codeLines.add(" * @author SmartDev AutoCode v1.0");
        codeLines.add(" */");
        codeLines.add("@@Mapper");
        codeLines.add("public interface " + poInExcel.getEntityNameEn() + "Dao extends IBaseDao<" + poInExcel.getEntityNameEn() + "Po> {{");
        codeLines.add("");
        codeLines.add("}");


        /* 3- 写入文件 */
        FileUtil.del(javaPoFile);
        FileUtil.appendUtf8Lines(codeLines, javaPoFile);
    }
}