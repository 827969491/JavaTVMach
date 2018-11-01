package org.jtm.query.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Hello world!
 *
 */
@EnableEurekaClient
@SpringBootApplication
public class QueryApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(QueryApplication.class, args);
    }
}
