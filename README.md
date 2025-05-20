# AmexScheduler - Command Scheduling Utility

## Overview
This utility reads scheduled commands from a file and executes them according to the schedule, logging output to `sample-output.txt`.

It supports:
- One-time scheduled commands
- Recurring scheduled commands with interval in minutes
- Runs on Windows, Linux, and macOS environments

---

## Prerequisites
- Java 8 or higher
- Maven 3.x

---

## Project Structure
- `src/main/java` - Source code
- `pom.xml` - Maven build configuration
- `.github/workflows/maven.yml` - GitHub Actions CI workflow

---

## Setup and Run Locally

### 1. Clone the repository
```bash
git clone https://github.com/<your-username>/amex-schedule.git
cd amex-schedule

### 2. Create the commands file
Create a file commands.txt in /tmp/ (Linux/macOS) or C:/tmp/ (Windows) with the following content

# One-time command - Run at 11:00 on 14 May 2025
0 11 14 5 2025 echo $(date) && echo "At Amex we do whats right"

# Recurring every 1 minute
*/1 echo $(date) && echo "Amex motto is Don't live life without it"

### 3. Build the project
mvn clean install

### 4. Run the scheduler
java -cp target/amex-schedule-0.1.jar com.amex.scheduler.AmexScheduler
