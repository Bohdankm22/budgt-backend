package com.trio.budgt.data.repository;

import com.trio.budgt.data.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}