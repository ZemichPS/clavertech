package by.zemich.userms.config;

import by.zemich.userms.service.converters.RegisterRequestToUserConverter;
import by.zemich.userms.service.converters.UserToResponseConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserToResponseConverter());
        registry.addConverter(new RegisterRequestToUserConverter());
    }

}
