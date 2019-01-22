package org.esteco.anniversary;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Hello world!
 *
 */
@Configuration
@Import(ApplicationConfig.class)
public class NotificationConfig
{

    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
    }
}
