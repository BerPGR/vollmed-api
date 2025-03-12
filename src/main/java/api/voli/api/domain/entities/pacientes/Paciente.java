package api.voli.api.domain.entities.pacientes;

import api.voli.api.domain.entities.Especialidade;
import api.voli.api.domain.entities.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name="pacientes")
@Entity(name="Paciente")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;
}
