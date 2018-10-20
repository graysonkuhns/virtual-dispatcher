# VirtualDispatcher

The Problem.  
At the busy University of Central Missouri airport, a human dispatcher is responsible for maintaining the organized flow of flight
operations by checking in pilots, matching them up with available aircraft, sending them to flight practice zones (so that no more than two
aircraft are in the same zone), and maintaining a waitlist when there are more pilots than aircraft available. The dispatcher must adapt to
changing conditions, such as aircraft becoming unavailable due to maintenance. Additionally, they must answer numerous radio calls from
aircraft, answer the phone, and help with miscellaneous tasks around the airport. During peak flight times, the process often breaks down
due to task saturation for the dispatcher. This results in inefficient use of aircraft resources, long wait times for pilots, and general 
frustration for everyone.

The Solution.  
Virtual Dispatcher is an automated system that relieves much of the workload for the human dispatcher. There is a desktop computer kiosk
for pilot check in and check out. An automated system matches checked-in pilots with available aircraft and practice zones & displays this
information in the pilot waiting area. If necessary, pilots are put on a wait list in the order they checked in. If a pilot needs to leave
before getting assigned to a flight, they check out and are removed from the wait list. Pilots use their cell phones to notify the system
when their flight is about to takeoff, after they land, and if something is wrong with the aircraft (needs maintenance). The dispatcher
view shows aircraft status, pilot wait list, and allows them to change aircraft maintenance status. This solution could be expanded to
include integration with existing aircraft scheduling and maintenance software currently used by the University.


How to start the VirtualDispatcher application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/virtual-dispatcher-0.1.0-SNAPSHOT.jar server config.yml`
1. To check that your application is running enter url `http://localhost:8080`

Health Check
---

To see your applications health enter url `http://localhost:8081/healthcheck`
