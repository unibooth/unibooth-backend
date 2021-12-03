package com.unibooth.unibooth.stamp.repository;

import com.unibooth.unibooth.domain.user.model.User;
import com.unibooth.unibooth.stamp.model.Collect;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectRepository extends JpaRepository<Collect, Long> {

    long countByUser(User user);
}
