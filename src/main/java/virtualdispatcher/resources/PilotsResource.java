package virtualdispatcher.resources;

import com.codahale.metrics.annotation.Timed;
import java.util.List;
import java.util.Optional;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import virtualdispatcher.api.Pilot;
import virtualdispatcher.core.scheduling.PilotQueue;
import virtualdispatcher.db.dao.PilotDAO;

@Singleton
@Path("/api/pilots")
@Produces(MediaType.APPLICATION_JSON)
public class PilotsResource implements Resource {

  // Dependencies
  private final PilotDAO pilotDAO;
  private final PilotQueue pilotQueue;

  @Inject
  PilotsResource(
      final PilotDAO pilotDAO,
      final PilotQueue pilotQueue) {

    this.pilotDAO = pilotDAO;
    this.pilotQueue = pilotQueue;
  }

  @GET
  @Timed
  public List<Pilot> getPilots() {
    return pilotDAO.list();
  }

  @GET
  @Timed
  @Path("/next")
  public Optional<Pilot> getNextPilot() {
    return pilotQueue.getNextPilot();
  }
}
