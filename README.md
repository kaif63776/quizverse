# ⚡ QuizVerse — Speed Evaluation Platform

An interactive, high-performance coding assessment web application built with Java, Maven, MySQL, and vanilla web standards (HTML5/CSS3/ES6+). Features 4 domains, difficulty-based countdown timers, zero-latency Web Audio sound synthesis, anti-tamper answer locking, and detailed post-quiz performance breakdowns.

---

## 🚀 Key Features

- **4 Core Technical Domains:** Java, Python, Web Development, and DSA.
- **3 Difficulty Tiers & Dynamic Speed Timers:**
  - 🟢 **Easy:** 15 seconds / question (15 challenges)
  - 🟡 **Medium:** 12 seconds / question (15 challenges)
  - 🔴 **Hard:** 10 seconds / question (15 challenges)
- **Strict Anti-Tamper Logic:** Questions lock permanently once an option is selected. Past questions are viewable only in read-only review mode.
- **Synthesized Web Audio Engine:** Procedural real-time audio (correct chime, incorrect buzzer, warning countdown ticks, victory melody, and minor chords for zero-score defeat).
- **Comprehensive Solution Matrix:** End-of-quiz audit detailing user responses vs. verified solutions.
- **Live Leaderboard & Operations Deck:** Level XP progression, 5-day active streak tracking, and live ranking feed.

---

## 🛠️ Prerequisites & Tech Stack

- **Backend:** Java JDK 17+
- **Database:** MySQL Server 8.0+
- **Build Tool:** Apache Maven (or bundled `./mvnw` wrapper)
- **Server:** Embedded Eclipse Jetty Server
- **Frontend:** Vanilla HTML5, Modern CSS (Glassmorphism, 3D Hover & Kinetic Animations), Vanilla JavaScript (Canvas Particle Engine & Web Audio API)

---

## ⚙️ How to Setup & Run

### 1. Clone the Repository
```bash
git clone [https://github.com/mohdkaif/quizbackend.git](https://github.com/mohdkaif/quizbackend.git)
cd quizbackend
2. MySQL Database Setup
Open MySQL command-line client or MySQL Workbench:

Bash


mysql -u root -p
Create the database and schema:

SQL


CREATE DATABASE IF NOT EXISTS quiz_app;
USE quiz_app;

-- Users Table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    score INT DEFAULT 0
);

-- Questions Table
CREATE TABLE IF NOT EXISTS questions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    question_text TEXT NOT NULL,
    option1 VARCHAR(255) NOT NULL,
    option2 VARCHAR(255) NOT NULL,
    option3 VARCHAR(255) NOT NULL,
    option4 VARCHAR(255) NOT NULL,
    correct_answer INT NOT NULL,
    category VARCHAR(100) NOT NULL,
    difficulty VARCHAR(20) DEFAULT 'Easy'
);
3. Database Credentials Configuration
Open src/main/java/com/kaif/quizbackend/util/DBConnection.java and verify your local MySQL credentials:

Java


private static final String URL = "jdbc:mysql://localhost:3306/quiz_app?useSSL=false&allowPublicKeyRetrieval=true";
private static final String USER = "root";
private static final String PASSWORD = "your_mysql_password"; // Replace with your MySQL root password
4. Build and Launch Server
Start the embedded Jetty server using Maven wrapper:

On macOS / Linux:

Bash


./mvnw clean compile && ./mvnw org.eclipse.jetty:jetty-maven-plugin:run
On Windows:

DOS


mvnw.cmd clean compile && mvnw.cmd org.eclipse.jetty:jetty-maven-plugin:run
5. Access the Web App
Open any browser and navigate to:

Plaintext


http://localhost:8080/index.html
📂 Project Directory Structure
Plaintext


quizbackend/
├── src/
│   ├── main/
│   │   ├── java/com/kaif/quizbackend/
│   │   │   ├── controller/      # REST API Controllers (User & Question servlets)
│   │   │   ├── dao/             # Database Access Objects (JDBC queries)
│   │   │   ├── model/           # Question & User POJO models
│   │   │   └── util/            # MySQL Connection Utility
│   │   └── webapp/
│   │       └── index.html       # Single-Page UI (HTML5, Cyber CSS, JS Engine)
├── pom.xml                      # Maven build file & Jetty plugin configuration
├── README.md                    # Project documentation
└── .gitignore                   # Ignored files (target, .class, IDE configs)
👤 Author
Developer: Mohd Kaif
