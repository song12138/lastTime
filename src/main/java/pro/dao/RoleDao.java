package pro.dao;

import common.annotation.MybatisDao;
import org.apache.ibatis.annotations.Param;
import pro.entity.Role;

import java.util.List;

/**
 * Created by paul on 2017/10/23.
 */
@MybatisDao
public interface RoleDao {
    List<Role> findRoleByUserId(@Param("userId")String userId);
}
