# Database Eval - Hibernate Console App

This Java Maven project uses Hibernate ORM with an in-memory H2 database. It creates a `college` table, inserts sample rows, and prints them to the console.

## Requirements
- Java 17+
- Maven 3.8+

## Run steps
```bash
mvn clean
mvn compile
mvn exec:java
```

Expected output:
```text
cid	cName	clocation
101	CU	Punjab
102	DU	Delhi
103	PU	Patna
```

## Files
- `src/main/java/com/example/databaseeval/College.java` — JPA entity for `college`
- `src/main/java/com/example/databaseeval/Main.java` — Seeds and prints data
- `src/main/resources/hibernate.cfg.xml` — Hibernate config (H2 in-memory)
- `pom.xml` — Dependencies and exec plugin

## Notes
- Uses `hbm2ddl.auto=update` to auto-manage schema.
- DB is in-memory (`jdbc:h2:mem:testdb`) and resets each run.
