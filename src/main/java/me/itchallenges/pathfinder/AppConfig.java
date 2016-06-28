package me.itchallenges.pathfinder;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.net.UnknownHostException;
import java.util.ResourceBundle;

/**
 * Date: 06/08/2016 4:48 PM
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "me.itchallenges.pathfinder.*")
@PropertySource("classpath:/db.properties")
public class AppConfig implements InitializingBean {
    public static final Logger LOG = Logger.getLogger(AppConfig.class);
    @Autowired
    private Environment environment;
    private String dbHost;
    private String dbScheme;

    @Override
    public void afterPropertiesSet() throws Exception {
        dbHost = environment.getProperty("db.host");
        dbScheme = environment.getProperty("db.scheme");
        LOG.info(String.format("Database connection (host: %s, scheme:%s)", dbHost, dbScheme));
    }

    @Bean
    public MongoClient mongo() throws UnknownHostException {
        MongoClientOptions options = MongoClientOptions.builder().connectionsPerHost(200).build();
        return new MongoClient(dbHost, options);
    }

    @Bean
    public MongoOperations mongoOperations() throws UnknownHostException {
        return new MongoTemplate(mongo(), dbScheme);
    }

    @Bean
    public ResourceBundle messages() {
        return ResourceBundle.getBundle("messages");
    }

}
