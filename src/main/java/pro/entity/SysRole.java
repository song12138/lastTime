package pro.entity;

public class SysRole {
  private String id;
  private String role;
  private String name;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  /**
   * 返回JSON格式的String字符串方法
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append("\"id\":\"")
            .append(id).append('\"');
    sb.append(",\"role\":\"")
            .append(role).append('\"');
    sb.append(",\"name\":\"")
            .append(name).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
