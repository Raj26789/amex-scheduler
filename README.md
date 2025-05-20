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
