# About the St. Louis metrolink data

All of the data is compiled from the GTFS (Google Transit Feed Specification) which is a nationwide format for aggregating transit data.

The data contained in this repository was obtained by the GTFS exchange.  The last update to this repo was September 2013

http://www.gtfs-data-exchange.com/agency/metro-st-louis/

# Instructions on how to use sqlite3


To view the database navigate to the directory of the database file and type on the command line

`sqlite3 metrolink.db`

To exit the database type

`.exit`

To view the schema of the database type

`.schema`

To view the schema of a single table type

`.schema {table name}`


# build the database tables

`.read metrolink.sql`


#import the data

`.separator ,`

`.import trips.txt trips`


#remember the order of the csv columns get matched with database!


#helpful commands


`.schema`             -shows all tables schemas

`.schema stops`       -shows the schema for the stops table

`.headers ON`         -shows headers of the tables

`.exit`               -returns to the command prompt

`.mode csv`           -sets output to csv

`.mode stdout`        -returns output to standard out (console)

`.output some.txt`    -the file that you would like the csv to occupy
