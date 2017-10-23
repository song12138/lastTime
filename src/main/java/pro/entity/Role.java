package pro.entity;

import common.metaData.BaseEntity;
import org.apache.ibatis.type.Alias;

import java.util.List;

/**
 * @Description 角色
 * @author william [yeemin_shon@163.com]
 * @Date 2017/6/16 0016 11:43
 */
@Alias("Role")
public class Role extends BaseEntity<Role>{

    private static final long serialVersionUID = -4699178964423143927L;

    private String role; //角色标识 程序中判断使用,如"admin"

    private String ename;

    private String description; //角色描述,UI界面显示使用

    private List<String> resourceIds; //拥有的资源

    private Boolean available = Boolean.FALSE; //是否可用,如果不可用将不会添加给用户

    public Role() {
    }

    public Role(String role, String description, Boolean available) {
        this.role = role;
        this.description = description;
        this.available = available;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    /*public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getResourceIds() {
        if (CollectionUtil.isEmpty(resourceIds)) {
            resourceIds = new TList<String>();
        }
        return resourceIds.toString();
    }

    public List<String> getResourceIdList() {
        if (CollectionUtil.isEmpty(resourceIds)) {
            resourceIds = new TList<String>();
        }
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        getResourceIdList();
        String[] resourceIdArray = resourceIds.split(",");
        for (String resourceId : resourceIdArray) {
            if (StringUtil.isNotEmpty(resourceId)) {
                this.resourceIds.add(resourceId);
            }
        }
    }

    public void setResourceIdList(List<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        if (id != null ? !id.equals(role.id) : role.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", role='" + role + '\'' +
                ", description='" + description + '\'' +
                ", resourceIds=" + resourceIds +
                ", available=" + available +
                '}';
    }*/
}
