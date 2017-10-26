package pro.entity;

public class SysUserRole {
  private String id;
  private String userId;
  private String roleId;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getRoleId() {
    return roleId;
  }

  public void setRoleId(String roleId) {
    this.roleId = roleId;
  }

  /**
   * 返回JSON格式的String字符串方法
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append("\"id\":\"")
            .append(id).append('\"');
    sb.append(",\"userId\":\"")
            .append(userId).append('\"');
    sb.append(",\"roleId\":\"")
            .append(roleId).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
