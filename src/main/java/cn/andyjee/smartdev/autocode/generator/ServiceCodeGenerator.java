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
public class ServiceCodeGenerator {

    /**
     * 生成PO
     *
     * @param codeBasePath  代码生成目录
     * @param poInExcelList PO对象列表
     */
    public static void generatorCode(String codeBasePath, List<PoData> poInExcelList) {


        for (PoData poInExcel : poInExcelList) {
            ServiceCodeGenerator.generatorServiceCode(codeBasePath, poInExcel);
            ServiceCodeGenerator.generatorServiceImplCode(codeBasePath, poInExcel);
        }
    }

    /**
     * 生成Service
     *
     * @param codeBasePath 代码生成目录
     * @param poInExcel    PO对象
     */
    public static void generatorServiceCode(String codeBasePath, PoData poInExcel) {

        List<PropertyData> propertyList = poInExcel.getPropertyList();

        /* 1-创建文件*/
        String codePackage = poInExcel.getCodePackage();
        String codeChildPackageDir = codePackage.replaceAll("\\.", "\\" + File.separator);
        String fileName = StrUtil.upperFirst(poInExcel.getEntityNameEn());
        File javaFile = new File(codeBasePath + File.separator + codeChildPackageDir + File.separator + "service" + File.separator + "I" + fileName + "Service.java");


        /* 2-编写文件内容 */
        List<String> codeLines = new ArrayList();

        //package
        codeLines.add("package " + codePackage + ".service;");
        codeLines.add("");

        //import枚举
        codeLines.add("import cn.andyjee.smartdev.service.IBaseService;");
        codeLines.add("import " + codePackage + ".po." + poInExcel.getEntityNameEn() + "Po;");
        codeLines.add("");

        //class
        codeLines.add("/**");
        codeLines.add(" * " + poInExcel.getEntityNameCn() + "服务");
        codeLines.add(" *");
        codeLines.add(" * @author SmartDev AutoCode v1.0");
        codeLines.add(" */");
        codeLines.add("public interface I" + poInExcel.getEntityNameEn() + "Service extends IBaseService<" + poInExcel.getEntityNameEn() + "Po> {");
        codeLines.add("");
        codeLines.add("}");


        /* 3- 写入文件 */
        FileUtil.del(javaFile);
        FileUtil.appendUtf8Lines(codeLines, javaFile);
    }

    /**
     * 生成ServiceImpl
     *
     * @param codeBasePath 代码生成目录
     * @param poInExcel    PO对象
     */
    public static void generatorServiceImplCode(String codeBasePath, PoData poInExcel) {

        List<PropertyData> propertyList = poInExcel.getPropertyList();
        String entityNameEn = poInExcel.getEntityNameEn();

        /* 1-创建文件*/
        String codePackage = poInExcel.getCodePackage();
        String codeChildPackageDir = codePackage.replaceAll("\\.", "\\" + File.separator);
        String fileName = StrUtil.upperFirst(poInExcel.getEntityNameEn());
        File javaFile = new File(codeBasePath + File.separator + codeChildPackageDir + File.separator + "service" + File.separator + "impl" + File.separator + fileName + "ServiceImpl.java");


        /* 2-编写文件内容 */
        List<String> codeLines = new ArrayList();

        //package
        codeLines.add("package " + codePackage + ".service.impl;");
        codeLines.add("");

        //import枚举
        codeLines.add("import cn.andyjee.smartdev.service.impl.BaseService;");
        codeLines.add("import " + codePackage + ".dao.I" + entityNameEn + "Dao;");
        codeLines.add("import " + codePackage + ".po." + entityNameEn + "Po;");
        codeLines.add("import " + codePackage + ".service.I" + entityNameEn + "Service;");
        codeLines.add("import org.springframework.stereotype.Service;");
        codeLines.add("import org.springframework.transaction.annotation.Transactional;");
        codeLines.add("");
        codeLines.add("import java.util.*;");
        codeLines.add("");

        //class
        codeLines.add("/**");
        codeLines.add(" * " + poInExcel.getEntityNameCn() + "服务实现");
        codeLines.add(" *");
        codeLines.add(" * @author SmartDev AutoCode v1.0");
        codeLines.add(" */");
        codeLines.add("@Service");
        codeLines.add("public class " + entityNameEn + "ServiceImpl extends BaseService<I" + entityNameEn + "Dao, " + entityNameEn + "Po> implements I" + entityNameEn + "Service {");
        codeLines.add("");
        codeLines.add("}");


        /* 3- 写入文件 */
        FileUtil.del(javaFile);
        FileUtil.appendUtf8Lines(codeLines, javaFile);
    }
}