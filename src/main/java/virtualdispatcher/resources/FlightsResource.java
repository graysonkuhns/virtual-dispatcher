package virtualdispatcher.resources;

import com.codahale.metrics.annotation.Timed;
import io.dropwizard.jersey.PATCH;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import virtualdispatcher.api.Flight;
import virtualdispatcher.core.request.UpdateFlightStatusRequest;
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

  @PATCH
  @Timed
  @Path("{id}")
  public Response updateFlight(@PathParam("id") String idStr, final UpdateFlightStatusRequest request) {
    int id = Integer.parseInt(idStr);

    if (request.getStarted() != null) {
      flightDAO.changeStartedStatus(id, request.getStarted());
    }

    if (request.getCompleted() != null) {
      flightDAO.changeCompletedStatus(id, request.getCompleted());
    }

    return Response
        .ok()
        .build();
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
