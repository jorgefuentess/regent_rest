package com.regent.servicios.implementaciones;


 import com.regent.dtos.PresentacionPorTipoEntidadDTO;
 import com.regent.dtos.TipoDescuentoTipoEntidadDTO;
 import com.regent.dtos.TipoPresentacionDTO;
 import com.regent.negocio.TipoPresentacion;
 import com.regent.repositories.interfaces.PresentacionPorTipoEntidadRepository;
 import com.regent.repositories.interfaces.TipoPresentacionRepository;
 import com.regent.servicios.interfaces.TipoPresentacionService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 @Service
 public class TipoPresentacionServiceImpl
   implements TipoPresentacionService
 {
   @Autowired
   TipoPresentacionRepository tpRepo;
   @Autowired
   PresentacionPorTipoEntidadRepository pteRepo;
   
   public Collection<TipoPresentacionDTO> getAllTipoPresentacion() {
     Collection<Object[]> tipoPresentacion = this.tpRepo.getAllTipoPresentacion();
     Collection<TipoPresentacionDTO> tipoPresentacionDTO = new ArrayList<>();
     for (Object[] tp : tipoPresentacion) {
       TipoPresentacionDTO tpDTO = new TipoPresentacionDTO(String.valueOf(tp[0]), (String)tp[1], (String)tp[2], null, (String)tp[3], (String)tp[4]);
       tipoPresentacionDTO.add(tpDTO);
     } 
     return tipoPresentacionDTO;
   }
 
   
   public void nuevoTipoPresentacion(TipoPresentacionDTO pDTO, String usuario) {
     String vence = "";
     if ("Sí".equals(pDTO.getVence().trim()) || "SI".equals(pDTO.getVence().trim())) {
       vence = "SI";
     } else if ("No".equals(pDTO.getEstado().trim()) || "NO".equals(pDTO.getEstado().trim())) {
       vence = "NO";
     } 
     
     String estado = "";
     if ("Activo".equals(pDTO.getEstado().trim()) || "A".equals(pDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(pDTO.getEstado().trim()) || "I".equals(pDTO.getEstado().trim())) {
       estado = "I";
     } 
     
     TipoPresentacion tp = new TipoPresentacion();
     tp.setVence(!"".equals(vence) ? vence : "SI");
     tp.setEstado(!"".equals(estado) ? estado : "A");
     String nombre = pDTO.getTipoPresentacion().trim();
     tp.setNombreTipoPresentacion(nombre);
     
     if (pDTO.getNombreArchivo().trim().length() == 8) {
       tp.setNombreArchivo(pDTO.getNombreArchivo().trim());
     }
     tp.setUsuario(usuario);
     tp.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.tpRepo.save(tp);
   }
   
   public void updateTipoPresentacion(TipoPresentacionDTO pDTO, String usuario) {
     TipoPresentacion tp = tpRepo.findOne(Integer.valueOf(pDTO.getCodigoTipoPresentacion()));
     
     tp.setVence(pDTO.getVence().trim());
     tp.setEstado(pDTO.getEstado().trim());
     
     String nombre = pDTO.getTipoPresentacion().trim();
     tp.setNombreTipoPresentacion(nombre);
     
     tp.setUsuario(usuario);
     tp.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.tpRepo.save(tp);
   }
 
 
 
 
 
 
   
   public Collection<PresentacionPorTipoEntidadDTO> getTipoPresentacionByCodigoTipoEntidad(TipoDescuentoTipoEntidadDTO tDTO) {
     Collection<Object[]> tiposPresentacion = this.pteRepo.getPresentacionPorTipoEntidadByCodigoTipoEntidadAndCodigoTipoDescuento(tDTO.getCodigoTipoEntidad(), tDTO.getCodigoTipoDescuento());
     Collection<PresentacionPorTipoEntidadDTO> tipoPresentacionDTO = new ArrayList<>();
     for (Object[] tp : tiposPresentacion) {
       PresentacionPorTipoEntidadDTO tpDTO = new PresentacionPorTipoEntidadDTO(String.valueOf(tp[0]), (String)tp[1], "", "", "", String.valueOf(tp[2]));
       tipoPresentacionDTO.add(tpDTO);
     } 
     return tipoPresentacionDTO;
   }
 
 
 
 
 
 
 
   
   public void updateTipoPresentacionPorTipoEntidad(Collection<PresentacionPorTipoEntidadDTO> presentacionesDTO, String usuario) {
     for (PresentacionPorTipoEntidadDTO ppteDTO : presentacionesDTO) {
       Object[] o = this.pteRepo.getPresentacionPorTipoEntidadAndTipoDescuento(ppteDTO.getCodigoTipoPresentacion(), ppteDTO.getCodigoTipoEntidad(), ppteDTO.getCodigoTipoDescuento());
       
       if (o.length > 0) {
         
         Object[] o2 = (Object[])o[0];
         String estadoAnterior = (String)o2[2];
         String checked = ppteDTO.getEstado();
         Boolean cambiaEstado = Boolean.valueOf(!((!"A".equals(estadoAnterior) || !"false".equals(checked)) && (
             !"I".equals(estadoAnterior) || !"true".equals(checked))));
         
         if (cambiaEstado.booleanValue()) {
           String estadoNuevo = "true".equals(checked) ? "A" : "I";
           String actualizado = String.valueOf(new Timestamp(System.currentTimeMillis()));
           this.pteRepo.updatePresentacionPorTipoEntidadAndTipoDescuento(ppteDTO.getCodigoTipoPresentacion(), ppteDTO.getCodigoTipoEntidad(), ppteDTO.getCodigoTipoDescuento(), estadoNuevo, usuario, actualizado);
         }  continue;
       }  if ("true".equals(ppteDTO.getEstado())) {
         
         String actualizado = String.valueOf(new Timestamp(System.currentTimeMillis()));
         this.pteRepo.insertPresentacionPorTipoEntidadAndTipoDescuento(Integer.valueOf(ppteDTO.getCodigoTipoPresentacion()), Integer.valueOf(ppteDTO.getCodigoTipoEntidad()), 
             Integer.valueOf(ppteDTO.getCodigoTipoDescuento()), "A", usuario, actualizado);
       } 
     } 
   }
 }
