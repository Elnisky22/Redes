package cliente;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import javax.swing.GroupLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;

public class ClienteFrame extends javax.swing.JFrame {

    /**
     * Creates new form NewJFrame
     */
    public ClienteFrame() {
        initComponents();
    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtChat = new javax.swing.JTextArea();
        bntEnviar = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField3.setText("ip");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(500, 300));

        txtChat.setEditable(false);
        txtChat.setBackground(UIManager.getColor("TextPane.inactiveBackground"));
        txtChat.setColumns(20);
        txtChat.setRows(5);
        jScrollPane2.setViewportView(txtChat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE)
        			.addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(Alignment.LEADING, jPanel1Layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
        			.addContainerGap())
        );
        jPanel1.setLayout(jPanel1Layout);

        bntEnviar.setText("enviar");
        bntEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                acaoBtnEnviar(evt);
            }
        });

        btnLimpar.setText("limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jTextField1.setText("porta");
        jTextField1.setToolTipText("porta do servidor");
        jTextField1.setName("porta");
        jTextField3.setToolTipText("ip do servidor");
        jTextField3.setName("ip"); // NOI18N
        jTextArea1 = new javax.swing.JTextArea();
        
                jTextArea1.setColumns(20);
                jTextArea1.setRows(5);
        
        btnSairCanal = new JButton("Sair Canal");
        btnSairCanal.setHorizontalAlignment(SwingConstants.LEFT);
        btnSairCanal.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent evt) {
        		acaoBtnSairCanal(evt);
        	}
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addGroup(layout.createParallelGroup(Alignment.LEADING, false)
        						.addComponent(jTextField1, 225, 225, 225)
        						.addGroup(layout.createSequentialGroup()
        							.addComponent(btnLimpar)
        							.addPreferredGap(ComponentPlacement.UNRELATED)
        							.addComponent(btnSairCanal, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        							.addPreferredGap(ComponentPlacement.RELATED)
        							.addComponent(bntEnviar, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE))
        						.addComponent(jTextField3, 225, 225, 225))
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jTextArea1, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.TRAILING)
        		.addGroup(layout.createSequentialGroup()
        			.addContainerGap()
        			.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(bntEnviar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
        						.addComponent(btnLimpar)
        						.addComponent(btnSairCanal)))
        				.addComponent(jTextArea1, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        getContentPane().setLayout(layout);

        jTextField1.getAccessibleContext().setAccessibleName("");
        jTextField3.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void acaoBtnSairCanal(ActionEvent evt) {
    	isRunning = false;
    	tServidor.stop();
    	try {
			tServidor.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    }
    
    private Thread tServidor;
    static volatile boolean isRunning = false;
    
    private void acaoBtnEnviar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{ int port = Integer.parseInt(jTextField1.getText());
        	if(!isRunning) {
		        isRunning = true;
		        tServidor = new Thread(new Runnable(){
		            public void run(){
		                receber(port-1);
		            }
		        });
		        tServidor.start();
	        }
            try{
                InetAddress ip= InetAddress.getByName(jTextField3.getText());
                Empacotador.send(Empacotador.empacotar(jTextArea1.getText()),ip,port);
            }catch (UnknownHostException h) {txtChat.append("ip invalido\n");
            }
        }catch(NumberFormatException n){txtChat.append("porta invalida\n");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextArea1.setText("");
        jTextField1.setText("");
        jTextField3.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void receber(int port){
        try{
        	//cria o socket para o recebimento
            DatagramSocket ds = new DatagramSocket(port);     
            //Prepara para receber o pacote de dados
            DatagramPacket packet = new DatagramPacket(new byte[32767], 32767);
	        while(isRunning) {
	            //Recebimento do socket
	            ds.receive(packet);
	            txtChat.append(Desempacotador.desempacotar(packet.getData())+"\n");
	        }
            ds.close();
        }catch(IOException e){}
    }

    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
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
            java.util.logging.Logger.getLogger(ClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClienteFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ClienteFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntEnviar;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea txtChat;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private JButton btnSairCanal;
    // End of variables declaration//GEN-END:variables
}
