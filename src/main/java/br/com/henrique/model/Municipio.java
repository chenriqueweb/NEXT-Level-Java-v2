package br.com.henrique.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Municipio {
    
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)  // Usando sequencia do DB2
    @ApiModelProperty(value = "Código do Município", required = true)
    private Integer codigo_ID;
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "O campo é obrigatório é não foi preenchido")
    @Size(min=4, max=50, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "Nome do Município", required = true)
    private String nome;
    
    // FK com Estado
    @ManyToOne
    @JoinColumn(name="sigla")
    private Estado estado;
    
    public Municipio() {
        super();
    }

    public Municipio(Integer codigo_ID, String nome, Estado estado) {
        super();
        this.codigo_ID = codigo_ID;
        this.nome = nome;
        this.estado = estado;
    }

    // Método para identificar registro novo
    public boolean isNovo() {
        return nome == null;
    }    
    
    public Integer getCodigo_ID() {
        return codigo_ID;
    }
    public void setCodigo_ID(Integer codigo_ID) {
        this.codigo_ID = codigo_ID;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo_ID == null) ? 0 : codigo_ID.hashCode());
        result = prime * result + ((estado == null) ? 0 : estado.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Municipio other = (Municipio) obj;
        if (codigo_ID == null) {
            if (other.codigo_ID != null)
                return false;
        } else if (!codigo_ID.equals(other.codigo_ID))
            return false;
        if (estado == null) {
            if (other.estado != null)
                return false;
        } else if (!estado.equals(other.estado))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        return true;
    }

}
