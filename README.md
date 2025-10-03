# Spring Boot Blog System

Вебдодаток для створення та управління блогом, розроблений з використанням Spring Boot та MVC архітектури.

## 🚀 Технології

- **Backend:** Spring Boot, Spring MVC, Spring Data JPA
- **Security:** JWT Authentication, Spring Security
- **Database:** H2 (in-memory)
- **View:** Thymeleaf Templates
- **Build Tool:** Maven/Gradle

## 📋 Функціональність

- ✅ CRUD операції для постів
- ✅ Реєстрація та аутентифікація користувачів
- ✅ Система авторизації (редагування власних постів)
- ✅ Пагінація та сортування списку постів
- ✅ Унікальні slug для постів
- ✅ Зв'язок User ↔ Post (один-до-багатьох)
- ✅ JWT токени в HTTP-only cookies

## 🏗️ Архітектура

Проєкт побудований за принципом тришарової архітектури:

```
Controller → Service → DAO/Repository
```

## 🗂️ Структура проєкту

```
src/main/java/com.blog/
├── application/
│   ├── contract/          # Сервісні інтерфейси
│   ├── controller/        # HTTP endpoints (MVC Controllers)
│   ├── dto/              
│   │   ├── req/          # Request DTO
│   │   └── resp/         # Response DTO
│   ├── mapper/           # Entity ↔ DTO мапери
│   └── service/          # Реалізація бізнес-логіки
├── domain/               # Доменні сутності (User, Post)
├── infrastructure/
│   ├── dao/             # Data Access Objects
│   └── view/            # Projection Views
└── security/            # JWT фільтри та конфігурація
```

## 🔐 Безпека

- JWT токени зберігаються в HTTP-only cookies
- Spring Security для авторизації та аутентифікації
- Захист від XSS та CSRF атак
- Валідація вхідних даних (@Valid, @Validated)
- Кастомний JWT Authentication Filter

## 🚦 Запуск проєкту

```bash
# Клонувати репозиторій
git clone git@github.com:mykyta-k1/spring-blog-mvc.git

# Перейти в директорію
cd spring-blog-mvc

# Запустити додаток
./mvnw spring-boot:run
```

Додаток буде доступний за адресою: `http://localhost:8080`

## 🌐 Endpoints

### Сторінки (MVC)
- `GET /auth/login` - Сторінка входу
- `GET /auth/register` - Сторінка реєстрації
- `GET /posts` - Список постів (з пагінацією та сортуванням)
- `GET /posts/{slug}` - Перегляд окремого поста
- `POST /posts/add-post` - Створення поста
- `POST /posts/{slug}/update` - Оновлення поста

### API
- `POST /auth/login` - Аутентифікація (отримання JWT)
- `POST /auth/register` - Реєстрація користувача
- `GET /user/info` - Інформація про поточного користувача

## 📊 Особливості реалізації

### Пагінація та сортування
```java
// Приклад: /posts?page=0&size=10&sort=createdAt=desc
PageRequest.of(page, size, Sort.by(parseSort(sort)))
```

### JWT в Cookies
```java
Cookie cookie = new Cookie("token", token);
cookie.setHttpOnly(true);  // Захист від XSS
cookie.setPath("/");
cookie.setMaxAge(86400);   // 24 години
```

### Авторизація власника
Користувач може редагувати лише свої пости. Перевірка відбувається на рівні контролера:
```java
boolean isOwner = post.getAuthor().getId().equals(currentUser.getId());
```

## 🎓 Навчальна мета

Проєкт створений в рамках практичної роботи для вивчення:
- Spring MVC та Spring Boot
- JPA/Hibernate (Entity relationships)
- Тришарової архітектури (Controller-Service-DAO)
- JWT аутентифікації з Spring Security
- RESTful та MVC patterns
- DTO pattern та Mappers
- Thymeleaf Templates

## 📄 Ліцензія

MIT License
