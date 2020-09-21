package com.regent.util;

import com.regent.repositories.interfaces.ExceptionRepository;
import com.regent.util.interfaces.ExceptionUtil;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExceptionUtilImpl implements ExceptionUtil {
  @Autowired
  ExceptionRepository eRepo;
  
  public void registrarExcepcion(String error, String servicio, String usuario) {
    this.eRepo.registrarExcepcion(usuario, servicio, String.valueOf(new Timestamp(System.currentTimeMillis())), error);
  }
}
