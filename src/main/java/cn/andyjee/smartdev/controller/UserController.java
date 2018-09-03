package cn.andyjee.smartdev.controller;

import cn.andyjee.smartdev.po.UserPo;
import cn.andyjee.smartdev.service.IUserRoleService;
import cn.andyjee.smartdev.service.IUserService;
import cn.andyjee.smartdev.vo.R;
import com.xiaoleilu.hutool.lang.Assert;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户管理相关
 *
 * @author AndyJee
 */
@Api(tags = {"用户管理"}, description = "用户管理相关，包含用户修改密码、用户信息的管理")
@RestController
@RequestMapping("/api/v1/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService iUserService;
    @Autowired
    private IUserRoleService iUserRoleService;

    /**
     * 获取当前登录的用户信息
     */
    @ApiOperation(value = "获取当前登录的用户信息", notes = "仅在登录之后，才能正常返回")
    @GetMapping("/info")
    @RequiresUser
    public R info() {
        return R.ok().put("user", getUser());
    }

    /**
     * 修改登录用户密码
     */
    @ApiOperation(value = "修改登录用户密码", notes = "仅本用户自己才能修改自己的密码")
//    @OperationLog("修改密码")
    @PutMapping("/password")
    @RequiresUser
    public R password(@RequestParam String password, @RequestParam String newPassword) {
        Assert.notBlank(newPassword, "新密码不为能空");

        Long userId = getUserId();
        if (userId == null) {
            return R.error("未登录，无法修改密码");
        }

        //更新密码
        boolean flag = iUserService.updatePassword(getUserId(), password, newPassword);
        if (!flag) {
            return R.error("原密码不正确");
        }

        return R.ok();
    }

    /**
     * 用户信息
     */
    @ApiOperation(value = "获取指定用户的信息", notes = "仅有登录的管理员和此用户的代理商，才能正常返回")
    @GetMapping("/info/{userId}")
    @RequiresRoles(value = {"1", "2", "3"}, logical = Logical.OR)
    public R info(@PathVariable("userId") Long userId) {
        UserPo user = iUserService.selectById(userId);

        if (user == null) {
            return R.error("无此用户");
        }

        //获取用户所属的角色列表
        List<Long> roleIdList = iUserRoleService.queryRoleIdList(userId);
        user.setRoleIdList(roleIdList);

        return R.ok().put("user", user);
    }

}
