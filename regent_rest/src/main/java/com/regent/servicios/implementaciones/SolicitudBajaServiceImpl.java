package com.regent.servicios.implementaciones;


 import com.regent.dtos.SolicitudBajaDTO;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.SolicitudBaja;
 import com.regent.negocio.Usuario;
 import com.regent.repositories.interfaces.SolicitudBajaRepository;
 import com.regent.repositories.interfaces.UsuarioRepository;
 import com.regent.servicios.interfaces.EntidadService;
 import com.regent.servicios.interfaces.MailService;
 import com.regent.servicios.interfaces.SolicitudBajaService;
 import com.regent.servicios.interfaces.UsuarioService;
 import java.sql.Timestamp;
 import java.util.Collection;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 
 
 @Service
 public class SolicitudBajaServiceImpl
   implements SolicitudBajaService
 {
   public static String BAJA_S = "Baja por Solicitud";
   
   @Autowired
   SolicitudBajaRepository sbRepo;
   
   @Autowired
   EntidadService eService;
   
   @Autowired
   UsuarioService uService;
   @Autowired
   MailService mService;
   @Autowired
   UsuarioRepository uRepo;
   
   public void modificarSolicitudBaja(Integer codigoSolicitud, String baja, String nroResolucion, String usuario) {
     SolicitudBaja sb = sbRepo.findOne(codigoSolicitud);
     
     if (sb != null && sb.getBaja() == null && ((
       "SI".equals(baja) && !"".equals(nroResolucion) && nroResolucion != null) || "NO".equals(baja))) {
       
       if ("SI".equals(baja)) {
         sb.setBaja("SI");
         sb.setNumeroExpediente(nroResolucion);
         
         this.eService.cambiarEstadoEntidad(sb.getEntidad(), BAJA_S, usuario);
         this.uService.inhabilitarUsuario(sb.getEntidad().getCuit().toString(), usuario);
       } else {
         sb.setBaja("NO");
         sb.setNumeroExpediente(null);
       } 
       
       sb.setUsuario(usuario);
       sb.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.sbRepo.save(sb);
 
       
       this.mService.sendAvisoSolicitudBajaProcesada(sb.getEntidad().geteMail(), sb.getEntidad().getSolicitante(), sb.getBaja());
 
       
       if ("SI".equals(sb.getBaja())) {
         Collection<Usuario> usuarios = this.uRepo.getUsuariosRolRegistro();
         for (Usuario u : usuarios) {
           this.mService.sendConfirmacionDeBajaPorSolicitud(sb, u);
         }
       } 
     } 
   }
 
   
   public SolicitudBajaDTO getSolicitudBajaVigente(Entidad e) {
     SolicitudBaja sb = this.sbRepo.findSolicitudBajaVigente(e.getCodigoEntidad());
     
     SolicitudBajaDTO sbDTO = new SolicitudBajaDTO();
     if (sb != null) {
       sbDTO.setCodigoSolicitud(sb.getCodigoSolicitud().toString());
       sbDTO.setFechaSolicitud(sb.getFechaSolicitud());
     } 
     
     return sbDTO;
   }
 }

