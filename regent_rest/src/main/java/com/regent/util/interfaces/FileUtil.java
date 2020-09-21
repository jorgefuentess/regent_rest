package com.regent.util.interfaces;


import java.io.IOException;

public interface FileUtil {
  void limpiarZip();
  
  void limpiarCertif();
  
  void limpiarSolicitud(Long paramLong) throws IOException;
}
