# Flight Search

## Requirements

- Search a flight with:
    - departure
    - destination
    - date
- List all flights available

## Acceptance Criterias

- AC1: Users can only fetch a flight if all departure, destination and date are provided
- AC2: Users see a message if the flight is full
- AC3: Users see a message if there are no flights matching
- AC4: Listing all the flights available does not show fully booked flights
- AC5: Listing all the flights available shows only future flights
- AC6: Users can't query flights in the past

## Assumptions

- Time is left out of the scenario, only the date is used.
- Only one way flights are handled due to time constraint.