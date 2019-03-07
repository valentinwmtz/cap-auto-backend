package com.aston.capauto;

import com.aston.capauto.properties.FileStorageProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@EnableConfigurationProperties({
        FileStorageProperty.class
})
public class CapAutoApplication implements ApplicationRunner {
    private static final Logger LOGGER = LogManager.getLogger(CapAutoApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(CapAutoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.debug("Debbuging log");
        LOGGER.info("Log info");
        LOGGER.warn("Warning info");
        LOGGER.error("Error log");
        LOGGER.fatal("Fatal log");
    }
}
