package virtualdispatcher.resources;

import com.codahale.metrics.annotation.Timed;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import virtualdispatcher.api.Flight;
import virtualdispatcher.core.scheduling.FlightScheduler;
import virtualdispatcher.db.dao.FlightDAO;

/**
 * {@link Flight}s resource.
 *
 * @author Grayson Kuhns
 */
@Singleton
@Path("/api/flights")
@Produces(MediaType.APPLICATION_JSON)
public class FlightsResource implements Resource {

  // Dependencies
  private final FlightDAO flightDAO;
  private final FlightScheduler flightScheduler;

  @Inject
  FlightsResource(
      final FlightDAO flightDAO,
      final FlightScheduler flightScheduler) {

    this.flightDAO = flightDAO;
    this.flightScheduler = flightScheduler;
  }

  @GET
  @Timed
  public List<Flight> getFlights(
      @QueryParam("aircraftId") final Integer aircraftId,
      @QueryParam("completed") final Boolean completed,
      @QueryParam("started") final Boolean started) {

    return flightDAO
        .list(completed, started)
        .stream()
        .filter(flight -> aircraftId == null || flight.getAircraftId() == aircraftId)
        .collect(Collectors.toList());
  }

  @GET
  @Timed
  @Path("/schedule")
  public Response scheduleFlight() {
    flightScheduler.scheduleFlights();

    return Response
        .ok()
        .build();
  }
}
