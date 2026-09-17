package com.example.app.ui.view;

import com.example.app.entity.User;
import io.vhs.ui.view.DataView;
import org.springframework.security.access.prepost.PreAuthorize;

@PreAuthorize("hasRole('ADMIN')")
public class UsersView extends DataView<User> {

    public UsersView() {
        super(User.class);
    }
}
