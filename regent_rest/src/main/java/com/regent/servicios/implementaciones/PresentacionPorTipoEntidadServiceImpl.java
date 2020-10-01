package com.regent.servicios.implementaciones;


 import com.regent.dtos.PresentacionPorTipoEntidadDTO;
 import com.regent.negocio.PresentacionPorTipoEntidad;
 import com.regent.repositories.interfaces.PresentacionPorTipoEntidadRepository;
 import com.regent.servicios.interfaces.PresentacionPorTipoEntidadService;
 import java.util.ArrayList;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 
 
 
 @Service
 public class PresentacionPorTipoEntidadServiceImpl
   implements PresentacionPorTipoEntidadService
 {
   @Autowired
   PresentacionPorTipoEntidadRepository pRepo;
   
   public Collection<PresentacionPorTipoEntidadDTO> getPresentaciones() {
     Collection<PresentacionPorTipoEntidad> ppte = this.pRepo.getPresentaciones();
     Collection<PresentacionPorTipoEntidadDTO> ppteDTO = new ArrayList<>();
     
     for (PresentacionPorTipoEntidad p : ppte) {
       PresentacionPorTipoEntidadDTO pDTO = new PresentacionPorTipoEntidadDTO();
       pDTO.setCodigoTipoDescuento(p.getPresentacionPorTipoEntidadPk().getTipoDescuento().getCodigoTipoDescuento().toString());
       pDTO.setCodigoTipoEntidad(p.getPresentacionPorTipoEntidadPk().getTipoEntidad().getCodigoTipoEntidad().toString());
       pDTO.setCodigoTipoPresentacion(p.getPresentacionPorTipoEntidadPk().getTipoPresentacion().getCodigoTipoPresentacion().toString());
       pDTO.setNombreArchivo(p.getPresentacionPorTipoEntidadPk().getTipoPresentacion().getNombreArchivo());
       pDTO.setTipoPresentacion(p.getPresentacionPorTipoEntidadPk().getTipoPresentacion().getNombreTipoPresentacion());
       ppteDTO.add(pDTO);
     } 
     
     return ppteDTO;
   }
 }