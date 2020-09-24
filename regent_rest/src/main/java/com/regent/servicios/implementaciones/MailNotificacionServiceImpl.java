package com.regent.servicios.implementaciones;


 import com.regent.negocio.Usuario;
 import com.regent.servicios.interfaces.MailNotificacionService;
 import com.regent.util.Config;
 import javax.mail.MessagingException;
 import javax.mail.internet.MimeMessage;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.mail.javamail.JavaMailSenderImpl;
 import org.springframework.mail.javamail.MimeMessageHelper;
 import org.springframework.stereotype.Service;
 
 
 
 @Service
 public class MailNotificacionServiceImpl
   implements MailNotificacionService
 {
   public static String URL_REGISTRO = String.valueOf(Config.getIP()) + "/registro/";
 
 
 
 
   
   @Autowired
   private JavaMailSenderImpl mailSender;
 
 
 
 
 
   
   public void setMailSender(JavaMailSenderImpl mailSender) {
     this.mailSender = mailSender;
   }
 
 
 
   
   public void sendPrimeraNotificacionMandato(String correo) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a:<br><br>");
       cuerpoMail.append("Por medio del presente se informa que el Mandato de las autoridades vigentes de la entidad está próximo a vencerse.");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(correo);
       helper.setSubject("Aviso: Mandato próximo a vencer");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendSegundaNotificacionMandato(String correo) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a:<br><br>");
       cuerpoMail.append("Por medio del presente se informa que el Mandato de las autoridades se encuentra vencido. Deberá acompañar Acta de Directorio con Distribución de cargos, bajo apercibimiento de iniciarse la Baja de la Entidad en este Registro.");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(correo);
       helper.setSubject("Aviso: Mandato vencido");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendPrimeraNotificacionBalance(String correo) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a:<br><br>");
       cuerpoMail.append("Por medio del presente se informa que el Balance de cierre de ejercicio obrante en el expediente en trámite ante el Registro de Entidades en el régimen de deducción de haberes del Decreto N° 14/2012 está próximo a vencerse.");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(correo);
       helper.setSubject("Aviso: Balance próximo a vencer");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendSegundaNotificacionBalance(String correo) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a:<br><br>");
       cuerpoMail.append("Por medio del presente se informa que el Balance de cierre de ejercicio obrante en el expediente en trámite ante el Registro de Entidades en el régimen de deducción de haberes del Decreto N° 14/2012 se encuentra vencido. Deberá ser presentado conjuntamente con el acta de aprobación por la Asamblea en el plazo de 15 días, bajo apercibimiento de iniciarse la Baja de la Entidad en este Registro.");
       cuerpoMail.append("<br><br>");
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(correo);
       helper.setSubject("Aviso: Balance vencido");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 
 
 
   
   public void sendNotificacionesDelDiaARegistro(String mandatosVencidos, String balancesVencidos, Usuario u) {
     MimeMessage message = this.mailSender.createMimeMessage();
 
     
     try {
       MimeMessageHelper helper = new MimeMessageHelper(message, true);
       
       StringBuffer cuerpoMail = new StringBuffer();
       cuerpoMail.append("<font face=\"Varela Round, sans-serif\">");
       cuerpoMail.append("Estimado/a " + u.getDenominacion() + ":<br><br>");
       cuerpoMail.append("En el día de la fecha se enviaron las siguientes notificaciones por vencimiento: ");
       cuerpoMail.append("<br><br>");
       if (mandatosVencidos.length() > 0) {
         cuerpoMail.append("Notificaciones por Mandato Vencido: <br>");
         cuerpoMail.append(mandatosVencidos);
         cuerpoMail.append("<br><br>");
       } 
       if (balancesVencidos.length() > 0) {
         cuerpoMail.append("Notificaciones por Balance Vencido: <br>");
         cuerpoMail.append(balancesVencidos);
         cuerpoMail.append("<br><br>");
       } 
       cuerpoMail.append("Para acceder al Registro ingrese <a href=\"" + URL_REGISTRO + "\">aquí</a>");
       cuerpoMail.append("<br><br>Muchas gracias, ");
       cuerpoMail.append("<br><br><b>Registro de Entidades</b> ");
       cuerpoMail.append("<br><b><font color=\"#00acc1\">Secretaría de Gobierno de Modernización</font></b> ");
       cuerpoMail.append("<br><br><br>");
       cuerpoMail.append("</font>");
 
       
       helper.setTo(u.getCorreoElectronico());
       helper.setSubject("Aviso: Balance vencido");
       helper.setText(cuerpoMail.toString(), true);
       helper.setFrom(this.mailSender.getUsername());
     } catch (MessagingException e) {}
 
 
 
     
     this.mailSender.send(message);
   }
 }

