package pro.entity;

public class SysResource {
  private String id;
  private String name;
  private String type;
  private String url;
  private String level;
  private String permission;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

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

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  /**
   * 返回JSON格式的String字符串方法
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append("\"id\":\"")
            .append(id).append('\"');
    sb.append(",\"name\":\"")
            .append(name).append('\"');
    sb.append(",\"type\":\"")
            .append(type).append('\"');
    sb.append(",\"url\":\"")
            .append(url).append('\"');
    sb.append(",\"level\":\"")
            .append(level).append('\"');
    sb.append(",\"permission\":\"")
            .append(permission).append('\"');
    sb.append('}');
    return sb.toString();
  }
}
