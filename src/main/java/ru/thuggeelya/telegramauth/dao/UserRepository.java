package ru.thuggeelya.telegramauth.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.thuggeelya.telegramauth.dao.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}