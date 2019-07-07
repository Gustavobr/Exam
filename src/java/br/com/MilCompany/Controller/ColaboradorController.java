/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.MilCompany.Controller;

import br.com.MilCompany.Model.Colaborador;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author gustavoscipiao
 */
@ManagedBean(name = "controller")
@ViewScoped
public class ColaboradorController {

    /*Implementação métodos com regras de entrada e saídas
    Regras Entrada
    User Interface -> Validators
    -Minimum length
    -Field fill (preenchimento obrigatório
    -Unique
    -Máscara (Regex) campo salário
     */
   
    Colaborador c = new Colaborador();

    public Colaborador getC() {
        return c;
    }

    public void setC(Colaborador c) {
        this.c = c;
    }

    /*
    public static EntityManager getConexao()throws EntityNotFoundException, Exception, RuntimeException{
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mil-AppPU");
            EntityManager        em  = emf.createEntityManager();
            
        }catch(EntityNotFoundException ex){
            System.out.println("Erro ao obter conexao"+ ex.getCause().getMessage().toUpperCase());
        }
       /*finally * { 
}
}
     */
    /**
     *
     * @return @throws java.lang.Exception"
     */
    public List<Colaborador> listar_nome_alfabetico() {
        /*Query HQL */
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mil-AppPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Query query = em.createQuery("SELECT col.id, col.nome, col.idade, col.salario from Colaborador col ORDER BY COL ASC");
        List<Colaborador> lista_ordenada = query.getResultList();
        return lista_ordenada;
    }
    
    public void  atualizarColaborador(long id)throws RuntimeException,EntityNotFoundException{
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mil-AppPU");
        EntityManager em = emf.createEntityManager();
        Colaborador c = em.find(Colaborador.class, id);
        em.getTransaction().begin();
        em.merge(c);//Update Method
        em.getTransaction().commit();
        em.close();
        
        
    }
            
    public void removeColaborador() throws EntityNotFoundException, Exception {
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mil-AppPU");
            EntityManager em = emf.createEntityManager();
            /*Abrindo transacao */
            em.getTransaction().begin();
            em.remove(c);
            em.getTransaction().commit();
            em.close();
            /*Fechando transação */
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Colaborador" + "\n" + c.getNome() + "" + "Removido"));
        } catch (EntityNotFoundException ex) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("erro.xhtml");
        }

    }

    public void addColaborador() throws EntityNotFoundException, Exception {
        /*
        if (c instanceof Colaborador) {
            if (c.getNome().isEmpty() != false) { */
        try {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mil-AppPU");
            EntityManager em = emf.createEntityManager();
            /*Abrindo transacao */
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            /*Fechando Transação */
            em.close();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Colaborador" + "\n" + c.getNome() + "Cadastrado"));

        } catch (EntityNotFoundException ex) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("erro.xhtml");
        }

    }

}
