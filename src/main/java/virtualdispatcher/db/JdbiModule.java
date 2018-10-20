package virtualdispatcher.db;

import com.google.inject.AbstractModule;
import io.dropwizard.jdbi3.JdbiFactory;
import io.dropwizard.setup.Environment;
import org.jdbi.v3.core.Jdbi;
import virtualdispatcher.VirtualDispatcherConfiguration;

public class JdbiModule extends AbstractModule {

  // Properties
  private final Jdbi jdbi;

  /**
   * Constructor.
   *
   * @param environment The application environment.
   * @param config The application configuration.
   */
  JdbiModule(
      final Environment environment,
      final VirtualDispatcherConfiguration config) {

    // Create the JDBI instance to use for database connectivity
    jdbi = new JdbiFactory().build(
        environment,
        config.getDataSourceFactory(),
        "mariadb");
  }

  /**
   * Configures the module.
   */
  @Override
  public void configure() {
    bind(Jdbi.class).toInstance(jdbi);
  }
}
