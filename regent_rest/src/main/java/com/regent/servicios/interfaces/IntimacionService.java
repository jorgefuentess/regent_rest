package com.regent.servicios.interfaces;

import java.text.ParseException;

import com.regent.dtos.IntimacionDTO;

public interface IntimacionService {
  IntimacionDTO getIntimacion(String paramString);
  
  void nuevaIntimacion(IntimacionDTO paramIntimacionDTO, String paramString) throws ParseException;
  
  void updateIntimacion(IntimacionDTO paramIntimacionDTO, String paramString) throws ParseException;
  
  void cerrarIntimacion(IntimacionDTO paramIntimacionDTO, String paramString) throws ParseException;
  
  void programarAvisosPorIntimacionVencida();
}
