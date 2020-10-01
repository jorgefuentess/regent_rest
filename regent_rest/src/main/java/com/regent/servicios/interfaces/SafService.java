package com.regent.servicios.interfaces;


import com.regent.dtos.CertificacionDTO;
import com.regent.dtos.DatosSafDTO;
import com.regent.dtos.OrganismoDTO;
import com.regent.dtos.SafDTO;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;

public interface SafService {
  Collection<SafDTO> getAllSafActivos();
  
  SafDTO getInfoSaf(String paramString);
  
  void nuevoSAF(SafDTO paramSafDTO, String paramString);
  
  void updateSAF(SafDTO paramSafDTO, String paramString);
  
  Collection<OrganismoDTO> getOrganismosFromSaf();
  
  Collection<OrganismoDTO> getOrganismosWithoutSaf();
  
  Collection<OrganismoDTO> getOrganismosWithoutUsuarioSaf();
  
  String[] sendCsvFile(MultipartFile paramMultipartFile, String paramString, HttpServletRequest paramHttpServletRequest) throws IOException;
  
  Collection<DatosSafDTO> getAllDatosSafByUsuario(String paramString) throws ParseException;
  
  Integer getPorcentajeProcesamiento(String paramString);
  
  void generarCertificacionHaberes(CertificacionDTO paramCertificacionDTO, String paramString, HttpServletRequest paramHttpServletRequest) throws IOException, ParseException;
  
  void updateInfoSaf(String paramString, SafDTO paramSafDTO);
}
