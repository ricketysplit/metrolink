
drop table if exists trips;
drop table if exists routes;
drop table if exists stops;
drop table if exists stop_times;
drop view  if exists metrolink_stops;

create table trips (
	block_id char(40),
	route_id char(40),
	direction_id char(2),
	trip_headsign char(20),
	shape_id char(20),
	service_id char(20),
	trip_id char(20) PRIMARY KEY,
	FOREIGN KEY(route_id) references routes(route_id)
);

create table routes (
	route_long_name char(200),
	route_id char(40) PRIMARY KEY,
	route_type char(40),
	agency_id char(40),
	route_color char(40),
	route_short_name char(40)
);

create table stops (
	stop_lat Decimal(9,6),
	zone_id char(30),
	stop_lon Decimal(9,6),
	stop_id char(30) PRIMARY KEY,
	stop_desc char(30),
	stop_name char(30),
	location_type char(30)
);

create table stop_times ( 
	trip_id char(40),
	arrival_time char(40), 
	departure_time char(40), 
	stop_id char(40), 
	stop_sequence char(40), 
	stop_headsign char(40), 
	pickup_type char(40), 
	drop_off_type char(40), 
	shape_dist_traveled char(40),
	FOREIGN KEY(stop_id) references stops(stop_id),
	FOREIGN KEY(trip_id) references trips(trip_id)
);

create view metrolink_stops as 
	select 	t.trip_headsign, 
		s.stop_name,
                r.route_color,
                r.route_type, 
		st.stop_sequence, 
		arrival_time, 
		departure_time 
	from routes r 
	join trips t on t.route_id = r.route_id 
	join stop_times st on st.trip_id = t.trip_id 
	join stops s on s.stop_id = st.stop_id 
	where route_type = 2;
