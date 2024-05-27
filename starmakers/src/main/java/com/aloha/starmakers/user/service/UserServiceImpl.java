package com.aloha.starmakers.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import com.aloha.starmakers.user.dto.UserAuth;
import com.aloha.starmakers.user.dto.Users;
import com.aloha.starmakers.user.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public boolean login(Users user, HttpServletRequest request) throws Exception {
        // // 💍 토큰 생성
        String username = user.getEmail();    // 아이디 (✅ 이메일)
        String password = user.getConfirmPassword();    // 암호화되지 않은 비밀번호
        UsernamePasswordAuthenticationToken token 
            = new UsernamePasswordAuthenticationToken(username, password);
        
        // 토큰에 요청 정보 등록 (바로로그인)
        token.setDetails( new WebAuthenticationDetails(request));

        // 토큰을 이용하여 인증
        Authentication authentication = authenticationManager.authenticate(token);

        // 인증 여부 확인
        boolean result = authentication.isAuthenticated();

        // 시큐리티 컨텍스트에 등록
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // 세션의 정보 등록
        user = userMapper.select(username);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        return result;
    }

    @Override
    public Users select(String username) throws Exception {
        Users user = userMapper.select(username);
        return user;
    }

    @Override
    public int join(Users user) throws Exception {
        String email = user.getEmail();
        String password = user.getPassword();
        String encodedPassword = passwordEncoder.encode(password);  // 🔒 비밀번호 암호화
        user.setPassword(encodedPassword);

        // 회원 등록
        int result = userMapper.join(user);

        if( result > 0 ) {
            // 회원 기본 권한 등록
            UserAuth userAuth = new UserAuth();
            userAuth.setUserId(email);              // ✅ 아이디(이메일)
            userAuth.setAuth("ROLE_USER");
            result = userMapper.insertAuth(userAuth);
        }
        return result;
    }

    @Override
    public int update(Users user) throws Exception {
        int result = userMapper.update(user);
        return result;
    }

    @Override
    public int insertAuth(UserAuth userAuth) throws Exception {
        int result = userMapper.insertAuth(userAuth);
        return result;
    }

    @Override
    public int delete(Users user) throws Exception {

        int result = userMapper.delete(user);
        return result;
    }

    @Override
    public Users read(String email) throws Exception {

        Users user = userMapper.read(email);
        return user;
    }

    // 가입 여부 확인
    @Override
    public int selectEmail(Users user) throws Exception {
        int result = userMapper.selectEmail(user);
        
        return result;
    }

    // 아이디 중복 확인
    @Override
    public int selectId(Users user) throws Exception {
        int result = userMapper.selectId(user);
        return result;
    }

}