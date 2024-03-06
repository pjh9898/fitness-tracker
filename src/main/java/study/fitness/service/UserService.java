package study.fitness.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.fitness.auth.JwtUtil;
import study.fitness.domain.User;
import study.fitness.dto.JWTInputDto;
import study.fitness.dto.LoginRequestDto;
import study.fitness.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public String signUp(User user) {
        validateDuplicateUser(user.getUserId());

        userRepository.save(user);
        return user.getUserId();
    }

    public boolean validateDuplicateUser(String userId) {
        boolean isDuplicate = userRepository.existsByUserId(userId);

        if (isDuplicate) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        return true;
    }


    public String login(LoginRequestDto dto) {
        String id = dto.getUserId();
        String password = dto.getPassword();
        User user = userRepository.findByUserId(id).orElseThrow(() -> new UsernameNotFoundException("해당 회원이 존재하지 않습니다."));

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }

        JWTInputDto info = JWTInputDto.of(user.getUserId(), user.getNickname());

        return jwtUtil.createAccessToken(info);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> fineOne(String userId) {
        return userRepository.findByUserId(userId);
    }
}
