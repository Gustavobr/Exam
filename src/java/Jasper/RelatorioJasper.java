/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jasper;

import br.com.MilCompany.Model.Colaborador;
import static br.com.MilCompany.Model.Colaborador_.idade;
import static br.com.MilCompany.Model.Colaborador_.nome;
import static br.com.MilCompany.Model.Colaborador_.salario;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.internal.jaxb.mapping.orm.JaxbEntityResult;

/**
 *
 * @author gustavoscipiao
 */
public class RelatorioJasper {

    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("Mil-AppPU");
    public EntityManager em = emf.createEntityManager();

    public RelatorioJasper() {

    }

    public void gerar(String layout) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign desenho = JRXmlLoader.load(layout);
        JasperReport relatorio = JasperCompileManager.compileReport(desenho); //compila layout!
        try{
            em.getTransaction().begin();
            Query query = em.createQuery("SELECT col.id, col.nome, col.idade, col.salario from Colaborador col ORDER BY COL ASC");
            List<Colaborador> lista = query.getResultList();
           JRResultSetDataSource jrs = new JRResultSetDataSource((ResultSet) lista);
           
           Map parametros = new HashMap();
           parametros.put("nome",nome);
           parametros.put("idade", idade);
           parametros.put("salario",salario);
           
           JasperPrint impressao = JasperFillManager.fillReport(relatorio, parametros, jrs);
           JasperViewer viewer = new JasperViewer(impressao, true);
           viewer.show();
        }catch(EntityNotFoundException ex){
            throw new JRException("Erro ao gerar o relatório");
        }
        /*
       static void main(String[] args){
           new RelatorioJasper.gerar("relatorio.jrxml");
       }
*/

    }
}
