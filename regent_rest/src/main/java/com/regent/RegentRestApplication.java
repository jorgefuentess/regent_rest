package com.regent;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import com.regent.servicios.interfaces.IntimacionService;
import com.regent.servicios.interfaces.PresentacionEntidadService;
import com.regent.util.interfaces.FileUtil;

//@SpringBootApplication(scanBasePackages = {"com.regent"})
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})

public class RegentRestApplication extends SpringBootServletInitializer {
  @Autowired
  PresentacionEntidadService peService;
  
  @Autowired
  IntimacionService iService;
  
  @Autowired
  FileUtil fileUtil;
  
  public static void main(String[] args) {
    SpringApplication.run(com.regent.RegentRestApplication.class, args);
  }
  
  @EventListener
  public void handleContextRefresh(ContextRefreshedEvent event) {
    this.peService.programarEnvioNotificaciones();
    this.iService.programarAvisosPorIntimacionVencida();
    this.fileUtil.limpiarZip();
    this.fileUtil.limpiarCertif();
  }
  
  protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
    return application.sources(new Class[] { com.regent.RegentRestApplication.class });
  }
}
