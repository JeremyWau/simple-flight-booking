# simple-flight-booking

You can find more information about the features and their assumptions in the features folder.

## How to
### Run

Run 'docker compose up'

The database and backend will start up. Backend will be accessible on port 8080.

System contains some default data upon first startup.

Default users (username / password):
- test / test
- test-2 / test

### Interact

To interact with the plaform, use the postman collection. You can also see below in this document a list of existing data.

First of all, ensure that you have an environment selected (top right).

You will find three folders:
1. Authentication
   1. Registration endpoint
   2. Login endpoint, there are already 2 existing users upon startup (test / test) and (test-2 / test)
2. Search
   1. The details for a specific scheduled flight (given its departure, arrival and date in format YYYY-MM-DD e.g. 2026-04-23) - See in "Existing data" below for dates with flights.
   2. The listing for all the upcoming scheduled flights, with seats available
3. Booking, this requires authentication, you can either use the default behaviour included (pre request script is authenticate with "test" user) or manually override the token by authenticating with your account and copying the token into the Authorization header.
   1. Booking a scheduled flight by providing a json object with its id and the amount of seats
   2. List all bookings 
   3. View a specific booking by providing its id in the path
   4. Delete a specific booking by providing its id in the path

### Existing data
#### Flights:
```
ID	DEP     ARR     SEAT CAPACITY  
1	"LFMN"	"EBBR"	120  
2	"LFML"	"EBBR"	115  
3	"LSGG"	"EBBR"	95  
4	"EBBR"	"LFMN"	120  
5	"KJFK"	"EBBR"	190  
6	"EBBR"	"KJFK"	190  
```

#### Scheduled Flights:
```
ID	DATE                           	 	FLIGHT_ID  
1	CURRENT_DATE - INTERVAL '1 day' 	1  
2	CURRENT_DATE	                	1  
3	CURRENT_DATE + INTERVAL '1 day' 	1  
4	CURRENT_DATE + INTERVAL '7 days'	1  
5	CURRENT_DATE + INTERVAL '8 days'	1  
6	CURRENT_DATE + INTERVAL '2 days'	2  
7	CURRENT_DATE + INTERVAL '14 days'	2  
8	CURRENT_DATE - INTERVAL '7 days'	5  
9	CURRENT_DATE + INTERVAL '1 day' 	5  
10	CURRENT_DATE + INTERVAL '7 days'	5  
11	CURRENT_DATE + INTERVAL '3 days'	6
```
## Tests

To run the tests, use maven 'mvn test'

## Consideration for improvements
### Microservices for scalability
To allow complete scaling of the application, it would be interesting to split it into independent microservices.
I would recommend splitting these as:
1. Flight search
2. Flight booking
3. User management

Each with their own Database (true microservice).

The reason is that there will most likely be much more search request made than actual booking.
This would mean that this service can be scale independently of the others as there will be a big difference between read/write.

Finally, Flight booking and Flight search should communicate between them with a service bus (Kafka, ...) to ensure an eventual consistency and a real separation between the microservices.

### Credentials
Also, in a work environment, I would recommend using environment variables to prevent exposing credentials/... on the repo.

### Use of Flyway (or equivalent db versioning)
To ensure database consistency and versioning, the usage of Flyway is recommended, allowing to track db changes, apply them incrementally for example over existing database.  
Setting it up would be quite straight forward by:
1. Adding the maven dependency.
2. Creating the base migration (For example using Intellij Ultimate's persistence tool).
3. And then creating diff migrations based on the previous model snapshots and actual state of the model. 