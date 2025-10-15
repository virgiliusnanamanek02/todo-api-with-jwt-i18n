# 📝 To-Do List API — Spring Boot + JWT + i18n 🌍

Proyek ini adalah REST API sederhana untuk **manajemen pengguna (user)** dan **daftar tugas (to-do)**.
Dibangun menggunakan **Spring Boot**, **Spring Data JPA**, **PostgreSQL**, dan **Spring Security JWT**.
Proyek ini juga mendukung **multi-bahasa (i18n)** melalui `Accept-Language` header (`en` & `id`).

---

## Clone Project

### 1️⃣ Clone repository dari GitHub (gunakan SSH)
```bash
git clone git@github.com:virgiliusnanamanek02/todo-api-with-jwt-i18n.git
```
### 2️⃣ Masuk ke folder proyek
```bash
cd todo-api-with-jwt-i18n
```

### 3️⃣ Pastikan branch utama bernama master
```bash
git branch -M master
```

## 🚀 Fitur Utama

| Fitur                      | Deskripsi                                                                                                       |
| -------------------------- | --------------------------------------------------------------------------------------------------------------- |
| 🔐 **User Authentication** | Registrasi, login, dan pengelolaan profil pengguna                                                              |
| 🧾 **To-Do Management**    | CRUD task (Create, Read, Update, Delete) per user                                                               |
| 🔑 **JWT Security**        | Autentikasi berbasis token JWT                                                                                  |
| 🌐 **i18n Support**        | Bahasa Inggris (`en`) & Indonesia (`id`)                                                                        |
| 🧩 **Error Handling**      | Global exception handler dengan respons JSON terstruktur                                                        |
| 🧱 **Database**            | PostgreSQL + JPA (`ddl-auto=update`)                                                                            |
| 🧠 **Clean Architecture**  | SoC (Separation of Concerns): `controller`, `service`, `impl`, `repository`, `model`, `dto`, `common`, `config` |

---

## 📂 Struktur Proyek

```
src/
├── main/
│   ├── java/com/todo/todoapi/
│   │   ├── config/                  # Konfigurasi global
│   │   │   ├── AppConfig.java       # MessageSource, LocaleResolver, PasswordEncoder
│   │   │   ├── SecurityConfig.java  # (JWT Filter, HTTP Security)
│   │   │   ├── JwtAuthFilter.java
│   │   │   └── JwtUtils.java
│   │   │
│   │   ├── common/                  # Struktur respons dan handler error
│   │   │   ├── ApiResponse.java
│   │   │   ├── ErrorResponse.java
│   │   │   ├── GlobalExceptionHandler.java
│   │   │   └── Messages.java        # Key untuk i18n messages
│   │   │
│   │   ├── user/
│   │   │   ├── controller/          # Endpoint API untuk user
│   │   │   │   └── UserController.java
│   │   │   ├── dto/                 # Data Transfer Object
│   │   │   │   ├── LoginRequest.java
│   │   │   │   ├── LoginResponse.java
│   │   │   │   ├── RegisterRequest.java
│   │   │   │   └── UserResponse.java
│   │   │   ├── model/
│   │   │   │   └── User.java
│   │   │   ├── repository/
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/
│   │   │   │   ├── UserService.java
│   │   │   │   └── impl/UserServiceImpl.java
│   │   │   └── service/security/
│   │   │       └── CustomUserDetailsServiceImpl.java
│   │   │
│   │   ├── task/
│   │   │   ├── controller/TaskController.java
│   │   │   ├── model/Task.java
│   │   │   ├── dto/TaskRequest.java
│   │   │   ├── dto/TaskResponse.java
│   │   │   ├── repository/TaskRepository.java
│   │   │   └── service/TaskService.java
│   │   │
│   │   └── TodoApiApplication.java  # Main class (Spring Boot entry point)
│   │
│   └── resources/
│       ├── application.properties
│       ├── messages_en.properties   # Bahasa default (Inggris)
│       ├── messages_id.properties   # Bahasa Indonesia
│
└── test/ (unit test)
```

