package com.example.security_study.filter;

import com.example.security_study.util.JWTUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    @Autowired private JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        if(header == null || !header.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = header.substring(7);
        try {
            if(jwtUtil.validation(token)) {
                // token에서 loginId, roles 추출
                String loginID = jwtUtil.getLoginId(token);
                List<String> roles = jwtUtil.getRoles(token);

                // roles를 Spring security의 요청에 맞는 모양으로 변경
                List<SimpleGrantedAuthority> auths = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .toList();

                // 토큰 값을 Spring security의 요청에 맞는 모양으로 변경
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(loginID, null, auths);

                // 해당 토큰값을 Spring security에 보고
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }
}
