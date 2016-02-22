/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loader;

import br.edu.ifpb.patterngames.entity.Cliente;
import br.edu.ifpb.patterngames.entity.Jogo;
import br.edu.ifpb.patterngames.entity.Locacao;
import br.edu.ifpb.patterngames.exceptions.JogoAlugadoException;
import br.edu.ifpb.patterngames.persistencia.ClienteBdDao;
import br.edu.ifpb.patterngames.persistencia.JogoBdDao;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Natarajan
 */
public class Teste {

    public static void main(String[] args) throws JogoAlugadoException {
        List<Jogo> jogos = new JogoBdDao().listarTodos();
        try {

            for (Jogo jogo : jogos) {
                System.out.println(jogo.getId() + " - " + jogo.getNome());
//                System.out.println(">" + jogo.getNome() + "</option>\n");
            }
            
            
        } catch (Exception e) {
        }
        
        Cliente c = new ClienteBdDao().buscar("007.980.214-11");
        
        Jogo j = new Jogo();
        j.setId(jogos.get(0).getId());
        j.setNome(jogos.get(0).getNome());
        
        Locacao loc = new Locacao(c, LocalDate.now(), j);
        
        System.out.println(loc);
        
        DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate dtDevol = LocalDate.of(2016, Month.MARCH, 25);
        
        System.out.println("Data de Devolução: " + dtDevol.format(DTF));
               
        System.out.println("Total: " + loc.calcularValor(dtDevol));
        
        System.out.println(j);
        
        Locacao loc2 = null;
        try {
            loc2 = new Locacao(c, LocalDate.now(), j);
        } catch(JogoAlugadoException e){
            System.out.println(e.getMessage());
        } finally {
            if (loc2 == null)
                System.out.println("deu certo");
            else 
                System.out.println("erro");
        }
            
        
    }
}
