package beans;

import entidades.Eleitor;
import entidades.Governador;
import entidades.Prefeito;
import entidades.Presidente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ManagedBean;
import negocio.EleitorService;
import negocio.GovernadorService;
import negocio.PrefeitoService;
import negocio.PresidenteService;

@ManagedBean
@SessionScoped
public class MenuBean implements Serializable {
    
    //Entidade eleitor para usar como base nos metodos
    //Ela esta static para setar os valores do eleitor lá do LoginBean
    //Não posso estanciar o Login Bean por que o Eleitor não vai vir com os valores setados
    //Dou somente um MenuBean.e para referenciar esse eleitor
    //Auxilio nesta parte
    public static Eleitor e = new Eleitor();
    
    //Semelhante as classes DAO que usavamos no desktop
    //Você cria metodos lá e envia as informações por aq
    //Por exemplo es.metodoCriadoNoEleitorService(e);
    //O "e" é o eleitor criado acima
    //Vc envia o eleitor e faz as regras de negócio la
    EleitorService es = new EleitorService();
    
    //Retorno do numero do candidato associado a bolinha de voto
    private Integer numeroPresidente;
    private Integer numeroGovernador;
    private Integer numeroPrefeito;
    
    //Booleanos para desativar o voto associado a propiedade disable das bolinhas de voto
    //Setando disable como false elas aparecem, setando como verdadeiro bloqueiam
    private Boolean votouPredidente = false;
    private Boolean votouGovernador = false;
    private Boolean votouPrefeito = false;
    private Boolean btnVotar = false;

    
    
    //Metodos "DAO" para funções dos cargos. Só tem um método em cada que
    //faz uma busca de todos os eleitores por ordem de voto
    
    PresidenteService ps = new PresidenteService();
    GovernadorService gs = new GovernadorService();
    PrefeitoService prs = new PrefeitoService();

    //Lugar para armazenar o retorno das listas
    private List<Presidente> listaPresidentes = new ArrayList<>();
    private List<Governador> listaGovernadores = new ArrayList<>();
    private List<Prefeito> listaPrefeitos = new ArrayList<>();
    
    public MenuBean() {
        //Para quando  startar a pagina, ele já desabilitar a votação se vcja votou
        VerificarVotou();
        listaPresidentes = ps.buscarPresidentes();
        listaGovernadores = gs.buscarGovernadores();
        listaPrefeitos = prs.buscarPrefeitos();
    }
    //Atualizar as listas
    public void att(){
        listaPresidentes = ps.buscarPresidentes();
        listaGovernadores = gs.buscarGovernadores();
        listaPrefeitos = prs.buscarPrefeitos();
    }
    //Verificar e desabilita os campos se vc já votou ou não
    public void VerificarVotou(){
       
        //criar coluna de boolean
        //No caso 1=true e 0=false
        //esse es.verificarVotou retorna o valor do banco
        //Ai eu comparo e vejo se é igual a 1
        //Por padrão o votPres ali já ta como falso
        boolean votPres;
        votPres = es.verificarVotou(e).getVotou() ==1;
        
        //Desabilitando ou habilitando os paineis de voto junto com o botão votar
        votouPredidente = votPres;
        votouGovernador = votPres;
        votouPrefeito = votPres;
        btnVotar = votPres;
    }
    //Muda o valor do Votou do eleitor
    //Basicamente faz com que o eleitor não possa votar mais
    public void attVotou(){
        e.setVotou(es.votou(e).getVotou());
        boolean votPres;
        votPres = e.getVotou() == 1;
        votouPredidente = votPres;
        votouGovernador = votPres;
        votouPrefeito = votPres;
        btnVotar = votPres;
        
        
    }
    public void votarPresidente() {
        //Envia o numero do candidato escolhido nas bolinha
        //Esse metodo n gera retorno
        ps.votarPresidente(numeroPresidente);
    }

    public void votarGovernador() {
        //Mesma coisa que o presidente
        gs.votarGovernador(numeroGovernador);
    }

    public void votarPrefeito() {
        //Mesa coisa que o presidente
        prs.votarPrefeito(numeroPrefeito);
    }

    public void votar() {
        //junta  os 4 metodos de cima... Vota nos 3 escolhidos e desabilita a votação
        votarPresidente();
        votarGovernador();
        votarPrefeito();
        attVotou();
        
    }
    //Getters and Setters

    public Boolean getVotouPredidente() {
        return votouPredidente;
    }

    public void setVotouPredidente(Boolean votouPredidente) {
        this.votouPredidente = votouPredidente;
    }

    public Boolean getVotouGovernador() {
        return votouGovernador;
    }

    public void setVotouGovernador(Boolean votouGovernador) {
        this.votouGovernador = votouGovernador;
    }

    public Boolean getVotouPrefeito() {
        return votouPrefeito;
    }

    public void setVotouPrefeito(Boolean votouPrefeito) {
        this.votouPrefeito = votouPrefeito;
    }
    
    public void setNumeroPresidente(Integer num) {
        this.numeroPresidente = num;
    }

    public Integer getNumeroPresidente() {
        return numeroPresidente;
    }
    public void setNumeroGovernador(Integer num) {
        this.numeroGovernador = num;
    }

    public Integer getNumeroGovernador() {
        return numeroGovernador;
    }
    public void setNumeroPrefeito(Integer num) {
        this.numeroPrefeito = num;
    }

    public Integer getNumeroPrefeito() {
        return numeroPrefeito;
    }
    public List<Presidente> getListaPresidentes() {
        return listaPresidentes;
    }

    public void setListaPresidentes(List<Presidente> listaPresidentes) {
        this.listaPresidentes = listaPresidentes;
    }

    public List<Governador> getListaGovernadores() {
        return listaGovernadores;
    }

    public void setListaGovernadores(List<Governador> listaGovernadores) {
        this.listaGovernadores = listaGovernadores;
    }

    public List<Prefeito> getListaPrefeitos() {
        return listaPrefeitos;
    }

    public void setListaPrefeitos(List<Prefeito> listaPrefeitos) {
        this.listaPrefeitos = listaPrefeitos;
    }

    public Eleitor getE() {
        return e;
    }

    public void setE(Eleitor e) {
        this.e = e;
    }

    public Boolean getBtnVotar() {
        return btnVotar;
    }

    public void setBtnVotar(Boolean btnVotar) {
        this.btnVotar = btnVotar;
    }
    
}
