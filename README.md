# AmexScheduler - Command Scheduling Utility

## 🧭 Overview
This utility reads scheduled commands from a file and executes them according to a defined schedule, logging output to `sample-output.txt`.

It supports:
- ✅ One-time scheduled commands
- 🔁 Recurring scheduled commands (with interval in minutes)
- 💻 Works on Windows, Linux, and macOS

---

## 🔧 Prerequisites
- Java 8 or higher
- Maven 3.x

---

## 📁 Project Structure
- `src/main/java` – Java source code
- `pom.xml` – Maven project configuration
- `.github/workflows/maven.yml` – GitHub Actions CI workflow
- `sample-output.txt` – Output log after running scheduled commands

---

## 🚀 Setup & Run Instructions

### 1️⃣ Clone the Repository
```bash
git clone https://github.com/<your-username>/amex-schedule.git
cd amex-schedule
