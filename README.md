Outline
============

I chose this challenge because it reflects a real-world problem that a developer may encounter. 
It involves data analysis and reporting which I think are always a part of a good software.

The program was coded in two parts. There is an ETL (Extract-Transform-Load) part where a given file needs to be parse
then process to load in a database. In this program, I used a file datastore to persist the data in a rich format.
The processor is implemented with a Chain of Responsibility pattern that helps the business logic layer be extensible 
for future criteria that may be identified.

Manager Layers are also visible in this code which makes the unit testing easy especially in the data retrieval part.
Data matching was implemented using Java Regular Expression to accommodate complex query.


Building the program
======================

1. Build the system using the standard maven command:

       	mvn clean install

Running the program
======================

1. Run the sample standalone program by issuing this command from the command line: 

        mvn exec:java