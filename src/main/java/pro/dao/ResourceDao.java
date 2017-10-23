package pro.dao;

import common.annotation.MybatisDao;
import pro.entity.Resource;
import pro.entity.Role;

import java.util.List;

/**
 * Created by paul on 2017/10/23.
 */
@MybatisDao
public interface ResourceDao {
    List<Resource> findResourceByRolesId(List<Role> list);
}
