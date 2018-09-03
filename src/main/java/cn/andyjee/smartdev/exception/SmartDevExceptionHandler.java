package cn.andyjee.smartdev.exception;

import cn.andyjee.smartdev.vo.R;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * 异常处理器
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月27日 下午10:16:19
 */
@RestControllerAdvice
public class SmartDevExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(SmartDevException.class)
    public R handleRRException(SmartDevException e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());

        return r;
    }

    /**
     * 访问路径不存在情况，返回404
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public R handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return R.error(404, "路径不存在，请检查路径是否正确");
    }

    /**
     * 数据库重复ID异常，返回-1
     *
     * @param e
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return R.error("数据库中已存在该记录");
    }

    /**
     * 授权异常的捕捉处理，发送错误码-2，通知前台
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public R handleAuthorizationException(AuthorizationException e) {
        logger.error(e.getMessage(), e);
        return R.error(-2, "没有权限，请联系管理员授权");
    }

    /**
     * 标准业务异常，返回-1
     *
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return R.error();
    }

    /**
     * 参数异常，返回-1
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public R handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        logger.error(e.getMessage(), e);
        return R.error("参数异常");
    }

}
