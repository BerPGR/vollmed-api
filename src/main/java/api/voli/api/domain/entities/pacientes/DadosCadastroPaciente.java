package api.voli.api.domain.entities.pacientes;

import api.voli.api.domain.entities.endereco.DadosEndereco;

public record DadosCadastroPaciente(String nome,
                                    String email,
                                    String telefone,
                                    String cpf,
                                    DadosEndereco endereco) {
}
