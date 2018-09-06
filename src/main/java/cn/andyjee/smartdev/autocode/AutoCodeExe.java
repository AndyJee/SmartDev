package cn.andyjee.smartdev.autocode;

import cn.andyjee.smartdev.autocode.bean.PoData;
import cn.andyjee.smartdev.autocode.bean.PropertyData;
import cn.andyjee.smartdev.autocode.generator.PoCodeGenerator;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AutoCodeExe {

    public static void main(String[] args) {

        System.out.println("自动生成代码工具开始工作。版本v1.0");

        File excelFile = null;
        if (args.length != 2) {
            System.err.println("请输入文件路径");
        } else {
            System.out.println("正在读取文件：" + args[1]);
            excelFile = new File(args[1]);
        }

        List<PoData> poInExcelList = ExcelReader.readExcel(excelFile);

        //获取当前代码路径
        String classRootPath = AutoCodeExe.class.getResource("/").getFile();
        String projectRootPath = StrUtil.subBefore(classRootPath, "target", true);
        String codeBasePath = projectRootPath + "src/main/java/";
        System.out.println("代码路径：" + codeBasePath);

        //todo test data
        PropertyData propertyData1 = new PropertyData();
        propertyData1.setIsKey(true);
        propertyData1.setCanNull(false);
        propertyData1.setNameEn("id");
        propertyData1.setNameCn("ID");
        propertyData1.setType("Long");
        propertyData1.setTypeLength("20");
        propertyData1.setDefaultValue("");

        PropertyData propertyData2 = new PropertyData();
        propertyData2.setIsKey(false);
        propertyData2.setCanNull(false);
        propertyData2.setNameEn("type");
        propertyData2.setNameCn("类型");
        propertyData2.setType("SystemLogTypeEnum");
        propertyData2.setTypeLength("255");
        propertyData2.setDefaultValue("未知类型");

        PropertyData propertyData3 = new PropertyData();
        propertyData3.setIsKey(false);
        propertyData3.setCanNull(false);
        propertyData3.setNameEn("description");
        propertyData3.setNameCn("描述");
        propertyData3.setType("String");
        propertyData3.setTypeLength("20");
        propertyData3.setDefaultValue("");

        List<PropertyData> propertyDataList = new ArrayList<>();
        propertyDataList.add(propertyData1);
        propertyDataList.add(propertyData2);
        propertyDataList.add(propertyData3);

        PoData poData = new PoData();
        poData.setCodePackage("com.company.project");
        poData.setEntityNameEn("SystemLog");
        poData.setEntityNameCn("系统日志，用于记录系统级故障及提供排查手段");
        poData.setTableName("sd_system_log");
        poData.setPropertyList(propertyDataList);

        poInExcelList = new ArrayList<>();
        poInExcelList.add(poData);

        //生成PO层代码
        PoCodeGenerator.generatorCode(codeBasePath, poInExcelList);


    }
}
