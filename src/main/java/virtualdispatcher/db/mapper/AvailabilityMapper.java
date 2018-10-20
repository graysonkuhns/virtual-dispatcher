package virtualdispatcher.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import virtualdispatcher.api.Availability;
import virtualdispatcher.api.AvailabilityFactory;

/**
 * {@link Availability} mapper.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class AvailabilityMapper implements RowMapper<Availability> {

    // Constants
    private static final String KEY_CREATED = "created";
    private static final String KEY_PILOT_ID = "pilot_id";

    // Dependencies
    private final AvailabilityFactory availabilityFactory;

    /**
     * Constructor.
     *
     * @param availabilityFactory The {@link AvailabilityFactory}.
     */
    @Inject
    AvailabilityMapper(final AvailabilityFactory availabilityFactory) {
        this.availabilityFactory = availabilityFactory;
    }

    @Override
    public Availability map(final ResultSet rs, final StatementContext ctx) throws SQLException {
        return availabilityFactory.create(
            rs.getInt(KEY_PILOT_ID),
            rs.getTimestamp(KEY_CREATED).toInstant());
    }
}