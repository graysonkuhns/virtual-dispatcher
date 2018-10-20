package virtualdispatcher.db.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import virtualdispatcher.api.Pilot;

/**
 * {@link Pilot} mapper.
 */
public class PilotMapper implements RowMapper<Pilot> {



  @Override
  public Pilot map(ResultSet rs, StatementContext ctx) throws SQLException {
    return null;
  }
}
