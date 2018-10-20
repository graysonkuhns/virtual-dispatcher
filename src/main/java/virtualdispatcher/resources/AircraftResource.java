package virtualdispatcher.resources;

import com.codahale.metrics.annotation.Timed;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.util.List;
import java.util.Optional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import virtualdispatcher.api.Aircraft;
import virtualdispatcher.core.scheduling.AircraftLocator;
import virtualdispatcher.db.dao.AircraftDAO;

@Singleton
@Path("/api/aircraft")
@Produces(MediaType.APPLICATION_JSON)
public class AircraftResource implements Resource {

  // Dependencies
  private final AircraftDAO aircraftDAO;
  private final AircraftLocator aircraftLocator;

  @Inject
  AircraftResource(
      final AircraftDAO aircraftDAO,
      final AircraftLocator aircraftLocator) {

    this.aircraftDAO = aircraftDAO;
    this.aircraftLocator = aircraftLocator;
  }

  @GET
  @Timed
  public List<Aircraft> getAircraft(@QueryParam("operational") final Boolean operational) {
    return aircraftDAO.list(operational);
  }

  @GET
  @Timed
  @Path("/available")
  public List<Aircraft> getAvailableAircraft() {
    return aircraftLocator.getAvailableAircraft();
  }

  @GET
  @Timed
  @Path("/next")
  public Optional<Aircraft> getNextAvailableAircraft() {
    return aircraftLocator.getNextAvailableAircraft();
  }
}
