package com.regent.servicios.implementaciones;

 
 import com.regent.negocio.Entidad;
 import com.regent.negocio.Intimacion;
 import com.regent.negocio.SolicitudBaja;
 import com.regent.negocio.SolicitudEntidad;
 import com.regent.negocio.Usuario;
 import com.regent.servicios.interfaces.MailService;
 import com.regent.util.Config;
 import java.text.SimpleDateFormat;
 import java.util.Collection;
 import javax.mail.MessagingException;
 import javax.mail.internet.MimeMessage;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.mail.javamail.JavaMailSenderImpl;
 import org.springframework.mail.javamail.MimeMessageHelper;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class MailServiceImpl
   implements MailService
 {
   public static String SOLICITANTE = "SOLICITANTE";
 
   
   public static String URL_REGISTRO = String.valueOf(Config.getIP()) + "/registro/";
 
 
 
 
   
   @Autowired
   private JavaMailSenderImpl mailSender;
 
 
 
 
 
   
   public void setMailSender(JavaMailSenderImpl mailSender) {
     this.mailSender = mailSender;
   }
 
 
 
 
 
 
   
   public void sendNuevoUsuario(Usuario usuario, String clave) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Le damos la bienvenida al Registro de Entidades.<br>");
       cuerpoMail.append("Podrá acceder al sistema con los siguientes datos de acceso:<br>");
       cuerpoMail.append("<br>\t<b>Usuario:</b> ");
       cuerpoMail.append(usuario.getNombreUsuario());
       cuerpoMail.append("<br>\t<b>Contraseña:</b> ");
       cuerpoMail.append(clave);
       if (!SOLICITANTE.equals(usuario.getRol().getNombreRol())) {
         cuerpoMail.append("<br>\t<b>Rol:</b> ");
         cuerpoMail.append(usuario.getRol().getNombreRol());
       } 
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Cuando acceda por primera vez al sistema, se le solicitará que cambie la contraseña.");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: ¡Bienvenido!");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
   
   public void sendCambioMailYUsuario(Usuario usuario) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Detectamos que un usuario fue modificado y quedó asociado a su casilla de correo electrónico.<br>");
       cuerpoMail.append("A continuación le enviamos los datos actualizados del usuario:<br>");
       cuerpoMail.append("<br>\t<b>Usuario:</b> ");
       cuerpoMail.append(usuario.getNombreUsuario());
       cuerpoMail.append("<br>\t<b>Rol:</b> ");
       cuerpoMail.append(usuario.getRol().getNombreRol());
       cuerpoMail.append("<br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Modificación de datos");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
   
   public void sendCambioUsuario(Usuario usuario) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Detectamos que su nombre usuario fue modificado.<br>");
       cuerpoMail.append("A continuación, le enviamos los datos actualizados del usuario:<br>");
       cuerpoMail.append("<br>\t<b>Usuario:</b> ");
       cuerpoMail.append(usuario.getNombreUsuario());
       cuerpoMail.append("<br>\t<b>Rol:</b> ");
       cuerpoMail.append(usuario.getRol().getNombreRol());
       cuerpoMail.append("<br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Modificación de nombre de usuario");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
   
   public void sendCambioMail(Usuario usuario) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Detectamos que un usuario fue modificado y quedó asociado a su casilla de correo electrónico.<br>");
       cuerpoMail.append("A continuación le enviamos los datos de su usuario:<br>");
       cuerpoMail.append("<br>\t<b>Usuario:</b> ");
       cuerpoMail.append(usuario.getNombreUsuario());
       cuerpoMail.append("<br>\t<b>Rol:</b> ");
       cuerpoMail.append(usuario.getRol().getNombreRol());
       cuerpoMail.append("<br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Modificación de correo electrónico");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
   
   public void sendClaveNueva(Usuario usuario, String clave) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Hemos detectado reiterados intentos de acceso incorrectos al sistema con su usuario, ");
       cuerpoMail.append("razón por la cual le hemos generado una nueva contraseña.");
       cuerpoMail.append("Podrá acceder al sistema con los siguientes datos de acceso:<br>");
       cuerpoMail.append("<br>\t<b>Usuario:</b> ");
       cuerpoMail.append(usuario.getNombreUsuario());
       cuerpoMail.append("<br>\t<b>Contraseña:</b> ");
       cuerpoMail.append(clave);
       if (!SOLICITANTE.equals(usuario.getRol().getNombreRol())) {
         cuerpoMail.append("<br>\t<b>Rol:</b> ");
         cuerpoMail.append(usuario.getRol().getNombreRol());
       } 
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br>");
       cuerpoMail.append("Cuando acceda por primera vez al sistema, se le solicitará que cambie la contraseña.");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Se cambió tu contraseña");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
   
   public void sendClaveNuevaPorOlvido(Usuario usuario, String clave) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Hemos recibido una solicitud de olvido de contraseña, ");
       cuerpoMail.append("razón por la cual le hemos generado una nueva.");
       cuerpoMail.append("Podrá acceder al sistema con los siguientes datos de acceso:<br>");
       cuerpoMail.append("<br>\t<b>Usuario:</b> ");
       cuerpoMail.append(usuario.getNombreUsuario());
       cuerpoMail.append("<br>\t<b>Contraseña:</b> ");
       cuerpoMail.append(clave);
       if (!SOLICITANTE.equals(usuario.getRol().getNombreRol())) {
         cuerpoMail.append("<br>\t<b>Rol:</b> ");
         cuerpoMail.append(usuario.getRol().getNombreRol());
       } 
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br>");
       cuerpoMail.append("Cuando acceda nuevamente al sistema, se le solicitará que cambie la contraseña.");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Olvido de contraseña");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
   
   public void sendNuevoUsuarioSolicitante(Usuario usuario, String clave) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Su Solicitud de alta ha sido recibida.<br>");
       cuerpoMail.append("Deberá ingresar al sistema para completar su solicitud, con los siguientes datos de acceso:<br>");
       cuerpoMail.append("<br>\t<b>Usuario:</b> ");
       cuerpoMail.append(usuario.getNombreUsuario());
       cuerpoMail.append("<br>\t<b>Contraseña:</b> ");
       cuerpoMail.append(clave);
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Cuando acceda por primera vez al sistema, se le solicitará que cambie la contraseña.");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: ¡Bienvenido!");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
   
   public void sendAvisoSolicitudARevision(Usuario usuario) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Su Solicitud de alta de Entidad ha sido enviada a revisión.<br>");
       cuerpoMail.append("Deberá presentar los originales de la documentación cargada, con sus correspondientes certificados.<br><br>");
       cuerpoMail.append("Debe llevarlos a Av. Diagonal Roque Saenz Peña 511, Piso 7° Oficina 706 (CABA) de Lunes a Viernes de 9 a 16 h.<br>");
       cuerpoMail.append("Ante cualquier consulta no dude en comunicarse al 5985-5700 int. 5733 o por correo electrónico a registroentidades@modernizacion.gob.ar<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Solicitud enviada a Revisión");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
 
   
   public void sendAvisoDevolucionSolicitud(String nombre, String mail, String observaciones) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + nombre + "</b>:<br><br>");
       cuerpoMail.append("Su Solicitud de alta de Entidad le ha sido devuelta para modificación");
       if (!"".equals(observaciones)) {
         cuerpoMail.append(" con las siguientes observaciones:<br>");
         cuerpoMail.append("<i>" + observaciones + "</i><br><br>");
       } else {
         cuerpoMail.append(".<br>");
       } 
       cuerpoMail.append("Una vez realizadas las modificaciones solicitadas, podrá enviarla nuevamente a revisión.");
       cuerpoMail.append("Ante cualquier consulta no dude en comunicarse al 5985-5700 int. 5733 o por correo electrónico a registroentidades@modernizacion.gob.ar<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(mail);
       helper.setSubject("Registro de Entidades: Devolución de Solicitud");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
   
   public void sendNuevaSolicitudARolRegistro(SolicitudEntidad se, Usuario usuario) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Se ha recibido para revisión una nueva solicitud, de la siguiente entidad: <br>");
       cuerpoMail.append("Tipo: " + se.getTipoEntidad().getNombreTipoEntidad() + "<br>");
       cuerpoMail.append("Denominación: " + se.getDenominacion() + "<br>");
       cuerpoMail.append("CUIT: " + se.getCuit() + "<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Nueva Solicitud de Entidad");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
 
 
 
 
   
   public void sendEntidadAprobada(Entidad e) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + e.getSolicitante() + "</b>:<br><br>");
       cuerpoMail.append("Su Solicitud de Alta de la Entidad \"" + e.getDenominacion() + "\" ha sido <strong>aprobada</strong>.<br>");
       cuerpoMail.append("Podrá continuar accediendo al sistema con el usuario y contraseña que recibió al iniciar la solicitud.<br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(e.geteMail());
       helper.setSubject("Registro de Entidades: Solicitud Aprobada");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException ex) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendNuevaSolicitudAMesaEntradas(Usuario usuario, SolicitudEntidad se) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + usuario.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Se encuentra lista para su caratulación una nueva Solicitud de Entidad:<br>");
       cuerpoMail.append("Tipo: " + se.getTipoEntidad().getNombreTipoEntidad() + "<br>");
       cuerpoMail.append("Denominación: " + se.getDenominacion() + "<br>");
       cuerpoMail.append("CUIT: " + se.getCuit() + "<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(usuario.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Solicitud lista para Caratulación");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendSolicitudCaratuladaARolRegistro(SolicitudEntidad se, Usuario u) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + u.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("La siguiente entidad ha sido caratulada por Mesa de Entradas: <br>");
       cuerpoMail.append("Tipo: " + se.getTipoEntidad().getNombreTipoEntidad() + "<br>");
       cuerpoMail.append("Denominación: " + se.getDenominacion() + "<br>");
       cuerpoMail.append("CUIT: " + se.getCuit() + "<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(u.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Solicitud Caratulada");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendRecuerdoDocumentacion(String nombre, String mail) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + nombre + "</b>:<br><br>");
       cuerpoMail.append("La carga de archivos se realizó exitosamente. Recuerde que deberá acercar los originales al Registro de Entidades para validar la carga de los mismos.<br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(mail);
       helper.setSubject("Registro de Entidades: Presentaciones recibidas");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendAvisosIntimacionARegistro(Collection<Intimacion> intimaciones, Usuario u) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + u.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("Las siguientes intimaciones se vencieron (pasaron 15 días corridos desde la recepción o devolución de la Carta Documento) y se debe iniciar el proceso de baja a las entidades:<br>");
       for (Intimacion i : intimaciones) {
         cuerpoMail.append("<strong>Entidad: </strong>");
         cuerpoMail.append(i.getEntidad().getDenominacion());
         cuerpoMail.append("<strong>CUIT: </strong>");
         cuerpoMail.append(i.getEntidad().getCuit().toString());
         cuerpoMail.append("<br><strong>Número de Carta Documento: </strong>");
         cuerpoMail.append(i.getNumeroCartaDocumento());
         cuerpoMail.append("<br><strong>Fecha de envío de la Carta Documento: </strong>");
         cuerpoMail.append((new SimpleDateFormat("dd/MM/yyyy")).format(i.getFechaEnvio()));
         cuerpoMail.append("<br><strong>Fecha de Recepción de la Carta Documento: </strong>");
         cuerpoMail.append((new SimpleDateFormat("dd/MM/yyyy")).format(i.getFechaRecepcion()));
         cuerpoMail.append("<br><br>");
       } 
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(u.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Intimaciones vencidas");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendNuevaSolicitudBajaARegistro(SolicitudBaja sb, Usuario u) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + u.getDenominacion() + "</b>:<br><br>");
       cuerpoMail.append("La siguiente entidad ha solicitado la baja: <br>");
       cuerpoMail.append("Tipo: " + sb.getEntidad().getTipoEntidad().getNombreTipoEntidad() + "<br>");
       cuerpoMail.append("Denominación: " + sb.getEntidad().getDenominacion() + "<br>");
       cuerpoMail.append("CUIT: " + sb.getEntidad().getCuit() + "<br><br>");
       cuerpoMail.append("Deberá ingresar al Registro de Entidades para confirmar o rechazar la baja.<br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(u.getCorreoElectronico());
       helper.setSubject("Registro de Entidades: Solicitud de Baja");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendAvisoRecepcionSolicitudBaja(String correo, String nombre) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + nombre + "</b>:<br><br>");
       cuerpoMail.append("Su Solicitud de baja de Entidad ha sido recibida.<br>");
       cuerpoMail.append("Deberá presentar el original firmado del formulario de Solicitud de Baja al Registro de Entidades.<br><br>");
       cuerpoMail.append("Debe llevarlo a Av. Diagonal Roque Saenz Peña 511, Piso 7° Oficina 706 (CABA) de Lunes a Viernes de 9 a 16 h.<br>");
       cuerpoMail.append("Ante cualquier consulta no dude en comunicarse al 5985-5700 int. 5733 o por correo electrónico a registroentidades@modernizacion.gob.ar<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
 
       
       helper.setTo("pdomergue@modernizacion.gob.ar");
       helper.setSubject("Registro de Entidades: Solicitud de Baja recibida");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendAvisoSolicitudBajaProcesada(String correo, String nombre, String baja) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a <b>" + nombre + "</b>:<br><br>");
       cuerpoMail.append("Su Solicitud de Baja de Entidad ha sido procesada.<br>");
       if ("SI".equals(baja)) {
         cuerpoMail.append("La Entidad ha sido de baja y ya no operará en el Registro de Entidades.<br>");
       } else {
         cuerpoMail.append("Como su solicitud ha sido rechazada, la Entidad continuará operando en el Registro de Entidades.<br>");
       } 
       cuerpoMail.append("Ante cualquier consulta no dude en comunicarse al 5985-5700 int. 5733 o por correo electrónico a registroentidades@modernizacion.gob.ar<br><br>");
       cuerpoMail.append("También puede acercarse a Av. Diagonal Roque Saenz Peña 511, Piso 7° Oficina 706 (CABA) de Lunes a Viernes de 9 a 16 h.<br>");
       if ("NO".equals(baja)) {
         cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
         cuerpoMail.append("<br><br>");
       } 
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       String texto = "SI".equals(baja) ? "aceptada" : "rechazada";
       helper.setTo(correo);
       helper.setSubject("Registro de Entidades: Solicitud de Baja " + texto);
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
   
   public void sendConfirmacionDeBajaPorSolicitud(SolicitudBaja sb, Usuario u) {
     if ("SI".equals(sb.getBaja())) {
       
       MimeMessage message = this.mailSender.createMimeMessage();
 
       
       try {
         MimeMessageHelper helper = new MimeMessageHelper(message, true);
         
         StringBuffer cuerpoMail = new StringBuffer();
         cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
         cuerpoMail.append("Estimado/a <b>" + u.getDenominacion() + "</b>:<br><br>");
         cuerpoMail.append("La siguiente entidad ha sido dada de Baja por Solicitud: <br>");
         cuerpoMail.append("Tipo: " + sb.getEntidad().getTipoEntidad().getNombreTipoEntidad() + "<br>");
         cuerpoMail.append("Denominación: " + sb.getEntidad().getDenominacion() + "<br>");
         cuerpoMail.append("CUIT: " + sb.getEntidad().getCuit() + "<br><br>");
         cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
         cuerpoMail.append("<br><br>");
         cuerpoMail.append("<br><br>Muchas gracias, ");
         cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
         cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
         cuerpoMail.append("<br><br><br>");
         cuerpoMail.append("</font>");
 
         
         helper.setTo(u.getCorreoElectronico());
         helper.setSubject("Registro de Entidades: Nueva Baja por Solicitud");
         helper.setText(cuerpoMail.toString(), true);
         helper.setFrom(this.mailSender.getUsername());
       } catch (MessagingException e) {}
 
 
 
       
       this.mailSender.send(message);
     } 
   }
 }

