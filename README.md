# VHS Example

Демонстрационный проект на основе [VHS](https://vhsframework.ru/)

## Что включено

- [Конфигурация Spring Boot](src/main/java/com/example/app/config/ApplicationConfiguration.java)
- [Подключение базы данных](src/main/resources/application.properties)
- [Навигационное меню](src/main/java/com/example/app/MainView.java)
- [Аудит изменений](src/main/java/com/example/app/config/AuditConfiguration.java)
- [Базовая сущность](src/main/java/com/example/app/common/BaseEntity.java)
  - [Демо сущность](src/main/java/com/example/app/demo/DemoEntity.java)
  - [Пользователи, роли, аутентификация](src/main/java/com/example/app/user)
  - [Загрузка, хранение и скачивание файлов](src/main/java/com/example/app/file)
  - [Кастомные настройки](src/main/java/com/example/app/settings)

## Запуск

- `Java 17+`
- `CREATE DATABASE vhs-example`
- `mvn spring-boot:run`
- `http://localhost:8080`