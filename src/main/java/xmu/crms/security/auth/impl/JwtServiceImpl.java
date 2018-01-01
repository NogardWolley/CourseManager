package xmu.crms.security.auth.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xmu.crms.entity.User;
import xmu.crms.security.auth.JwtPayload;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @author LuLongfei
 * @date 2017-12-22
 */
@Service
public class JwtServiceImpl implements xmu.crms.security.auth.JwtService {

    @Value("${hmac.sha256.secret-key}")
    private String secret;

    private Long expireTime = 24 * 60 * 60 * 1000L;
    /**
     * 固定的 JWT header
     */
    private static final Map<String, String> JWT_HEADER =
            new HashMap<String, String>() {{
                put("alg", "HS256");
                put("typ", "JWT");
            }};


    /**
     * 从登录成功的用户信息生成 JWT
     *
     * @param user
     * @return
     */
    @Override
    public String generateJwt(User user) {
        ObjectMapper objectMapper = new ObjectMapper();
        JwtPayload jwtPayload = new JwtPayload(user.getId(),
                user.getType() == null ? "unbinded" : (user.getType() == 1 ? "teacher" : "student"),
                user.getName(), System.currentTimeMillis() + expireTime);
        try {
            String header = objectMapper.writeValueAsString(JWT_HEADER);
            String payload = objectMapper.writeValueAsString(jwtPayload);
            return generateJwt(header, payload);
        } catch (Exception e) {

        }
        return null;
    }

    /**
     * 由 JWT 的 header 和 payload 生成含有 signature 的 JWT
     *
     * @param header
     * @param payload
     * @return
     */
    private String generateJwt(String header, String payload) {
        String base64header = base64encode(header);
        String base64payload = base64encode(payload);
        String signature = generateSignature(base64header, base64payload);
        return base64header + '.' + base64payload + '.' + signature;
    }

    private String base64encode(String src) {
        return Base64.getEncoder().encodeToString(src.getBytes());
    }

    private String base64decode(String src) {
        return new String(Base64.getDecoder().decode(src));
    }

    /**
     * 由 JWT 的 header 和 payload 生成 signature
     *
     * @param header
     * @param payload
     * @return
     */
    private String generateSignature(String header, String payload) {
        String signature = null;
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            mac.init(secretKey);
            String src = header + '.' + payload;
            signature = Base64.getEncoder().encodeToString(mac.doFinal(src.getBytes()));
        } catch (Exception e) {

        }
        return signature;
    }

    @Override
    public JwtPayload verifyJwt(String jwtString) {

        String[] t = jwtString.split("\\.");
        if (t.length != 3) {
            return null;
        }
        String headerString = t[0];
        String payloadString = t[1];
        String signature = t[2];
        if (signature.equals(generateSignature(headerString, payloadString)) == false) {
            return null;
        }
        User user = new User();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            payloadString = base64decode(payloadString);
            JwtPayload jwtPayload = objectMapper.readValue(payloadString.getBytes(), JwtPayload.class);
            return jwtPayload;
        } catch (Exception e) {
            return null;
        }

    }

}
