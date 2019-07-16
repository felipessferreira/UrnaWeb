package entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "GOVERNADOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Governador.findAll", query = "SELECT g FROM Governador g")
    , @NamedQuery(name = "Governador.findByNumero", query = "SELECT g FROM Governador g WHERE g.numero = :numero")
    , @NamedQuery(name = "Governador.findByNomegovernador", query = "SELECT g FROM Governador g WHERE g.nomegovernador = :nomegovernador")
    , @NamedQuery(name = "Governador.findByVice", query = "SELECT g FROM Governador g WHERE g.vice = :vice")
    , @NamedQuery(name = "Governador.findByPartido", query = "SELECT g FROM Governador g WHERE g.partido = :partido")
    , @NamedQuery(name = "Governador.findBySlogan", query = "SELECT g FROM Governador g WHERE g.slogan = :slogan")
    , @NamedQuery(name = "Governador.findByVotos", query = "SELECT g FROM Governador g WHERE g.votos = :votos")})
public class Governador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMEGOVERNADOR")
    private String nomegovernador;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "VICE")
    private String vice;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "PARTIDO")
    private String partido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "SLOGAN")
    private String slogan;
    @Column(name = "VOTOS")
    private Integer votos;

    public Governador() {
    }

    public Governador(Integer numero) {
        this.numero = numero;
    }

    public Governador(Integer numero, String nomegovernador, String vice, String partido, String slogan) {
        this.numero = numero;
        this.nomegovernador = nomegovernador;
        this.vice = vice;
        this.partido = partido;
        this.slogan = slogan;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getNomegovernador() {
        return nomegovernador;
    }

    public void setNomegovernador(String nomegovernador) {
        this.nomegovernador = nomegovernador;
    }

    public String getVice() {
        return vice;
    }

    public void setVice(String vice) {
        this.vice = vice;
    }

    public String getPartido() {
        return partido;
    }

    public void setPartido(String partido) {
        this.partido = partido;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public Integer getVotos() {
        return votos;
    }

    public void setVotos(Integer votos) {
        this.votos = votos;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numero != null ? numero.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Governador)) {
            return false;
        }
        Governador other = (Governador) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }
 
    @Override
    public String toString() {
        return "entidades.Governador[ numero=" + numero + " ]";
    }

}
