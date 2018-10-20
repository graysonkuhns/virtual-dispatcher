package virtualdispatcher;

import com.google.inject.Guice;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class VirtualDispatcherApplication extends Application<VirtualDispatcherConfiguration> {

    public static void main(final String[] args) throws Exception {
        new VirtualDispatcherApplication().run(args);
    }

    @Override
    public String getName() {
        return "VirtualDispatcher";
    }

    @Override
    public void initialize(final Bootstrap<VirtualDispatcherConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final VirtualDispatcherConfiguration configuration,
                    final Environment environment) {
        Guice
            .createInjector(new ApplicationModule(environment, configuration))
            .getInstance(ApplicationRunner.class)
            .run(environment, configuration);
    }

}
