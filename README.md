# Ticket Service
This is very simple and quick implementation of a ticket service that allows to discover, hold, 
and reserve seats within a venue.


# Assumptions

1. Users are givens seats based on the availability.
2. Hold time for the seats is 30 seconds. If the user tries to reserve expired hold the 
notification that hold expired will be shown to the user.  
5. If user takes advantage of the hold and reserves seats before the hold expired seats statuses are changed from ONHOLD
 to RESERVED and those seats won't be available for the next user. 

# Building Project

1.	Clone the project
	
	git clone https://github.com/AnyaYudkovsky/assignment.git
	
	cd assignment
	
2.	To build
	
	./gradlew build  
	
3.	To run/execute
	
	./gradlew run
	
	Note: executions will wait (30 sec) in order to test hold expiration.
	
4.	To run tests

	./gradlew test  