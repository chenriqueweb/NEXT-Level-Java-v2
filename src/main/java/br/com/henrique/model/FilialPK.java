package br.com.henrique.model;

 import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import io.swagger.annotations.ApiModelProperty;

@Embeddable
public class FilialPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @ApiModelProperty(value = "Código da Empresa", required = true)
    private Integer codigoEmpresa;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Código da Filial", required = true)
    private Integer codigoFilial;
    
    public FilialPK() {
        super();
    }
    
    public FilialPK(Integer codigoEmpresa, Integer codigoFilial) {
        super();
        this.codigoEmpresa = codigoEmpresa;
        this.codigoFilial = codigoFilial;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoEmpresa == null) ? 0 : codigoEmpresa.hashCode());
        result = prime * result + ((codigoFilial == null) ? 0 : codigoFilial.hashCode());
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
        FilialPK other = (FilialPK) obj;
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
        return true;
    }
    
}
