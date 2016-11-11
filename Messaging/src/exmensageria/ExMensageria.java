/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exmensageria;

import static java.lang.Thread.sleep;
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.QueueConnectionFactory;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author mayara.barbosa
 */
public class ExMensageria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws NamingException, JMSException, InterruptedException {
        
        Context ctx; 
        ctx = new InitialContext();
        QueueConnectionFactory factory;
        factory = (QueueConnectionFactory) ctx.lookup("QueueCF");
        
        Connection conexao;
        conexao = (Connection) factory.createConnection();
     
        Session sessao;
        //primeira: transacao
        //segundo: notificar o servidor
        sessao =  conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
        
        Destination destino;        
        destino = sessao.createQueue("fila_03");
        MessageProducer produtor;
        produtor = sessao.createProducer(destino);
        for(;;){
        TextMessage mensagens;
        mensagens = sessao.createTextMessage("hello");
        
        
        produtor.send(mensagens);
        sleep(500);
        }
        
    }
    
}
