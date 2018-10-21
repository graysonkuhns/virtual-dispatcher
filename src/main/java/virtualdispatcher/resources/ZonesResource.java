package virtualdispatcher.resources;

import com.codahale.metrics.annotation.Timed;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import virtualdispatcher.api.Zone;
import virtualdispatcher.db.dao.ZoneDAO;

@Singleton
@Path("/zones")
@Produces(MediaType.APPLICATION_JSON)
public class ZonesResource implements Resource {

  // Dependencies
  private final ZoneDAO zoneDAO;

  @Inject
  ZonesResource(final ZoneDAO zoneDAO) {
    this.zoneDAO = zoneDAO;
  }

  @GET
  @Timed
  public List<Zone> getZone() {
    return zoneDAO.list();
  }
}
