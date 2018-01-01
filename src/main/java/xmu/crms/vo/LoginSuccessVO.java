package xmu.crms.vo;

import java.math.BigInteger;

public class LoginSuccessVO {
    private BigInteger id;
    private String type;
    private String name;
    private String jwt;
    private String openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public LoginSuccessVO() {
    }

    public LoginSuccessVO(String openid) {
        this.openid = openid;

    }
    public LoginSuccessVO(BigInteger id, String type, String name, String jwt) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.jwt = jwt;
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

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public void setJwt(String header, String payload, String signature) {
        this.jwt = header + '.' + payload + '.' + signature;
    }

}
