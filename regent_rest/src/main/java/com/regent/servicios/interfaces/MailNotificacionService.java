package com.regent.servicios.interfaces;

import com.regent.negocio.Usuario;

public interface MailNotificacionService {
  void sendPrimeraNotificacionMandato(String paramString);
  
  void sendSegundaNotificacionMandato(String paramString);
  
  void sendPrimeraNotificacionBalance(String paramString);
  
  void sendSegundaNotificacionBalance(String paramString);
  
  void sendNotificacionesDelDiaARegistro(String paramString1, String paramString2, Usuario paramUsuario);
}