package com.identity.identity_service.repositoty;

import com.identity.identity_service.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRegistrationRepository extends JpaRepository<Admin,Integer>
{
    Admin findByPhone(String phone);
}
