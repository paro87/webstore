package com.paro.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/*
    we can consider the DispatcherServletInitializer class as equivalent to web.xml as it extends from
    the AbstractAnnotationConfigDispatcherServletInitializer
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    //getRootConfigClasses: This specifies the configuration classes for the root application context
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { RootApplicationContextConfig.class
        };
    }

    //getServletConfigClasses: This specifies the configuration classes for the Dispatcher servlet application context
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {
                //will look for the controllers and views
                WebApplicationContextConfig.class };
    }
    //getServletMappings: This specifies the servlet mappings for DispatcherServlet
    /*
    when we return the string array containing only the “/” character, it indicates the
    DispatcherServlet configuration as the default servlet of the application. So every
    incoming request will be handled by DispatcherServlet. If
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/app/*"};
    }
    Then http://localhost:8080/webstore/app/welcome
    webstore - context name, application name
     */
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }


}

/* the context loaded using the getRootConfigClasses method
is the root context, which belongs to the whole application, while the one
initialized using the getServletConfigClasses method is actually
specific to that Dispatcher servlet. Technically, you can have multiple
Dispatcher servlets in an application and so multiple such contexts, each
specific for the respective Dispatcher servlet but with the same root
context.
*/