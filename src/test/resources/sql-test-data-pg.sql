-- Flights
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('EBBR', 'LFMN', 120);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('EBBR', 'LFML', 115);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('EBBR', 'LSGG', 95);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('LFMN', 'EBBR', 120);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('EBBR', 'KJFK', 190);
INSERT INTO flights (departure, arrival, maximum_capacity) VALUES ('KJFK', 'EBBR', 190);

-- Scheduled flights
-- EBBR LFMN
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), CURRENT_DATE - INTERVAL '1 day');
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), CURRENT_DATE);
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), CURRENT_DATE + INTERVAL '1 day');
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), CURRENT_DATE + INTERVAL '7 days');
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN'), CURRENT_DATE + INTERVAL '8 days');
-- EBBR LFML
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFML'), CURRENT_DATE + INTERVAL '2 days');
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFML'), CURRENT_DATE + INTERVAL '14 days');
-- EBBR KJFK
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'KJFK'), CURRENT_DATE - INTERVAL '7 days');
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'KJFK'), CURRENT_DATE + INTERVAL '1 day');
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'KJFK'), CURRENT_DATE + INTERVAL '7 days');
-- KJFK EBBR
INSERT INTO scheduled_flights (flight_id, departure_date) VALUES ((SELECT id FROM flights WHERE departure = 'KJFK' AND arrival = 'EBBR'), CURRENT_DATE + INTERVAL '3 days');

-- Existing bookings
INSERT INTO bookings (seats, scheduled_flight_id) VALUE (120, (SELECT id FROM scheduled_flights WHERE flight_id = (SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN') AND departure_date = CURRENT_DATE + INTERVAL '7 days'))
INSERT INTO bookings (seats, scheduled_flight_id) VALUE (118, (SELECT id FROM scheduled_flights WHERE flight_id = (SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'LFMN') AND departure_date = CURRENT_DATE + INTERVAL '8 days'))
INSERT INTO bookings (seats, scheduled_flight_id) VALUE (189, (SELECT id FROM scheduled_flights WHERE flight_id = (SELECT id FROM flights WHERE departure = 'EBBR' AND arrival = 'KJFK') AND departure_date = CURRENT_DATE + INTERVAL '7 days'))