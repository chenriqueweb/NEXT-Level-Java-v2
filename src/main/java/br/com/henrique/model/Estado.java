package br.com.henrique.model;

import java.util.ResourceBundle;

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
    
    //@NotEmpty(message = "Você deve informar algo em Nome do Estado")
    @NotEmpty(message = "{NotEmpty.nome}")
    @NotNull(message = "{NotNull.nome}")
    @Size(min=4, max=50, message="Informe um valor entre 4 e 50 bytes em Nome do Estado")
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
