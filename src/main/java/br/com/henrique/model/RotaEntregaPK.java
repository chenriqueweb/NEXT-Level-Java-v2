package br.com.henrique.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class RotaEntregaPK  implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private String siglaEstado;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoRota;
       
    public RotaEntregaPK() {
        super();
    }
    
    public RotaEntregaPK(String siglaEstado, Integer codigoRota) {
        super();
        this.siglaEstado = siglaEstado;
        this.codigoRota = codigoRota;
    }


    public String getSiglaEstado() {
        return siglaEstado;
    }
    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
    public Integer getCodigoRota() {
        return codigoRota;
    }
    public void setCodigoRota(Integer codigoRota) {
        this.codigoRota = codigoRota;
    }
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoRota == null) ? 0 : codigoRota.hashCode());
        result = prime * result + ((siglaEstado == null) ? 0 : siglaEstado.hashCode());
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
        RotaEntregaPK other = (RotaEntregaPK) obj;
        if (codigoRota == null) {
            if (other.codigoRota != null)
                return false;
        } else if (!codigoRota.equals(other.codigoRota))
            return false;
        if (siglaEstado == null) {
            if (other.siglaEstado != null)
                return false;
        } else if (!siglaEstado.equals(other.siglaEstado))
            return false;
        return true;
    }
}       