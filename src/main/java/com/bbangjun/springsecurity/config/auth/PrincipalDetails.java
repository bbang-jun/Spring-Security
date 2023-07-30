package com.bbangjun.springsecurity.config.auth;

import com.bbangjun.springsecurity.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인 진행
// 로그인 진행 완료 시 시큐리티 session을 생성 (Security ContextHolder)
// 오브젝트 타입 => Authentication 타입 객체. Authentication 안에 User 정보가 있어야 함.
// Security Session에 session 정보를 저장하며, 들어갈 수 있는 객체는 Authentication
// Authentication 안에 들어갈 수 있는 객체는 UserDeatails 객체. 이를 통해 User object에 접근 가능
// Security Session => Authentication => UserDetails(PrincipalDetails)
public class PrincipalDetails implements UserDetails { // PrincipalDetails가 UserDetails 타입이 됨.

    private User user; // 콤포지션

    public PrincipalDetails(User user){
        this.user = user;
    }

    // 해당 User의 권한을 return하는 메서드
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
