package labweb.labweb_be.utils.p6spy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class P6SpyConfig {

    @Bean
    public P6SpyEventListener p6SpyEventListener() {
        return new P6SpyEventListener();
    }

    @Bean
    public P6SpyFormatter p6SpyFormatter() {
        return new P6SpyFormatter();
    }
}
