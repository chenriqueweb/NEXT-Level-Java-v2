package br.com.henrique.model;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class FaixasCEPMicrozona {

    @EmbeddedId
    @ApiModelProperty(value = "Chave para Rota Faixa de CEP Microzona", required = true)
    private FaixasCEPMicrozonaPK faixasCEPMicrozonaPK;
    
    @NotNull(message = "O campo não pode ser nulo")
    //@Pattern(regexp = "\\d{5}-\\d{3})")
    @ApiModelProperty(value = "CEP Inicial", required = true)
    private Integer CEPinicial;
    
    @NotNull(message = "O campo não pode ser nulo")
    //@Pattern(regexp = "\\d{5}-\\d{3})")
    @ApiModelProperty(value = "CEP Final", required = true)
    private Integer CEPfinal;
    
    // Método para identificar registro novo
    public boolean isNovo() {
        return CEPinicial == 0;
    }

    // Construtor
    public FaixasCEPMicrozona() {
        super();
    }

	public FaixasCEPMicrozona(FaixasCEPMicrozonaPK faixasCEPMicrozonaPK, Integer cEPinicial, Integer cEPfinal) {
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
	public Integer getCEPinicial() {
		return CEPinicial;
	}
	public void setCEPinicial(Integer cEPinicial) {
		CEPinicial = cEPinicial;
	}
	public Integer getCEPfinal() {
		return CEPfinal;
	}
	public void setCEPfinal(Integer cEPfinal) {
		CEPfinal = cEPfinal;
	}

	@Override
	public int hashCode() {
		return Objects.hash(CEPfinal, CEPinicial, faixasCEPMicrozonaPK);
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
		return Objects.equals(CEPfinal, other.CEPfinal) && Objects.equals(CEPinicial, other.CEPinicial)
				&& Objects.equals(faixasCEPMicrozonaPK, other.faixasCEPMicrozonaPK);
	}

}
