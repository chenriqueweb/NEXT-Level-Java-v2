package br.com.henrique.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Estado {
	
    @Id
    @NotEmpty
    @NotNull
    @Column(columnDefinition = "Character(2)")
    @ApiModelProperty(value = "Sigla da Unidade Federativa", required = true)
    private String sigla;
    
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @NotNull(message = "O campo não pode ser nulo")
    @Size(min=4, max=50, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "Nome do Estado", required = true)
    private String nome;

    // Construtores da Class
    public Estado() {
        super();
    }
    
    public Estado(String sigla, String nome) {
        super();
        this.sigla = sigla;
        this.nome = nome;
    }
    
    // Método para identificar registro novo
    public boolean isNovo() {
        return sigla == null;
    }
        
    public String getSigla() {
        return sigla;
    }
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((sigla == null) ? 0 : sigla.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Estado other = (Estado) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (sigla == null) {
            if (other.sigla != null)
                return false;
        } else if (!sigla.equals(other.sigla))
            return false;
        return true;
    }
}
