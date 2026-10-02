package com.wasil.journal_app.respository;

import com.wasil.journal_app.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}