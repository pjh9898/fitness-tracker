package study.fitness.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.fitness.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUserId(String id);

    Optional<User> findByNickname(String nickname);
}
