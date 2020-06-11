package com.spboot.springbootlesson.persist.repo;

import com.spboot.springbootlesson.persist.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
