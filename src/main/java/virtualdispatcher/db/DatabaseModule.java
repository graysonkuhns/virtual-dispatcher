package virtualdispatcher.db;

import com.google.inject.AbstractModule;
import io.dropwizard.setup.Environment;
import virtualdispatcher.VirtualDispatcherConfiguration;
import virtualdispatcher.db.dao.AircraftDAO;
import virtualdispatcher.db.dao.AvailabilityDAO;
import virtualdispatcher.db.dao.FlightDAO;
import virtualdispatcher.db.dao.PilotDAO;
import virtualdispatcher.db.mapper.FlightMapper;
import virtualdispatcher.db.dao.ZoneDAO;

/**
 * Database Guice module.
 *
 * @author Grayson Kuhns
 */
public class DatabaseModule extends AbstractModule {

  // Properties
  private final Environment environment;
  private final VirtualDispatcherConfiguration config;

  /**
   * Constructor.
   *
   * @param environment The application environment.
   * @param config The application configuration.
   */
  public DatabaseModule(
      final Environment environment,
      final VirtualDispatcherConfiguration config) {

    this.environment = environment;
    this.config = config;
  }

  /**
   * Configures the module.
   */
  @Override
  protected void configure() {
    // Jdbi
    install(new JdbiModule(environment, config));

    // Mappers
    bind(FlightMapper.class);


    // DAOs
    bind(FlightDAO.class);
    bind(PilotDAO.class);
    bind(AircraftDAO.class);
    bind(AvailabilityDAO.class);
    bind(ZoneDAO.class);
  }
}
