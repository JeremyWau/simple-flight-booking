# User Management

## Requirements

- Register a new user
- Authenticate an existing user

## Acceptance Criterias

- AC1: Authenticating with correct credentials should authenticate successfully
- AC2: Authenticating with incorrect username should return an error
- AC3: Authenticating with incorrect password should return an error
- AC4: Registering with a non existing username should succeed
- AC5: Registering with an existing username should return an error

## Assumptions

- Test are simplified to testing the services directly due to the lack of time. Ideally, controllers should be also tested.
- Basic users with username (unique) and password.
- Using JWT with a TTL of 24 hours, in reality this value should be lowered and use a refresh mechanism to prevent having long living tokens.
- Bookings are simply deleted upon cancellation, for work environment they may need to remain in the system for audit/...