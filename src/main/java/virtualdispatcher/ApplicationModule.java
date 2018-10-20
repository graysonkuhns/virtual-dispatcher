package virtualdispatcher;

import com.google.inject.AbstractModule;
import io.dropwizard.setup.Environment;
import virtualdispatcher.api.ApiModule;
import virtualdispatcher.db.DatabaseModule;
import virtualdispatcher.resources.ResourcesModule;

public class ApplicationModule extends AbstractModule {

  // Properties
  private final Environment environment;
  private final VirtualDispatcherConfiguration config;

  /**
   * Constructor.
   *
   * @param environment The application environment.
   * @param config The application configuration.
   */
  public ApplicationModule(
      final Environment environment,
      final VirtualDispatcherConfiguration config) {

    this.environment = environment;
    this.config = config;
  }

  @Override
  protected void configure() {
    install(new ApiModule());
    install(new DatabaseModule(environment, config));
    install(new ResourcesModule());
  }
}
