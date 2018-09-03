package cn.andyjee.smartdev.service;

import cn.andyjee.smartdev.po.CaptchaPo;

import java.io.OutputStream;

/**
 * 验证码服务
 *
 * @author AndyJee
 */
public interface ICaptchaService extends IBaseService<CaptchaPo> {

    /**
     * 创建图片验证码
     * 后台将记录UUID与验证码关系，同时返回输出流
     *
     * @param uuid         前台生成的UUID
     * @param outputStream 验证码response输出流
     */
    void createCaptcha(String uuid, OutputStream outputStream);

    /**
     * 验证码校验
     * 一旦校验指定UUID，即刻删除，避免
     *
     * @param uuid        前台生成的UUID
     * @param captchaCode 输入的验证码
     * @return
     */
    boolean validate(String uuid, String captchaCode);

}
