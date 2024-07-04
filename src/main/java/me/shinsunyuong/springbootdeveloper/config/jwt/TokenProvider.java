package me.shinsunyuong.springbootdeveloper.config.jwt;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import me.shinsunyuong.springbootdeveloper.domain.User;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.SignatureException;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Handler;

@RequiredArgsConstructor
@Service

public class TokenProvider {

    private final JwtProperties jwtProperties;

    public String generateToken(User user, Duration expiredAt) {
        Date now = new Date();
        return makeToken(new Date(now.getTime() + expiredAt.toMillis()), user);
    }

    //jwt 생성 메서드
    public String makeToken(Data data) {
        Date now = new Date();

        return Jwts.builder()
                .setHeaderParam(Handler.TYPE, Handler.JWT_TYPE) //헤더 typ: JWT ,내용 iss : properties에서 설정한값
                .setIssuer(jwtProperties.getIssuer())
                .setIssuedAt(now) //내용 iat 현재시간
                .setExprition(expiry) //내용 exp : expriy 맴버 변수값
                .setSubject(user.getEail()) //내용 sub : 유저 이메일
                .claim("id", user.getId())  //클레임아이디 : 유저 아이디
                //서명 : 비밀값과 함깨 해시값을 HS256 방식으로 암호화
                .signWith(SignatureAlgotithm.HS256, jwtProperties.getSecretKey())
                .compact();
    }

    // JWT 유효성 검증 메서드
    public boolean validateToken(String token) {
       try{
           jwts.parser()
                   .setSigningKey(jwtProperties.getSecretKey()) //비밀값으로 복호화
                   .parseClaimsJws(token);

           return true;
       } catch (Exception e) { //  복호과정에서 에러나면 유효 x
           return false;
       }
    }

    //토큰기반 으로 인증정보를 가져오는 메서드
    public Authentication getAuthentication(String token) {
        Claims claims = getClaims(token);
        Set<SimpleGrantedAuthority> authorities = Collections.singleton(new
                SimpleGrantedAuthority("ROLE_USER"));

        return new UsernamePasswordAuthenticationToken(new org.springframework
                .security.core.userdetails.User(claims.getSubject(), "",
                authorities), token, authorities);
    }

    //토큰기반으로 유저 id를 가져오는메서드
    public Long getUserId(String token) {
        Claims claims = getClaims(token);
        return claims.get("id", Long.class);
    }
    private Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(jwtProperties.getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }
}
