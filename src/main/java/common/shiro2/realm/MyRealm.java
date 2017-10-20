package common.shiro2.realm;

import common.exception.CustomException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pro.entity.User;
import pro.service.impl.UserServiceImpl;


/**
 * Created by paul on 2017/8/9.
 */
public class MyRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(MyRealm.class);

    @Autowired
    private UserServiceImpl userServiceImpl;

    //授权信息,可以理解为是权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName= (String) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        //设置角色

        //设置权限






        return info;
    }
    //认证，可以理解为登陆验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        String userName= (String) token.getPrincipal();
//        if(!StringUtils.isNotBlank(userName)){
//            try {
//                throw new CustomException();
//            } catch (CustomException e) {
//                e.printStackTrace();
//            }
//        }
        User u = new User();
        u.setUsername(userName);
        if(userServiceImpl.findUserByAttribute(u).size()>0){
            User user= userServiceImpl.findUserByAttribute(u).get(0);
            if(null == user){
                logger.error("没有此用户"+userName);
                throw new UnknownAccountException();
            }

            if("1".equals(user.getLocked())){
                logger.error(userName+"已被锁定");
                throw new LockedAccountException();
            }

//        交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(
                    user.getUsername(),//pricipal
                    user.getPassword(),//Credentials 凭证
                    ByteSource.Util.bytes( user.getUsername()+user.getSalt()),//salt
                    getName()//realm Name
            );
//            SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(
//                    user.getUsername(),//pricipal
//                    user.getPassword(),//Credentials 凭证
//                    getName()//realm Name
//            );
            logger.debug(userName+"登录成功");

            return info;
        }else {
            return null;
        }




    }
}
