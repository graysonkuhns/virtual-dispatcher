package virtualdispatcher.db.dao;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;
import virtualdispatcher.api.Aircraft;
import virtualdispatcher.db.mapper.AircraftMapper;

@Singleton
public class AircraftDAO {

  // Dependencies
  private final Jdbi jdbi;

  @Inject
  AircraftDAO(
      final Jdbi jdbi,
      final AircraftMapper aircraftMapper) {

    this.jdbi = jdbi;

    // Register the mapper if it has not been already
    if (!jdbi.getConfig().get(RowMappers.class).findFor(Aircraft.class).isPresent()) {
      jdbi.registerRowMapper(aircraftMapper);
    }
  }

  public List<Aircraft> list() {
    return jdbi.withHandle(handle -> handle
      .createQuery("SELECT * FROM aircraft")
      .mapTo(Aircraft.class)
      .list());
  }
}