---

## ⚙️ Setup Lingkungan

### 1️⃣ Pastikan sudah terinstall

* Java 17+
* Maven / Gradle
* PostgreSQL (jalankan di port `5432`)
* IDE: IntelliJ IDEA / VS Code / Eclipse

### 2️⃣ Buat Database

```sql
CREATE DATABASE todo_app_db;
```

### 3️⃣ Update file `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/todo_app_db
spring.datasource.username=postgres
spring.datasource.password=yourpassword
```

---

## 🧩 Jalankan Proyek

```bash
mvn spring-boot:run
```

atau dari IDE:

```
Run → TodoApiApplication
```

API akan berjalan di:

```
http://localhost:8080
```

---

## 🔐 Endpoint Utama (User)

| HTTP     | Endpoint              | Deskripsi                    | Auth |
| -------- | --------------------- | ---------------------------- | ---- |
| `POST`   | `/api/users/register` | Registrasi user baru         | ❌    |
| `POST`   | `/api/users/login`    | Login, mendapatkan token JWT | ❌    |
| `GET`    | `/api/users/{email}`  | Mendapatkan profil user      | ✅    |
| `PUT`    | `/api/users/{id}`     | Update data user             | ✅    |
| `DELETE` | `/api/users/{id}`     | Hapus user                   | ✅    |

---

## 🧾 Contoh Request / Response

### 🔹 Register

**POST** `/api/users/register`

```json
{
  "username": "virgilius",
  "email": "virgilius@example.com",
  "password": "123456"
}
```

✅ Response

```json
{
  "success": true,
  "message": "User registered successfully",
  "data": {
    "id": 1,
    "username": "virgilius",
    "email": "virgilius@example.com"
  }
}
```

---

### 🔹 Login

**POST** `/api/users/login`

```json
{
  "email": "virgilius@example.com",
  "password": "123456"
}
```

✅ Response

```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
}
```

Gunakan token ini untuk semua request berikutnya:

```
Authorization: Bearer <token>
```

---

## 🌐 i18n (Internationalization)

API mendukung dua bahasa:

* 🇺🇸 English (default)
* 🇮🇩 Bahasa Indonesia

Tambahkan header ke request:

```
Accept-Language: id
```

contoh response:

```json
{
  "success": true,
  "message": "Login berhasil",
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9..."
  }
}
```

---

## ⚠️ Error Response (GlobalExceptionHandler)

Contoh jika user tidak ditemukan:

```json
{
  "status": 400,
  "error": "Bad Request",
  "message": "User not found",
  "timestamp": "2025-10-09T20:42:31.120234"
}
```

Semua error ditangani secara global melalui `GlobalExceptionHandler`.

---

## 🧠 Arsitektur (SoC - Separation of Concerns)

| Layer          | Fungsi                                   |
| -------------- | ---------------------------------------- |
| **controller** | Menangani HTTP request & response        |
| **service**    | Menangani logika bisnis                  |
| **repository** | Komunikasi dengan database               |
| **model**      | Entity JPA                               |
| **dto**        | Transfer data antara layer               |
| **common**     | Response wrapper & handler error         |
| **config**     | Konfigurasi global (security, i18n, jwt) |

---

## 🔑 JWT Flow (Simplified)

```
[Login Request]
     ↓
[UserServiceImpl.login()]
     ↓  (generate token)
[JwtUtils.generateToken()]
     ↓
[Return token ke client]
     ↓
Client → kirim token (Authorization: Bearer xxx)
     ↓
[JwtAuthFilter] → validateToken()
     ↓
SecurityContextHolder authenticated
     ↓
