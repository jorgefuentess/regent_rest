package com.regent.util;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.stereotype.Service;

import com.regent.util.interfaces.FileUtil;

@Service
public class FileUtilImpl implements FileUtil {
  public void limpiarZip() {
    ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
    execService.scheduleAtFixedRate(() -> {
          if (!Config.getLocal().booleanValue()) {
            String zipDir = "/home/registro/zip/";
            File zipFolder = new File(zipDir);
            try {
              FileUtils.cleanDirectory(zipFolder);
            } catch (IOException e) {
              e.printStackTrace();
            } 
          } 
        },0L, 86400000L, TimeUnit.MILLISECONDS);
  }
  
  public void limpiarCertif() {
    ScheduledExecutorService execService = Executors.newScheduledThreadPool(5);
    execService.scheduleAtFixedRate(() -> {
          if (!Config.getLocal().booleanValue()) {
            String certifDir = "/home/registro/certificaciones/";
            File certifFolder = new File(certifDir);
            try {
              FileUtils.cleanDirectory(certifFolder);
            } catch (IOException e) {
              e.printStackTrace();
            } 
          } 
        },0L, 86400000L, TimeUnit.MILLISECONDS);
  }
  
  public void limpiarSolicitud(Long cuit) throws IOException {
    String solicitudDir = "/home/registro/pdf/" + cuit.toString() + "/";
    File solicitudFolder = new File(solicitudDir);
    FileUtils.cleanDirectory(solicitudFolder);
  }
}

