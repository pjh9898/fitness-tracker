package study.fitness.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.fitness.auth.JwtUtil;
import study.fitness.domain.User;
import study.fitness.dto.LoginRequestDto;
import study.fitness.dto.UserLoginDto;
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
    private final ModelMapper modelMapper;

    @Transactional
    public String signUp(User user) {
        validateDuplicateUser(user.getUserId());

        userRepository.save(user);
        return user.getUserId();
    }

    public boolean validateDuplicateUser(String userId) {
        boolean isValid = !userRepository.existsByUserId(userId);

        if (!isValid) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

        return true;
    }


    public String login(LoginRequestDto dto) {
        System.out.println("dto = " + dto);
        String id = dto.getUserId();
        System.out.println("id = " + id);
        String password = dto.getPassword();
        System.out.println("password = " + password);
        User user = userRepository.findByUserId(id).orElseThrow(() -> new UsernameNotFoundException("해당 회원이 존재하지 않습니다."));

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지 않습니다");
        }

        UserLoginDto info = modelMapper.map(user, UserLoginDto.class);

        return jwtUtil.createAccessToken(info);
    }

    public List<User> findUsers() {
        return userRepository.findAll();
    }

    public Optional<User> fineOne(String userId) {
        return userRepository.findByUserId(userId);
    }
}
