# flight-service-exercise

## Prequisites

 - Java 1.8

## Building and running

 - Execute the following from command line
 
```bash
mvnw clean install spring-boot:run
```

## API
 - After running the application, it can be accessed by:
 ```http://localhost:8080/api/flights```
 - Supported Query parameters:
   * airlineCode: if specified, filters the results by the given airline code.
   * departureDate: if specified, in ISO format (YYYY-MM-DD), filters the results by the given departure date (ignores time).
 - Sample request:
 ```http://localhost:8080/api/flights?airlineCode=AA&departureDate=2019-10-17```

## Notes/Limitations
 - For the given task, city api was not required/used.
 - Ideally we should handle all the error pages on /api, and the /error page, not done in the interest of time.
 - For this application, since there was no format specified for the date, I have chosen the standard ISO format: YYYY-MM-DD.
 - The expectation is the user will specify only date in departureDate, (so we trim out the time part of it) since the filter is going to be by departureDate.
 - For missing field of FlightDetails, we show N/A (it is currently only implemented on gate, should be extended to all getters).
 - If no query parameters (i.e. filters) are specified, we show all the results.
 - Ideally we would write unit tests for isSameDay method too.
 - The list of available flights are queried once and cached at the start of the application, if required, the cache can be deleted and refreshed frequently.