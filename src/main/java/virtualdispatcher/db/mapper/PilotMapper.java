package virtualdispatcher.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import virtualdispatcher.api.Pilot;
import virtualdispatcher.api.PilotFactory;

/**
 * {@link Pilot} mapper.
 *
 * @author Grayson Kuhns
 */
@Singleton
public class PilotMapper implements RowMapper<Pilot> {

    // Constants
    private static final String KEY_ID = "id";
    private static final String KEY_FIRSTNAME = "f_name";
    private static final String KEY_LASTNAME = "l_name";

    // Dependencies
    private final PilotFactory pilotFactory;

    /**
     * Constructor.
     *
     * @param pilotFactory The {@link PilotFactory}.
     */
    @Inject
    PilotMapper(final PilotFactory pilotFactory) {
        this.pilotFactory = pilotFactory;
    }

    @Override
    public Pilot map(final ResultSet rs, final StatementContext ctx) throws SQLException {
        return pilotFactory.create(
                rs.getInt(KEY_ID),
                rs.getString(KEY_FIRSTNAME),
                rs.getString(KEY_LASTNAME));
    }
}
