package ru.student.services;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import ru.student.db.DataConfiguration;

@Configuration
@ComponentScan
@Import(DataConfiguration.class)
public class ServicesConfiguration {
}
