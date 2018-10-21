package virtualdispatcher.resources;

import com.codahale.metrics.annotation.Timed;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import virtualdispatcher.api.Availability;
import virtualdispatcher.core.request.CreateAvailabilityRequest;
import virtualdispatcher.core.request.DeleteAvailabilityRequest;
import virtualdispatcher.db.dao.AvailabilityDAO;

/**
 * {@link Availability}s resource.
 *
 * @author Grayson Kuhns/Jerome Tujague
 */
@Singleton
@Path("/availability")
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

  @POST
  @Timed
  @Consumes(MediaType.APPLICATION_JSON)
  public Response createAvailability(final CreateAvailabilityRequest request) {
    availabilityDAO.create(request.getPilotId());

    return Response
        .ok()
        .build();
  }

  @DELETE
  @Timed
  @Consumes(MediaType.APPLICATION_JSON)
  public Response deleteAvailability(final DeleteAvailabilityRequest request) {
    availabilityDAO.delete(request.getPilotId());

    return Response
        .ok()
        .build();
  }
}
