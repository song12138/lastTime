package common.shiro.realm;

import common.exception.CustomException;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import pro.dao.ResourceDao;
import pro.dao.RoleDao;
import pro.entity.Resource;
import pro.entity.Role;
import pro.entity.User;
import pro.service.impl.UserServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by paul on 2017/8/9.
 */
public class UserRealm extends AuthorizingRealm {

    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private ResourceDao resourceDao;
    @Autowired
    private RoleDao roleDao;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //授权信息,可以理解为是权限验证
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName= (String) getAvailablePrincipal(principals);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();


        List<Role> rolesList=roleDao.findRoleByUserId(getId());
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        if (!CollectionUtils.isEmpty(rolesList)) {
            for (Role r : rolesList) {
                roles.add(r.getRole());
            }
            List<Resource> resourceList=resourceDao.findResourceByRolesId(rolesList);
            if (!CollectionUtils.isEmpty(resourceList)) {
                for (Resource re : resourceList) {
                    permissions.add(re.getPermission());
                }
            }

        }

        //设置角色
        info.setRoles(roles);
        //设置权限
        info.setStringPermissions(permissions);






        return info;
    }
    //认证，可以理解为登陆验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
       String userName= (String) token.getPrincipal();
        if(!StringUtils.isNotBlank(userName)){
//            try {
//                throw new CustomException();
//            } catch (CustomException e) {
////                e.printStackTrace();
//                return null;
//            }
            return null;
        }
        User u = new User();
        u.setUsername(userName);
            List<User> userList= userServiceImpl.findUserByAttribute(u);
        if(userList == null || userList.size()==0){
            return null;
        }
        User user = userList.get(0);
        if(null == user){
            logger.error("没有此用户"+userName);
            throw new UnknownAccountException();
        }

        if("1".equals(user.getLocked())){
            logger.error(userName+"已被锁定");
            throw new LockedAccountException();
        }
        setId(user.getId());
        //交给AuthenticatingRealm使用CredentialsMatcher进行密码匹配
        SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(
                user.getUsername(),//pricipal
                user.getPassword(),//Credentials 凭证
                ByteSource.Util.bytes( user.getUsername()+user.getSalt()),//salt
                getName()//realm Name
                );

        logger.debug(userName+"登录成功");

        return info;
    }
}
