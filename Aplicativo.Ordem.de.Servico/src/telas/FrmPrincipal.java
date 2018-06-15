/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import DAO.Conexao;
import java.text.DateFormat;
import java.util.Date;
//import javax.security.auth.spi.LoginModule;
import javax.swing.JOptionPane;
import java.sql.*;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author rafael
 */
public class FrmPrincipal extends javax.swing.JFrame {

    Connection conexao = null;
    
    /**
     * Creates new form FrmPrincipal
     */
    public FrmPrincipal() {
        initComponents();
        conexao = Conexao.conector();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Desktop = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        lblData = new javax.swing.JLabel();
        MenuBar = new javax.swing.JMenuBar();
        mnCad = new javax.swing.JMenu();
        mnCadCli = new javax.swing.JMenuItem();
        mnCadOS = new javax.swing.JMenuItem();
        mnCadUser = new javax.swing.JMenuItem();
        mnRel = new javax.swing.JMenu();
        mnRelSer = new javax.swing.JMenuItem();
        mnRelClientes = new javax.swing.JMenuItem();
        mnAjuda = new javax.swing.JMenu();
        mnAjudaSobre = new javax.swing.JMenuItem();
        mnOp = new javax.swing.JMenu();
        mnSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema para controle de Ordem de serviços");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        javax.swing.GroupLayout DesktopLayout = new javax.swing.GroupLayout(Desktop);
        Desktop.setLayout(DesktopLayout);
        DesktopLayout.setHorizontalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 490, Short.MAX_VALUE)
        );
        DesktopLayout.setVerticalGroup(
            DesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagens/img-home2.png"))); // NOI18N

        lblUsuario.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblUsuario.setText("Usuário");

        lblData.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        lblData.setText("Data");

        mnCad.setText("Cadastro");

        mnCadCli.setText("Cliente");
        mnCadCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCadCliActionPerformed(evt);
            }
        });
        mnCad.add(mnCadCli);

        mnCadOS.setText("OS");
        mnCadOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCadOSActionPerformed(evt);
            }
        });
        mnCad.add(mnCadOS);

        mnCadUser.setText("Usuários");
        mnCadUser.setEnabled(false);
        mnCadUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnCadUserActionPerformed(evt);
            }
        });
        mnCad.add(mnCadUser);

        MenuBar.add(mnCad);

        mnRel.setText("Relatório");

        mnRelSer.setText("Serviços");
        mnRelSer.setEnabled(false);
        mnRelSer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRelSerActionPerformed(evt);
            }
        });
        mnRel.add(mnRelSer);

        mnRelClientes.setText("Clientes");
        mnRelClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnRelClientesActionPerformed(evt);
            }
        });
        mnRel.add(mnRelClientes);

        MenuBar.add(mnRel);

        mnAjuda.setText("Ajuda");

        mnAjudaSobre.setText("Sobre");
        mnAjudaSobre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAjudaSobreActionPerformed(evt);
            }
        });
        mnAjuda.add(mnAjudaSobre);

        MenuBar.add(mnAjuda);

        mnOp.setText("Opções");

        mnSair.setText("Sair do Sistema");
        mnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSairActionPerformed(evt);
            }
        });
        mnOp.add(mnSair);

        MenuBar.add(mnOp);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(Desktop)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblData)
                    .addComponent(lblUsuario)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desktop)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblUsuario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblData)
                .addGap(0, 179, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void mnCadUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCadUserActionPerformed
        // Essas linhas abrem o cadastro de usuário no painel 
        IntFrmUsuario usuario = new IntFrmUsuario();
        usuario.setVisible(true);
        Desktop.add(usuario);
    }//GEN-LAST:event_mnCadUserActionPerformed

    private void mnRelSerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRelSerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mnRelSerActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
        // Essas linhas geram data e usuário na tela principal
        Date data = new Date();
        DateFormat formatador = DateFormat.getDateInstance(DateFormat.LONG);
        lblData.setText(formatador.format(data));
    }//GEN-LAST:event_formWindowActivated

    private void mnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSairActionPerformed
        // Botão sair do menu exibi caixa sim ou não
        int sair = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja sair", "Atenção",JOptionPane.YES_NO_OPTION);
        // Se jOptionPane for yes ele ira fechar o sistema
        if (sair == JOptionPane.YES_OPTION) {
            // chama tela login
            FrmLogin login = new FrmLogin();
            login.setVisible(true);
            this.dispose();
        }
        
    }//GEN-LAST:event_mnSairActionPerformed

    private void mnAjudaSobreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAjudaSobreActionPerformed
        // Chamar tela sobre
        FrmSobre sobre = new  FrmSobre();
        sobre.setVisible(true);
    }//GEN-LAST:event_mnAjudaSobreActionPerformed

    private void mnCadCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCadCliActionPerformed
        // Cadastro de clientes
        IntFrmCliente cliente = new IntFrmCliente();
        cliente.setVisible(true);
        Desktop.add(cliente);
    }//GEN-LAST:event_mnCadCliActionPerformed

    private void mnCadOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnCadOSActionPerformed
        // Tela de Ordem de serviços
        IntFrmOs os = new IntFrmOs();
        os.setVisible(true);
        Desktop.add(os);
    }//GEN-LAST:event_mnCadOSActionPerformed

    private void mnRelClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnRelClientesActionPerformed
        // Relatorio de clientes
        int confirma = JOptionPane.showConfirmDialog(null, "Visualizar relatorio", "Atenção",JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            // imprimindo o relatorio de clientes
            try {
                // usando a class jasperprinter para impressao
                JasperPrint imprimir = JasperFillManager.fillReport("/home/rafael/Sistema_OS/Aplicativo.Ordem.de.Servico/src/relatorios/clientes.jasper", null, conexao);
                // linha exibi o relatorio atravers da classe jasperviewer
                JasperViewer.viewReport(imprimir,false);
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
        }
    }//GEN-LAST:event_mnRelClientesActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane Desktop;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblData;
    public static javax.swing.JLabel lblUsuario;
    private javax.swing.JMenu mnAjuda;
    private javax.swing.JMenuItem mnAjudaSobre;
    private javax.swing.JMenu mnCad;
    private javax.swing.JMenuItem mnCadCli;
    private javax.swing.JMenuItem mnCadOS;
    public static javax.swing.JMenuItem mnCadUser;
    private javax.swing.JMenu mnOp;
    private javax.swing.JMenu mnRel;
    private javax.swing.JMenuItem mnRelClientes;
    public static javax.swing.JMenuItem mnRelSer;
    private javax.swing.JMenuItem mnSair;
    // End of variables declaration//GEN-END:variables
}
