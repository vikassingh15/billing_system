package com.product.billing.repository;

import com.product.billing.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{

    Role findFirstByName(String name);
}
