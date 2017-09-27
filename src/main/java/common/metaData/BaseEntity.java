package common.metaData;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by paul on 2017/9/27.
 */
public class BaseEntity<T> implements Serializable {


    /**
     * id
     */
    protected String id;

    /**
     * 创建者
     */
    protected String createUser;

    /**
     * 创建时间
     */
    protected Date createTime;

    /**
     * 更新者
     */
    protected String updateUser;

    /**
     * 更新时间
     */
    protected Date updateTime;
    /**
     * 删除标志
     */
    protected String delFlag;

    /**
     * 分页
     */
    private Page<T> page;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Page<T> getPage() {
        return page;
    }

    public void setPage(Page<T> page) {
        this.page = page;
    }
}
