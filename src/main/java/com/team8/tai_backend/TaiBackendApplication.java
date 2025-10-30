package com.team8.tai_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TaiBackendApplication {

  public static void main(String[] args) {
    SpringApplication.run(TaiBackendApplication.class, args);
  }

}
