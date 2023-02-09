package com.api.user.repositories;

import com.api.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByCpf(String cpf);
    @Query(value = "select u from TB_USERS u where u.name like ?1%")
    Page<User> getName(String name, Pageable pageable);
    @Query(value = "select u from TB_USERS u where u.address.state like ?1%")
    Page<User> findByState(String state, Pageable pageable);
    @Query(value = "select u from TB_USERS u where u.isActive like ?1%")
    Page<User> findByIsActive(Boolean status, Pageable pageable);
}
