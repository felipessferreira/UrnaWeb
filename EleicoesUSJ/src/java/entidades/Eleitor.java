/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "ELEITOR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Eleitor.findAll", query = "SELECT e FROM Eleitor e")
    , @NamedQuery(name = "Eleitor.findByCpf", query = "SELECT e FROM Eleitor e WHERE e.cpf = :cpf")
    , @NamedQuery(name = "Eleitor.findByNome", query = "SELECT e FROM Eleitor e WHERE e.nome = :nome")
    , @NamedQuery(name = "Eleitor.findBySenha", query = "SELECT e FROM Eleitor e WHERE e.senha = :senha")
    , @NamedQuery(name = "Eleitor.findByVotou", query = "SELECT e FROM Eleitor e WHERE e.votou = :votou")})
public class Eleitor implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "CPF")
    private Integer cpf;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "NOME")
    private String nome;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "SENHA")
    private String senha;
    @Column(name = "VOTOU")
    private Integer votou;

    public Eleitor() {
    }

    public Eleitor(Integer cpf) {
        this.cpf = cpf;
    }

    public Eleitor(Integer cpf, String nome, String senha) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
    }

    public Integer getCpf() {
        return cpf;
    }

    public void setCpf(Integer cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getVotou() {
        return votou;
    }

    public void setVotou(Integer votou) {
        this.votou = votou;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cpf != null ? cpf.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Eleitor)) {
            return false;
        }
        Eleitor other = (Eleitor) object;
        if ((this.cpf == null && other.cpf != null) || (this.cpf != null && !this.cpf.equals(other.cpf))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entidades.Eleitor[ cpf=" + cpf + " ]";
    }
    
}
