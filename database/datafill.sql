# Virtual Dispacher Database Datafill
# Last Edit: 10/19/2018 7:54 PM

INSERT INTO pilot (f_name, l_name)
	VALUES
		("Denise", "Bruce"),
		("Mitchell", "Jurich"),
		("Jerome", "Tujague"),
		("Grayson", "Kuhns"),
		("Jon", "Brockhorst");
		
INSERT INTO aircraft (operational)
	VALUES
		(true),
		(true),
		(true),
		(false),
		(true),
		(false),
		(true),
		(true),
		(true);
	
INSERT INTO zones
	VALUES
		(),
		(),
		(),
		(),
		(),
		();
	
INSERT INTO availability
	VALUES
		(2, "2018-10-20 11:02:12"),
		(1, "2018-10-20 10:33:00"),
		(5, "2018-10-20 13:14:47");
	
INSERT INTO flights (started, completed, pilot_id, aircraft_id, zone_id)
	VALUES
		(true, true, 2, 4, 2),
		(true, true, 1, 6, 3),
		(true, true, 3, 2, 1),
		(false, false, 3, 1, 1),
		(false, false, 4, 2, 1);