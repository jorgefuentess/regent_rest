package com.regent.servicios.interfaces;

import com.regent.negocio.Entidad;
import com.regent.negocio.Intimacion;
import com.regent.negocio.SolicitudBaja;
import com.regent.negocio.SolicitudEntidad;
import com.regent.negocio.Usuario;
import java.util.Collection;

public interface MailService {
  void sendNuevoUsuario(Usuario paramUsuario, String paramString);
  
  void sendCambioMailYUsuario(Usuario paramUsuario);
  
  void sendCambioUsuario(Usuario paramUsuario);
  
  void sendCambioMail(Usuario paramUsuario);
  
  void sendClaveNueva(Usuario paramUsuario, String paramString);
  
  void sendClaveNuevaPorOlvido(Usuario paramUsuario, String paramString);
  
  void sendNuevoUsuarioSolicitante(Usuario paramUsuario, String paramString);
  
  void sendAvisoSolicitudARevision(Usuario paramUsuario);
  
  void sendNuevaSolicitudARolRegistro(SolicitudEntidad paramSolicitudEntidad, Usuario paramUsuario);
  
  void sendAvisoDevolucionSolicitud(String paramString1, String paramString2, String paramString3);
  
  void sendEntidadAprobada(Entidad paramEntidad);
  
  void sendNuevaSolicitudAMesaEntradas(Usuario paramUsuario, SolicitudEntidad paramSolicitudEntidad);
  
  void sendSolicitudCaratuladaARolRegistro(SolicitudEntidad paramSolicitudEntidad, Usuario paramUsuario);
  
  void sendRecuerdoDocumentacion(String paramString1, String paramString2);
  
  void sendAvisosIntimacionARegistro(Collection<Intimacion> paramCollection, Usuario paramUsuario);
  
  void sendAvisoRecepcionSolicitudBaja(String paramString1, String paramString2);
  
  void sendNuevaSolicitudBajaARegistro(SolicitudBaja paramSolicitudBaja, Usuario paramUsuario);
  
  void sendAvisoSolicitudBajaProcesada(String paramString1, String paramString2, String paramString3);
  
  void sendConfirmacionDeBajaPorSolicitud(SolicitudBaja paramSolicitudBaja, Usuario paramUsuario);
}