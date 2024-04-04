# HW1: Mid-term assignment

## 1. Objective

Develop a simple full-stack web application supplied with automated tests.

## 2. Project Scope

The application should provide a services API for bus tickets selling, implementing, at least, two use cases:

- Search for bus connections (trips) between two cities.

- Book a Reservation for a passenger.

In addition to the backend, you should also create a simplified web app to demonstrate the core use cases.
Variations on the theme are acceptable and welcome, given you meet the mandatory requirements presented next.

The project must include:
 1. Your own API (REST) that can be invoked by external clients. Your API should allow one to programmatically search the backend for trips (direct bus connections between two major cities), create and check reservations.
 2. A minimalist web app/page which allows users to enter/select the origin, destination, and date to search for possible trips. Then the user should pick one and provide passenger details. The web app is expected to be simple: use a limited number of “cities”, no authentication, etc.
 3. Prices should be given in the currency preferred by the user (reflecting current exchange rates). You should get the current exchange rate from an on-line resource (and cache them for optimization; the cache should implement a Time-to-Live logic).
 4. No user authentication is required for this project (web and API). Consider adding a “token”/code to the Reservation to facilitate later queries.
 5. Use some logger support (so that your solution produces a useful log of events).

 ## 3. Technologies stack

| Component | Technology |
| --------- | ---------- |
| Frontend | React + Vite|
| Backend | Spring Boot |
| Database | MySQL |
| API | [Exchange Rate API](https://app.exchangerate-api.com/) |

## 4. How to run

```bash
# In the root directory
docker compose up --build

# If the database does not have any data, you can run the following command to insert some data
./db_data.sh
```

> The Website will be available at `http://localhost:3030` and the REST API at `http://localhost:8080`

## 5. How to test

```bash
# With the docker compose running
mvn test
```

## 5. Author

José Mendes, 107188
