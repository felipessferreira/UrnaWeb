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
@Table(name = "PRESIDENTE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Presidente.findAll", query = "SELECT p FROM Presidente p")
    , @NamedQuery(name = "Presidente.findByNumero", query = "SELECT p FROM Presidente p WHERE p.numero = :numero")
    , @NamedQuery(name = "Presidente.findByNomepresidente", query = "SELECT p FROM Presidente p WHERE p.nomepresidente = :nomepresidente")
    , @NamedQuery(name = "Presidente.findByVice", query = "SELECT p FROM Presidente p WHERE p.vice = :vice")
    , @NamedQuery(name = "Presidente.findByPartido", query = "SELECT p FROM Presidente p WHERE p.partido = :partido")
    , @NamedQuery(name = "Presidente.findBySlogan", query = "SELECT p FROM Presidente p WHERE p.slogan = :slogan")
    , @NamedQuery(name = "Presidente.findByVotos", query = "SELECT p FROM Presidente p WHERE p.votos = :votos")})
public class Presidente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUMERO")
    private Integer numero;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOMEPRESIDENTE")
    private String nomepresidente;
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

    public Presidente() {
    }

    public Presidente(Integer numero) {
        this.numero = numero;
    }

    public Presidente(Integer numero, String nomepresidente, String vice, String partido, String slogan) {
        this.numero = numero;
        this.nomepresidente = nomepresidente;
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

    public String getNomepresidente() {
        return nomepresidente;
    }

    public void setNomepresidente(String nomepresidente) {
        this.nomepresidente = nomepresidente;
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
        if (!(object instanceof Presidente)) {
            return false;
        }
        Presidente other = (Presidente) object;
        if ((this.numero == null && other.numero != null) || (this.numero != null && !this.numero.equals(other.numero))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Presidente[ numero=" + numero + " ]";
    }

}
