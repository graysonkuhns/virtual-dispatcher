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
import virtualdispatcher.api.Flight;
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

  @Inject
  FlightsResource(final FlightDAO flightDAO) {
    this.flightDAO = flightDAO;
  }

  @GET
  @Timed
  public List<Flight> getFlights(
      @QueryParam("aircraftId") final Integer aircraftId,
      @QueryParam("completed") final Boolean completed) {

    return flightDAO
        .list()
        .stream()
        .filter(flight -> aircraftId == null || flight.getAircraftId() == aircraftId)
        .filter(flight -> completed == null || flight.isCompleted() == completed)
        .collect(Collectors.toList());
  }
}
