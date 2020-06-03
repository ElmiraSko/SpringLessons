package com.spboot.springbootlesson.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spboot.springbootlesson.persist.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
