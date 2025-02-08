# Pharmacy Management System
## 📝 Project Description
The Pharmacy Management System is a Java Swing desktop application designed to streamline operations for pharmacies and drugstores. It enables healthcare professionals to manage medication inventory, process sales, track prescriptions, and generate analytical reports. With integrated AI-powered chatbots and real-time data visualization, the system helps pharmacies optimize stock levels, improve customer service, and comply with regulatory requirements. Built using Java Swing for the frontend and MySQL for database management, it supports role-based access for admins, pharmacists, and inventory managers.

## 🚀 Features
### 📦 Inventory Management 
- **Category-wise Stock Tracking**: Organize medicines by type (tablets, liquids, equipment)
- **Medicine Management**: Add/Edit/Delete medicines with batch details
- **Expiration Alerts**: Automatic notifications for expiring medications

### 💰 Sales & Billing
- **Dynamic Receipt Generation**: Customizable templates with clinic/drugstore branding
- **Sales Processing**: Track transactions and customer purchases
- **Invoice Management**: Generate and print PDF receipts

### 📊 Analytics Dashboard 
- **Interactive Charts**:  
  - Weekly sales performance
  - Top-selling medicine categories 
  - Stock distribution by category

### 🤖 AI ChatBot
- **Natural Language Processing**:  
  - Medicine information queries
  - Drug interaction checks
  - Prescription assistance

## 🛠️ Technology Stack
### Frontend:
- **Java Swing**: Desktop GUI components
- **JavaFX**: ChatBot interface
- **JFreeChart**: Interactive charts for analytics

### Backend:
- **Core Java**: Business logic and workflow
- **JDBC**: MySQL database connectivity

### Database:
- **MySQL**: Relational database management

### AI Integration:
- **Deepseek API**: Natural Language Processing for ChatBot

## 📸 Screenshots
| Login Interface | Registration Screen |
|-----------------|---------------------|
| ![Login](https://github.com/user-attachments/assets/01950950-56c1-46fc-90af-04681729cc05) | ![Signup](https://github.com/user-attachments/assets/fa51c7aa-a13a-4096-b81f-bb4850b4aa75) |

| Dashboard | Medicine Management |
|-----------|---------------------|
| ![Home](https://github.com/user-attachments/assets/90f0868b-3f96-49e5-9dd1-751866c72235) | ![Addmedecine](https://github.com/user-attachments/assets/54d7a744-4a5d-4a60-9790-91520e2059d7) |

| ChatBot Interface | Sales Processing |
|-------------------|------------------|
| ![chatBot](https://github.com/user-attachments/assets/0af6d3d5-1b42-4f2e-962e-b07969b22346) | ![addsale](https://github.com/user-attachments/assets/18149854-cb41-4a87-aef6-866e51618dc6) |

## 💻 Installation

```bash
# Clone repository
git clone https://github.com/younessaitbenyssa/Pharmacy-Management.git

#add your URL and API KEY in Llm.java
apiUrl = "URL"
apiKey = "API-KEY"

# Build the application
mvn clean install

# Run the application
mvn javafx:run 

```
## Contributors
- [OULAID MOHAMMED](https://github.com/OULAIDMOHAMMED)
