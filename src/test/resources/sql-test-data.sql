-- Flights
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('EBBR', 'LFMN', 120);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('EBBR', 'LFML', 115);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('EBBR', 'LSGG', 95);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('LFMN', 'EBBR', 120);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('EBBR', 'KJFK', 190);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('KJFK', 'EBBR', 190);

-- Scheduled flights
-- EBBR LFMN
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), DATEADD('DAY', -1, CURRENT_DATE));
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), CURRENT_DATE);
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), DATEADD('DAY', 1, CURRENT_DATE));
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), DATEADD('DAY', 7, CURRENT_DATE));
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), DATEADD('DAY', 8, CURRENT_DATE));
-- EBBR LFML
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFML'), DATEADD('DAY', 2, CURRENT_DATE));
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFML'), DATEADD('DAY', 14, CURRENT_DATE));
-- EBBR KJFK
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'KJFK'), DATEADD('DAY', -7, CURRENT_DATE));
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'KJFK'), DATEADD('DAY', 1, CURRENT_DATE));
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'KJFK'), DATEADD('DAY', 7, CURRENT_DATE));
-- KJFK EBBR
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'KJFK' AND arrival = 'EBBR'), DATEADD('DAY', 3, CURRENT_DATE));

-- Existing bookings
INSERT INTO bookings (seats, scheduled_flight_id) VALUES (120, (SELECT id FROM scheduled_flights WHERE flight_id = (SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN') AND departure_date = DATEADD('DAY', 7, CURRENT_DATE)));
INSERT INTO bookings (seats, scheduled_flight_id) VALUES (118, (SELECT id FROM scheduled_flights WHERE flight_id = (SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN') AND departure_date = DATEADD('DAY', 8, CURRENT_DATE)));
INSERT INTO bookings (seats, scheduled_flight_id) VALUES (189, (SELECT id FROM scheduled_flights WHERE flight_id = (SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'KJFK') AND departure_date = DATEADD('DAY', 7, CURRENT_DATE)));