package cn.andyjee.smartdev.service.impl;

import cn.andyjee.smartdev.dao.CaptchaDao;
import cn.andyjee.smartdev.exception.SmartDevException;
import cn.andyjee.smartdev.po.CaptchaPo;
import cn.andyjee.smartdev.service.ICaptchaService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.xiaoleilu.hutool.captcha.CaptchaUtil;
import com.xiaoleilu.hutool.captcha.CircleCaptcha;
import com.xiaoleilu.hutool.date.DateUtil;
import com.xiaoleilu.hutool.util.StrUtil;
import org.springframework.stereotype.Service;

import java.io.OutputStream;
import java.util.Date;

/**
 * 验证码
 *
 * @author AndyJee
 */
@Service
public class CaptchaServiceImpl extends BaseService<CaptchaDao, CaptchaPo> implements ICaptchaService {


    @Override
    public void createCaptcha(String uuid, OutputStream outputStream) {
        if (StrUtil.isBlank(uuid)) {
            throw new SmartDevException("uuid不能为空");
        }

        //生成验证码
        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);
        captcha.write(outputStream);

        //保存此验证码
        CaptchaPo captchaPo = new CaptchaPo();
        captchaPo.setUuid(uuid);
        String captchaCode = captcha.getCode();
        captchaPo.setCode(captchaCode);
        captchaPo.setExpireTime(DateUtil.offsetMinute(new Date(), 5));
        this.insertOrUpdate(captchaPo);
    }

    @Override
    public boolean validate(String uuid, String code) {
        CaptchaPo captchaEntity = this.selectOne(new EntityWrapper<CaptchaPo>().eq("uuid", uuid));
        if (captchaEntity == null) {
            return false;
        }

        //删除验证码
        this.deleteById(uuid);

        if (captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()) {
            return true;
        }

        return false;
    }
}
