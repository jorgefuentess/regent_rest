package com.regent.servicios.implementaciones;

import com.regent.dtos.ConsultaSQLDTO;
 import com.regent.negocio.ConsultaSQL;
 import com.regent.repositories.interfaces.ConsultaSQLRepository;
 import com.regent.servicios.interfaces.ConsultaSQLService;
 import com.regent.util.JdbcConnection;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.beans.factory.annotation.Value;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 @Service
 public class ConsultaSQLServiceImpl
   implements ConsultaSQLService
 {
   @Autowired
   ConsultaSQLRepository cRepo;
   @Value("${spring.datasource.url}")
   private String urlDB;
   @Value("${spring.datasource.username}")
   private String userDB;
   @Value("${spring.datasource.password}")
   private String passwordDB;
   
   public Collection<ConsultaSQLDTO> getConsultasLite() {
     Collection<ConsultaSQL> consultas = this.cRepo.getConsultas();
     Collection<ConsultaSQLDTO> consultasDTO = new ArrayList<>();
     for (ConsultaSQL c : consultas) {
       ConsultaSQLDTO cDTO = new ConsultaSQLDTO();
       cDTO.setCodigoConsulta(c.getCodigoConsulta());
       cDTO.setNombre(c.getNombre());
       cDTO.setEstado(c.getEstado());
       
       consultasDTO.add(cDTO);
     } 
     return consultasDTO;
   }
 
   
   public void nuevaConsulta(ConsultaSQLDTO cDTO, String usuario) {
     String estado = "";
     if ("Activo".equals(cDTO.getEstado().trim()) || "A".equals(cDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(cDTO.getEstado().trim()) || "I".equals(cDTO.getEstado().trim())) {
       estado = "I";
     } 
     ConsultaSQL consulta = new ConsultaSQL();
     consulta.setEstado(!"".equals(estado) ? estado : "A");
     
     consulta.setNombre(cDTO.getNombre().trim());
     consulta.setConsulta(cDTO.getConsulta().trim().toUpperCase().replace("DELETE", "").replace("UPDATE", "").replace("INSERT", "").replace("CREATE", "").replace("DROP", "").replace("DATABASE", "").replace("TRUNCATE", ""));
     consulta.setObservaciones(cDTO.getObservaciones().trim());
     
     consulta.setUsuario(usuario);
     consulta.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.cRepo.save(consulta);
   }
 
   
   public void updateConsulta(ConsultaSQLDTO cDTO, String usuario) {
     ConsultaSQL consulta = cRepo.findById(Integer.valueOf(cDTO.getCodigoConsulta().intValue())).orElse(new ConsultaSQL());
     
     if (consulta != null) {
       consulta.setEstado("I".equals(cDTO.getEstado().trim()) ? "I" : "A");
       
       consulta.setNombre(cDTO.getNombre().trim());
       consulta.setConsulta(cDTO.getConsulta().trim().toUpperCase().replace("DELETE", "").replace("UPDATE", "").replace("INSERT", "").replace("CREATE", "").replace("DROP", "").replace("DATABASE", "").replace("TRUNCATE", ""));
       consulta.setObservaciones(cDTO.getObservaciones().trim());
       
       consulta.setUsuario(usuario);
       consulta.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.cRepo.save(consulta);
     } 
   }
 
   
   public ConsultaSQLDTO getConsultaById(Integer codigoConsulta) {
     ConsultaSQL c = cRepo.findById(codigoConsulta).orElse(new ConsultaSQL());
     
     if (c != null) {
       ConsultaSQLDTO cDTO = new ConsultaSQLDTO();
       cDTO.setCodigoConsulta(c.getCodigoConsulta());
       cDTO.setNombre(c.getNombre());
       cDTO.setConsulta(c.getConsulta());
       cDTO.setObservaciones(c.getObservaciones());
       cDTO.setEstado(c.getEstado());
       
       return cDTO;
     } 
     return null;
   }
 
 
   
   public Collection<ConsultaSQLDTO> getConsultasActivas() {
     Collection<ConsultaSQL> consultas = this.cRepo.getConsultasActivas();
     Collection<ConsultaSQLDTO> consultasDTO = new ArrayList<>();
     for (ConsultaSQL c : consultas) {
       ConsultaSQLDTO cDTO = new ConsultaSQLDTO();
       cDTO.setCodigoConsulta(c.getCodigoConsulta());
       cDTO.setNombre(c.getNombre());
       cDTO.setObservaciones(c.getObservaciones());
       
       Boolean periodoDesde = Boolean.FALSE;
       Boolean periodoHasta = Boolean.FALSE;
       
       if (c.getConsulta().contains(":periodo_desde") || c.getConsulta().contains(":PERIODO_DESDE")) {
         periodoDesde = Boolean.TRUE;
         cDTO.setPeriodoDesde(periodoDesde);
       } 
       
       if (c.getConsulta().contains(":periodo_hasta") || c.getConsulta().contains(":PERIODO_HASTA")) {
         periodoHasta = Boolean.TRUE;
         cDTO.setPeriodoHasta(periodoHasta);
       } 
       
       if (!periodoDesde.booleanValue() && !periodoHasta.booleanValue() && (
         c.getConsulta().contains(":periodo") || c.getConsulta().contains(":PERIODO"))) {
         cDTO.setPeriodo(Boolean.TRUE);
       }
       
       consultasDTO.add(cDTO);
     } 
     return consultasDTO;
   }
 
   
   public Collection<Object[]> ejecutarConsulta(ConsultaSQLDTO cDTO, String usuario) {
     ConsultaSQL c = cRepo.findById(cDTO.getCodigoConsulta()).orElse(new ConsultaSQL());
     
     Boolean periodoDesde = Boolean.FALSE;
     Boolean periodoHasta = Boolean.FALSE;
 
 
 
 
     
     String consulta = c.getConsulta().toUpperCase();
     Integer tamanio = Integer.valueOf(consulta.length());
     if (";".equals(consulta.substring(tamanio.intValue() - 1, tamanio.intValue()))) {
       consulta = consulta.substring(0, tamanio.intValue() - 1);
     }
     
     if (consulta.contains(":PERIODO_DESDE")) {
       Integer periodoDesdeI; periodoDesde = Boolean.TRUE;
       if (cDTO.getPeriodoDesdeI() != null && String.valueOf(cDTO.getPeriodoDesdeI()).length() == 6) {
         periodoDesdeI = cDTO.getPeriodoDesdeI();
       } else {
         periodoDesdeI = Integer.valueOf(999998);
       } 
       consulta = consulta.replace(":PERIODO_DESDE", periodoDesdeI.toString());
     } 
     
     if (consulta.contains(":PERIODO_HASTA")) {
       Integer periodoHastaI; periodoHasta = Boolean.TRUE;
       if (cDTO.getPeriodoHastaI() != null && String.valueOf(cDTO.getPeriodoHastaI()).length() == 6) {
         periodoHastaI = cDTO.getPeriodoHastaI();
       } else {
         periodoHastaI = Integer.valueOf(999999);
       } 
       consulta = consulta.replace(":PERIODO_HASTA", periodoHastaI.toString());
     } 
     
     if (!periodoDesde.booleanValue() && !periodoHasta.booleanValue() && consulta.contains(":PERIODO")) {
       Integer periodoI; if (cDTO.getPeriodoI() != null && String.valueOf(cDTO.getPeriodoI()).length() == 6) {
         periodoI = cDTO.getPeriodoI();
       } else {
         periodoI = Integer.valueOf(999999);
       } 
       consulta = consulta.replace(":PERIODO", periodoI.toString());
     } 
 
     
     Collection<Object[]> salida = JdbcConnection.ejecutarConsulta(consulta, this.urlDB, this.userDB, this.passwordDB);
     
     return salida;
   }
 }