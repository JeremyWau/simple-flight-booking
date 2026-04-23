# Flight Booking

## Requirements

- Book a flight
- View a booking
- Cancel a booking

## Acceptance Criterias

- AC1: Users receive a confirmation if the flight is successfully booked
- AC2: Users receive an error if the flight is already fully booked

## Assumptions

- Concurrent access when creating a booking is handled.
- No edition of the booking is possible.
- Users can book multiple times and multiple seats on a same scheduled flight.
- Not all tests were covered due to time constraint, for example the tests to ensure correct ownership are not in scope.