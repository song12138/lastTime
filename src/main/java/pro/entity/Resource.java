package pro.entity;

import common.metaData.BaseEntity;

/**
 * @Description 资源
 * @author william [yeemin_shon@163.com]
 * @Date 2017/6/16 0016 11:43
 */
public class Resource extends BaseEntity<Resource>{
    private static final long serialVersionUID = -5764552276491009585L;
    private String name; //资源名称
//    private ResourceType type = ResourceType.menu; //资源类型
    private String type; //资源类型
    private String url; //资源路径
    private String permission; //权限字符串
    private String parentIds; //父编号列表
    private Boolean available = Boolean.FALSE;
    private String sort;
    /**
     * treegrid相关字段
     */
    private Integer level;
    private Boolean leaf;
    private Boolean loaded = Boolean.TRUE;
    private Boolean expanded = Boolean.TRUE;

//    public static enum ResourceType {
//        menu("菜单"), button("按钮");
//
//        private final String info;
//        private ResourceType(String info) {
//            this.info = info;
//        }
//
//        public String getInfo() {
//            return info;
//        }
//    }


    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public ResourceType getType() {
//        return type;
//    }

//    public String getTypeEn() {
//        return type.name();
//    }

//    public void setType(ResourceType type) {
//        this.type = type;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }





    /*public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getParentIds() {
        return parentIds;
    }

    public void setParentIds(String parentIds) {
        this.parentIds = parentIds;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public boolean isRootNode() {
        return parent == "0";
    }

    public String makeSelfAsParentIds() {
        return getParentIds() + getId() + "/";
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
//        if (4 == level) {
//            leaf = true;
//        }
    }

    public Boolean getLeaf() {
        return leaf;
    }

    public void setLeaf(Boolean leaf) {
        this.leaf = leaf;
    }

    public Boolean getLoaded() {
        return loaded;
    }

    public Boolean getExpanded() {
        return expanded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resource resource = (Resource) o;

        if (id != null ? !id.equals(resource.id) : resource.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", permission='" + permission + '\'' +
                ", parent=" + parent +
                ", parentIds='" + parentIds + '\'' +
                ", available=" + available +
                '}';
    }*/
}
