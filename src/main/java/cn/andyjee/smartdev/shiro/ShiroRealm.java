package cn.andyjee.smartdev.shiro;

import cn.andyjee.smartdev.po.UserPo;
import cn.andyjee.smartdev.service.IUserRoleService;
import cn.andyjee.smartdev.service.IUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;

/**
 * 自定义Realm，用于Shiro
 *
 * @author AndyJee
 */
public class ShiroRealm extends AuthorizingRealm {

    @Resource
    public IUserService iUserService;

    @Autowired
    private IUserRoleService iUserRoleService;

    /**
     * 定义如何获取用户的角色和权限的逻辑，给shiro做权限判断
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        if (principals == null) {
            throw new AuthorizationException("权限校验不能为空");
        }

        UserPo user = (UserPo) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //todo 删除打印
        System.out.println("获取角色信息：" + user.getRoleIdList());
        System.out.println("获取权限信息：" + user.getPermsList());

        //加入角色列表
        HashSet<String> roles = new HashSet<>();
        for (Long roleId : user.getRoleIdList()) {
            roles.add(roleId.toString());
        }
        info.setRoles(roles);

        //加入权限列表
        //未来可根据人员将权限进一步细化，不仅靠角色来对应
        HashSet<String> perms = new HashSet<>();
        for (String perm : user.getPermsList()) {
            perms.add(perm);
        }
        info.setStringPermissions(perms);
        return info;
    }

    /**
     * 定义如何获取用户信息的业务逻辑，给shiro做登录
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        char[] password = upToken.getPassword();

        if (username == null) {
            throw new AccountException("用户名不能为空");
        }

        UserPo userPo = iUserService.queryByUserName(username);

        //检查账号不存在情况
        if (userPo == null) {
            throw new UnknownAccountException("不存在用户名" + userPo.getUserName());
        }

        //密码不正确情况
        String dbPasswordMD5 = userPo.getPassword();
        String tokenPasswordMD5 = new String(password);
        if (!dbPasswordMD5.equals(tokenPasswordMD5)) {
            throw new IncorrectCredentialsException("密码错误");
        }

        //查询用户的角色和权限存到SimpleAuthenticationInfo中，
        //这样在其它地方SecurityUtils.getSubject().getPrincipal()就能拿出用户的所有信息，包括角色和权限
        List<Long> roleIdList = iUserRoleService.queryRoleIdList(userPo.getUserId());
        List<String> permList = iUserService.queryAllPerms(userPo.getUserId());

        userPo.getRoleIdList().addAll(roleIdList);
        userPo.getPermsList().addAll(permList);

        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(userPo, userPo.getPassword(), getName());

//        //暂不考虑盐值问题
//        if (userPo.getSalt() != null) {
//            info.setCredentialsSalt(ByteSource.Util.bytes(userPo.getSalt()));
//        }

        return info;

    }

}