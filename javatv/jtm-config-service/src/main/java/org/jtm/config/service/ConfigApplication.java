package org.jtm.config.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * Hello world!
 *
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(ConfigApplication.class, args);
    }
}
