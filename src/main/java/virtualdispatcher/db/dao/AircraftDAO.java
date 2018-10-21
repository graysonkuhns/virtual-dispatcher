package virtualdispatcher.db.dao;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;
import virtualdispatcher.api.Aircraft;
import virtualdispatcher.api.Availability;
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
    return list(null);
  }

  /**
   * Updates a {@link Aircraft}.
   *
   * @param id The pilot ID.
   * @param operational The operational status
   */
  public void updateOperationalStatus(final int id, final boolean operational) {
    jdbi.useHandle(handle -> handle
        .createUpdate("UPDATE aircraft SET operational = :operational WHERE id = :id")
        .bind("operational", operational)
        .bind("id", id)
        .execute());
  }

  public List<Aircraft> list(final Boolean operational) {
    List<Aircraft> aircraft = jdbi.withHandle(handle -> handle
      .createQuery("SELECT * FROM aircraft")
      .mapTo(Aircraft.class)
      .list());

    return aircraft
        .stream()
        .filter(craft -> operational == null || craft.isOperational() == operational)
        .collect(Collectors.toList());
  }
}
