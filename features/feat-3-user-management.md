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