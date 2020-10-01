package com.regent.servicios.interfaces;

import com.regent.dtos.MotivoNotificacionDTO;
import java.util.Collection;

public interface MotivoNotificacionService {
  Collection<MotivoNotificacionDTO> getAllMotivoNotificacion();
  
  void nuevoMotivoNotificacion(MotivoNotificacionDTO paramMotivoNotificacionDTO, String paramString);
  
  void updateMotivoNotificacion(MotivoNotificacionDTO paramMotivoNotificacionDTO, String paramString);
}