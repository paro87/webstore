package com.paro.interceptor;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
If you don't want to implement all the methods from the
HandlerInterceptor interface in your interceptor class, you could
consider extending your interceptor from
org.springframework.web.servlet.handler.HandlerIntercepto
rAdapter, which is a convenient class provided by Spring MVC as a
default implementation of all the methods from the
HandlerInterceptor interface.
 */
public class ProcessingTimeLogInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = Logger.getLogger(ProcessingTimeLogInterceptor.class);

    /*
    In the preHandle method, we just set the current time value in the
request object for later retrieval. Whenever a request comes to our web application, it first
comes through this preHandle method and sets the current time in the request object
before reaching the Controller. We are returning true from this method because we want
the execution chain to proceed with the next interceptor or the Controller itself. Otherwise,
DispatcherServlet assumes that this interceptor has already dealt with the response
itself. So if we return false from the preHandle method, the request won't proceed to the
Controller or the next interceptor.
     */
    /*
    preHandle: This method will get called just before the web request reaches the Controller for execution
postHandle: This method will get called just after the Controller method execution
afterCompletion: This method will get called after the completion of the entire
web request cycle
     */

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long startTime = System.currentTimeMillis();
        request.setAttribute("startTime", startTime);
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        String queryString = request.getQueryString() == null ? "" : "?" + request.getQueryString();
        String path = request.getRequestURL() + queryString;
        long startTime = (Long)request.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        LOGGER.info(String.format("%s millisecond taken to process the request %s.",(endTime - startTime), path));
    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception exceptionIfAny){
// NO operation.
    }
}