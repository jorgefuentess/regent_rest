package com.regent.pdf;


 import com.itextpdf.io.image.ImageDataFactory;
 import com.itextpdf.kernel.color.Color;
 import com.itextpdf.kernel.font.PdfFont;
 import com.itextpdf.kernel.font.PdfFontFactory;
 import com.itextpdf.kernel.pdf.PdfDocument;
 import com.itextpdf.kernel.pdf.PdfWriter;
 import com.itextpdf.layout.Document;
 import com.itextpdf.layout.element.AreaBreak;
 import com.itextpdf.layout.element.Cell;
 import com.itextpdf.layout.element.IBlockElement;
 import com.itextpdf.layout.element.ILeafElement;
 import com.itextpdf.layout.element.Image;
 import com.itextpdf.layout.element.List;
 import com.itextpdf.layout.element.ListItem;
 import com.itextpdf.layout.element.Paragraph;
 import com.itextpdf.layout.element.Table;
 import com.itextpdf.layout.property.AreaBreakType;
 import com.itextpdf.layout.property.TextAlignment;
 import com.regent.dtos.CertificacionDTO;
 import com.regent.negocio.CodigoDescuento;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.SolicitudEntidad;
 import com.regent.util.Config;
 import java.io.IOException;
 import java.text.DecimalFormat;
 import java.text.ParseException;
 import java.util.Calendar;
 import java.util.Collection;
 import java.util.Date;
 import javax.servlet.http.HttpServletRequest;
 
 
 
 
 
 
 
 public class PdfGenerator
 {
   public static void generarPdfSolicitud(SolicitudEntidad se, Collection<String> servicios, Collection<String> archivos, String membreteAnio, String membreteOrganismo, String nroDecreto, String ruta) throws IOException {
     PdfWriter writer = new PdfWriter(String.valueOf(ruta) + "solicitu_" + se.getCuit() + ".pdf");
     PdfDocument pdf = new PdfDocument(writer);
     Document documento = new Document(pdf);
 
     
     documento.setMargins(36.0F, 36.0F, 36.0F, 108.0F);
     documento.setFontSize(12.0F);
     documento.setFontColor(Color.BLACK);
     PdfFont font = PdfFontFactory.createFont("Helvetica");
     documento.setFont(font);
 
     
     Paragraph anioTitulo = new Paragraph();
     anioTitulo.setTextAlignment(TextAlignment.RIGHT);
     anioTitulo.setFontSize(8.0F);
     anioTitulo.add(membreteAnio);
     documento.add((IBlockElement)anioTitulo);
 
     
     Paragraph saltolinea1 = new Paragraph();
     saltolinea1.add("");
 
     
     String fontPath = "/home/registro/util/font/shelley.ttf";
     PdfFont fontShelley = PdfFontFactory.createFont(fontPath, "Cp1252", true);
 
     
     String imagePath = "/home/registro/util/img/escudo.png";
     Image escudo = new Image(ImageDataFactory.create(imagePath));
     escudo.scaleAbsolute(80.0F, 57.0F);
 
     
     Paragraph organismo = new Paragraph();
     organismo.setTextAlignment(TextAlignment.CENTER);
     organismo.setFont(fontShelley);
     organismo.setFontSize(16.0F);
     organismo.add((ILeafElement)escudo);
     organismo.add("\n");
     organismo.add(membreteOrganismo);
     documento.add((IBlockElement)organismo);
 
     
     Paragraph pretitulo = new Paragraph();
     pretitulo.setTextAlignment(TextAlignment.RIGHT);
     pretitulo.add("ANEXO III");
 
 
     
     Paragraph titulo = new Paragraph();
     titulo.setTextAlignment(TextAlignment.CENTER);
     titulo.setFont(PdfFontFactory.createFont("Helvetica-Bold"));
     titulo.add("SOLICITUD DE INSCRIPCIÓN");
     titulo.add("\nREGISTRO DE ENTIDADES\n");
     titulo.add(nroDecreto);
     
     Paragraph subtitulo = new Paragraph();
     subtitulo.setTextAlignment(TextAlignment.RIGHT);
     subtitulo.add("BUENOS AIRES, " + obtenerFechaActual());
     
     documento.add((IBlockElement)pretitulo);
     documento.add((IBlockElement)titulo);
     documento.add((IBlockElement)subtitulo);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph destinatario = new Paragraph();
     destinatario.setTextAlignment(TextAlignment.LEFT);
     destinatario.add("SEÑOR COORDINADOR DEL REGISTRO DE ENTIDADES: ");
     documento.add((IBlockElement)destinatario);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo1 = new Paragraph();
     parrafo1.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo1.add("         Quien suscribe se dirige a usted en su carácter de ");
     parrafo1.add(se.getCaracterSolicitante());
     parrafo1.add(" de la ");
     parrafo1.add(se.getDenominacion());
     parrafo1.add(", inscripta en el Registro de ");
     parrafo1.add(se.getInstitucionHabilitante().getNombreInstitucionHabilitante());
     parrafo1.add(" bajo el N° ");
     parrafo1.add(String.valueOf(se.getNroRegHabilitante()) + ".");
     
     documento.add((IBlockElement)parrafo1);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo2 = new Paragraph();
     parrafo2.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo2.add("         El domicilio legal de la mencionada entidad se encuentra ubicado en la calle ");
     parrafo2.add(se.getDomicilioLegal());
     parrafo2.add(" de la localidad de ");
     parrafo2.add(se.getLocalidad());
     parrafo2.add(" CP ");
     parrafo2.add(String.valueOf(se.getCodigoPostal()) + ".");
     
     documento.add((IBlockElement)parrafo2);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo3 = new Paragraph();
     parrafo3.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo3.add(
         "         A todos los efectos derivados del presente trámite constituye domicilio especial en la calle ");
     parrafo3.add(se.getDomicilioEspecial());
     parrafo3.add(
         ", de la Ciudad Autónoma de Buenos Aires, dónde serán válidas todas las notificaciones que curse el Registro ");
     parrafo3.add(String.valueOf(generarTipoEntidad(se.getTipoEntidad().getNombreTipoEntidad())) + ".");
     
     documento.add((IBlockElement)parrafo3);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo4 = new Paragraph();
     parrafo4.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo4.add("         Quien suscribe, que acredita la personería invocada mediante ");
     parrafo4.add(se.getInstrumentoPersoneria());
     parrafo4.add(", viene a solicitar la inscripción de la Entidad que representa en el Registro creado por el " + 
         nroDecreto + 
         ", a su cargo, declarando bajo juramento que la entidad cuya inscripción solicita en este acto se encuentra legalmente habilitada, y autorizada por la autoridad de aplicación, sin que hasta la fecha se encuentre suspendida como así tampoco inhabilitada, para ofrecer los siguientes servicios: \n");
     
     List list = (new List()).setSymbolIndent(12.0F);
     for (String s : servicios) {
       list.add(new ListItem(s));
     }
     parrafo4.add((IBlockElement)list);
     documento.add((IBlockElement)parrafo4);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo5 = new Paragraph();
     parrafo5.setTextAlignment(TextAlignment.LEFT);
     parrafo5.add(
         "         A tales fines acompaña la siguiente documentación, cuya copia simple quedará archivada en esa Coordinación cualquiera fuere la resolución que se adopte respecto de la presente petición: ");
     
     List list2 = (new List()).setSymbolIndent(12.0F);
     for (String a : archivos) {
       list2.add(new ListItem(a));
     }
     parrafo5.add((IBlockElement)list2);
     documento.add((IBlockElement)parrafo5);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo6 = new Paragraph();
     parrafo6.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo6.add(
         "         Quien suscribe declara conocer el Decreto N° 14/2012, como así también su reglamentación, por lo que la Entidad que representa se compromete a cumplimentar todas las disposiciones legales, reglamentarias vigentes y las que se dicten en el futuro, así como las de ejecución que dicte la Autoridad de Aplicación, a mantener actualizada la información que suministra en este acto, y a remitir cualquier modificación al Registro dentro de las CUARENTA Y OCHO (48) horas de producida.");
     parrafo6.add("\n           Saluda a Ud. atentamente.");
     
     documento.add((IBlockElement)parrafo6);
     
     documento.add((IBlockElement)saltolinea1);
     
     Paragraph firmas = new Paragraph();
     firmas.setTextAlignment(TextAlignment.RIGHT);
     firmas.add("----------------------------------");
     firmas.add("\n Firma");
     firmas.add("\n\n\n ----------------------------------");
     firmas.add("\n Aclaración");
     firmas.add("\n\n\n ----------------------------------");
     firmas.add("\n D.N.I.");
     
     documento.add((IBlockElement)firmas);
 
     
     documento.close();
   }
 
 
   
   public static void generarPdfCertificacionHaberes(CertificacionDTO cDTO, String membreteAnio, String organismoSaf, CodigoDescuento cd, String nroDecreto, String fechaDecreto, Entidad entidad, HttpServletRequest request) throws IOException, ParseException {
     nroDecreto = nroDecreto.replace("(", "").replace(")", "");
     
     String downloadDir = "/home/registro/certificaciones/";
     if (Config.getLocal().booleanValue()) {
       downloadDir = request.getServletContext().getRealPath(downloadDir);
     }
     
     String nombre = "certificacion_" + cDTO.getDniSolicitante() + ".pdf";
 
 
     
     PdfWriter writer = new PdfWriter(String.valueOf(downloadDir) + nombre);
     
     PdfDocument pdf = new PdfDocument(writer);
 
     
     Document documento = new Document(pdf);
     documento.setMargins(36.0F, 36.0F, 36.0F, 36.0F);
     documento.setFontSize(12.0F);
     documento.setFontColor(Color.BLACK);
     PdfFont font = PdfFontFactory.createFont("Helvetica");
     documento.setFont(font);
 
     
     Paragraph anioTitulo = new Paragraph();
     anioTitulo.setTextAlignment(TextAlignment.RIGHT);
     anioTitulo.setFontSize(8.0F);
     anioTitulo.add(membreteAnio);
     documento.add((IBlockElement)anioTitulo);
 
     
     String fontPath = "/home/registro/util/font/shelley.ttf";
     if (Config.getLocal().booleanValue()) {
       fontPath = request.getServletContext().getRealPath("/font/shelley.ttf");
     }
     PdfFont fontShelley = PdfFontFactory.createFont(fontPath, "Cp1252", true);
 
     
     String imagePath = "/home/registro/util/img/escudo.png";
     if (Config.getLocal().booleanValue()) {
       imagePath = request.getServletContext().getRealPath("/img/escudo.png");
     }
     Image escudo = new Image(ImageDataFactory.create(imagePath));
     escudo.scaleAbsolute(80.0F, 57.0F);
 
     
     Paragraph organismo = new Paragraph();
     organismo.setTextAlignment(TextAlignment.CENTER);
     organismo.setFont(fontShelley);
     organismo.setFontSize(16.0F);
     organismo.add((ILeafElement)escudo);
     organismo.add("\n");
     organismo.add(organismoSaf);
     documento.add((IBlockElement)organismo);
 
     
     Paragraph saltolinea1 = new Paragraph();
     saltolinea1.add("");
 
     
     Paragraph pretitulo = new Paragraph();
     pretitulo.setTextAlignment(TextAlignment.RIGHT);
     pretitulo.add("ANEXO V");
 
 
     
     Paragraph titulo = new Paragraph();
     titulo.setTextAlignment(TextAlignment.CENTER);
     titulo.setFont(PdfFontFactory.createFont("Helvetica-Bold"));
     titulo.add("CERTIFICACIÓN DE HABERES");
     titulo.add("\n(Artículo 4° ");
     titulo.add(nroDecreto);
     titulo.add(")\n");
     
     documento.add((IBlockElement)pretitulo);
     documento.add((IBlockElement)titulo);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo1 = new Paragraph();
     parrafo1.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo1.add("Atento a lo establecido por el artículo 4° del " + nroDecreto + ", de fecha " + fechaDecreto);
     parrafo1.add(", certifico que ");
     if ("F".equals(cDTO.getGeneroSolicitante())) {
       parrafo1.add(" la Señora ");
     } else {
       parrafo1.add(" el Señor ");
     } 
     parrafo1.add(cDTO.getNaSolicitante());
     parrafo1.add(" (DNI " + cDTO.getDniSolicitante() + ") ");
     parrafo1.add("presta servicios en " + organismoSaf);
     parrafo1.add(", revistando como personal " + cDTO.getSituRevistaSolicitante());
     parrafo1.add(", y goza de una de una antigüedad en la Administración Pública Nacional de " + 
         cDTO.getAntiguedad() + ".");
     
     documento.add((IBlockElement)parrafo1);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     float[] pointColumnWidths = { 450.0F, 100.0F };
     Table table = new Table(pointColumnWidths);
     
     Cell cell1 = new Cell();
     cell1.add("CONCEPTO");
     cell1.setTextAlignment(TextAlignment.CENTER);
     table.addCell(cell1);
     
     Cell cell2 = new Cell();
     cell2.add("MONTO ($)");
     cell2.setTextAlignment(TextAlignment.CENTER);
     table.addCell(cell2);
     
     Cell cell3 = new Cell();
     cell3.add("RETRIBUCION MENSUAL, NORMAL, HABITUAL Y PERMANENTE");
     table.addCell(cell3);
     
     Cell cell4 = new Cell();
     DecimalFormat df = new DecimalFormat("0.00");
     String retriStr = df.format(Double.valueOf(cDTO.getRetribucion().replace(",", ".")));
     cell4.add(retriStr);
     table.addCell(cell4);
     
     Cell cell5 = new Cell(1, 2);
     cell5.add("RETENCIONES - CONCEPTOS");
     table.addCell(cell5);
     
     Integer i = Integer.valueOf(1);
     Double neto = Double.valueOf(cDTO.getRetribucion().replace(",", "."));
     
     if (cDTO.getConceptos().size() > 0) {
       for (Object[] o : cDTO.getConceptos()) {
         if (cDTO.getConceptos().size() == 1 && "".equals(o[0])) {
           Cell cell6 = new Cell(1, 2);
           cell6.add("No posee Retenciones");
           cell6.setTextAlignment(TextAlignment.CENTER);
           cell6.setFont(PdfFontFactory.createFont("Helvetica-Oblique"));
           table.addCell(cell6); continue;
         } 
         Cell cellA = new Cell();
         cellA.add(i + " " + (String)o[0]);
         table.addCell(cellA);
         
         Cell cellB = new Cell();
         String s = (String)o[1];
         cellB.add(Double.valueOf(s.replace(",", ".")).toString());
         table.addCell(cellB);
         
         i = Integer.valueOf(i.intValue() + 1);
         String s2 = (String)o[1];
         neto = Double.valueOf(neto.doubleValue() - Double.valueOf(s2.replace(",", ".")).doubleValue());
       } 
     } else {
       
       Cell cell6 = new Cell(1, 2);
       cell6.add("El agente no posee Retenciones");
       cell6.setTextAlignment(TextAlignment.CENTER);
       cell6.setFont(PdfFontFactory.createFont("Helvetica-Oblique"));
       table.addCell(cell6);
     } 
     
     Cell cell7 = new Cell();
     cell7.add("MONTO MENSUAL DISPONIBLE PARA EFECTUAR DEDUCCIONES");
     table.addCell(cell7);
     
     String netoStr = df.format(neto);
     
     Cell cell8 = new Cell();
     cell8.add(netoStr);
     table.addCell(cell8);
     
     documento.add((IBlockElement)table);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo2 = new Paragraph();
     parrafo2.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo2.add("El presente Certificado se emite el " + obtenerFechaActual());
     parrafo2.add(" con validez hasta el " + obtenerFechaActualMasDias(Integer.valueOf(30)) + ".");
     parrafo2.add("\nCertifico que el saldo en los haberes que percibirá el agente " + cDTO.getNaSolicitante());
     parrafo2.add(
         ", una vez efectuadas todas las deducciones, incluso las que derivarán del uso del precitado monto disponible, no será inferior al sueldo mínimo vital y móvil vigente al día de la fecha.");
     parrafo2.add("\nEl presente Certificado se extiende a fin de ser presentado ante la entidad " + 
         entidad.getDenominacion());
     parrafo2.add(", la que se encuentra facultada a operar en los términos del " + nroDecreto);
     parrafo2.add(", por encontrarse inscripta en el Registro de Entidades bajo el número " + 
         entidad.getNroRegHabilitante() + ".");
     parrafo2.add("\nEl presente Certificado se extiende a fin de solicitar el servicio de " + 
         cd.getTipoDescuento().getNombreTipoDescuento() + " (Código N° " + cd.getCodigoDescuento() + ").");
     
     parrafo2.add(
         "\nSe deja constancia que en caso que el presente Certificado sea utilizado para la obtención de créditos por parte del agente peticionante, la tasa de interés que la entidad le cobre no podrá superar el porcentual establecido en el artículo 12 del " + 
         nroDecreto + ".");
     parrafo2.add("\nSe deja constancia que la tasa de interés vigente al día de la fecha es de " + 
         cDTO.getTasa().replace("%", "") + "% .");
     
     documento.add((IBlockElement)parrafo2);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo3 = new Paragraph();
     parrafo3.setTextAlignment(TextAlignment.JUSTIFIED);
     
     parrafo3.add("Suscribe el presente " + cDTO.getNaSuscribe());
     parrafo3.add(", en calidad de " + cDTO.getCargoSuscribe());
     
     documento.add((IBlockElement)parrafo3);
     
     documento.add((IBlockElement)saltolinea1);
     documento.add((IBlockElement)saltolinea1);
     documento.add((IBlockElement)saltolinea1);
     documento.add((IBlockElement)saltolinea1);
     
     Paragraph firmaEmisor = new Paragraph();
     firmaEmisor.setTextAlignment(TextAlignment.RIGHT);
     firmaEmisor.add("Firma y aclaración del emisor");
     documento.add((IBlockElement)firmaEmisor);
 
     
     documento.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
 
     
     documento.add((IBlockElement)anioTitulo);
     documento.add((IBlockElement)organismo);
     
     Paragraph parrafo4 = new Paragraph();
     parrafo4.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo4.add(String.valueOf(cDTO.getNaSolicitante()) + " (DNI " + cDTO.getDniSolicitante() + ")");
     parrafo4.add(", recibo de plena conformidad la presente certificación");
     documento.add((IBlockElement)parrafo4);
     
     documento.add((IBlockElement)saltolinea1);
     documento.add((IBlockElement)saltolinea1);
     documento.add((IBlockElement)saltolinea1);
     documento.add((IBlockElement)saltolinea1);
     
     Paragraph firmaSolicitante = new Paragraph();
     firmaSolicitante.setTextAlignment(TextAlignment.RIGHT);
     firmaSolicitante.add("Firma y aclaración del agente solicitante");
     documento.add((IBlockElement)firmaSolicitante);
 
     
     documento.close();
   }
 
 
   
   public static void generarPdfSolicitudBaja(Entidad e, String nroResolucion, String codigosStr, String membreteAnio, String membreteOrganismo, String nroDecreto, HttpServletRequest request) throws IOException {
     String downloadDir = "/home/registro/bajas/";
     if (Config.getLocal().booleanValue()) {
       downloadDir = request.getServletContext().getRealPath(downloadDir);
     }
     
     String nombre = "solicitud_baja_" + e.getCuit() + ".pdf";
 
 
     
     PdfWriter writer = new PdfWriter(String.valueOf(downloadDir) + nombre);
     
     PdfDocument pdf = new PdfDocument(writer);
 
     
     Document documento = new Document(pdf);
     documento.setMargins(36.0F, 36.0F, 36.0F, 108.0F);
     documento.setFontSize(12.0F);
     documento.setFontColor(Color.BLACK);
     PdfFont font = PdfFontFactory.createFont("Helvetica");
     documento.setFont(font);
     
     Paragraph anioTitulo = new Paragraph();
     anioTitulo.setTextAlignment(TextAlignment.RIGHT);
     anioTitulo.setFontSize(8.0F);
     anioTitulo.add(membreteAnio);
     documento.add((IBlockElement)anioTitulo);
 
     
     String fontPath = "/home/registro/util/font/shelley.ttf";
     if (Config.getLocal().booleanValue()) {
       fontPath = request.getServletContext().getRealPath("/font/shelley.ttf");
     }
     PdfFont fontShelley = PdfFontFactory.createFont(fontPath, "Cp1252", true);
 
     
     String imagePath = "/home/registro/util/img/escudo.png";
     if (Config.getLocal().booleanValue()) {
       imagePath = request.getServletContext().getRealPath("/img/escudo.png");
     }
     Image escudo = new Image(ImageDataFactory.create(imagePath));
     escudo.scaleAbsolute(80.0F, 57.0F);
 
     
     Paragraph organismo = new Paragraph();
     organismo.setTextAlignment(TextAlignment.CENTER);
     organismo.setFont(fontShelley);
     organismo.setFontSize(16.0F);
     organismo.add((ILeafElement)escudo);
     organismo.add("\n");
     organismo.add(membreteOrganismo);
     documento.add((IBlockElement)organismo);
 
     
     Paragraph saltolinea1 = new Paragraph();
     saltolinea1.add("");
 
 
     
     Paragraph titulo = new Paragraph();
     titulo.setTextAlignment(TextAlignment.CENTER);
     titulo.setFont(PdfFontFactory.createFont("Helvetica-Bold"));
     titulo.add("SOLICITUD DE BAJA");
     titulo.add("\nREGISTRO DE ENTIDADES\n");
     titulo.add("(" + nroDecreto + ")");
     
     Paragraph subtitulo = new Paragraph();
     subtitulo.setTextAlignment(TextAlignment.RIGHT);
     subtitulo.add("BUENOS AIRES, " + obtenerFechaActual());
     
     documento.add((IBlockElement)titulo);
     documento.add((IBlockElement)subtitulo);
     
     documento.add((IBlockElement)saltolinea1);
     
     Paragraph destinatario = new Paragraph();
     destinatario.setTextAlignment(TextAlignment.LEFT);
     destinatario.add("SEÑOR COORDINADOR DEL REGISTRO DE ENTIDADES: ");
     documento.add((IBlockElement)destinatario);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo1 = new Paragraph();
     parrafo1.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo1.add("         Quien suscribe se dirige a usted en su carácter de ");
     parrafo1.add(e.getCaracterSolicitante());
     parrafo1.add(" " + generarTipoEntidad2(e.getTipoEntidad().getNombreTipoEntidad()) + " ");
     parrafo1.add(e.getDenominacion());
     parrafo1.add(", CUIT N° ");
     parrafo1.add(e.getCuit().toString());
     if (!"".equals(e.getDomicilio_especial()) && e.getDomicilio_especial() != null) {
       parrafo1.add(", con domicilio constituido en ");
       parrafo1.add(e.getDomicilio_especial());
     } 
     parrafo1.add(", inscript" + obtenerGeneroEntidad(e.getTipoEntidad().getNombreTipoEntidad()));
     parrafo1.add(" en el Registro de Entidades del " + nroDecreto);
     parrafo1.add(" mediante Resolución N° " + nroResolucion);
     parrafo1.add(".");
 
     
     documento.add((IBlockElement)parrafo1);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo2 = new Paragraph();
     parrafo2.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo2.add(
         "         Quien suscribe viene a solicitar la baja de la inscripción y la cancelación de los códigos de descuento N° " + 
         codigosStr);
     parrafo2.add(
         " para el régimen de deducción de haberes, de conformidad a lo previsto en el Art. 11, del Anexo II de la Resolución - ex SGyCA - N° 52/2014.");
     
     documento.add((IBlockElement)parrafo2);
     
     documento.add((IBlockElement)saltolinea1);
 
     
     Paragraph parrafo3 = new Paragraph();
     parrafo3.setTextAlignment(TextAlignment.JUSTIFIED);
     parrafo3.add("\n           Saluda a Ud. atentamente.");
     
     documento.add((IBlockElement)parrafo3);
     
     documento.add((IBlockElement)saltolinea1);
     
     Paragraph firmas = new Paragraph();
     firmas.setTextAlignment(TextAlignment.RIGHT);
     firmas.add("----------------------------------");
     firmas.add("\n Firma");
     firmas.add("\n\n\n ----------------------------------");
     firmas.add("\n Aclaración");
     firmas.add("\n\n\n ----------------------------------");
     firmas.add("\n D.N.I.");
     
     documento.add((IBlockElement)firmas);
 
     
     documento.close();
   }
 
   
   private static String generarTipoEntidad(String tipoEntidad) {
     String retorno = "";
     String str1;
     switch ((str1 = tipoEntidad).hashCode()) { case -1830307284: if (!str1.equals("Cooperativas")) {
           break;
         }
 
         
         retorno = "a la cooperativa";
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
         
         return retorno;case -880103858: if (!str1.equals("Bancos y Financieras")) break;  retorno = "al banco/financiera"; return retorno;case -864039256: if (!str1.equals("Entidades Oficiales")) break;  retorno = "a la entidad oficial"; return retorno;case 157248285: if (!str1.equals("Sindicatos")) break;  retorno = "al sindicato"; return retorno;case 948188130: if (!str1.equals("Mutuales")) break;  retorno = "a la mutual"; return retorno;case 1805854442: if (!str1.equals("Obras Sociales")) break;  retorno = "a la obra social"; return retorno; }  retorno = "al/a la" + tipoEntidad.toLowerCase(); return retorno;
   }
   
   private static String generarTipoEntidad2(String tipoEntidad) {
     String retorno = "";
     String str1;
     switch ((str1 = tipoEntidad).hashCode()) { case -1830307284: if (!str1.equals("Cooperativas")) {
           break;
         }
 
         
         retorno = "de la cooperativa";
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
         
         return retorno;case -880103858: if (!str1.equals("Bancos y Financieras")) break;  retorno = "del banco/financiera"; return retorno;case -864039256: if (!str1.equals("Entidades Oficiales")) break;  retorno = "de la entidad oficial"; return retorno;case 157248285: if (!str1.equals("Sindicatos")) break;  retorno = "del sindicato"; return retorno;case 948188130: if (!str1.equals("Mutuales")) break;  retorno = "de la mutual"; return retorno;case 1805854442: if (!str1.equals("Obras Sociales")) break;  retorno = "de la obra social"; return retorno; }  retorno = "el/la" + tipoEntidad.toLowerCase(); return retorno;
   }
   
   private static String obtenerGeneroEntidad(String tipoEntidad) {
     String retorno = "";
     String str1;
     switch ((str1 = tipoEntidad).hashCode()) { case -1830307284: if (!str1.equals("Cooperativas")) {
           break;
         }
 
         
         retorno = "a";
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
         
         return retorno;case -880103858: if (!str1.equals("Bancos y Financieras")) break;  retorno = "a"; return retorno;case -864039256: if (!str1.equals("Entidades Oficiales")) break;  retorno = "a"; return retorno;case 157248285: if (!str1.equals("Sindicatos")) break;  retorno = "o"; return retorno;case 948188130: if (!str1.equals("Mutuales")) break;  retorno = "a"; return retorno;case 1805854442: if (!str1.equals("Obras Sociales")) break;  retorno = "a"; return retorno; }  retorno = "o/a"; return retorno;
   }
   
   private static String obtenerFechaActual() {
     Date hoy = new Date();
     
     Calendar cal = Calendar.getInstance();
     cal.setTime(hoy);
     int year = cal.get(1);
     int month = cal.get(2);
     int day = cal.get(5);
     
     String mesString = "";
     switch (month + 1)
     { case 1:
         mesString = "Enero";
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
         
         return 
 
           
           String.valueOf(day) + " de " + mesString + " del " + year;case 2: mesString = "Febrero"; return String.valueOf(day) + " de " + mesString + " del " + year;case 3: mesString = "Marzo"; return String.valueOf(day) + " de " + mesString + " del " + year;case 4: mesString = "Abril"; return String.valueOf(day) + " de " + mesString + " del " + year;case 5: mesString = "Mayo"; return String.valueOf(day) + " de " + mesString + " del " + year;case 6: mesString = "Junio"; return String.valueOf(day) + " de " + mesString + " del " + year;case 7: mesString = "Julio"; return String.valueOf(day) + " de " + mesString + " del " + year;case 8: mesString = "Agosto"; return String.valueOf(day) + " de " + mesString + " del " + year;case 9: mesString = "Septiembre"; return String.valueOf(day) + " de " + mesString + " del " + year;case 10: mesString = "Octubre"; return String.valueOf(day) + " de " + mesString + " del " + year;case 11: mesString = "Noviembre"; return String.valueOf(day) + " de " + mesString + " del " + year; }  mesString = "Diciembre"; return String.valueOf(day) + " de " + mesString + " del " + year;
   }
 
   
   private static String obtenerFechaActualMasDias(Integer dias) {
     Date hoy = new Date();
     
     Calendar cal = Calendar.getInstance();
     cal.setTime(hoy);
     
     cal.add(6, dias.intValue());
     
     int year = cal.get(1);
     int month = cal.get(2);
     int day = cal.get(5);
     
     String mesString = "";
     switch (month + 1)
     { case 1:
         mesString = "Enero";
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
         
         return 
 
           
           String.valueOf(day) + " de " + mesString + " del " + year;case 2: mesString = "Febrero"; return String.valueOf(day) + " de " + mesString + " del " + year;case 3: mesString = "Marzo"; return String.valueOf(day) + " de " + mesString + " del " + year;case 4: mesString = "Abril"; return String.valueOf(day) + " de " + mesString + " del " + year;case 5: mesString = "Mayo"; return String.valueOf(day) + " de " + mesString + " del " + year;case 6: mesString = "Junio"; return String.valueOf(day) + " de " + mesString + " del " + year;case 7: mesString = "Julio"; return String.valueOf(day) + " de " + mesString + " del " + year;case 8: mesString = "Agosto"; return String.valueOf(day) + " de " + mesString + " del " + year;case 9: mesString = "Septiembre"; return String.valueOf(day) + " de " + mesString + " del " + year;case 10: mesString = "Octubre"; return String.valueOf(day) + " de " + mesString + " del " + year;case 11: mesString = "Noviembre"; return String.valueOf(day) + " de " + mesString + " del " + year; }  mesString = "Diciembre"; return String.valueOf(day) + " de " + mesString + " del " + year;
   }
 }
