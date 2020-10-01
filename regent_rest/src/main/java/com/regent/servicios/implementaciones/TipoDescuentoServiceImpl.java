package com.regent.servicios.implementaciones;


 import com.regent.dtos.TipoDescuentoDTO;
 import com.regent.dtos.TipoDescuentoTipoEntidadDTO;
 import com.regent.negocio.TipoDescuento;
 import com.regent.repositories.interfaces.CodigoDescuentoRepository;
 import com.regent.repositories.interfaces.TipoDescuentoRepository;
 import com.regent.repositories.interfaces.TipoEntidadRepository;
 import com.regent.servicios.interfaces.TipoDescuentoService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class TipoDescuentoServiceImpl
   implements TipoDescuentoService
 {
   @Autowired
   TipoDescuentoRepository tdRepo;
   @Autowired
   TipoEntidadRepository teRepo;
   @Autowired
   CodigoDescuentoRepository cdRepo;
   
   public Collection<TipoDescuentoDTO> getAllTipoDescuento() {
     Collection<Object[]> tipoDescuento = this.tdRepo.getAllTipoDescuento();
     Collection<TipoDescuentoDTO> tipoDescuentoDTO = new ArrayList<>();
     for (Object[] td : tipoDescuento) {
       TipoDescuentoDTO tdDTO = new TipoDescuentoDTO(String.valueOf(td[0]), (String)td[1], (String)td[2]);
       tipoDescuentoDTO.add(tdDTO);
     } 
     return tipoDescuentoDTO;
   }
   
   public Collection<TipoDescuentoDTO> getAllTipoDescuentoVigente() {
     Collection<TipoDescuento> tipoDescuento = this.tdRepo.getAllTipoDescuentoVigente();
     Collection<TipoDescuentoDTO> tipoDescuentoDTO = new ArrayList<>();
     for (TipoDescuento td : tipoDescuento) {
       TipoDescuentoDTO tdDTO = new TipoDescuentoDTO();
       tdDTO.setCodigoTipoDescuento(td.getCodigoTipoDescuento().toString());
       tdDTO.setTipoDescuento(td.getNombreTipoDescuento());
       tipoDescuentoDTO.add(tdDTO);
     } 
     return tipoDescuentoDTO;
   }
 
   
   public void nuevoTipoDescuento(TipoDescuentoDTO dDTO, String usuario) {
     String estado = "";
     
     if ("Activo".equals(dDTO.getEstado().trim()) || "A".equals(dDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(dDTO.getEstado().trim()) || "I".equals(dDTO.getEstado().trim())) {
       estado = "I";
     } 
     
     TipoDescuento td = new TipoDescuento();
     td.setEstado(!"".equals(estado) ? estado : "A");
     
     String nombre = dDTO.getTipoDescuento().trim();
     td.setNombreTipoDescuento(nombre);
     
     td.setUsuario(usuario);
     td.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.tdRepo.save(td);
   }
 
 
   public void updateTipoDescuento(TipoDescuentoDTO dDTO, String usuario) {
     TipoDescuento td = tdRepo.findOne(Integer.valueOf(dDTO.getCodigoTipoDescuento()));
     
     td.setEstado(dDTO.getEstado().trim());
     
     String nombre = dDTO.getTipoDescuento().trim();
     td.setNombreTipoDescuento(nombre);
     
     td.setUsuario(usuario);
     td.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.tdRepo.save(td);
   }
 
 
 
 
 
 
 
   
   public Collection<TipoDescuentoTipoEntidadDTO> getAllTipoEntidadTipoDescuento() {
     Collection<Object[]> tipoDescuentoTipoEntidad = this.tdRepo.getAllTipoEntidadTipoDescuento();
     Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidadDTO = new ArrayList<>();
     Integer vuelta = Integer.valueOf(0);
     for (Object[] td : tipoDescuentoTipoEntidad) {
       vuelta = Integer.valueOf(vuelta.intValue() + 1);
       TipoDescuentoTipoEntidadDTO tdDTO = new TipoDescuentoTipoEntidadDTO(String.valueOf(td[0]), String.valueOf(td[1]), (String)td[2], "", "");
       tipoDescuentoTipoEntidadDTO.add(tdDTO);
     } 
     return tipoDescuentoTipoEntidadDTO;
   }
 
 
 
 
 
 
   
   public Collection<TipoDescuentoTipoEntidadDTO> getTipoEntidadTipoDescuentoByEntidad() {
     Collection<Object[]> tipoDescuentoTipoEntidad = this.tdRepo.getTipoEntidadTipoDescuentoByEntidad();
     Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidadDTO = new ArrayList<>();
     for (Object[] td : tipoDescuentoTipoEntidad) {
       TipoDescuentoTipoEntidadDTO tdDTO = new TipoDescuentoTipoEntidadDTO(String.valueOf(td[0]), String.valueOf(td[1]), (String)td[2], "", "");
       tipoDescuentoTipoEntidadDTO.add(tdDTO);
     } 
     return tipoDescuentoTipoEntidadDTO;
   }
 
 
 
 
 
 
   
   public Collection<TipoDescuentoTipoEntidadDTO> getTipoEntidadWithCheckedFlag() {
     Collection<Object[]> tipoDescuentoTipoEntidad = this.tdRepo.getTipoEntidadWithCheckedFlag();
     Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidadDTO = new ArrayList<>();
     for (Object[] td : tipoDescuentoTipoEntidad) {
       TipoDescuentoTipoEntidadDTO tdteDTO = new TipoDescuentoTipoEntidadDTO(String.valueOf(td[0]), String.valueOf(td[1]), (String)td[2], String.valueOf(td[3]), "");
       tipoDescuentoTipoEntidadDTO.add(tdteDTO);
     } 
     return tipoDescuentoTipoEntidadDTO;
   }
 }
