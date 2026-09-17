# Example Application

Демонстрационный проект на основе **_[VHS.framework](https://vhsframework.ru/)_**

## Навигация

- [Конфигурация Spring Boot](src/main/java/com/example/app/config/ApplicationConfiguration.java)
- [Конфигурация приложения](src/main/resources/application.properties)
- [Навигационное меню](src/main/java/com/example/app/ui/MainView.java)
- [Аудит изменений](src/main/java/com/example/app/config/AuditConfiguration.java)
- [Базовая сущность](src/main/java/com/example/app/entity/BaseEntity.java)
  - [Клиенты](src/main/java/com/example/app/entity/Client.java) / [Заказы](src/main/java/com/example/app/entity/Order.java) / [Продукты](src/main/java/com/example/app/entity/Product.java) 
  - [Пользователи](src/main/java/com/example/app/entity/User.java) / [Роли](src/main/java/com/example/app/entity/UserRole.java) 
  - [Файлы](src/main/java/com/example/app/entity/File.java)

## Запуск

- `Java 21+`
- `CREATE DATABASE example-app`
- `mvn spring-boot:run`
- `http://localhost:8080`