package cn.andyjee.smartdev.controller;

import cn.andyjee.smartdev.exception.SmartDevException;
import cn.andyjee.smartdev.po.MenuPo;
import cn.andyjee.smartdev.po.UserPo;
import cn.andyjee.smartdev.service.ICaptchaService;
import cn.andyjee.smartdev.service.IMenuService;
import cn.andyjee.smartdev.vo.LoginVo;
import cn.andyjee.smartdev.vo.R;
import com.xiaoleilu.hutool.crypto.SecureUtil;
import com.xiaoleilu.hutool.json.JSONArray;
import com.xiaoleilu.hutool.json.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 登录相关
 *
 * @author AndyJee
 */
@Api(tags = {"用户登录"}, description = "用户登录相关，包含登录、注销，及登录后菜单的获取")
@RestController
@RequestMapping("/api/v1")
public class UserLoginController extends BaseController {

    @Autowired
    private ICaptchaService iCaptchaService;
    @Autowired
    private IMenuService iMenuService;

    /**
     * 获取验证码
     */
    @ApiOperation(value = "获取验证码", notes = "前台生成UUID获取验证码，之后提交时再次使用此UUID")
    @GetMapping("/user/login/captcha.jpg")
    public void captcha(HttpServletResponse response, @RequestParam String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");
        iCaptchaService.createCaptcha(uuid, response.getOutputStream());
    }

    /**
     * 登录
     */
    @ApiOperation(value = "用户登录", notes = "提供UUID、验证码、用户名、密码")
//    @OperationLog("用户登录")
    @PostMapping("/user/login")
    public R login(@RequestBody LoginVo loginVo) {

        //验证码校验
        boolean captcha = iCaptchaService.validate(loginVo.getUuid(), loginVo.getCaptcha());
        if (!captcha) {
            return R.error("验证码不正确");
        }

        //Shiro校验，登录，记录session
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUsername(), SecureUtil.md5(loginVo.getPassword()));
//            token.setRememberMe(loginVo.isRememberMe());
            subject.login(token);

        } catch (UnknownAccountException e) {
            return R.error("用户名不存在");
        } catch (IncorrectCredentialsException e) {

            e.printStackTrace();
            return R.error("登录密码错误");
        } catch (AuthenticationException e) {
            return R.error("其他错误：" + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }


//        //不涉及分布式服务，暂时不记录Token
//        //生成token，并保存到数据库
//       iUserTokenService.createToken(user.getUserId());

        UserPo user = getUser();
//        boolean isCustomer = UserRoleTool.isCustomer(user);
//        boolean isAgent = UserRoleTool.isAgent(user);
//        boolean isPlatformAdmin = UserRoleTool.isPlatformAdmin(user);
//        boolean isSystemAdmin = UserRoleTool.isSystemAdmin(user);

        return R.ok()
                .put("userId", user.getUserId())
                .put("userName", user.getUserName());
//                .put("isCustomer", isCustomer)
//                .put("isAgent", isAgent)
//                .put("isPlatformAdmin", isPlatformAdmin)
//                .put("isSystemAdmin", isSystemAdmin);
    }


    /**
     * 退出登录
     */
    @ApiOperation(value = "用户退出登录", notes = "session中将注销登录")
//    @OperationLog("用户退出登录")
    @PostMapping("/user/logout")
    public R logout() {

        Subject subject = SecurityUtils.getSubject();
        subject.logout();

//        //不涉及分布式服务，暂时不记录Token
//        iUserTokenService.logout(getUserId());
        return R.ok();
    }


    /**
     * 获取登录人员的可显示菜单
     */
    @ApiOperation(value = "获取登录人员的可显示菜单", notes = "菜单编号前后台约定好，此处仅返回ID列表")
    @GetMapping("/user/menus")
    public R getUserMenus() {

        Long userId = getUserId();
        if (userId == null) {
            throw new SmartDevException("未登录");
        }

        try {
            List<MenuPo> userMenuList = iMenuService.getUserMenuList(userId);
            JSONArray menusShow = new JSONArray();
            for (MenuPo menuPo : userMenuList) {
                JSONObject menuShow = new JSONObject();
                menuShow.put("id", menuPo.getMenuId());
                menuShow.put("parentId", menuPo.getParentId());
                menuShow.put("type", menuPo.getType());
                menuShow.put("menuName", menuPo.getMenuName());
                menuShow.put("orderNum", menuPo.getOrderNum());
                menusShow.add(menuShow);
            }
            return R.ok().put("menusShow", menusShow);
        } catch (Exception e) {
            logger.error("获取人员" + userId + "的菜单异常");
            return R.error();
        }
    }
}
