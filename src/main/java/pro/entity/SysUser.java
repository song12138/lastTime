package pro.entity;

public class SysUser {
  private String id;
  private String username;
  private String realname;
  private String password;
  private String salt;
  private Long locked;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getRealname() {
    return realname;
  }

  public void setRealname(String realname) {
    this.realname = realname;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getSalt() {
    return salt;
  }

  public void setSalt(String salt) {
    this.salt = salt;
  }

  public Long getLocked() {
    return locked;
  }

  public void setLocked(Long locked) {
    this.locked = locked;
  }

  /**
   * 返回JSON格式的String字符串方法
   */
  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("{");
    sb.append("\"id\":\"")
            .append(id).append('\"');
    sb.append(",\"username\":\"")
            .append(username).append('\"');
    sb.append(",\"realname\":\"")
            .append(realname).append('\"');
    sb.append(",\"password\":\"")
            .append(password).append('\"');
    sb.append(",\"salt\":\"")
            .append(salt).append('\"');
    sb.append(",\"locked\":")
            .append(locked);
    sb.append('}');
    return sb.toString();
  }
}
