package net.lelyak.utills;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nazar Lelyak.
 */
@Data
@ConfigurationProperties(prefix = "flight.data")
@Configuration("locationProperties")
public class FileLocation {
    private String file;
}
