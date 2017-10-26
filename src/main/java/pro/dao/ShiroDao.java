package pro.dao;

import common.annotation.MybatisDao;
import org.apache.ibatis.annotations.Param;
import pro.entity.SysResource;
import pro.entity.SysRole;
import pro.entity.SysUser;

import java.util.Set;

/**
 * Created by paul on 2017/10/25.
 */
@MybatisDao
public interface ShiroDao {

    SysUser findByUsername(@Param("username") String  username);
    Set<SysRole> findRoles(@Param("username")String username);// 根据用户名查找其角色  
    Set<SysResource> findPermissions(@Param("username")String username);// 根据用户名查找其权限  

    int addUser(SysUser sysUser);



}
