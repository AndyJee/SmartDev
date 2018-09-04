package cn.andyjee.smartdev.autocode;

import cn.andyjee.smartdev.autocode.bean.PoInExcel;
import cn.andyjee.smartdev.autocode.generator.PoCodeGenerator;

import java.io.File;
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

        List<PoInExcel> poInExcelList = ExcelReader.readExcel(excelFile);

        //获取当前代码路径
        String codeBasePath = "";

        //生成PO层代码
        PoCodeGenerator.generatorCode(codeBasePath, poInExcelList);
    }
}
