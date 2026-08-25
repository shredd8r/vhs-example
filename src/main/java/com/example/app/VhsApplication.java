package com.example.app;

import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.component.page.Push;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.Lumo;
import io.vhs.ui.Vhs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Push
@StyleSheet(Lumo.STYLESHEET)
@StyleSheet(Lumo.UTILITY_STYLESHEET)
@StyleSheet(Vhs.STYLESHEET)
@PageTitle("Демо")
@SpringBootApplication
public class VhsApplication implements AppShellConfigurator {

    static void main(String[] args) {
        SpringApplication.run(VhsApplication.class, args);
    }
}
