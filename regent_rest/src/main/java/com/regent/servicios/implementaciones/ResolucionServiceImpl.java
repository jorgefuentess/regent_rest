package com.regent.servicios.implementaciones;


 import com.regent.dtos.ResolucionDTO;
 import com.regent.dtos.TipoDescuentoTipoEntidadDTO;
 import com.regent.negocio.CodigoDescuento;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.Resolucion;
 import com.regent.negocio.SolicitudEntidad;
 import com.regent.negocio.TipoDescuento;
 import com.regent.repositories.interfaces.CodigoDescuentoRepository;
 import com.regent.repositories.interfaces.EntidadRepository;
 import com.regent.repositories.interfaces.ResolucionRepository;
 import com.regent.repositories.interfaces.TipoDescuentoRepository;
 import com.regent.repositories.interfaces.TipoDescuentoSolicitudRepository;
 import com.regent.servicios.interfaces.ResolucionService;
 import com.regent.servicios.interfaces.SolicitudEntidadService;
 import java.sql.Timestamp;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Date;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class ResolucionServiceImpl
   implements ResolucionService
 {
   @Autowired
   ResolucionRepository rRepo;
   @Autowired
   EntidadRepository eRepo;
   @Autowired
   TipoDescuentoRepository tdRepo;
   @Autowired
   CodigoDescuentoRepository cdRepo;
   @Autowired
   TipoDescuentoSolicitudRepository tdsRepo;
   @Autowired
   SolicitudEntidadService seService;
   
   public Collection<ResolucionDTO> getResoluciones() {
     Collection<Resolucion> reso = this.rRepo.getResoluciones();
     Collection<ResolucionDTO> resoDTO = new ArrayList<>();
     for (Resolucion r : reso) {
       ResolucionDTO rDTO = new ResolucionDTO();
       
       rDTO.setCodigoResolucion(String.valueOf(r.getCodigoResolucion()));
       rDTO.setExpMmod(r.getExpMmod());
       rDTO.setExpJgm(r.getExpJgm());
       rDTO.setCodigo_entidad(String.valueOf(r.getEntidad().getCodigoEntidad()));
       rDTO.setEstadoEntidad(r.getEntidad().getEstadoEntidad().getNombreEstado());
       rDTO.setFechaVigencia((r.getFechaVigencia() != null) ? r.getFechaVigencia().toString() : "");
       rDTO.setLinkBoletin(r.getLinkBoletin());
       rDTO.setNroResolucion(r.getNroResolucion());
       rDTO.setTipoDescuentoTipoEntidad(getTipoDescuentoWithCodigoDescuento(r.getCodigoResolucion(), r.getEntidad().getCodigoEntidad()));
       
       resoDTO.add(rDTO);
     } 
     return resoDTO;
   }
 
 
 
 
   
   public void nuevaResolucion(ResolucionDTO rDTO, String usuario) throws ParseException {
     Resolucion r = new Resolucion();
     
     Entidad en = eRepo.findOne(Integer.valueOf(rDTO.getCodigo_entidad()));
     
     r.setEntidad(en);
     r.setNroResolucion(rDTO.getNroResolucion());
     r.setExpJgm(rDTO.getExpJgm());
     r.setExpMmod(rDTO.getExpMmod());
     r.setLinkBoletin(rDTO.getLinkBoletin());
     try {
       r.setFechaVigencia((new SimpleDateFormat("yyyy-MM-dd")).parse(rDTO.getFechaVigencia().trim()));
     } catch (Exception e) {
       r.setFechaVigencia(null);
     } 
     r.setUsuario(usuario);
     r.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     r = (Resolucion)this.rRepo.save(r);
 
     
     for (TipoDescuentoTipoEntidadDTO tdte : rDTO.getTipoDescuentoTipoEntidad()) {
       if ("true".equals(tdte.getEstado())) {
         
         CodigoDescuento cd = new CodigoDescuento();
         TipoDescuento td = tdRepo.findOne(Integer.valueOf(tdte.getCodigoTipoDescuento()));
         String codigoGenerado = generarCodigoDescuento(en.getTipoEntidad().getCodigoTipoEntidad(), td.getCodigoTipoDescuento());
         cd.setCodigoDescuento(Integer.valueOf(codigoGenerado));
         cd.setResolucion(r);
         cd.setTipoDescuento(td);
         cd.setEstado("A");
         cd.setUsuario(usuario);
         cd.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         
         this.cdRepo.save(cd);
       } 
     } 
   }
 
 
   
   public Resolucion nuevaResolucion(ResolucionDTO rDTO, SolicitudEntidad se, Entidad e, String usuario) throws ParseException {
     Resolucion r = new Resolucion();
     
     r.setCodigoResolucion(Integer.valueOf(this.rRepo.getMaxCodigoResolucion().intValue() + 1));
     r.setEntidad(e);
     r.setExpJgm(rDTO.getExpJgm());
     r.setExpMmod(rDTO.getExpMmod());
     r.setUsuario(usuario);
     r.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     r = (Resolucion)this.rRepo.save(r);
 
     
     Collection<Object[]> tipoDescuentoSolicitud = this.tdsRepo.getTipoDescuentoSolicitudCheckedByCodigoSolicitud(se.getCodigoSolicitud());
     for (Object[] o : tipoDescuentoSolicitud) {
       CodigoDescuento cd = new CodigoDescuento();
       TipoDescuento td = tdRepo.findOne(Integer.valueOf(String.valueOf(o[0])));
       String codigoGenerado = generarCodigoDescuento(e.getTipoEntidad().getCodigoTipoEntidad(), td.getCodigoTipoDescuento());
       cd.setCodigoDescuento(Integer.valueOf(codigoGenerado));
       cd.setResolucion(r);
       cd.setTipoDescuento(td);
       cd.setEstado("A");
       cd.setUsuario(usuario);
       cd.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.cdRepo.save(cd);
     } 
     
     return r;
   }
 
 
 
 
 
 
 
 
   
   public void updateResolucion(ResolucionDTO rDTO, String usuario) throws ParseException {
     Date fechaAnterior, fechaNueva;
     Boolean cambio = Boolean.FALSE;
     Resolucion r = rRepo.findOne(Integer.valueOf(rDTO.getCodigoResolucion()));
     
     String nroResoAnterior = (r.getNroResolucion() != null) ? r.getNroResolucion() : "";
     
     if (!rDTO.getNroResolucion().trim().equals(r.getNroResolucion())) {
       r.setNroResolucion(rDTO.getNroResolucion().trim());
       cambio = Boolean.TRUE;
     } 
     if (!compararString(rDTO.getExpJgm().trim(), r.getExpJgm()).booleanValue()) {
       r.setExpJgm(rDTO.getExpJgm().trim());
       cambio = Boolean.TRUE;
     } 
     if (!compararString(rDTO.getExpMmod().trim(), r.getExpMmod()).booleanValue()) {
       r.setExpMmod(rDTO.getExpMmod().trim());
       cambio = Boolean.TRUE;
     } 
     if (!rDTO.getLinkBoletin().trim().equals(r.getLinkBoletin())) {
       r.setLinkBoletin(rDTO.getLinkBoletin().trim());
       cambio = Boolean.TRUE;
     } 
 
 
     
     try {
       fechaNueva = (new SimpleDateFormat("yyyy-MM-dd")).parse(rDTO.getFechaVigencia().trim());
     } catch (Exception e) {
       fechaNueva = null;
     } 
     
     try {
       fechaAnterior = (new SimpleDateFormat("yyyy-MM-dd")).parse(r.getFechaVigencia().toString());
     } catch (Exception e) {
       fechaAnterior = null;
     } 
     
     if ((fechaAnterior != null && fechaNueva != null && !fechaAnterior.equals(fechaNueva)) || (
       fechaAnterior != null && fechaNueva == null) || (fechaNueva != null && fechaAnterior == null)) {
       r.setFechaVigencia(fechaNueva);
       cambio = Boolean.TRUE;
     } 
 
     
     if (cambio == Boolean.TRUE) {
       r.setUsuario(usuario);
       r.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       r = (Resolucion)this.rRepo.save(r);
     } 
     
     if ("Alta".equals(r.getEntidad().getEstadoEntidad().getNombreEstado()) && "NO".equals(r.getEntidad().getVigente()) && 
       "".equals(nroResoAnterior) && nroResoAnterior != r.getNroResolucion())
     {
       this.seService.aprobarSolicitud(r.getEntidad(), usuario);
     }
 
     
     for (TipoDescuentoTipoEntidadDTO tdte : rDTO.getTipoDescuentoTipoEntidad()) {
       CodigoDescuento cd = this.cdRepo.getCodigoDescuentoByResolucionAndTipoDescuento(r.getCodigoResolucion(), Integer.valueOf(tdte.getCodigoTipoDescuento()));
       if (cd != null && "true".equals(tdte.getEstado())) {
 
         
         if ("I".equals(cd.getEstado())) {
           
           cd.setEstado("A");
           cd.setUsuario(usuario);
           cd.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           this.cdRepo.save(cd);
         }  continue;
       } 
       if (cd != null && "false".equals(tdte.getEstado())) {
 
         
         if ("A".equals(cd.getEstado())) {
           
           cd.setEstado("I");
           cd.setUsuario(usuario);
           cd.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           this.cdRepo.save(cd);
         }  continue;
       } 
       if (cd == null && "true".equals(tdte.getEstado())) {
         
         CodigoDescuento cdN = new CodigoDescuento();
         cdN.setCodigoDescuento(Integer.valueOf(tdte.getCodigo()));
         cdN.setResolucion(r);
         cdN.setTipoDescuento(tdRepo.findOne(Integer.valueOf(tdte.getCodigoTipoDescuento())));
         cdN.setEstado("A");
         cdN.setUsuario(usuario);
         cdN.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         
         this.cdRepo.save(cdN);
       } 
     } 
   }
 
   
   private Collection<TipoDescuentoTipoEntidadDTO> getTipoDescuentoWithCodigoDescuento(Integer codigoResolucion, Integer codigoEntidad) {
     Collection<Object[]> tipoDescuentoTipoEntidad = this.cdRepo.getTipoDescuentoWithCodigoDescuento(codigoResolucion, codigoEntidad);
     Collection<TipoDescuentoTipoEntidadDTO> tipoDescuentoTipoEntidadDTO = new ArrayList<>();
     for (Object[] td : tipoDescuentoTipoEntidad) {
       TipoDescuentoTipoEntidadDTO tdDTO = new TipoDescuentoTipoEntidadDTO(String.valueOf(td[0]), String.valueOf(td[1]), td[2].toString(), td[3].toString(), String.valueOf(td[4]));
       tipoDescuentoTipoEntidadDTO.add(tdDTO);
     } 
     return tipoDescuentoTipoEntidadDTO;
   }
 
   
   private Boolean compararString(String str1, String str2) {
     return (!str1.equals(str2) && (!str1.equals("") || str2 != null) && (!str2.equals("") || str1 != null)) ? Boolean.valueOf(false) : Boolean.valueOf(true);
   }
   
   private String generarCodigoDescuento(Integer codigoTipoEntidad, Integer codigoTipoDescuento) {
     String codigo = "";
     
     codigo = String.valueOf(codigo) + codigoTipoEntidad;
     
     codigo = String.valueOf(codigo) + String.format("%04d", new Object[] { Integer.valueOf(this.eRepo.getMaxCUID().intValue()) });
     
     codigo = String.valueOf(codigo) + codigoTipoDescuento;
     
     return codigo;
   }
 }