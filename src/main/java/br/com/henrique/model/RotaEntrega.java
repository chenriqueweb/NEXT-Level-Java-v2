package br.com.henrique.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class RotaEntrega {
    
    @EmbeddedId
    @ApiModelProperty(value = "Chave para Rota de Entrega", required = true)
    private RotaEntregaPK rotaEntregaPK;    
    
    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "Você precisa informar algo")
    @Size(min=5, max=50, message="Tamanho inválido! Digite no mínimo {min} e no máximo {max} caracteres")
    @ApiModelProperty(value = "Nome da Rota de Entrega", required = true)
    private String nome;

    @NotNull(message = "O campo não pode ser nulo")
    @NotEmpty(message = "Você precisa informar algo")
    @Size(min=1, max=1)
    @ApiModelProperty(value = "Status da Rota de Entrega", required = true)
    private String status;
    
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "Código da Empresa", required = true)
    private Integer codigoEmpresa;
    
    @NotNull(message = "O campo não pode ser nulo")
    @ApiModelProperty(value = "Código da Filial", required = true)
    private Integer codigoFilial;
    
    @ApiModelProperty(value = "Prazo para Expedição", required = false)
    private Integer prazoExpedicao;
    
    // FK com Estado
    @ManyToOne
    @JoinColumn(name="rotaEntregaPK.siglaEstado", referencedColumnName="sigla") 
    private Estado estado; 
    
    // FK com Filial
    @ManyToOne
    @JoinColumns({@JoinColumn(updatable = false, insertable = false, 
                                    name = "filialPK.codigoEmpresa", referencedColumnName = "codigoEmpresa"), 
                  @JoinColumn(updatable = false, insertable = false,
                                    name = "filialPK.codigoFilial",  referencedColumnName = "codigoFilial")
                 })  
    private Filial filial;    
    
    
    public RotaEntrega() {
        super();
    }
    
    public RotaEntrega(RotaEntregaPK rotaEntregaPK, String nome, String status, Integer codigoEmpresa, Integer codigoFilial,
                    Integer prazoExpedicao) {
        super();
        this.rotaEntregaPK = rotaEntregaPK;
        this.nome = nome;
        this.status = status;
        this.codigoEmpresa = codigoEmpresa;
        this.codigoFilial = codigoFilial;
        this.prazoExpedicao = prazoExpedicao;
    }

    // MÃ©todo para identificar registro novo
    public boolean isNovo() {
        return nome == null;
    }

    public RotaEntregaPK getRotaEntregaPK() {
        return rotaEntregaPK;
    }
    public void setRotaEntregaPK(RotaEntregaPK rotaEntregaPK) {
        this.rotaEntregaPK = rotaEntregaPK;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public Integer getCodigoEmpresa() {
        return codigoEmpresa;
    }
    public void setCodigoEmpresa(Integer codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }
    public Integer getCodigoFilial() {
        return codigoFilial;
    }
    public void setCodigoFilial(Integer codigoFilial) {
        this.codigoFilial = codigoFilial;
    }
    public Integer getPrazoExpedicao() {
        return prazoExpedicao;
    }
    public void setPrazoExpedicao(Integer prazoExpedicao) {
        this.prazoExpedicao = prazoExpedicao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoEmpresa == null) ? 0 : codigoEmpresa.hashCode());
        result = prime * result + ((codigoFilial == null) ? 0 : codigoFilial.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((prazoExpedicao == null) ? 0 : prazoExpedicao.hashCode());
        result = prime * result + ((rotaEntregaPK == null) ? 0 : rotaEntregaPK.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
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
        RotaEntrega other = (RotaEntrega) obj;
        if (codigoEmpresa == null) {
            if (other.codigoEmpresa != null)
                return false;
        } else if (!codigoEmpresa.equals(other.codigoEmpresa))
            return false;
        if (codigoFilial == null) {
            if (other.codigoFilial != null)
                return false;
        } else if (!codigoFilial.equals(other.codigoFilial))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (prazoExpedicao == null) {
            if (other.prazoExpedicao != null)
                return false;
        } else if (!prazoExpedicao.equals(other.prazoExpedicao))
            return false;
        if (rotaEntregaPK == null) {
            if (other.rotaEntregaPK != null)
                return false;
        } else if (!rotaEntregaPK.equals(other.rotaEntregaPK))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }         

}
