package br.com.minhaloja.config;

import br.com.minhaloja.services.DBService;
import br.com.minhaloja.services.EmailService;
import br.com.minhaloja.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    DBService dbService;

    @Bean
    public boolean instanteateDatabase() throws ParseException {
        dbService.instantiateTesteDatabase();

        return true;
    }

    @Bean
    public EmailService emailService() {
        return new MockEmailService();
    }
}
