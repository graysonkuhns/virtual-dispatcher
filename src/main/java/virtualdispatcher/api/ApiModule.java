package virtualdispatcher.api;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

public class ApiModule extends AbstractModule {

  /**
   * Configures the model.
   */
  @Override
  protected void configure() {
    // Flight factory
    install(new FactoryModuleBuilder()
      .implement(Flight.class, DefaultFlight.class)
      .build(FlightFactory.class));
  }
}
