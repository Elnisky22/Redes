package servidor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import cliente.Empacotador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class ServidorFrame extends javax.swing.JFrame {

    /**
     * Creates new form ServidorFrame
     */
    public ServidorFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaLog = new javax.swing.JTextArea();
        jTextField1 = new javax.swing.JTextField();
        jTextField1.setHorizontalAlignment(SwingConstants.LEFT);
        btnReceber = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 320));

        txtAreaLog.setEditable(false);
        txtAreaLog.setBackground(new java.awt.Color(200, 200, 200));
        txtAreaLog.setColumns(15);
        txtAreaLog.setRows(5);
        jScrollPane2.setViewportView(txtAreaLog);

        jTextField1.setText("65432");

        btnReceber.setText("receber");
        btnReceber.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                acaoBtnReceber(evt);
            }
        });
        
        btnPararServico = new JButton("parar servico");
        btnPararServico.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		acaoBtnPararServico(evt);
        	}
        });
        txtAreaMsgs = new javax.swing.JTextArea();
        
                txtAreaMsgs.setEditable(false);
                txtAreaMsgs.setColumns(15);
                txtAreaMsgs.setRows(5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(txtAreaMsgs, GroupLayout.DEFAULT_SIZE, 207, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnReceber)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(btnPararServico))
        				.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
        			.addGap(12))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(txtAreaMsgs, GroupLayout.DEFAULT_SIZE, 259, Short.MAX_VALUE)
        					.addContainerGap())
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnReceber)
        						.addComponent(btnPararServico))
        					.addGap(10))))
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void acaoBtnPararServico(ActionEvent evt) {
    	txtAreaLog.append("parando tread do servidor\n");
    	isRunning = false;
    	tServidor.interrupt();
    	try {
			tServidor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	txtAreaLog.append("servidor parado com sucesso\n");
    }
    
    private Thread tServidor;
    static volatile boolean isRunning = false;
    
    private void acaoBtnReceber(ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            int port = Integer.parseInt(jTextField1.getText());
            isRunning = true;
            tServidor = new Thread(new Runnable(){
                public void run(){
                    servidor(port);
                }
            });
            tServidor.start();
        }catch(NumberFormatException n){
            txtAreaLog.append("erro: porta invalida");
        }
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void servidor(int port){
        try{
        	ArrayList<InetAddress> listaClientes = new ArrayList();
        	//cria o socket para o recebimento
            DatagramSocket ds = new DatagramSocket(port);
            //Prepara para receber o pacote de dados
            DatagramPacket packet = new DatagramPacket(new byte[32767], 32767);
	        while(isRunning) {
	            //Recebimento do socket
	        	txtAreaLog.append("esperando mensagem na porta: " + port + "\n");
	            ds.receive(packet);
	            txtAreaMsgs.append(Desempacotador.desempacotar(packet.getData())+"\n");
	            InetAddress ipRecebido = packet.getAddress();
	            int portaRecebida = packet.getPort();
	            txtAreaLog.append("menssagem recebida de "+ ipRecebido.toString()+":" + portaRecebida +"\n");
	            if(!listaClientes.contains(ipRecebido))
	            	listaClientes.add(ipRecebido);
	            for(int i=0;i<listaClientes.size();i++) {
	            	Empacotador.send(packet.getData(),listaClientes.get(i),port-1);
	            	
	            }
	        }
            ds.close();
        }catch(IOException e){}
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServidorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServidorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServidorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServidorFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServidorFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnReceber;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtAreaMsgs;
    private javax.swing.JTextArea txtAreaLog;
    private javax.swing.JTextField jTextField1;
    private JButton btnPararServico;
}
