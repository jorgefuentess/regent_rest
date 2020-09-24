package com.regent.servicios.implementaciones;


 import com.regent.dtos.ParametroDTO;
 import com.regent.negocio.Parametro;
 import com.regent.repositories.interfaces.ParametroRepository;
 import com.regent.servicios.interfaces.ParametroService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 
 
 
 
 @Service
 public class ParametroServiceImpl
   implements ParametroService
 {
   @Autowired
   ParametroRepository pRepo;
   
   public Collection<ParametroDTO> getParametros() {
     Collection<Parametro> parametros = this.pRepo.getParametros();
     Collection<ParametroDTO> parametroDTO = new ArrayList<>();
     for (Parametro p : parametros) {
       ParametroDTO pDTO = new ParametroDTO();
       pDTO.setCodigoParametro(p.getCodigoParametro());
       pDTO.setNombreParametro(p.getNombreParametro());
       pDTO.setDescripcion(p.getDescripcion());
       pDTO.setEstado(p.getEstado());
       parametroDTO.add(pDTO);
     } 
     
     return parametroDTO;
   }
 
 
 
 
 
 
   
   public void nuevoParametro(ParametroDTO pDTO, String usuario) {
     String estado = "";
     if ("Activo".equals(pDTO.getEstado().trim()) || "A".equals(pDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(pDTO.getEstado().trim()) || "I".equals(pDTO.getEstado().trim())) {
       estado = "I";
     } 
     Parametro parametro = new Parametro();
     parametro.setEstado(!"".equals(estado) ? estado : "A");
     
     parametro.setNombreParametro(pDTO.getNombreParametro().trim().toUpperCase().replace(" ", "_"));
     parametro.setDescripcion(pDTO.getDescripcion().trim());
     parametro.setUsuario(usuario);
     parametro.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.pRepo.save(parametro);
   }
 
 
 
 
 
 
   
   public void updateParametro(ParametroDTO pDTO, String usuario) {
     Parametro parametro = (Parametro)this.pRepo.findOne(Integer.valueOf(pDTO.getCodigoParametro().intValue()));
     
     parametro.setEstado(pDTO.getEstado().trim());
     parametro.setDescripcion(pDTO.getDescripcion().trim());
     parametro.setUsuario(usuario);
     parametro.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.pRepo.save(parametro);
   }
 
 
 
 
 
 
   
   public Parametro findByNombreParametro(String nombreParametro) {
     return this.pRepo.findByNombreParametro(nombreParametro);
   }
 }

