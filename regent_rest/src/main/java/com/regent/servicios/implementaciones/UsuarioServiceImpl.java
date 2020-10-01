package com.regent.servicios.implementaciones;

 import com.regent.dtos.FuncionDTO;
 import com.regent.dtos.UsuarioDTO;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.Rol;
 import com.regent.negocio.Saf;
 import com.regent.negocio.Usuario;
 import com.regent.repositories.interfaces.EntidadRepository;
 import com.regent.repositories.interfaces.FuncionRepository;
 import com.regent.repositories.interfaces.RolRepository;
 import com.regent.repositories.interfaces.SafRepository;
 import com.regent.repositories.interfaces.UsuarioRepository;
 import com.regent.security.hash.PasswordGenerator;
 import com.regent.security.hash.SimpleMD5;
 import com.regent.servicios.interfaces.MailService;
 import com.regent.servicios.interfaces.UsuarioService;
 import java.sql.Timestamp;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.regex.Pattern;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

 @Service
public class UsuarioServiceImpl implements UsuarioService
 {
   @Autowired
   UsuarioRepository uRepo;
   @Autowired
   FuncionRepository fRepo;
   @Autowired
   RolRepository rRepo;
   @Autowired
   SafRepository sRepo;
   @Autowired
   EntidadRepository eRepo;
   @Autowired
   MailService mService;
   
   public Collection<UsuarioDTO> getUsuarios() {
     Collection<Object[]> usuarios = this.uRepo.getUsuarios();
     Collection<UsuarioDTO> usuariosDTO = new ArrayList<>();
     for (Object[] u : usuarios) {
       UsuarioDTO uDTO = new UsuarioDTO(String.valueOf(u[0]), (String)u[5], "", (String)u[8], (String)u[4], 
           (String)u[1], (String)u[2], (String)u[3], (String)u[7], "", "", "");
       
       if ("SAF".equals(u[4])) {
         Object[] org = this.sRepo.getCfuOrgAndOrganismoExtByCfuOrg(uDTO.getUsuario().toUpperCase());
         Object[] org2 = (Object[])org[0];
         uDTO.setOrgExt(org2[1].toString());
       } else if ("ENTIDAD".equals(u[4])) {
         Entidad e = this.eRepo.findByCuit(Long.valueOf(uDTO.getUsuario()));
         uDTO.setCodEntidad(e.getCodigoEntidad().toString());
       } 
       
       usuariosDTO.add(uDTO);
     } 
     return usuariosDTO;
   }
   
   public Collection<FuncionDTO> getFuncionesByRol(String rol) {
     Collection<Object[]> func = this.fRepo.getFuncionesByRol(rol);
     Collection<FuncionDTO> funcDTO = new ArrayList<>();
     for (Object[] f : func) {
       FuncionDTO fDTO = new FuncionDTO(String.valueOf(f[0]), (String)f[1], (String)f[2], (String)f[3], (String)f[4]);
       funcDTO.add(fDTO);
     } 
     return funcDTO;
   }
 
   
   public Usuario findUsuarioByNombreUsuario(String nombreUsuario) {
     return this.uRepo.findByNombreUsuario(nombreUsuario);
   }
 
   
   @Transactional
   public UsuarioDTO nuevoUsuario(UsuarioDTO uDTO, String usuario) {
     Rol rol = rRepo.findOne(Integer.valueOf(uDTO.getRol()));
     String correo = uDTO.getCorreo().trim();
     
     String nombreUsuario = "";
     if ("SAF".equals(rol.getNombreRol())) {
       Object[] org = this.sRepo.getCfuOrgAndOrganismoExtByNSaf(uDTO.getnSaf());
       Object[] org2 = (Object[])org[0];
       nombreUsuario = org2[0].toString().toLowerCase();
     } else if ("ENTIDAD".equals(rol.getNombreRol())) {
       Entidad e = eRepo.findOne(Integer.valueOf(uDTO.getCodEntidad()));
       nombreUsuario = e.getCuit().toString();
     } else {
       nombreUsuario = generarNombreUsuario(correo);
     } 
     
     String clave = PasswordGenerator.getPassword(6);
     String claveHash = SimpleMD5.hash(clave);
     
     String estado = "";
     if ("Activo".equals(uDTO.getEstado().trim()) || "A".equals(uDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(uDTO.getEstado().trim()) || "I".equals(uDTO.getEstado().trim())) {
       estado = "I";
     } 
     
     Usuario u = new Usuario();
     u.setDenominacion(uDTO.getDenominacion().trim());
     u.setCorreoElectronico(correo);
     String observaciones = uDTO.getObservaciones();
     observaciones = (observaciones == null || "".equals(observaciones)) ? "-" : observaciones.trim();
     u.setObservaciones(observaciones);
     u.setRol(rol);
     u.setNombreUsuario(nombreUsuario);
     u.setPassword(claveHash);
     u.setEstado(!"".equals(estado) ? estado : "A");
     u.setCambiaClave("SI");
     u.setUsuario(usuario);
     u.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.uRepo.save(u);
     
     uDTO.setUsuario(nombreUsuario);
     uDTO.setCambiaClave("SI");
     uDTO.setObservaciones(observaciones);
     
     if ("A".equals(u.getEstado())) {
       this.mService.sendNuevoUsuario(u, clave);
     }
     
     return uDTO;
   }
 
   
   public void inhabilitarUsuario(String nombreUsuario, String usuarioModificador) {
     Usuario u = this.uRepo.findByNombreUsuario(nombreUsuario);
     
     if (u != null) {
       u.setEstado("I");
       u.setUsuario(usuarioModificador);
       u.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.uRepo.save(u);
     } 
   }
 
 
 
 
 
 
   
   public void updateUsuario(UsuarioDTO uDTO, String usuario) {
     Boolean cambio = Boolean.FALSE;
     Usuario usu = uRepo.findOne(Integer.valueOf(uDTO.getCodigoUsuario()));
     String nombreUsuarioNoSafNiEntidad = "";
     Boolean cambiaMail = Boolean.FALSE;
     Boolean cambiaUsuario = Boolean.FALSE;
     
     if (!uDTO.getCorreo().equals(usu.getCorreoElectronico())) {
       String correo = uDTO.getCorreo().trim();
       usu.setCorreoElectronico(correo);
       nombreUsuarioNoSafNiEntidad = generarNombreUsuario(correo);
       cambiaMail = Boolean.TRUE;
       cambio = Boolean.TRUE;
     } 
     
     if (Integer.valueOf(uDTO.getRol()) != usu.getRol().getCodigoRol()) {
       Rol rol = rRepo.findOne(Integer.valueOf(uDTO.getRol()));
       usu.setRol(rol);
       
       if ("SAF".equals(rol.getNombreRol())) {
         Object[] org = this.sRepo.getCfuOrgAndOrganismoExtByNSaf(uDTO.getnSaf());
         Object[] org2 = (Object[])org[0];
         usu.setNombreUsuario(org2[0].toString().toLowerCase());
         cambiaUsuario = Boolean.TRUE;
       } else if ("ENTIDAD".equals(rol.getNombreRol())) {
         Entidad e = eRepo.findOne(Integer.valueOf(uDTO.getCodEntidad()));
         usu.setNombreUsuario(e.getCuit().toString());
         cambiaUsuario = Boolean.TRUE;
       } else if (!"SOLICITANTE".equals(rol.getNombreRol())) {
         
         if (cambiaMail.booleanValue()) {
           usu.setNombreUsuario(nombreUsuarioNoSafNiEntidad);
           cambiaUsuario = Boolean.TRUE;
         } else {
           usu.setNombreUsuario(generarNombreUsuario(usu.getCorreoElectronico()));
           cambiaUsuario = Boolean.TRUE;
         } 
       }  cambio = Boolean.TRUE;
     } else if ("SAF".equals(usu.getRol().getNombreRol())) {
       Object[] org = this.sRepo.getCfuOrgAndOrganismoExtByNSaf(uDTO.getnSaf());
       Object[] org2 = (Object[])org[0];
       if (!org2[0].toString().equals(usu.getNombreUsuario())) {
         usu.setNombreUsuario(org2[0].toString().toLowerCase());
         cambiaUsuario = Boolean.TRUE;
         cambio = Boolean.TRUE;
       } 
     } else if ("ENTIDAD".equals(usu.getRol().getNombreRol())) {
       Entidad e = eRepo.findOne(Integer.valueOf(uDTO.getCodEntidad()));
       if (!e.getCuit().toString().equals(usu.getNombreUsuario())) {
         usu.setNombreUsuario(e.getCuit().toString());
         cambiaUsuario = Boolean.TRUE;
         cambio = Boolean.TRUE;
       } 
     } else if (!"SOLICITANTE".equals(usu.getRol().getNombreRol())) {
       
       if (cambiaMail.booleanValue()) {
         usu.setNombreUsuario(nombreUsuarioNoSafNiEntidad);
         cambiaUsuario = Boolean.TRUE;
         cambio = Boolean.TRUE;
       } else {
         usu.setNombreUsuario(generarNombreUsuario(usu.getCorreoElectronico()));
       } 
     } 
     String estado = "";
     if ("Activo".equals(uDTO.getEstado().trim()) || "A".equals(uDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(uDTO.getEstado().trim()) || "I".equals(uDTO.getEstado().trim())) {
       estado = "I";
     } 
     if (!estado.equals(usu.getEstado())) {
       usu.setEstado(estado);
       cambio = Boolean.TRUE;
     } 
     
     if (!uDTO.getDenominacion().trim().equals(usu.getDenominacion())) {
       usu.setDenominacion(uDTO.getDenominacion().trim());
       cambio = Boolean.TRUE;
     } 
     
     String observaciones = uDTO.getObservaciones();
     observaciones = (observaciones == null || "".equals(observaciones)) ? "-" : observaciones.trim();
     if (!observaciones.equals(usu.getObservaciones())) {
       usu.setObservaciones(observaciones);
       cambio = Boolean.TRUE;
     } 
     
     if (cambio.booleanValue()) {
       usu.setUsuario(usuario);
       usu.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       this.uRepo.save(usu);
     } 
     
     if (cambiaMail.booleanValue() && cambiaUsuario.booleanValue()) {
       this.mService.sendCambioMailYUsuario(usu);
     } else if (cambiaMail.booleanValue()) {
       this.mService.sendCambioMail(usu);
     } else if (cambiaUsuario.booleanValue()) {
       this.mService.sendCambioUsuario(usu);
     } 
   }
   
   public String cambiarClave(UsuarioDTO uDTO, String usuario) {
     Usuario usu = this.uRepo.findByNombreUsuario(usuario);
     String nuevaPassword = SimpleMD5.hash(uDTO.getClave());
     
     Boolean valida = claveValida(uDTO.getClave());
     Boolean iguales = usu.getPassword().equals(nuevaPassword) ? Boolean.TRUE : Boolean.FALSE;
     
     if (valida.booleanValue() && !iguales.booleanValue()) {
       usu.setPassword(nuevaPassword);
       usu.setCambiaClave("NO");
       
       usu.setUsuario("sistema");
       usu.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.uRepo.save(usu);
       
       return "OK";
     }  if (!valida.booleanValue())
       return "INVALIDA"; 
     if (iguales.booleanValue()) {
       return "DISTINTAS";
     }
     return "OK";
   }
   
   public String ingresoIncorrecto(UsuarioDTO uDTO) {
     Usuario usu = this.uRepo.findByNombreUsuario(uDTO.getUsuario());
     if (usu != null && "A".equals(usu.getEstado())) {
       Integer intentos = this.uRepo.getAccesosIncorrectos(usu.getCodigoUsuario());
       
       if (intentos == null) {
         
         this.uRepo.nuevoAccesoIncorrecto(usu.getCodigoUsuario(), Integer.valueOf(1), String.valueOf(new Timestamp(System.currentTimeMillis())));
         return "OK";
       }  if (intentos.intValue() + 1 < 5) {
         
         this.uRepo.updateAccesoIncorrecto(usu.getCodigoUsuario(), Integer.valueOf(intentos.intValue() + 1), String.valueOf(new Timestamp(System.currentTimeMillis())));
         return "OK";
       }  if (intentos.intValue() + 1 >= 5) {
         
         String clave = PasswordGenerator.getPassword(6);
         String claveHash = SimpleMD5.hash(clave);
         usu.setPassword(claveHash);
         usu.setCambiaClave("SI");
         
         usu.setUsuario("sistema");
         usu.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         
         this.uRepo.updateAccesoIncorrecto(usu.getCodigoUsuario(), Integer.valueOf(0), String.valueOf(new Timestamp(System.currentTimeMillis())));
         
         this.mService.sendClaveNueva(usu, clave);
         
         return "CAMBIO";
       } 
     } 
     return "OK";
   }
 
   
   public void olvidoClave(UsuarioDTO uDTO) {
     Usuario usu = this.uRepo.findByNombreUsuario(uDTO.getUsuario());
     if (usu == null)
     {
       usu = this.uRepo.findByCorreoElectronico(uDTO.getUsuario());
     }
     if (usu != null && "A".equals(usu.getEstado())) {
       
       String clave = PasswordGenerator.getPassword(6);
       String claveHash = SimpleMD5.hash(clave);
       usu.setPassword(claveHash);
       usu.setCambiaClave("SI");
       
       usu.setUsuario("sistema");
       usu.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
       
       this.mService.sendClaveNuevaPorOlvido(usu, clave);
       
       this.uRepo.updateAccesoIncorrecto(usu.getCodigoUsuario(), Integer.valueOf(0), String.valueOf(new Timestamp(System.currentTimeMillis())));
     } 
   }
 
   
   private Boolean claveValida(String clave) {
     if ("".equals(clave) || clave.length() < 8 || 
       !Pattern.compile("^(?=(?:.*\\d){1})(?=(?:.*[a-z|A-Z]){1})\\S{8,}$").matcher(clave).find()) {
       return Boolean.FALSE;
     }
     return Boolean.TRUE;
   }
 
 
 
 
 
   
   private String generarNombreUsuario(String correo) {
     String nombreUsuario = correo.substring(0, correo.indexOf("@")).toLowerCase();
     
     String usuarioRetorno = nombreUsuario;
     Usuario u = this.uRepo.findByNombreUsuario(nombreUsuario);
     Integer i = Integer.valueOf(2);
     while (u != null && u.getNombreUsuario().equals(usuarioRetorno) && !u.getCorreoElectronico().equals(correo)) {
       usuarioRetorno = String.valueOf(nombreUsuario) + i.toString();
       i = Integer.valueOf(i.intValue() + 1);
       u = this.uRepo.findByNombreUsuario(usuarioRetorno);
     } 
     return usuarioRetorno;
   }
 
 
 
 
 
   
   public String[] enviarUsuariosNuevos(String usuario) {
     String[] retorno = new String[2];
 
     
     Collection<Entidad> entidades = this.eRepo.getAllEntidadSinUsuario();
     Integer usuariosEntidades = Integer.valueOf(0);
     for (Entidad e : entidades) {
       try {
         Usuario u = new Usuario();
         Rol rol = this.rRepo.findByNombreRol("ENTIDAD");
         u.setRol(rol);
         u.setCorreoElectronico(e.geteMail());
         
         String clave = PasswordGenerator.getPassword(6);
         String claveHash = SimpleMD5.hash(clave);
         u.setPassword(claveHash);
         
         u.setDenominacion(e.getSolicitante());
         u.setNombreUsuario(String.valueOf(e.getCuit()).trim());
         u.setObservaciones("");
         
         u.setEstado("A");
         u.setCambiaClave("SI");
         u.setUsuario(usuario);
         u.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         
         this.uRepo.save(u);
         usuariosEntidades = Integer.valueOf(usuariosEntidades.intValue() + 1);
         this.mService.sendNuevoUsuario(u, clave);
       }
       catch (Exception exception) {}
     } 
 
 
     
     Collection<Saf> saf = this.sRepo.getAllSafSinUsuario();
     Integer usuariosSaf = Integer.valueOf(0);
     for (Saf s : saf) {
       try {
         Usuario u = new Usuario();
         Rol rol = this.rRepo.findByNombreRol("SAF");
         u.setRol(rol);
         u.setCorreoElectronico(s.geteMail());
         
         String clave = PasswordGenerator.getPassword(6);
         String claveHash = SimpleMD5.hash(clave);
         u.setPassword(claveHash);
         
         u.setDenominacion(s.getResponsable());
         u.setNombreUsuario(s.getCfuOrganismo().toLowerCase());
         u.setObservaciones("");
         
         u.setEstado("A");
         u.setCambiaClave("SI");
         u.setUsuario(usuario);
         u.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
         
         this.uRepo.save(u);
         usuariosSaf = Integer.valueOf(usuariosSaf.intValue() + 1);
         this.mService.sendNuevoUsuario(u, clave);
       }
       catch (Exception exception) {}
     } 
 
     
     retorno[0] = usuariosEntidades.toString();
     retorno[1] = usuariosSaf.toString();
     
     return retorno;
   }
}
