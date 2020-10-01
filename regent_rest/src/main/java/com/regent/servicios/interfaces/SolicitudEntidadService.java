package com.regent.servicios.interfaces;


import com.regent.dtos.ResolucionDTO;
import com.regent.dtos.SolicitudEntidadDTO;
import com.regent.negocio.Entidad;
import com.regent.negocio.SolicitudEntidad;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SolicitudEntidadService {
  String nuevaSolicitudEntidad(SolicitudEntidadDTO paramSolicitudEntidadDTO, String paramString) throws IOException;
  
  SolicitudEntidadDTO findSolicitudEntidadByCuit(Long paramLong, HttpServletRequest paramHttpServletRequest);
  
  String guardarEdicionSolicitud(SolicitudEntidadDTO paramSolicitudEntidadDTO, MultipartFile[] paramArrayOfMultipartFile, String paramString, HttpServletRequest paramHttpServletRequest);
  
  String enviarARevisionSolicitud(SolicitudEntidadDTO paramSolicitudEntidadDTO, MultipartFile[] paramArrayOfMultipartFile, String paramString, HttpServletRequest paramHttpServletRequest);
  
  Collection<SolicitudEntidadDTO> getSolicitudesForAprobacion();
  
  SolicitudEntidad findSolicitudEntidadByCodigoSolicitud(Integer paramInteger);
  
  String registrarCambioSolicitud(Integer paramInteger, String paramString1, String paramString2, String paramString3);
  
  Collection<SolicitudEntidadDTO> getSolicitudesForCaratulacion();
  
  String caratularSolicitud(Integer paramInteger, String paramString);
  
  Collection<SolicitudEntidadDTO> getSolicitudesForResolucion();
  
  void resolverSolicitud(ResolucionDTO paramResolucionDTO, Integer paramInteger1, Integer paramInteger2, String paramString1, Integer paramInteger3, String paramString2, String paramString3, HttpServletRequest paramHttpServletRequest) throws ParseException;
  
  void aprobarSolicitud(Entidad paramEntidad, String paramString);
  
  void generarPdfSolicitud(Long paramLong) throws IOException;
  
  boolean deletePdfSolicitud(Long paramLong) throws IOException;
  
  String borrarPdfAnterior(String paramString1, String paramString2, String paramString3) throws IOException;
  
  boolean deletePdf(String paramString1, String paramString2, String paramString3) throws IOException;
}
