package virtualdispatcher.db;

import com.google.inject.AbstractModule;
import com.google.inject.PrivateModule;
import io.dropwizard.setup.Environment;
import virtualdispatcher.VirtualDispatcherConfiguration;
import virtualdispatcher.db.dao.AvailabilityDAO;
import virtualdispatcher.db.dao.FlightDAO;
import virtualdispatcher.db.mapper.FlightMapper;

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

    bind(AvailabilityDAO.class);
  }
}
