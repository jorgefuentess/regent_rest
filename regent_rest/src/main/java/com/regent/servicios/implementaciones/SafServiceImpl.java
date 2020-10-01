package com.regent.servicios.implementaciones;

import com.regent.dtos.CertificacionDTO;
 import com.regent.dtos.DatosSafDTO;
 import com.regent.dtos.OrganismoDTO;
 import com.regent.dtos.SafDTO;
 import com.regent.negocio.CodigoDescuento;
 import com.regent.negocio.DatosSaf;
 import com.regent.negocio.Entidad;
 import com.regent.negocio.Parametro;
 import com.regent.negocio.Saf;
 import com.regent.negocio.Usuario;
 import com.regent.pdf.PdfGenerator;
 import com.regent.repositories.interfaces.CodigoDescuentoRepository;
 import com.regent.repositories.interfaces.DatosSafRepository;
 import com.regent.repositories.interfaces.EntidadRepository;
 import com.regent.repositories.interfaces.SafRepository;
 import com.regent.repositories.interfaces.UsuarioRepository;
 import com.regent.servicios.interfaces.ParametroService;
 import com.regent.servicios.interfaces.SafService;
 import com.regent.servicios.interfaces.UsuarioService;
 import com.regent.util.Config;
 import java.io.BufferedReader;
 import java.io.File;
 import java.io.IOException;
 import java.io.InputStream;
 import java.io.InputStreamReader;
 import java.sql.Timestamp;
 import java.text.DateFormat;
 import java.text.ParseException;
 import java.text.SimpleDateFormat;
 import java.util.ArrayList;
 import java.util.Collection;
 import java.util.Date;
 import java.util.List;
 import javax.servlet.http.HttpServletRequest;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Service;
 import org.springframework.web.multipart.MultipartFile;
 
 
 
 @Service
 public class SafServiceImpl
   implements SafService
 {
   public static String ALTA = "Alta";
 
   
   public static String MEMBRETE_ANIO = "MEMBRETE_ANIO";
   public static String NRO_DECRETO = "NRO_DECRETO";
   public static String FECHA_DECRETO = "FECHA_DECRETO";
 
   
   public static String PATH = "/home/registro";
 
   
   @Autowired
   SafRepository sRepo;
   
   @Autowired
   UsuarioRepository uRepo;
   
   @Autowired
   CodigoDescuentoRepository cRepo;
   
   @Autowired
   DatosSafRepository dsRepo;
   
   @Autowired
   EntidadRepository eRepo;
   
   @Autowired
   UsuarioService uService;
   
   @Autowired
   ParametroService paService;
 
   
   public Collection<SafDTO> getAllSafActivos() {
     Collection<Object[]> saf = this.sRepo.getAllSafActivos();
     Collection<SafDTO> safDTO = new ArrayList<>();
     for (Object[] s : saf) {
       SafDTO sDTO = new SafDTO(String.valueOf(s[0]), (String)s[1], (String)s[2], (String)s[3], (String)s[4], (String)s[5]);
       safDTO.add(sDTO);
     } 
     return safDTO;
   }
 
   
   public SafDTO getInfoSaf(String cfuOrg) {
     Saf s = this.sRepo.findByCfuOrganismo(cfuOrg.toUpperCase());
     SafDTO sDTO = new SafDTO();
     sDTO.seteMail(s.geteMail());
     sDTO.setnSaf(s.getnSaf().toString());
     sDTO.setOrganismoExt(s.getOrganismoExt());
     sDTO.setResponsable(s.getResponsable());
     sDTO.setTelefono(s.getTelefono());
     if ("A".equals(s.getEstado())) {
       return sDTO;
     }
     return null;
   }
 
 
 
 
 
 
 
   
   public void nuevoSAF(SafDTO sDTO, String usuario) {
     Object[] org = this.sRepo.getCfuOrgAndOrganismoExtByNSaf(sDTO.getnSaf());
     Object[] org2 = (Object[])org[0];
     Saf saf = new Saf();
     saf.setnSaf(Integer.valueOf(sDTO.getnSaf()));
     saf.setCfuOrganismo(org2[0].toString());
     saf.setOrganismoExt(org2[1].toString());
     saf.setResponsable(sDTO.getResponsable());
     saf.seteMail(sDTO.geteMail());
     saf.setTelefono(sDTO.getTelefono());
     
     String estado = "";
     if ("Activo".equals(sDTO.getEstado().trim()) || "A".equals(sDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(sDTO.getEstado().trim()) || "I".equals(sDTO.getEstado().trim())) {
       estado = "I";
     } 
     saf.setEstado(!"".equals(estado) ? estado : "A");
     saf.setUsuario(usuario);
     saf.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
     
     this.sRepo.save(saf);
   }
 
 
 
 
 
 
   
   public void updateSAF(SafDTO sDTO, String usuario) {
     Saf saf = (Saf)sRepo.findOne(Integer.valueOf(sDTO.getnSaf()));
     
     Object[] org = this.sRepo.getCfuOrgAndOrganismoExtByNSaf(sDTO.getnSaf());
     Object[] org2 = (Object[])org[0];
     saf.setCfuOrganismo(org2[0].toString());
     saf.setOrganismoExt(org2[1].toString());
 
 
     
     saf.setResponsable(sDTO.getResponsable());
     saf.seteMail(sDTO.geteMail());
     saf.setTelefono(sDTO.getTelefono());
     
     String estado = "";
     if ("Activo".equals(sDTO.getEstado().trim()) || "A".equals(sDTO.getEstado().trim())) {
       estado = "A";
     } else if ("Inactivo".equals(sDTO.getEstado().trim()) || "I".equals(sDTO.getEstado().trim())) {
       estado = "I";
     } 
     saf.setEstado(!"".equals(estado) ? estado : "A");
 
     
     if ("I".equals(saf.getEstado())) {
       this.uService.inhabilitarUsuario(saf.getCfuOrganismo().trim().toLowerCase(), usuario);
     }
     
     saf.setUsuario(usuario);
     saf.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
 
     
     this.sRepo.save(saf);
   }
 
 
 
   
   public void updateInfoSaf(String cfuOrg, SafDTO sDTO) {
     Saf saf = this.sRepo.findByCfuOrganismo(cfuOrg.toUpperCase());
     
     if ("A".equals(saf.getEstado())) {
       saf.setResponsable(sDTO.getResponsable().trim());
       saf.seteMail(sDTO.geteMail().trim());
       saf.setTelefono(sDTO.getTelefono().trim());
 
       
       saf.setUsuario(cfuOrg.toLowerCase());
       saf.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
 
       
       this.sRepo.save(saf);
     } 
   }
 
 
 
 
 
 
   
   public Collection<OrganismoDTO> getOrganismosFromSaf() {
     Collection<Object[]> organismos = this.sRepo.getAllOrganismosFromSaf();
     Collection<OrganismoDTO> organismosDTO = new ArrayList<>();
     for (Object[] o : organismos) {
       OrganismoDTO oDTO = new OrganismoDTO((String)o[1], (String)o[0]);
       organismosDTO.add(oDTO);
     } 
     return organismosDTO;
   }
 
 
 
 
 
 
   
   public Collection<OrganismoDTO> getOrganismosWithoutSaf() {
     Collection<Object[]> organismos = this.sRepo.getOrganismosWithoutSaf();
     Collection<OrganismoDTO> organismosDTO = new ArrayList<>();
     for (Object[] o : organismos) {
       OrganismoDTO oDTO = new OrganismoDTO((String)o[1], (String)o[0]);
       organismosDTO.add(oDTO);
     } 
     return organismosDTO;
   }
 
 
 
 
 
 
   
   public Collection<OrganismoDTO> getOrganismosWithoutUsuarioSaf() {
     Collection<Object[]> organismos = this.sRepo.getOrganismosWithoutUsuarioSaf();
     Collection<OrganismoDTO> organismosDTO = new ArrayList<>();
     for (Object[] o : organismos) {
       OrganismoDTO oDTO = new OrganismoDTO((String)o[1], (String)o[0]);
       organismosDTO.add(oDTO);
     } 
     return organismosDTO;
   }
 
 
 
 
 
 
 
   
   public String[] sendCsvFile(MultipartFile file, String usuario, HttpServletRequest request) throws IOException {
     Usuario u = this.uRepo.findByNombreUsuario(usuario);
     Saf s = this.sRepo.findByCfuOrganismo(u.getNombreUsuario().toUpperCase());
     Boolean error = Boolean.FALSE;
     String mensaje = "";
     
     String mensajeAlerta = "";
     
     Integer registros = Integer.valueOf(0);
     
     ArrayList<String> numerosCertificado = new ArrayList<>();
     
     if (!file.getOriginalFilename().contains(".csv")) {
       String[] err = new String[1];
       err[0] = "La extensión del archivo no es .csv";
       return err;
     } 
     String[] mensajeNombreArchivo = new String[1];
     mensajeNombreArchivo[0] = nombreValido(file.getOriginalFilename(), s.getnSaf());
     if (mensajeNombreArchivo[0].length() > 0) {
       return mensajeNombreArchivo;
     }
 
     
     if (u != null && "SAF".equals(u.getRol().getNombreRol()))
     {
       if (s != null && "A".equals(s.getEstado())) {
         Integer nSafUsuario = s.getnSaf();
         List<String> filas = obtenerFilas(file);
         
         String anioDeclarado = file.getOriginalFilename().split("_")[1].substring(0, 4);
         String mesDeclarado = file.getOriginalFilename().split("_")[1].substring(4, 6);
         
         DateFormat format = new SimpleDateFormat("dd/MM/YYYY");
 
         
         this.sRepo.deleteProgreso(file.getOriginalFilename().trim().replace(".csv", ""));
         this.sRepo.insertProgreso(file.getOriginalFilename().trim().replace(".csv", ""), Integer.valueOf(filas.size()));
         
         for (int i = 1; i < filas.size(); i++) {
           String nFila = String.valueOf(i + 1);
           String[] datosSaf = ((String)filas.get(i)).split(";");
 
           
           try {
             Integer nSaf = Integer.valueOf(datosSaf[0].trim());
             if (!nSafUsuario.toString().equals(nSaf.toString())) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el SAF indicado en la columna A no corresponde al SAF del usuario logueado.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna A se espera un número entero correspondiente al número de SAF.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             Integer mes = Integer.valueOf(datosSaf[1].trim());
             if (Integer.valueOf(mesDeclarado) != mes) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el mes ingresado en la columna B no coincide con el mes del nombre del archivo.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna B se espera un número entero correspondiente al mes.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             Integer anio = Integer.valueOf(datosSaf[2].trim());
             if (!anioDeclarado.equals(anio.toString())) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el año ingresado en la columna C no coincide con el año del nombre del archivo.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna C se espera un número entero correspondiente al año.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             Long cuil = Long.valueOf(datosSaf[3].trim());
             if (cuil.longValue() < 0L) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el cuil ingresado en la columna D no puede ser menor a 0.<br>";
               error = Boolean.TRUE;
             } else if (Long.toString(cuil.longValue()).length() != 11) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el cuil ingresado en la columna D debe tener 11 dígitos.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna D se espera un número entero de 11 dígitos (sin guiones) correspondiente al cuil.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             CodigoDescuento cd = new CodigoDescuento();
             Integer codigoDescuento = Integer.valueOf(datosSaf[4].trim());
             cd = (CodigoDescuento)cRepo.findOne(codigoDescuento);
             if (cd == null) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el código de descuento indicado en la columna E no corresponde a ningún código de descuento existente.<br>";
               error = Boolean.TRUE;
             }
             else if (!ALTA.equals(cd.getResolucion().getEntidad().getEstadoEntidad().getNombreEstado())) {
               mensajeAlerta = String.valueOf(mensajeAlerta) + "El código de descuento de la fila " + nFila + " pertenece a la entidad <i>" + cd.getResolucion().getEntidad().getDenominacion() + "</i> que se encuentra en estado \"" + cd.getResolucion().getEntidad().getEstadoEntidad().getNombreEstado() + "\".<br>";
             }
           
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna E se espera un número entero correspondiente al código de descuento.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             String numCertif = datosSaf[5].trim();
             if (numCertif == null || "".equals(numCertif)) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": la columna F no puede estar vacía.<br>";
               error = Boolean.TRUE;
             } else if (numerosCertificado.contains(numCertif)) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el número de certificado de la columna F debe ser único en todo el archivo.<br>";
               error = Boolean.TRUE;
             } else {
               numerosCertificado.add(numCertif);
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el dato ingresado en la columna F es incorrecto.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             format.parse(datosSaf[6].trim());
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna G se espera una fecha con el formato dd/mm/aaaa correspondiente a la fecha de emisión de certificado.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             String tasaS = datosSaf[7].trim();
             tasaS = tasaS.replace("%", "");
             tasaS = tasaS.replace(".", "");
             tasaS = tasaS.replace(",", ".");
             Double tasa = Double.valueOf(tasaS);
             if (tasa.doubleValue() < 0.0D) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": la tasa ingresada en la columna H no puede ser menor a 0.<br>";
               error = Boolean.TRUE;
             
             }
           
           }
           catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna H se espera un número correspondiente a la tasa.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             String remuS = datosSaf[8].trim();
             remuS = remuS.replace("$", "");
             remuS = remuS.replace(".", "");
             remuS = remuS.replace(",", ".");
             Double remu = Double.valueOf(remuS);
             if (remu.doubleValue() < 0.0D) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": la remuneración ingresada en la columna I no puede ser menor a 0.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna I se espera un número correspondiente a la remuneración.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             String porcentajeS = datosSaf[9].trim();
             porcentajeS = porcentajeS.replace("%", "");
             porcentajeS = porcentajeS.replace(".", "");
             porcentajeS = porcentajeS.replace(",", ".");
             Double porcentaje = Double.valueOf(porcentajeS);
             if (porcentaje.doubleValue() < 0.0D) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el porcentaje ingresado en la columna J no puede ser menor a 0.<br>";
               error = Boolean.TRUE;
             } else if (porcentaje.doubleValue() > 100.0D) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": la tasa ingresada en la columna J no puede ser mayor a 100.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna J se espera un número correspondiente al porcentaje.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             format.parse(datosSaf[10].trim());
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna K se espera una fecha con el formato dd/mm/aaaa correspondiente a la fecha de comienzo de afectación.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             String mtS = datosSaf[11].trim();
             mtS = mtS.replace("$", "");
             mtS = mtS.replace(".", "");
             mtS = mtS.replace(",", ".");
             Double mt = Double.valueOf(mtS);
             if (mt.doubleValue() < 0.0D) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el monto total ingresado en la columna L no puede ser menor a 0.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna L se espera un número correspondiente al monto total.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             String mcS = datosSaf[12].trim();
             mcS = mcS.replace("$", "");
             mcS = mcS.replace(".", "");
             mcS = mcS.replace(",", ".");
             Double mc = Double.valueOf(mcS);
             if (mc.doubleValue() < 0.0D) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el monto de la cuota ingresado en la columna M no puede ser menor a 0.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna M se espera un número correspondiente al monto de la cuota.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             Integer cantCuotas = Integer.valueOf(datosSaf[13].trim());
             if (cantCuotas.intValue() < 0) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": la cantidad de cuotas ingresada en la columna N no puede ser menor a 0.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna N se espera un número entero correspondiente a la cantidad de cuotas.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             Integer cantCuotas = Integer.valueOf(datosSaf[13].trim());
             Integer nroCuota = Integer.valueOf(datosSaf[14].trim());
             if (nroCuota.intValue() < 0) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el número de cuota ingresado en la columna O no puede ser menor a 0.<br>";
               error = Boolean.TRUE;
             } else if (cantCuotas.intValue() > 0 && nroCuota.intValue() > cantCuotas.intValue()) {
               mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": el número de cuota ingresado en la columna O no puede ser mayor a la cantidad de cuotas de la columna N.<br>";
               error = Boolean.TRUE;
             } 
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna O se espera un número entero correspondiente al número de cuota.<br>";
             error = Boolean.TRUE;
           } 
 
           
           try {
             format.parse(datosSaf[15].trim());
           } catch (Exception e) {
             mensaje = String.valueOf(mensaje) + "Error en la fila " + nFila + ": en la columna P se espera una fecha con el formato dd/mm/aaaa correspondiente a la fecha de fin de afectación.<br>";
             error = Boolean.TRUE;
           } 
 
           
           this.sRepo.updateProgreso(file.getOriginalFilename().trim().replace(".csv", ""), Integer.valueOf(i));
         } 
 
         
         if (error == Boolean.TRUE) {
           String[] arrayOfString = new String[1];
           arrayOfString[0] = mensaje;
 
           
           this.sRepo.deleteProgreso(file.getOriginalFilename().trim().replace(".csv", ""));
           return arrayOfString;
         } 
         Collection<DatosSaf> datosSafList = new ArrayList<>();
         for (int j = 1; j < filas.size(); j++) {
           String[] datosSaf = ((String)filas.get(j)).split(";");
           
           DatosSaf ds = new DatosSaf();
           ds.setSaf(s);
           ds.setMes(Integer.valueOf(datosSaf[1].trim()));
           ds.setAnio(Integer.valueOf(datosSaf[2].trim()));
           ds.setCuil(Long.valueOf(datosSaf[3].trim()));
           CodigoDescuento cd = (CodigoDescuento) cRepo.findOne(Integer.valueOf(datosSaf[4].trim()));
           ds.setCodigoDescuento(cd);
           
           ds.setNroCertificado(datosSaf[5].trim());
           try {
             ds.setFechaEmision(format.parse(datosSaf[6].trim()));
           } catch (ParseException e) {
             break;
           } 
 
 
 
 
           
           String tasaS = datosSaf[7].trim();
           tasaS = tasaS.replace("%", "");
           tasaS = tasaS.replace(".", "");
           tasaS = tasaS.replace(",", ".");
           ds.setTasa(Double.valueOf(tasaS));
           
           String remuS = datosSaf[8].trim();
           remuS = remuS.replace("$", "");
           remuS = remuS.replace(".", "");
           remuS = remuS.replace(",", ".");
           ds.setRemuneracion(Double.valueOf(remuS));
           
           String porcentajeS = datosSaf[9].trim();
           porcentajeS = porcentajeS.replace("%", "");
           porcentajeS = porcentajeS.replace(".", "");
           porcentajeS = porcentajeS.replace(",", ".");
           ds.setPorcentaje(Double.valueOf(porcentajeS));
           
           try {
             ds.setComienzoAfectacion(format.parse(datosSaf[10].trim()));
           } catch (ParseException e) {
             break;
           } 
           
           String mtS = datosSaf[11].trim();
           mtS = mtS.replace("$", "");
           mtS = mtS.replace(".", "");
           mtS = mtS.replace(",", ".");
           ds.setMontoTotal(Double.valueOf(mtS));
           
           String mcS = datosSaf[12].trim();
           mcS = mcS.replace("$", "");
           mcS = mcS.replace(".", "");
           mcS = mcS.replace(",", ".");
           ds.setMontoCuota(Double.valueOf(mcS));
           
           ds.setCantCuotas(Integer.valueOf(datosSaf[13].trim()));
           ds.setNroCuota(Integer.valueOf(datosSaf[14].trim()));
           
           try {
             ds.setFechaFin(format.parse(datosSaf[15].trim()));
           } catch (ParseException e) {
             break;
           } 
           
           ds.setUsuario(usuario);
           ds.setActualizado(String.valueOf(new Timestamp(System.currentTimeMillis())));
           
           datosSafList.add(ds);
         } 
         
         dsRepo.save(datosSafList);
 
         
         String uploadsDir = String.valueOf(PATH) + "/csv/";
         if (Config.getLocal().booleanValue()) {
           uploadsDir = request.getServletContext().getRealPath(uploadsDir);
         }
         if (!(new File(uploadsDir)).exists())
         {
           (new File(uploadsDir)).mkdir();
         }
         
         String orgName = file.getOriginalFilename();
         String filePath = String.valueOf(uploadsDir) + orgName;
         File dest = new File(filePath);
         file.transferTo(dest);
         
         registros = Integer.valueOf(datosSafList.size());
       } 
     }
 
     
     String[] retorno = new String[2];
     retorno[0] = (registros.intValue() > 0) ? registros.toString() : "";
     retorno[1] = mensajeAlerta;
 
     
     this.sRepo.deleteProgreso(file.getOriginalFilename().trim().replace(".csv", ""));
     
     return retorno;
   }
 
   
   public Collection<DatosSafDTO> getAllDatosSafByUsuario(String usuario) throws ParseException {
     Usuario u = this.uRepo.findByNombreUsuario(usuario);
     Collection<DatosSafDTO> datosSafDTO = new ArrayList<>();
     
     if (u != null && "SAF".equals(u.getRol().getNombreRol())) {
       Saf safUsuario = this.sRepo.findByCfuOrganismo(u.getNombreUsuario().toUpperCase());
       
       if (safUsuario != null && "A".equals(safUsuario.getEstado())) {
         Collection<DatosSaf> datosSaf = this.dsRepo.getAllDatosSafBySaf(safUsuario.getnSaf());
         
         for (DatosSaf ds : datosSaf) {
           Date actualizado = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(ds.getActualizado());
           String fechaCarga = (new SimpleDateFormat("dd/MM/YYYY")).format(actualizado).toString();
           
           Date emision = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(ds.getFechaEmision().toString());
           String fechaEmision = (new SimpleDateFormat("dd/MM/YYYY")).format(emision).toString();
           
           Date comienzoAfectacion = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(ds.getComienzoAfectacion().toString());
           String fechacomienzoAfectacion = (new SimpleDateFormat("dd/MM/YYYY")).format(comienzoAfectacion).toString();
           
           Date fin = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(ds.getFechaFin().toString());
           String fechaFin = (new SimpleDateFormat("dd/MM/YYYY")).format(fin).toString();
           
           DatosSafDTO dsDTO = new DatosSafDTO(String.valueOf(ds.getMes().toString()) + "/" + ds.getAnio().toString(), ds.getCuil().toString(), 
               ds.getCodigoDescuento().getCodigoDescuento().toString(), 
               ds.getNroCertificado(), fechaEmision, 
               ds.getTasa().toString(), "$ " + ds.getRemuneracion().toString(), 
               String.valueOf(ds.getPorcentaje().toString()) + " %", fechacomienzoAfectacion, 
               "$ " + ds.getMontoTotal().toString(), "$ " + ds.getMontoCuota().toString(), 
               String.valueOf(ds.getNroCuota().toString()) + " de " + ds.getCantCuotas(), 
               fechaFin, fechaCarga);
           
           datosSafDTO.add(dsDTO);
         } 
       } 
     } 
     
     return datosSafDTO;
   }
   
   public void generarCertificacionHaberes(CertificacionDTO cDTO, String cfuOrg, HttpServletRequest request) throws IOException, ParseException {
     Saf s = this.sRepo.findByCfuOrganismo(cfuOrg.toUpperCase());
     Entidad e = (Entidad)this.eRepo.findOne(Integer.valueOf(cDTO.getCodigoEntidad()));
     
     Parametro param = this.paService.findByNombreParametro(MEMBRETE_ANIO);
     String membreteAnio = (param != null && "A".equals(param.getEstado())) ? param.getDescripcion() : "";
     
     param = this.paService.findByNombreParametro(NRO_DECRETO);
     String nroDecreto = (param != null && "A".equals(param.getEstado())) ? ("(" + param.getDescripcion() + ")") : "";
     
     param = this.paService.findByNombreParametro(FECHA_DECRETO);
     String fechaDecreto = (param != null && "A".equals(param.getEstado())) ? param.getDescripcion() : "5 de enero de 2012";
 
     
     CodigoDescuento cd = this.cRepo.getCodigoDescuentoByEntidadAndTipoDescuento(e.getCodigoEntidad(), Integer.valueOf(cDTO.getCodigoDescuento()));
     
     PdfGenerator.generarPdfCertificacionHaberes(cDTO, membreteAnio, s.getOrganismoExt(), cd, nroDecreto, fechaDecreto, e, request);
   }
 
   
   public Integer getPorcentajeProcesamiento(String id) {
     return this.sRepo.getPorcentajeProcesamiento(id);
   }
 
   
   private List<String> obtenerFilas(MultipartFile file) {
     List<String> result = new ArrayList<>();
 
 
     
     try {
       InputStream is = file.getInputStream();
       BufferedReader br = new BufferedReader(new InputStreamReader(is)); String line;
       while ((line = br.readLine()) != null) {
         result.add(line);
       }
       return result;
     } catch (IOException e) {
       String line; return null;
     } 
   }
   
   private String nombreValido(String nombre, Integer nSaf) {
     String mensaje = "";
     
     String nom = nombre.replace(".csv", "");
     
     if (nom.length() != 10 || (nom.split("_")).length != 2) {
       return "El nombre del archivo no cumple con el formato válido.";
     }
     
     Boolean valido = Boolean.TRUE;
     Boolean parte1 = Boolean.FALSE;
     Boolean parte2 = Boolean.FALSE;
     
     if (!nSaf.toString().equals(nom.split("_")[0])) {
       
       valido = Boolean.FALSE;
       parte1 = Boolean.TRUE;
     } 
     
     if (Integer.valueOf(nom.split("_")[1].substring(0, 4)).intValue() < 2018) {
       
       valido = Boolean.FALSE;
       parte2 = Boolean.TRUE;
     } 
     
     if (Integer.valueOf(nom.split("_")[1].substring(4, 6)).intValue() < 0 || 
       Integer.valueOf(nom.split("_")[1].substring(4, 6)).intValue() > 12) {
       
       valido = Boolean.FALSE;
       parte2 = Boolean.TRUE;
     } 
     
     if (!valido.booleanValue()) {
       mensaje = "El nombre del archivo no cumple con el formato válido";
       if (parte1.booleanValue()) {
         mensaje = mensaje.concat(":<br><font style=\"text-indent: 25px;\">     El saf del nombre del archivo no coincide con el del usuario.");
       }
       if (parte2.booleanValue()) {
         mensaje = mensaje.concat("<br><font style=\"text-indent: 25px;\">     El período del nombre del archivo no es válido.");
       }
     } 
 
     
     return mensaje;
   }
 }
