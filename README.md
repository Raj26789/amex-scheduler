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

2️⃣ Create the Command File
✅ On Linux/macOS:
Create a file at /tmp/commands.txt

✅ On Windows:
Create a file at C:\tmp\commands.txt

Add the following content to it:

# One-time command - Run at 11:00 on 14 May 2025
0 11 14 5 2025 echo $(date) && echo "At Amex we do what's right"

# Recurring every 1 minute
*/1 echo $(date) && echo "Amex motto is Don't live life without it"
3️⃣ Build the Project

mvn clean install
4️⃣ Run the Scheduler

java -cp target/amex-schedule-0.1.jar com.amex.scheduler.AmexScheduler
📂 Output
All command outputs are logged in:

sample-output.txt
⚙️ CI/CD with GitHub Actions
This project includes a basic GitHub Actions workflow (.github/workflows/maven.yml) that:

Builds the project on push

Verifies successful compilation and tests (if any)

👤 Author
Rajkumar N
https://github.com/Raj26789


