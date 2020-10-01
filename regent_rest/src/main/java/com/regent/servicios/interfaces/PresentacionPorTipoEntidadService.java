package com.regent.servicios.interfaces;

import com.regent.dtos.PresentacionPorTipoEntidadDTO;
import java.util.Collection;

public interface PresentacionPorTipoEntidadService {
  Collection<PresentacionPorTipoEntidadDTO> getPresentaciones();
}
