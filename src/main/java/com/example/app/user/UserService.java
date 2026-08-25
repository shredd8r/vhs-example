package com.example.app.user;

import io.vhs.data.Dao;
import io.vhs.data.condition.Condition;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Primary
@Service
class UserService implements UserDetailsService {

    @Autowired
    private Dao dao;

    @PostConstruct
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
