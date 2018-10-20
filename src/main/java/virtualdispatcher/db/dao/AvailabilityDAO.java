package virtualdispatcher.db.dao;

import com.google.inject.Inject;
import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMappers;
import virtualdispatcher.api.Availability;
import virtualdispatcher.db.mapper.AvailabilityMapper;

/**
 * {@link Availability}
 */

public class AvailabilityDAO {

    // Dependencies
    private final Jdbi jdbi;
    private final AvailabilityMapper availabilityMapper;

    /**
     * Constructor.
     *
     * @param jdbi The database connector.
     * @param availabilityMapper The {@link AvailabilityMapper}.
     */
    @Inject
    AvailabilityDAO(
            final Jdbi jdbi,
            final AvailabilityMapper availabilityMapper) {

        this.jdbi = jdbi;
        this.availabilityMapper = availabilityMapper;

        //Register the mapper if it has not been already
        if (!jdbi.getConfig().get(RowMappers.class).findFor(Availability.class).isPresent()) {
            jdbi.registerRowMapper(availabilityMapper);
        }
    }

    /**
     * Creates an {@link Availability}.
     *
     * @param pilotId The pilot ID.
     */
    public void create(final int pilotId) {
        jdbi.useHandle(handle -> handle
            .createUpdate("INSERT INTO availability (pilot_id) VALUES (:pilot_id)")
            .bind("pilot_id", pilotId)
            .execute());
    }

    /**
     * Deletes an availability.
     *
     * @param availability The {@link Availability}.
     */
    public void delete(final Availability availability) {
        delete(availability.getPilotId());
    }

    /**
     * Deletes an {@link Availability}.
     *
     * @param pilotId The pilot ID.
     */
    public void delete(final int pilotId) {
        jdbi.useHandle(handle -> handle
            .createUpdate("DELETE FROM availability WHERE pilot_id = :pilot_id")
            .bind("pilot_id", pilotId)
            .execute());
    }

    /**
     * List availabilities.
     *
     * @return The availabilities.
     */
    public List<Availability> list() {
        return jdbi.withHandle(handle -> handle
                .createQuery("SELECT * FROM availability")
                .mapTo(Availability.class)
                .list());
    }
}
