package br.com.henrique.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Microzona {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;
        
    private String nome;
    private String status;
    private String atendimentoDiario;
    private String atendeSegunda;
    private String atendeTerca;
    private String atendeQuarta;
    private String atendeQuinta;
    private String atendeSexta;
    private String atendeSabado;
    
    // FK com Estado
    @ManyToOne
    @JoinColumn(name="sigla")    
    private Estado estadoRota;
    
    // FK com Municipio 
    @ManyToOne
    @JoinColumn(name="codigo_ID")
    private Municipio codigoMunicipio;
        
    
    private Integer codigoRota;

    public Microzona() {
        super();
    }

    public Microzona(Integer codigo, String nome, String status, String atendimentoDiario, String atendeSegunda, String atendeTerca,
                    String atendeQuarta, String atendeQuinta, String atendeSexta, String atendeSabado, Estado estadoRota,
                    Municipio codigoMunicipio, Integer codigoRota) {
        super();
        this.codigo = codigo;
        this.nome = nome;
        this.status = status;
        this.atendimentoDiario = atendimentoDiario;
        this.atendeSegunda = atendeSegunda;
        this.atendeTerca = atendeTerca;
        this.atendeQuarta = atendeQuarta;
        this.atendeQuinta = atendeQuinta;
        this.atendeSexta = atendeSexta;
        this.atendeSabado = atendeSabado;
        this.estadoRota = estadoRota;
        this.codigoMunicipio = codigoMunicipio;
        this.codigoRota = codigoRota;
    }

    // Método para identificar registro novo
    public boolean isNovo() {
        return nome == null;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
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

    public String getAtendimentoDiario() {
        return atendimentoDiario;
    }

    public void setAtendimentoDiario(String atendimentoDiario) {
        this.atendimentoDiario = atendimentoDiario;
    }

    public String getAtendeSegunda() {
        return atendeSegunda;
    }

    public void setAtendeSegunda(String atendeSegunda) {
        this.atendeSegunda = atendeSegunda;
    }

    public String getAtendeTerca() {
        return atendeTerca;
    }

    public void setAtendeTerca(String atendeTerca) {
        this.atendeTerca = atendeTerca;
    }

    public String getAtendeQuarta() {
        return atendeQuarta;
    }

    public void setAtendeQuarta(String atendeQuarta) {
        this.atendeQuarta = atendeQuarta;
    }

    public String getAtendeQuinta() {
        return atendeQuinta;
    }

    public void setAtendeQuinta(String atendeQuinta) {
        this.atendeQuinta = atendeQuinta;
    }

    public String getAtendeSexta() {
        return atendeSexta;
    }

    public void setAtendeSexta(String atendeSexta) {
        this.atendeSexta = atendeSexta;
    }

    public String getAtendeSabado() {
        return atendeSabado;
    }

    public void setAtendeSabado(String atendeSabado) {
        this.atendeSabado = atendeSabado;
    }

    public Estado getEstadoRota() {
        return estadoRota;
    }

    public void setEstadoRota(Estado estadoRota) {
        this.estadoRota = estadoRota;
    }

    public Municipio getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipio codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
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
        result = prime * result + ((atendeQuarta == null) ? 0 : atendeQuarta.hashCode());
        result = prime * result + ((atendeQuinta == null) ? 0 : atendeQuinta.hashCode());
        result = prime * result + ((atendeSabado == null) ? 0 : atendeSabado.hashCode());
        result = prime * result + ((atendeSegunda == null) ? 0 : atendeSegunda.hashCode());
        result = prime * result + ((atendeSexta == null) ? 0 : atendeSexta.hashCode());
        result = prime * result + ((atendeTerca == null) ? 0 : atendeTerca.hashCode());
        result = prime * result + ((atendimentoDiario == null) ? 0 : atendimentoDiario.hashCode());
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
        result = prime * result + ((codigoMunicipio == null) ? 0 : codigoMunicipio.hashCode());
        result = prime * result + ((codigoRota == null) ? 0 : codigoRota.hashCode());
        result = prime * result + ((estadoRota == null) ? 0 : estadoRota.hashCode());
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
        Microzona other = (Microzona) obj;
        if (atendeQuarta == null) {
            if (other.atendeQuarta != null)
                return false;
        } else if (!atendeQuarta.equals(other.atendeQuarta))
            return false;
        if (atendeQuinta == null) {
            if (other.atendeQuinta != null)
                return false;
        } else if (!atendeQuinta.equals(other.atendeQuinta))
            return false;
        if (atendeSabado == null) {
            if (other.atendeSabado != null)
                return false;
        } else if (!atendeSabado.equals(other.atendeSabado))
            return false;
        if (atendeSegunda == null) {
            if (other.atendeSegunda != null)
                return false;
        } else if (!atendeSegunda.equals(other.atendeSegunda))
            return false;
        if (atendeSexta == null) {
            if (other.atendeSexta != null)
                return false;
        } else if (!atendeSexta.equals(other.atendeSexta))
            return false;
        if (atendeTerca == null) {
            if (other.atendeTerca != null)
                return false;
        } else if (!atendeTerca.equals(other.atendeTerca))
            return false;
        if (atendimentoDiario == null) {
            if (other.atendimentoDiario != null)
                return false;
        } else if (!atendimentoDiario.equals(other.atendimentoDiario))
            return false;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        if (codigoMunicipio == null) {
            if (other.codigoMunicipio != null)
                return false;
        } else if (!codigoMunicipio.equals(other.codigoMunicipio))
            return false;
        if (codigoRota == null) {
            if (other.codigoRota != null)
                return false;
        } else if (!codigoRota.equals(other.codigoRota))
            return false;
        if (estadoRota == null) {
            if (other.estadoRota != null)
                return false;
        } else if (!estadoRota.equals(other.estadoRota))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (status == null) {
            if (other.status != null)
                return false;
        } else if (!status.equals(other.status))
            return false;
        return true;
    }

}
