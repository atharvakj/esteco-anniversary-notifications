package org.esteco.anniversary;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(EmployeeConfig.class)
public class NotificationConfig {

}
