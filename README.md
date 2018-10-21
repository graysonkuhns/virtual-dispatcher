# Virtual Dispatcher

## Problem Statement

At the busy University of Central Missouri airport, a human dispatcher is responsible for maintaining the organized flow of flight
operations by checking in pilots, matching them up with available aircraft, sending them to flight practice zones (so that no more than two
aircraft are in the same zone), and maintaining a waitlist when there are more pilots than aircraft available. The dispatcher must adapt to
changing conditions, such as aircraft becoming unavailable due to maintenance. Additionally, they must answer numerous radio calls from
aircraft, answer the phone, and help with miscellaneous tasks around the airport. During peak flight times, the process often breaks down
due to task saturation for the dispatcher. This results in inefficient use of aircraft resources, long wait times for pilots, and general 
frustration for everyone.

## Our Solution

Virtual Dispatcher is an automated system that relieves much of the workload for the human dispatcher, so they no longer have to think
about checking in pilots, maintaining a wait list, or assigning aircraft and practice zones to pilots. There is a desktop computer kiosk
for pilot check in and check out. An automated system matches checked-in pilots with available aircraft and practice zones & displays
this information in the pilot waiting area. If necessary, pilots are put on a wait list in the order they checked in. If a pilot needs
to leave the airport before getting assigned to a flight, they check out and are removed from the wait list. Pilots use their cell
phones from the flight line to notify the system when their flight is about to takeoff, after they land, or if something is wrong with
the aircraft (needs maintenance). The dispatcher view shows aircraft status and the pilot waitlist. For assigned aircraft, the aircraft
status boxes show the pilot name, the assigned practice zone, and whether the aircraft is on the ground or in flight. The dispatcher can
also click a box to change the maintenance status of any unassigned aircraft. Virtual Dispatcher could potentially be expanded to
include integration with existing aircraft scheduling and maintenance software currently used by the University.

## Technical Prerequisites

### Production
* Linux
* Git
* Docker Community Edition v17.06.2 or later

### Local Development
* Git
* Java 8
* Maven
* MariaDB

## Usage

### Production

1) Clone the master branch using Git
``` bash
git clone https://github.com/graysonkuhns/virtual-dispatcher.git
```

2) Prepare MariaDB
``` bash
# Start MariaDB
cd ./database
./up.sh
cd ..

# Initialize the database
docker exec -it mariadb /opt/virtual-dispatcher/database/init.sh
```

3) Start Virtual Dispatcher
``` bash
./up.sh
```

### Local Development

``` bash
# Build the application
mvn clean install

# Run the application
java -jar target/virtual-dispatcher-0.1.0-SNAPSHOT.jar server config.yml

# Interact with the application
Open `http://localhost:8080` in your browser
```
