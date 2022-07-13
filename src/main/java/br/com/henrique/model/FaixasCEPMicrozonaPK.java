package br.com.henrique.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class FaixasCEPMicrozonaPK implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoMicrozona;
    
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigoSequencial;

    // Construtor
    public FaixasCEPMicrozonaPK() {
        super();
    }

    public FaixasCEPMicrozonaPK(Integer codigoMicrozona, Integer codigoSequencial) {
        super();
        this.codigoMicrozona = codigoMicrozona;
        this.codigoSequencial = codigoSequencial;
    }

    public Integer getCodigoMicrozona() {
        return codigoMicrozona;
    }
    public void setCodigoMicrozona(Integer codigoMicrozona) {
        this.codigoMicrozona = codigoMicrozona;
    }
    public Integer getCodigoSequencial() {
        return codigoSequencial;
    }
    public void setCodigoSequencial(Integer codigoSequencial) {
        this.codigoSequencial = codigoSequencial;
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigoMicrozona == null) ? 0 : codigoMicrozona.hashCode());
        result = prime * result + ((codigoSequencial == null) ? 0 : codigoSequencial.hashCode());
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
        FaixasCEPMicrozonaPK other = (FaixasCEPMicrozonaPK) obj;
        if (codigoMicrozona == null) {
            if (other.codigoMicrozona != null)
                return false;
        } else if (!codigoMicrozona.equals(other.codigoMicrozona))
            return false;
        if (codigoSequencial == null) {
            if (other.codigoSequencial != null)
                return false;
        } else if (!codigoSequencial.equals(other.codigoSequencial))
            return false;
        return true;
    }
    
}
