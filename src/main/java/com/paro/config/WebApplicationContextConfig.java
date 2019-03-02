package com.paro.config;

import com.paro.interceptor.ProcessingTimeLogInterceptor;
import com.paro.interceptor.PromoCodeInterceptor;
import com.paro.validator.ProductValidator;
import com.paro.validator.UnitsInStockValidator;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.springframework.web.util.UrlPathHelper;

import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/*
WebApplicationContextConfig is nothing but a Java-based bean
configuration file for our web application context, where we can define the beans to be used
in our application.
 */
/*
@Configuration: This indicates that a class declares one or more @Bean methods
@EnableWebMvc: is needed to enable annotations such as @controller and @RequestMapping and so on
@ComponentScan: This specifies the base packages to scan for annotated components (beans)
 */

/*
in order to interpret the mappings defined in a request mapping, DispatcherServlet needs a HandlerMapping
(org.springframework.web.servlet.HandlerMapping) implementation.
Spring MVC provides many HandlerMapping implementations,
and the one we are using to detect and interpret mappings from the @RequestMapping
annotation is the RequestMappingHandlerMapping (org.springframework.web.servlet.mvc.method.annotation.
RequestMappingHandlerMapping) class. To start using RequestMappingHandlerMapping, we have to add the @EnableWebMvc annotation in our
web application context configuration file, so that Spring MVC can create and register a
bean for RequestMappingHandlerMapping in our web application context.
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.paro")
public class WebApplicationContextConfig extends WebMvcConfigurerAdapter {
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    /*
    The addResourceLocations method from ResourceHandlerRegistry defines the base
directory location of the static resources that you want to serve. In our case, we want to
serve all the images that are available under the src/main/webapp/resources/images/
directory; you may wonder then why we have only given /resources/images as the
location value instead of src/main/webapp/resources/images/. This is because, during
our application build and deployment time, Spring MVC will copy everything available
under the src/main/webapp/ directory to the root directory of our web application. So
during resource look up, Spring MVC will start looking up from the root directory.

The other method—addResourceHandler—just indicated the request path that needs to
be mapped to this resource directory. In our case, we assigned /img/** as the mapping
value. So if any web request comes with the request path /img, then it will be mapped to
the resources/images directory, and the /** symbol indicates to recursively look for any
resource files underneath the base resource directory.

That is why, if you noticed in step 3, we formed the URL as follows:
http://localhost:8080/webstore/img/P1234.png. So while serving this web request,
Spring MVC will consider /img/P1234.png as the request path, so it will try to map /img
to the resource base directory resources/images (/img=resources/images). From that directory, it will try to look
for the remaining path of the URL, which is /P1234.png.

So in our application, if any request comes with the request path prefix /img in its URL,
then Spring will look into the location directory that is configured in
ResourceHandlerRegistry and will return the requested file to the browser. It is good that we are able to serve product images without adding any extra request
mapping methods in our Controller.

if any request comes with the request path prefix /img, it will get mapped to the base resource
directory and any further remaining URL path will lead to the static file.
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/images/");
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/views/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource resource = new ResourceBundleMessageSource();
        resource.setBasename("messages");
        return resource;
    }

    @Bean
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("utf-8");
        return resolver;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ProcessingTimeLogInterceptor());
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("language");
        registry.addInterceptor(localeChangeInterceptor);
        registry.addInterceptor(promoCodeInterceptor())
                .addPathPatterns("/**/market/products/specialOffer");

    }
    /*
    If you notice, while adding promoCodeInterceptor to our InterceptorRegistry, we
can specify URL patterns using the addPathPatterns method. This way we can specify
the URL patterns to which the registered interceptor should apply. So our
promoCodeInterceptor will get executed only for a request that ends with
market/specialOffer.
     */


    /*
    We assigned the string language as the value for the paramName property in
LocaleChangeInterceptor. There is a reason for this because, if you notice, in step 2
when we created the locale choosing link in add products page (addProduct.jsp), we
used the same parameter name as the request parameter within the <a> tag:
<a href="?language=en" >English</a>|<a href="?language=nl" >Dutch</a>
This way we can give a hint to LocaleChangeInterceptor to choose the correct userpreferred locale. So whatever parameter name you plan to use in your URL, use the same
name as the value for the paramName property in LocaleChangeInterceptor.
     */
    @Bean
    public LocaleResolver localeResolver(){
        SessionLocaleResolver resolver = new SessionLocaleResolver();
        resolver.setDefaultLocale(new Locale("en"));
        return resolver;
    }

    @Bean
    public HandlerInterceptor promoCodeInterceptor() {
        PromoCodeInterceptor promoCodeInterceptor = new PromoCodeInterceptor();
        promoCodeInterceptor.setPromoCode("OFF3R");
        promoCodeInterceptor.setOfferRedirect("market/products");
        promoCodeInterceptor.setErrorRedirect("invalidPromoCode");
        return promoCodeInterceptor;
    }

    @Bean(name = "validator")
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }

    @Override
    public Validator getValidator() {
        return validator();
    }

    @Bean
    public ProductValidator productValidator () {
        Set<Validator> springValidators = new HashSet<>();
        springValidators.add(new UnitsInStockValidator());
        ProductValidator productValidator = new ProductValidator();
        productValidator.setSpringValidators(springValidators);
        return productValidator;
    }
}
