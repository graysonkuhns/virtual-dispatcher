package virtualdispatcher;

import io.dropwizard.setup.Environment;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;
import virtualdispatcher.resources.Resource;

@Singleton
public class ApplicationRunner {

  // Dependencies
  private final Set<Resource> resources;

  @Inject
  ApplicationRunner(final Set<Resource> resources) {
    this.resources = resources;
  }

  /**
   * Run the application.
   *
   * @param environment The {@link Environment}.
   * @param configuration The {@link VirtualDispatcherConfiguration}.
   */
  public void run(
      final Environment environment,
      final VirtualDispatcherConfiguration configuration) {

    // Register resources
    resources.forEach(resource -> environment.jersey().register(resource));
  }
}
