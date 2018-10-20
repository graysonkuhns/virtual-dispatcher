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
import virtualdispatcher.api.Availability;
import virtualdispatcher.db.dao.AvailabilityDAO;

/**
 * {@link Availability}s resource.
 *
 * @author Grayson Kuhns/Jerome Tujague
 */
@Singleton
@Path("/api/availability")
@Produces(MediaType.APPLICATION_JSON)
public class AvailabilityResource implements Resource {

  // Dependencies
  private final AvailabilityDAO availabilityDAO;

  @Inject
  AvailabilityResource(final AvailabilityDAO availabilityDAO) {
    this.availabilityDAO = availabilityDAO;
  }

  @GET
  @Timed
  public List<Availability> getAvailability() {
    return availabilityDAO.list();
  }
}
