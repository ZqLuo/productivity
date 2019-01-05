package com.productivity.web.config.shiro;

import com.productivity.web.entity.SysUser;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * AuthRealm完成根据用户名去数据库的查询,并且将用户信息放入shiro中,供第二个类调用.CredentialsMatcher,完成对于密码的校验.其中用户的信息来自shiro
 * Created by zqLuo
 */
public class AuthRealm extends AuthorizingRealm {

//    @Autowired
//    private SysUserService sysUserService;

    /**
     * 授权的方法是在碰到<shiro:hasPermission>标签的时候调用的,它会去检测shiro框架中的权限(这里的permissions)是否包含有该标签的name值,
     * 如果有,里面的内容显示,如果没有,里面的内容不予显示(这就完成了对于权限的认证.)
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

//        SysUser user = (SysUser)principal.fromRealm(this.getClass().getName())
//                .iterator()
//                .next();//获取session中的用户
//        List<String> permissions = new ArrayList<>();
//        Set<Role> roles = user.getRoles();
//        if (roles.size() > 0) {
//            for (Role role : roles) {
//                Set<Module> modules = role.getModules();
//                if (modules.size() > 0) {
//                    for (Module module : modules) {
//                        permissions.add(module.getMname());
//                    }
//                }
//            }
//        }
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        info.addStringPermissions(permissions);//将权限放入shiro中.
//        return info;

        return null;
    }

    /**
     * 这个方法主要是做登录验证，说白了就是去数据库里面校验用户是否存在，注意这里不需要进行秘密校验，shiro会帮我们做密码校验
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            UsernamePasswordToken utoken = (UsernamePasswordToken)token;//获取用户输入的token
            String username = utoken.getUsername();
            SysUser user = new SysUser();
            if (user != null) {
                // 若存在，将此用户存放到登录认证info中，无需自己做密码对比，Shiro会为我们进行密码对比校验
                return new SimpleAuthenticationInfo(user, user.getPassword(),
                        this.getClass().getName());//放入shiro.调用CredentialsMatcher检验密码
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
