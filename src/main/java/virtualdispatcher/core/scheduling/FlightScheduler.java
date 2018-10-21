package virtualdispatcher.core.scheduling;

import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import virtualdispatcher.api.Aircraft;
import virtualdispatcher.api.Flight;
import virtualdispatcher.api.FlightFactory;
import virtualdispatcher.api.Pilot;
import virtualdispatcher.api.Zone;
import virtualdispatcher.db.dao.AvailabilityDAO;
import virtualdispatcher.db.dao.FlightDAO;

@Singleton
public class FlightScheduler {

  // Dependencies
  private final AvailabilityDAO availabilityDAO;
  private final PilotQueue pilotQueue;
  private final AircraftLocator aircraftLocator;
  private final ZoneLocator zoneLocator;
  private final FlightFactory flightFactory;
  private final FlightDAO flightDAO;

  @Inject
  FlightScheduler(
      final AvailabilityDAO availabilityDAO,
      final PilotQueue pilotQueue,
      final AircraftLocator aircraftLocator,
      final ZoneLocator zoneLocator,
      final FlightFactory flightFactory,
      final FlightDAO flightDAO) {

    this.availabilityDAO = availabilityDAO;
    this.pilotQueue = pilotQueue;
    this.aircraftLocator = aircraftLocator;
    this.zoneLocator = zoneLocator;
    this.flightFactory = flightFactory;
    this.flightDAO = flightDAO;
  }

  public void scheduleFlights() {
    boolean cont = true;
    Optional<Pilot> pilot = pilotQueue.getNextPilot();

    while (pilot.isPresent() && cont) {
      // Schedule a flight for the pilot
      cont = scheduleFlightFor(pilot.get());

      // Look for another pilot
      if (cont) {
        pilot = pilotQueue.getNextPilot();
      }
    }
  }

  private boolean scheduleFlightFor(final Pilot pilot) {
    Optional<Aircraft> aircraft = aircraftLocator.getNextAvailableAircraft();
    if (!aircraft.isPresent()) {
      // No aircraft are available
      return false;
    }

    Optional<Zone> zone = zoneLocator.getAvailableZone();
    if (!zone.isPresent()) {
      // We can't fly anywhere
      return false;
    }

    // We can schedule the flight
    Flight flight = flightFactory.create(
        null,
        false,
        false,
        pilot.getId(),
        aircraft.get().getId(),
        zone.get().getId());
    flightDAO.create(flight);

    // Remove the pilot from the waiting queue
    availabilityDAO.delete(pilot);

    // A flight was scheduled
    return true;
  }
}
