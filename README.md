# Pharmacy Management System 🏥

[![Java Version](https://img.shields.io/badge/Java-17+-blue.svg)](https://java.com)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)
[![Open Source](https://badges.frapsoft.com/os/v2/open-source.svg?v=103)](https://github.com/ellerbrock/open-source-badges/)

A comprehensive desktop application for managing pharmacy operations built with Java Swing.

![Dashboard Preview](screenshots/dashboard.png)

## 📋 Table of Contents
- [Features](#-features)
- [Installation](#-installation)
- [Usage](#-usage)
- [Technology Stack](#-technology-stack)
- [Screenshots](#-screenshots)
- [Contributing](#-contributing)
- [License](#-license)
- [Contact](#-contact)

## 🚀 Features
- **Inventory Management**
  - Track medication stock levels
  - Expiration date alerts
  - Automated reorder points
- **Sales & Billing**
  - Generate invoices (PDF/print)
  - Sales history tracking
  - Customer management
- **Prescription System**
  - Digital prescription records
  - Drug interaction warnings
- **Reporting**
  - Daily sales reports
  - Stock movement history
  - Financial summaries
- **Security**
  - Role-based access control
  - Password encryption
  - Audit logging

## 💻 Installation

### Prerequisites
- Java JDK 17 or later
- MySQL 8.0+
- Maven 3.8+

```bash
# Clone repository
git clone https://github.com/yourusername/pharmacy-management.git
cd pharmacy-management

# Create database (MySQL)
mysql -u root -p < database/schema.sql

# Build project
mvn clean install

# Run application
java -jar target/pharmacy-system-1.0.0.jar
