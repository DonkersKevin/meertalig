package be.vdab.meertalig.controllers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

@Configuration
class WebConfig implements WebMvcConfigurer {
    private static final int ZEVEN_DAGEN = 604_800;

    @Bean
    LocaleResolver localeResolver() {
        //Fixed
/*        return new FixedLocaleResolver(new Locale("en", "US"));*/

        //User selected or browser default
/*        return new SessionLocaleResolver();*/

        //Via cookie
        var resolver = new CookieLocaleResolver();
        resolver.setCookieMaxAge(ZEVEN_DAGEN);
        return resolver;
    }

    //Need to register Interceptor to make hyperlink lang changes work, check index.html
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LocaleChangeInterceptor());
    }
}