Request boleh lanjut ke Controller
```

---

## 🧰 Tools & Dependencies

* **Spring Boot Starter Web**
* **Spring Boot Starter Data JPA**
* **Spring Boot Starter Security**
* **Spring Boot Starter Validation**
* **PostgreSQL Driver**
* **io.jsonwebtoken (JJWT 0.13.0)**
* **Spring Boot DevTools**

---

## 👨‍💻 Developer

| Role                              | Developer          |
| --------------------------------- | ------------------ |
| Backend (User, Auth, JWT, i18n)   | **Virgilius N. M**      |
| Backend (Task CRUD, User linkage) | **Arnold M** |

---

## 🧱 Next Steps

1. Buat `Task` module:

   * `Task.java`, `TaskRepository.java`, `TaskServiceImpl.java`, `TaskController.java`
   * Pastikan ada relasi `@ManyToOne` ke `User`
2. Gunakan token JWT untuk identifikasi user saat menambah task.
3. Buat endpoint:

   * `POST /api/tasks`
   * `GET /api/tasks`
   * `PUT /api/tasks/{id}`
   * `DELETE /api/tasks/{id}`

---


## 🗄️ **Database Design (Entities & Relationships)**

Proyek ini memiliki dua entitas utama: **`users`** dan **`tasks`**.
Keduanya saling berelasi dengan pola **One-to-Many** — satu user dapat memiliki banyak task, tetapi satu task hanya dimiliki oleh satu user.

---

### 🧱 **Tabel: `users`**

| Kolom        | Tipe Data        | Keterangan                        |
| ------------ | ---------------- | --------------------------------- |
| `id`         | `BIGSERIAL` (PK) | Primary key                       |
| `username`   | `VARCHAR(30)`    | Nama unik pengguna                |
| `email`      | `VARCHAR(100)`   | Email unik, digunakan untuk login |
| `password`   | `VARCHAR(100)`   | Password terenkripsi (BCrypt)     |
| `created_at` | `TIMESTAMP`      | Waktu pembuatan akun              |
| `updated_at` | `TIMESTAMP`      | Waktu terakhir diperbarui         |

---

### 📋 **Tabel: `tasks`**

| Kolom         | Tipe Data        | Keterangan                              |
| ------------- | ---------------- | --------------------------------------- |
| `id`          | `BIGSERIAL` (PK) | Primary key                             |
| `title`       | `VARCHAR(100)`   | Judul task                              |
| `description` | `TEXT`           | Deskripsi task                          |
| `status`      | `VARCHAR(20)`    | Misal: `PENDING`, `IN_PROGRESS`, `DONE` |
| `due_date`    | `TIMESTAMP`      | Tenggat waktu tugas                     |
| `created_at`  | `TIMESTAMP`      | Tanggal dibuat                          |
| `updated_at`  | `TIMESTAMP`      | Tanggal diperbarui                      |
| `user_id`     | `BIGINT` (FK)    | Relasi ke `users.id`                    |

---

### 🔗 **Relasi antar tabel**

```
          ┌──────────────┐
          │    users     │
          ├──────────────┤
          │ id (PK)      │
          │ username     │
          │ email        │
          │ password     │
          │ created_at   │
          │ updated_at   │
          └──────┬───────┘
                 │ 1
                 │
                 │
                 │ N
          ┌──────┴───────┐
          │    tasks     │
          ├──────────────┤
          │ id (PK)      │
          │ title        │
          │ description  │
          │ status       │
          │ due_date     │
          │ user_id (FK) │───► users.id
          └──────────────┘
```

---

### 🧠 **Catatan Penting**

* **Cascade Remove:** ketika `User` dihapus, seluruh `Task` miliknya ikut terhapus.
* **orphanRemoval = true:** task yang dilepas dari `User` akan otomatis dihapus dari DB.
* **Foreign Key Constraint:** `tasks.user_id` wajib mengacu ke `users.id`.
* **Validasi:** task tidak bisa dibuat tanpa user yang valid.

---

## 🧩 License

MIT — bisa dipakai untuk sumber pembelajaran

---

