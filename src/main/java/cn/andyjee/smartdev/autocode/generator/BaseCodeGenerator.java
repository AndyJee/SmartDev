package cn.andyjee.smartdev.autocode.generator;

import com.xiaoleilu.hutool.io.FileUtil;
import com.xiaoleilu.hutool.util.StrUtil;

import java.io.File;

/**
 * 代码生成器抽象类
 *
 * @author AndyJee
 */
public abstract class BaseCodeGenerator {


    /**
     * 创建代码文件，删除后重建
     *
     * @param codeBasePath 代码文件路径，形如 D:\SmartDev
     * @param codePackage  代码所在包，形如 cn.andyjee.smartdev
     * @param entityNameEn 实体英文名
     * @param type         自动生成代码的类型
     */
    protected static File createCodeFile(String codeBasePath, String codePackage, String entityNameEn, AutoCodeFileType type) {
        String sp = File.separator;
        StringBuffer fileFullPath = new StringBuffer(codeBasePath);


        switch (type) {
            case PO:
                fileFullPath.append(codePackage.replaceAll("\\.", "\\" + sp));
                fileFullPath.append(sp).append("po").append(sp);
                fileFullPath.append(StrUtil.upperFirst(entityNameEn)).append("Po.java");
                break;
            case DAO:
                fileFullPath.append(codePackage.replaceAll("\\.", "\\" + sp));
                fileFullPath.append(sp).append("dao").append(sp);
                fileFullPath.append("I").append(StrUtil.upperFirst(entityNameEn)).append("Dao.java");
                break;
            case SERVICE:
                fileFullPath.append(codePackage.replaceAll("\\.", "\\" + sp));
                fileFullPath.append(sp).append("service").append(sp);
                fileFullPath.append("I").append(StrUtil.upperFirst(entityNameEn)).append("Service.java");
                break;
            case SERVICE_IMPL:
                fileFullPath.append(codePackage.replaceAll("\\.", "\\" + sp));
                fileFullPath.append(sp).append("service").append(sp).append("impl").append(sp);
                fileFullPath.append(StrUtil.upperFirst(entityNameEn)).append("ServiceImpl.java");
                break;
            case CONTROLLER:
                fileFullPath.append(codePackage.replaceAll("\\.", "\\" + sp));
                fileFullPath.append(sp).append("controller").append(sp);
                fileFullPath.append(StrUtil.upperFirst(entityNameEn)).append("Controller.java");
                break;
            case ENUM:
                fileFullPath.append(codePackage.replaceAll("\\.", "\\" + sp));
                fileFullPath.append(sp).append("enums").append(sp);
                fileFullPath.append(StrUtil.upperFirst(entityNameEn)).append(".java");
                break;
            case SQL:
                fileFullPath.append(sp).append("db");
                fileFullPath.append(StrUtil.upperFirst(entityNameEn)).append(".sql");
                break;
        }

        File file = new File(fileFullPath.toString());
        FileUtil.del(file);
        FileUtil.touch(file);

        return file;
    }


}
