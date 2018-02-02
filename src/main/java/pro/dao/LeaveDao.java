package pro.dao;

import common.annotation.MybatisDao;
import org.apache.ibatis.annotations.Param;
import pro.entity.Leave;

/**
 * Created by paul on 2017/12/15.
 */
@MybatisDao
public interface LeaveDao {

    void saveLeave(Leave leave);

    void update(Leave leave);

    Leave get(@Param("id") String id);


}
