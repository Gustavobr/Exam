
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.TestCase;
import org.junit.Test;
import org.primefaces.validate.bean.AssertTrueClientValidationConstraint;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author gustavoscipiao
 */
public class Validators extends TestCase {
        
    @Test
    public void validateLengthIdade(int idade){
        AssertTrueClientValidationConstraint(idade);
        
    }
    
    
    public static ArrayList<String> alfabeto = new ArrayList<String>();
    
    @Test
    public void Verificar_ordem_nome(String nome){
        if(nome.isEmpty()){
            System.out.println("Lista em branco");
        }else{
            String[] alfabeto = null;
            alfabeto[0]="a";
            alfabeto[1]="b";
            alfabeto[2]="c";
            alfabeto[3]="d";
            alfabeto[4]="e";
            alfabeto[5]="f";
            alfabeto[6]="g";
            alfabeto[7]="h";
            alfabeto[8]="i";
            alfabeto[9]="j";
            alfabeto[10]="l";
            alfabeto[11]="m";
            char posicao = nome.charAt(1);
               assertEquals(posicao, posicao);
                
            }
        
    
          
           
  
            
        }

    private void AssertTrueClientValidationConstraint(int idade) {
        if(idade >= 18 & idade<=60){
            assertEquals(idade, idade);
        }
        throw new UnsupportedOperationException("Erro"); 
    }
           
                    
        
    }
    

