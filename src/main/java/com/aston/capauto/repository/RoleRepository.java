package com.aston.capauto.repository;

import com.aston.capauto.security.Role;
import com.aston.capauto.security.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleEnum roleName);
}
