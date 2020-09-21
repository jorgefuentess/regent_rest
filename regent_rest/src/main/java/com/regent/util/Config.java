package com.regent.util;


public class Config {
  public static String IP = "https://registro.modernizacion.gob.ar";
  
  public Config(String ip) {
    IP = ip;
  }
  
  public static Boolean getLocal() {
    if ("http://127.0.0.1:8080".equals(IP))
      return Boolean.TRUE; 
    return Boolean.FALSE;
  }
  
  public static String getIP() {
    return IP;
  }
}
