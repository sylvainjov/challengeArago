# Challenge: Arago

## Problem Statement

You are tasked with building an ad-serving and impression-tracking microservice.

These microservices should be capable of storing and retrieving data from a Redis database.

Time to resolve the challenge: **3 days**

## Ad-server Microservice

- Create a new ad.
- Retrieve an ad by its ID.
- Serve an ad: return Ad URL by id with impression tracking.

**Ad Structure**

An ad should have at least the following properties:

- ID (unique identifier for the ad) (**required**)
- Title (**required**)
- Description (*optional*)
- URL (**required**)

## Impression-tracker Microservice

Create a microservice for tracking ad impressions. This microservice should maintain a count of impressions for each ad and be updated when `ServeAd` is fired from the ad-serving microservice.

## Technical requirements

  1. Use `JAVA` as programming language
  2. Use `gRPC` communication between microservices
  3. Use `redis` for data storage

### Redis Integration

Use Redis as the database to store information.

## Bonus Points

### Expiration Time

Add an expiration time for ads. Ads should automatically be removed from the database after a certain period.

### Rate Limiting

Implement a rate-limiting mechanism to prevent abuse of the ad creation endpoint.

### Validation

Implement basic input validation for creating ads (e.g., ensure required fields are present).

### Multi-language

Implement the tracking service in `Go`.

## Submission

- Share the source code for both microservices through GitHub.
- Include a `README` file with instructions on how to run both microservices locally.
- Provide any necessary information about gRPC service definitions and how to make requests.
- Grant repository access to Luckystrike561 and fmaturel.
