package com.regent.servicios.implementaciones;


 import com.regent.dtos.TipoDescuentoTipoEntidadDTO;
 import com.regent.dtos.TipoEntidadDTO;
 import com.regent.negocio.TipoEntidad;
 import com.regent.repositories.interfaces.TipoDescuentoRepository;
 import com.regent.repositories.interfaces.TipoEntidadRepository;
 import com.regent.servicios.interfaces.TipoEntidadService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 @Service
 public class TipoEntidadServiceImpl
   implements TipoEntidadService
 {
   @Autowired
   TipoEntidadRepository teRepo;
   @Autowired
   TipoDescuentoRepository tdRepo;
   
   public Collection<TipoEntidadDTO> getAllTipoEntidad() {
     Collection<Object[]> tipoEntidad = this.teRepo.getAllTipoEntidad();
     Collection<TipoEntidadDTO> tipoEntidadDTO = new ArrayList<>();
     for (Object[] te : tipoEntidad) {
       TipoEntidadDTO teDTO = new TipoEntidadDTO(String.valueOf(te[0]), (String)te[1], (String)te[2], null);
       tipoEntidadDTO.add(teDTO);
     } 
     return tipoEntidadDTO;
   }

   public Collection<TipoEntidadDTO> getAllTipoEntidadActivos() {
     Collection<Object[]> tipoEntidad = this.teRepo.getAllTipoEntidadActivo();
     Collection<TipoEntidadDTO> tipoEntidadDTO = new ArrayList<>();
     for (Object[] te : tipoEntidad) {
       TipoEntidadDTO teDTO = new TipoEntidadDTO(String.valueOf(te[0]), (String)te[1], (String)te[2], null);
       tipoEntidadDTO.add(teDTO);
     } 
     return tipoEntidadDTO;
   }
 
   public void nuevoTipoEntidad(TipoEntidadDTO eDTO, String usuario) {
     String estado = "";
     
     if ("Activo".equals(eDTO.getEstado().trim()) || "A".equals(eDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(eDTO.getEstado().trim()) || "I".equals(eDTO.getEstado().trim())) {
       estado = "I";
     } 
     
     TipoEntidad te = new TipoEntidad();
     te.setEstado(!"".equals(estado) ? estado : "A");
     
     String nombre = eDTO.getTipoEntidad().trim();
     te.setNombreTipoEntidad(nombre);
     
     te.setUsuario(usuario);
     te.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     te = (TipoEntidad)this.teRepo.save(te);
 
     
     for (TipoDescuentoTipoEntidadDTO tdte : eDTO.getTipoDescuentoTipoEntidad()) {
       if ("true".equals(tdte.getEstado())) {
         
         String actualizado = String.valueOf(new Timestamp(System.currentTimeMillis()));
         this.tdRepo.insertTipoDescuentoTipoEntidadByCodTipoDescuentoAndCodTipoEntidad(te.getCodigoTipoEntidad(), Integer.valueOf(tdte.getCodigoTipoDescuento()), "A", usuario, actualizado);
       } 
     } 
   }
 
 
   
   public void updateTipoEntidad(TipoEntidadDTO eDTO, String usuario) {
     TipoEntidad te = teRepo.findOne(Integer.valueOf(eDTO.getCodigoTipoEntidad()));
     
     te.setEstado(eDTO.getEstado().trim());
     
     String nombre = eDTO.getTipoEntidad().trim();
     te.setNombreTipoEntidad(nombre);
     
     te.setUsuario(usuario);
     te.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.teRepo.save(te);
 
     
     for (TipoDescuentoTipoEntidadDTO tdte : eDTO.getTipoDescuentoTipoEntidad()) {
       Object[] o = this.tdRepo.getTipoDescuentoTipoEntidadByCodTipoDescuentoAndCodTipoEntidad(te.getCodigoTipoEntidad(), Integer.valueOf(tdte.getCodigoTipoDescuento()));
       if (o.length > 0) {
         
         String estadoAnterior = (String)o[0];
         String checked = tdte.getEstado();
         Boolean cambiaEstado = Boolean.valueOf(!((!"A".equals(estadoAnterior) || !"false".equals(checked)) && (
             !"I".equals(estadoAnterior) || !"true".equals(checked))));
         if (cambiaEstado.booleanValue()) {
           String estadoNuevo = "true".equals(checked) ? "A" : "I";
           String actualizado = String.valueOf(new Timestamp(System.currentTimeMillis()));
           this.tdRepo.updateTipoDescuentoTipoEntidadByCodTipoDescuentoAndCodTipoEntidad(te.getCodigoTipoEntidad(), Integer.valueOf(tdte.getCodigoTipoDescuento()), estadoNuevo, usuario, actualizado);
         }  continue;
       }  if ("true".equals(tdte.getEstado())) {
         
         String actualizado = String.valueOf(new Timestamp(System.currentTimeMillis()));
         this.tdRepo.insertTipoDescuentoTipoEntidadByCodTipoDescuentoAndCodTipoEntidad(te.getCodigoTipoEntidad(), Integer.valueOf(tdte.getCodigoTipoDescuento()), "A", usuario, actualizado);
       } 
     } 
   }
 }

