package rw.artmuse.gallery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfiguration implements WebMvcConfigurer {
@Override
    public void addViewControllers(ViewControllerRegistry registry)  {

    registry.addViewController("/").setViewName("index");
    registry.addViewController("/access-denied").setViewName("access-denied");
    registry.addViewController("/home").setViewName("home");
    registry.addViewController("/artist").setViewName("artist");
    registry.addViewController("/product").setViewName("product");
    registry.addViewController("/contact").setViewName("contact");
    registry.addViewController("/registration").setViewName("registration");
    registry.addViewController("/about").setViewName("about");

    }
}
