package com.unibooth.unibooth.domain.user.repository;

import com.unibooth.unibooth.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    default User findByEmailElseThrow(String email) {
       return this.findByEmail(email).orElseThrow(
               () -> new NullPointerException("사용자 이메일을 찾을 수 없어요.")
       );
   }

}
