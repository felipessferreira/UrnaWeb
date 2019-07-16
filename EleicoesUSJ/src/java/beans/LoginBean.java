package beans;

import entidades.Eleitor;
import java.io.Serializable;
import java.util.Objects;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import negocio.EleitorService;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    //Entidade eleitor para usar como base nos metodos
    //Lá no index.xhtml referenciar os campos assim:
    //#{menuBean.eleitor.AtributoQueEuQueroAssociarAoComponenteEscolhido}
    private Eleitor eleitor = new Eleitor();

    //Semelhante as classes DAO que usavamos no desktop
    //Você cria metodos lá e envia as informações por aq
    
    EleitorService eleitorService = new EleitorService();

    //Valor para habilitar o filtro
    private Boolean estaLogado = false;

    //Campo de confirmação da senha
    private String confirmacaoSenha;

    //Feedback de erro ou sucesso no logar e cadastrar
    private String resposta = "";

    public LoginBean() {
    }

    public String logar() {
        try {
            if (eleitor.getCpf() != null && eleitor.getSenha() != null && Objects.equals(eleitor.getCpf(), eleitorService.buscarEleitor(eleitor).getCpf()) && eleitor.getSenha().equals(eleitorService.buscarEleitor(eleitor).getSenha())) {
                estaLogado = true;
                //Lá no menu bean eu criei um Eleitor statico... Para fazer essa comunicação
                //entre os beans... Ai eu só dou um Menu.e e seto os valores do eleitor de la
                //La no menuBean tem um comentario melhor
                MenuBean.e.setCpf(eleitorService.buscarEleitor(eleitor).getCpf());
                MenuBean.e.setNome(eleitorService.buscarEleitor(eleitor).getNome());
                MenuBean.e.setSenha(eleitorService.buscarEleitor(eleitor).getSenha());
                MenuBean.e.setVotou(eleitorService.buscarEleitor(eleitor).getVotou());

                //Código do profesor... n sei o q faz
                Logger.getLogger(getClass().getName()).info("Eleitor logado com sucesso!");

                //Se colocar somente "/seguranca/menu" mas a página fica travada
                //Ficava com o URL do index.xhtml e não funciona os componentes
                return "/seguranca/menu.xhtml?faces-redirect=true";

            }
        } catch (Exception excessao) {
            //Seta o feedback de erro
            setResposta("ERRO no login!");
            //Ativa o filtro
            estaLogado = false;
            //zera o campo senha
            this.eleitor.setSenha("");
            //Código do proffessor
            Logger.getLogger(getClass().getName()).info("Falha no login!");
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro no Login", "Usuario e/ou senha invalidos.");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            //Retorna a mesma página
            return "/index";
        }
        return "/index";
    }

    public void cadastrar() {
        //Verificação de campos null e confirmação da senha
        if (eleitor.getNome() != null && eleitor.getCpf() != null && eleitor.getSenha() != null && confirmacaoSenha != null && confirmacaoSenha.equals(eleitor.getSenha())) {
            
                eleitor.setCpf(eleitor.getCpf());
                eleitor.setNome(eleitor.getNome());
                eleitor.setSenha(eleitor.getSenha());
                //Explicação do pq 0 e não false em MenuBean
                eleitor.setVotou(0);
                
                //metodo cadastrar retorna um boolean... Se gerar uma excessão retorna falso
                //e se persistir retorna true
                Boolean valor = eleitorService.cadastrar(eleitor);
                if (valor) {
                    setResposta("Cadastrado com sucesso!");
                } else {
                    setResposta("Não foi possivel realizar o cadastro!");
                }
        } else {
            setResposta("Erro no cadastro!");
        }
    }
    
    //Getters and Setters
    
    public Eleitor getEleitor() {
        return eleitor;
    }

    public void setEleitor(Eleitor eleitor) {
        this.eleitor = eleitor;
    }

    public Boolean getEstaLogado() {
        return estaLogado;
    }

    public void setEstaLogado(Boolean estaLogado) {
        this.estaLogado = estaLogado;
    }

    public String getConfirmacaoSenha() {
        return confirmacaoSenha;
    }

    public void setConfirmacaoSenha(String confirmacaoSenha) {
        this.confirmacaoSenha = confirmacaoSenha;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
}
