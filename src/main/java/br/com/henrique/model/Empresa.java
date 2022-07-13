package br.com.henrique.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empresa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
    
    private String razaoSocial;
    private String raizCNPJ;
    private String dataAbertura;
    
    // Construtor da Classe
    public Empresa() {
        super();
    }
    
    public Empresa(Integer codigo, String razaoSocial, String raizCNPJ, String dataAbertura) {
        super();
        this.razaoSocial = razaoSocial;
        this.raizCNPJ = raizCNPJ;
        this.dataAbertura = dataAbertura;
    }
    
    // Método para identificar registro novo
    public boolean isNovo() {
        return razaoSocial == null;
    } 
    
    public Integer getCodigo() {
        return codigo;
    }
    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }
    public String getRaizCNPJ() {
        return raizCNPJ;
    }
    public void setRaizCNPJ(String raizCNPJ) {
        this.raizCNPJ = raizCNPJ;
    }
    public String getDataAbertura() {
        return dataAbertura;
    }
    public void setDataAbertura(String dataAbertura) {
        this.dataAbertura = dataAbertura;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
        Empresa other = (Empresa) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }
}

