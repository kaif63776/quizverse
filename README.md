# ⚡ QuizVerse — Speed Evaluation Platform

An interactive full-stack coding assessment platform built with Java, Maven, MySQL, and dynamic vanilla web technologies. Features speed-tier countdowns, real-time feedback, strict answer locks, synthesized audio effects, and review analytics.

---

## 🚀 Key Features

- **4 Core Technical Domains:** Java, Python, Web Development, and DSA.
- **Tier-Based Speed Timers:**
  - 🟢 **Easy:** 15 seconds / question (15 challenges)
  - 🟡 **Medium:** 12 seconds / question (15 challenges)
  - 🔴 **Hard:** 10 seconds / question (15 challenges)
- **Strict Anti-Tamper Logic:** Questions lock permanently once an option is submitted (review mode only for past questions).
- **Zero-Latency Audio Engine:** Procedurally generated sound feedback using the Web Audio API.
- **Detailed Solution Breakdown:** End-of-quiz matrix reviewing user choices vs. verified solutions.
- **Live Leaderboard & Operations Deck:** Rank tracking, streak indicators, and scoreboards.

---

## 🛠️ Tech Stack & Requirements

- **Backend:** Java (JDK 17+)
- **Database:** MySQL Server 8.0+
- **Build Tool:** Apache Maven
- **Server:** Embedded Eclipse Jetty Plugin
- **Frontend:** HTML5, CSS3, JavaScript (ES6+, Canvas & Web Audio APIs)

---

## ⚙️ How to Run

### 1. Database Setup
```sql
CREATE DATABASE IF NOT EXISTS quiz_app;
USE quiz_app;
