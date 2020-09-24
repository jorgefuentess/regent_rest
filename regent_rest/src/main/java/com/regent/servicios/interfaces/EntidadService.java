package com.regent.servicios.interfaces;


import com.regent.dtos.EntidadDTO;
import com.regent.dtos.EntidadInfoDTO;
import com.regent.dtos.EntidadLiteDTO;
import com.regent.dtos.SolicitudBajaDTO;
import com.regent.dtos.TipoPresentacionDTO;
import com.regent.negocio.Entidad;
import com.regent.negocio.SolicitudEntidad;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.dom4j.DocumentException;
import org.springframework.web.multipart.MultipartFile;

public interface EntidadService {
  Collection<EntidadLiteDTO> getEntidades();
  
  Collection<EntidadDTO> getAllEntidades();
  
  Collection<EntidadInfoDTO> getInfoEntidades(String paramString);
  
  EntidadDTO getEntidadByCuit(String paramString);
  
  void nuevaEntidad(EntidadDTO paramEntidadDTO, String paramString) throws ParseException;
  
  void updateEntidad(EntidadDTO paramEntidadDTO, String paramString) throws ParseException;
  
  Entidad nuevaEntidad(SolicitudEntidad paramSolicitudEntidad, Integer paramInteger1, String paramString1, Integer paramInteger2, String paramString2) throws ParseException;
  
  Collection<TipoPresentacionDTO> getArchivosForActualizacion(String paramString) throws ParseException;
  
  String enviarActualizacion(MultipartFile[] paramArrayOfMultipartFile, String paramString, HttpServletRequest paramHttpServletRequest);
  
  void cambiarEstadoEntidad(Entidad paramEntidad, String paramString1, String paramString2);
  
  void generarArchivoSolicitudBaja(Long paramLong, HttpServletRequest paramHttpServletRequest) throws DocumentException, IOException;
  
  void enviarSolicitudBaja(MultipartFile paramMultipartFile, String paramString, HttpServletRequest paramHttpServletRequest) throws IllegalStateException, IOException;
  
  Collection<SolicitudBajaDTO> getSolicitudesBajaEnTramite();
  
  Entidad findEntidadByCuit(String paramString);
}


/* Location:              /Volumes/DatosHD/Users/javiermoreno/workspace/WS-Jefatura/RegistroEntidades/War productivo/11_03_2020/regent_rest.war!/WEB-INF/classes/com/regent/servicios/interfaces/EntidadService.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
