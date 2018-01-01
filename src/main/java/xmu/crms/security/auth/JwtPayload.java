package xmu.crms.security.auth;

import xmu.crms.entity.User;

import java.math.BigInteger;

/**
 * JWT payload 段
 *
 * @author lulongfei
 * @date 2017-12-21
 */
public class JwtPayload {
    private BigInteger id;
    private String type;
    private String name;
    private Long exp;

    public JwtPayload() {

    }

    public JwtPayload(BigInteger id, String type, String name, Long exp) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.exp = exp;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExp() {
        return exp;
    }

    public void setExp(Long exp) {
        this.exp = exp;
    }

    public User toUser() {
        User user = new User();
        user.setId(id);

        if (type.equals("student")) {
            user.setType(0);
        } else if(type.equals("teacher")){
            user.setType(1);
        }
        return user;
    }
}
