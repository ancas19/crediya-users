package co.com.crediya.users.model.commos.models;


import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Setter
@Getter
@SuperBuilder()
@NoArgsConstructor
@AllArgsConstructor
public class AbstractAuditoria {
    private String usuarioCreacion;
    private String usuarioActualizacion;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
