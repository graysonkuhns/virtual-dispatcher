package virtualdispatcher.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import virtualdispatcher.api.Zone;
import virtualdispatcher.api.ZoneFactory;

/**
 * {@link Zone} mapper.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class ZoneMapper implements RowMapper<Zone> {

    // Constants
    private static final String KEY_ID = "id";

    // Dependencies
    private final ZoneFactory zoneFactory;

    /**
     * Constructor.
     *
     * @param zoneFactory The {@link ZoneFactory}.
     */
    @Inject
    ZoneMapper(final ZoneFactory zoneFactory) {
        this.zoneFactory = zoneFactory;
    }

    @Override
    public Zone map(final ResultSet rs, final StatementContext ctx) throws SQLException {
        return zoneFactory.create(
                rs.getInt(KEY_ID));
    }
}
