# Documentation

This document explains how the project works end-to-end and how to run/modify it.

## What this app does
- Creates a `college` table in an in-memory H2 database via Hibernate.
- Inserts three rows: `(101, CU, Punjab)`, `(102, DU, Delhi)`, `(103, PU, Patna)` if the table is empty.
- Prints all rows to the console.

Console output format:
```
cid	cName	clocation
101	CU	Punjab
102	DU	Delhi
103	PU	Patna
```

## Tech stack
- Java 17
- Maven
- Hibernate ORM 6
- H2 (in-memory) database
- SLF4J (simple) for logging

## Important files
- `pom.xml` — manages dependencies and run configuration
- `src/main/resources/hibernate.cfg.xml` — Hibernate configuration (H2 URL, dialect, DDL settings)
- `src/main/java/com/example/databaseeval/College.java` — JPA entity mapping for `college`
- `src/main/java/com/example/databaseeval/Main.java` — program entry point (seed + print)

## How the code works
1. Configuration
   - `hibernate.cfg.xml` sets up an H2 in-memory database (`jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1`) and Hibernate dialect.
   - `hbm2ddl.auto=update` tells Hibernate to create/update the schema automatically to match entities.

2. Entity mapping (`College`)
   - Annotated with `@Entity` and `@Table(name = "college")`.
   - Fields: `cid` (primary key), `cName`, `cLocation` mapped to columns `cid`, `cName`, `clocation`.

3. App flow (`Main`)
   - Builds a `SessionFactory` using `hibernate.cfg.xml` and registers the `College` entity.
   - Calls `seedData(...)`:
     - Starts a transaction.
     - Checks if any `College` rows exist; if 0, persists three sample rows.
     - Commits the transaction.
   - Calls `printAllColleges(...)`:
     - Queries `from College` and prints a header and each row.

## How to run
From the project root:
```
mvn clean
mvn compile
mvn exec:java
```

## Changing the data
- Edit `seedData` in `Main.java` to modify the inserted rows or add more.
- Re-run the app; because the DB is in-memory, data resets on each run.

## Persisting data between runs (optional)
To switch from in-memory to a file-backed H2 database, change the URL in `hibernate.cfg.xml`:
```
jdbc:h2:file:./data/testdb;AUTO_SERVER=TRUE
```
Then create the `data` folder or let H2 create it, and run the app again.

## Troubleshooting
- Typo in Maven phase: ensure `mvn compile` (not `complie`).
- Java version: confirm Java 17+ (`java -version`).
- Clean build: run `mvn clean` before compiling if dependencies changed.

## FAQ
- Does the code print the data to the command line?
  - Yes. It prints a header and all `college` rows as shown above.
- Does the code create the table automatically?
  - Yes. Hibernate DDL setting `hbm2ddl.auto=update` creates/updates the table based on the `College` entity.
- Can I target MySQL/PostgreSQL instead of H2?
  - Yes. Update JDBC URL, driver, and dialect in `hibernate.cfg.xml` and add the appropriate JDBC driver to `pom.xml`.
