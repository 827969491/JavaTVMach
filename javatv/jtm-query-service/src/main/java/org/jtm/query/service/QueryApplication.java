package org.jtm.query.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
@RestController
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class QueryApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(QueryApplication.class, args);
    }
    
    @Value("${jtm.server.port}")
	String foo;
    
	@RequestMapping("/query1")
	public String hi(){
		
		return foo;
	}
}
