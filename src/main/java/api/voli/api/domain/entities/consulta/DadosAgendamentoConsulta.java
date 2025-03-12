package api.voli.api.domain.entities.consulta;

import api.voli.api.domain.entities.Especialidade;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long id,

        @NotNull
        Long idPaciente,

        @NotNull
        Long idMedico,

        //@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        @NotNull
        @Future
        LocalDateTime data,

        Especialidade especialidade
) {
}
