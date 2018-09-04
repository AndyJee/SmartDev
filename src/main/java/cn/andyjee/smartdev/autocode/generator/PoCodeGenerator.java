package cn.andyjee.smartdev.autocode.generator;

import cn.andyjee.smartdev.autocode.bean.PoInExcel;
import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成代码工具
 */
public class PoCodeGenerator {

    /**
     * 生成PO
     *
     * @param codeBasePath  代码生成目录
     * @param poInExcelList PO对象列表
     */
    public static void generatorCode(String codeBasePath, List<PoInExcel> poInExcelList) {

        for (PoInExcel poInExcel : poInExcelList) {
            PoCodeGenerator.generatorCode(codeBasePath, poInExcel);
        }
    }

    /**
     * 生成PO
     *
     * @param codeBasePath 代码生成目录
     * @param poInExcel    PO对象
     */
    public static void generatorCode(String codeBasePath, PoInExcel poInExcel) {

        /* 1-创建文件*/
        String codePackage = poInExcel.getCodePackage();
        String codeChildPackageDir = codePackage.replaceAll("\\.", File.separator);
        String fileName = StrUtil.upperFirst(poInExcel.getEntityNameEn());
        File poFile = FileUtil.touch(codeBasePath + File.separator + codeChildPackageDir + File.separator + fileName + "Po.java");

        /* 2-编写文件内容 */
        List<String> codeLines = new ArrayList();

        //package
        codeLines.add("package " + codePackage + ".po;");
        codeLines.add("");

        //import枚举
        codeLines.add("import ");

        /* 3- 写入文件 */
        FileUtil.appendUtf8Lines(codeLines, poFile);
    }
}