package br.com.henrique.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class FaixasCEPMicrozona {

    @EmbeddedId
    private FaixasCEPMicrozonaPK faixasCEPMicrozonaPK;
    
    private int CEPinicial;
    private int CEPfinal;
    
    // Método para identificar registro novo
    public boolean isNovo() {
        return CEPinicial == 0;
    }

    // Construtor
    public FaixasCEPMicrozona() {
        super();
    }

    public FaixasCEPMicrozona(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK, int cEPinicial, int cEPfinal) {
        super();
        this.faixasCEPMicrozonaPK = faixasCEPMicrozonaPK;
        CEPinicial = cEPinicial;
        CEPfinal = cEPfinal;
    }

    
    public FaixasCEPMicrozonaPK getFaixasCEPMicrozonaPK() {
        return faixasCEPMicrozonaPK;
    }
    public void setFaixasCEPMicrozonaPK(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK) {
        this.faixasCEPMicrozonaPK = faixasCEPMicrozonaPK;
    }
    public int getCEPinicial() {
        return CEPinicial;
    }
    public void setCEPinicial(int cEPinicial) {
        CEPinicial = cEPinicial;
    }
    public int getCEPfinal() {
        return CEPfinal;
    }
    public void setCEPfinal(int cEPfinal) {
        CEPfinal = cEPfinal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + CEPfinal;
        result = prime * result + CEPinicial;
        result = prime * result + ((faixasCEPMicrozonaPK == null) ? 0 : faixasCEPMicrozonaPK.hashCode());
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
        FaixasCEPMicrozona other = (FaixasCEPMicrozona) obj;
        if (CEPfinal != other.CEPfinal)
            return false;
        if (CEPinicial != other.CEPinicial)
            return false;
        if (faixasCEPMicrozonaPK == null) {
            if (other.faixasCEPMicrozonaPK != null)
                return false;
        } else if (!faixasCEPMicrozonaPK.equals(other.faixasCEPMicrozonaPK))
            return false;
        return true;
    } 
    
}
