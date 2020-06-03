package ru.eracom.persist.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.eracom.persist.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
