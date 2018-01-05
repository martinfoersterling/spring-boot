package mafoe;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * From the spring boot docs:
 * <p>
 * In addition to the usual Spring Framework events, such as ContextRefreshedEvent, a SpringApplication sends some
 * additional application events.
 * </p>
 */
@Component
public class DemoApplicationListener {

    private static final Logger LOG = LoggerFactory.getLogger(DemoApplicationListener.class);
    private final Environment env;

    public DemoApplicationListener(Environment env) {
        //don't even need to put @Autowired on this constructor because it's the only one
        this.env = env;
    }

    @EventListener
    public void doStuffWhenServerIsUp(ApplicationReadyEvent applicationReadyEvent) {

        LOG.info("DemoApplicationListener triggered with {}", applicationReadyEvent);
        LOG.info("Now the server is up and we could fill caches and other stuff");

        String localServerPort = env.getProperty("local.server.port");

        LOG.info("Try accessing the HelloController via http://localhost:{}/ to see a simple webmvc example",
                localServerPort);
        LOG.info("Try accessing the Books controller via http://localhost:{}/books to see a simple Freemarker example " +
                "displaying data from the database", localServerPort);
        LOG.info("Or start the JavaFX client via FxClient.java");
    }
}
