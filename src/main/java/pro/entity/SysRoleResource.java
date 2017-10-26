package pro.entity;

public class SysRoleResource {
  private String id;
  private String roleId;
  private String resourceId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  /**
   * 返回JSON格式的String字符串方法
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append("\"id\":\"")
            .append(id).append('\"');
    sb.append(",\"roleId\":\"")
            .append(roleId).append('\"');
    sb.append(",\"resourceId\":\"")
            .append(resourceId).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
