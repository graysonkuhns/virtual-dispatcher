package virtualdispatcher.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import virtualdispatcher.api.Aircraft;
import virtualdispatcher.api.AircraftFactory;

/**
 * {@link Aircraft} mapper.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class AircraftMapper implements RowMapper<Aircraft> {

    // Constants
    private static final String KEY_ID = "id";
    private static final String KEY_OPERATIONAL = "operational";

    // Dependencies
    private final AircraftFactory aircraftFactory;

    /**
     * Constructor.
     *
     * @param aircraftFactory The {@link AircraftFactory}.
     */
    @Inject
    AircraftMapper(final AircraftFactory aircraftFactory) {
        this.aircraftFactory = aircraftFactory;
    }

    @Override
    public Aircraft map(final ResultSet rs, final StatementContext ctx) throws SQLException {
        return aircraftFactory.create(
                rs.getInt(KEY_ID),
                rs.getBoolean(KEY_OPERATIONAL));
    }
}
