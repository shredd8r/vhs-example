package com.example.app.service;

import com.example.app.entity.User;
import com.example.app.entity.UserRole;
import io.vhs.data.Dao;
import io.vhs.data.condition.Condition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Primary
@Service
class UserService implements UserDetailsService {

    @Autowired
    private Dao dao;

    @Transactional
    @EventListener(ApplicationReadyEvent.class)
    void init() {
        if (!dao.load(User.class).exists()) {
            createAdminUser();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return dao.load(User.class)
                .condition(Condition.equals("username", username))
                .joinAll()
                .optional()
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    private void createAdminUser() {
        User user = dao.newInstance(User.class);
        user.setName("Администратор");
        user.setUsername("admin");
        user.setPassword("admin");
        user.setRole(UserRole.ADMIN);
        dao.create(user);
    }
}
