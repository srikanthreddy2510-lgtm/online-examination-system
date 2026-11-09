# Online Examination System
Spring Boot + Maven web application for online examinations and student result management.

## Requirements
JDK 17 or later and Maven 3.6.3 or later.

## Run on Windows PowerShell
Open the terminal in the folder containing pom.xml:
```powershell
mvn clean test
mvn spring-boot:run
```
Open http://localhost:8081

## H2 console
URL: http://localhost:8081/h2-console
JDBC URL: jdbc:h2:mem:examinationdb
Username: sa
Password: leave blank

## Included features
Dashboard, student listing/creation, examination creation, MCQ question creation, exam submission, automatic marks/percentage/pass-fail calculation, results listing, H2 database, Jenkinsfile and Azure Pipelines starter file.

## Sample data
Students: Srikanth Reddy (srikanth@example.com), Ananya Sharma (ananya@example.com), Rahul Kumar (rahul@example.com).
One Java Fundamentals exam with five sample questions is loaded at startup.

## REST endpoints
GET/POST /api/students
PUT/DELETE /api/students/{id}
GET/POST /api/exams
PUT/DELETE /api/exams/{id}
GET /api/exams/{id}/questions
POST /api/exams/{id}/questions
POST /api/submissions (JSON: {"studentId":1,"examId":1,"answers":{"1":1,"2":2}})
GET /api/results
GET /api/results?studentId=1

## Notes
This is a runnable educational prototype, not a production-secured examination platform. It has no login/authentication or proctoring. Admin functions are not access-controlled. H2 is in-memory, so data resets after application restart. Jenkinsfile uses shell commands; adapt commands for a Windows Jenkins agent if needed. Azure pipeline builds/tests/packages the project; deployment still needs an Azure hosting target and service configuration.
