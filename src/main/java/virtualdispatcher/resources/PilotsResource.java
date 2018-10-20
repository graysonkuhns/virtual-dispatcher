package virtualdispatcher.resources;

import com.codahale.metrics.annotation.Timed;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import virtualdispatcher.api.Pilot;
import virtualdispatcher.db.dao.PilotDAO;

@Singleton
@Path("/api/pilots")
@Produces(MediaType.APPLICATION_JSON)
public class PilotsResource implements Resource {

  // Dependencies
  private final PilotDAO pilotDAO;

  @Inject
  PilotsResource(final PilotDAO pilotDAO) {
    this.pilotDAO = pilotDAO;
  }

  @GET
  @Timed
  public List<Pilot> getPilots() {
    return pilotDAO.list();
  }
}
