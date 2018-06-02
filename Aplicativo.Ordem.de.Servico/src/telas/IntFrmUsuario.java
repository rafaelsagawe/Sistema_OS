/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.sql.*;
import DAO.Conexao;
import javax.swing.JOptionPane;

// Frameworks dentro do java.sql
/**
 *
 * @author rafael
 */
public class IntFrmUsuario extends javax.swing.JInternalFrame {

    // Variaveis de acesso ao banco de dados
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form IntFrmUsuario
     */
    // Construtos da janela
    public IntFrmUsuario() {
        initComponents();
        conexao = Conexao.conector();

    }
    // metodo de consulta de usuário

    private void consultar() {
        String sql = "select * from tb_usuario where id_user=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUseId.getText());
            rs = pst.executeQuery();
            // Se existe usuário 

            if (rs.next()) {
                txtUseNome.setText(rs.getString(2));
                txtUseFone.setText(rs.getString(3));
                txtUseLogin.setText(rs.getString(4));
                txtUseSenha.setText(rs.getString(5));
                // Linha se refer ao combo box
                cmbUsePerfil.setSelectedItem(rs.getString(6));
            } else { // tratamento na ausencia de usuário
                JOptionPane.showMessageDialog(null, "Usuário não cadastrado");
                // limpa os campos caso não exista usuário
                txtUseNome.setText(null);
                txtUseFone.setText(null);
                txtUseLogin.setText(null);
                txtUseSenha.setText(null);
                //cmbUsePerfil.setSelectedItem(null);

            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }

    // metodo de adição de usuário
    private void adicionar() {
        String sql = "insert into tb_usuario (id_user, usuario, fone, login, senha, perfil) values(?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUseId.getText());
            pst.setString(2, txtUseNome.getText());
            pst.setString(3, txtUseFone.getText());
            pst.setString(4, txtUseLogin.getText());
            pst.setString(5, txtUseSenha.getText());
            pst.setString(6, cmbUsePerfil.getSelectedItem().toString()); // Essa linha precisa ser convertido para Strindo assim e usado o .toString

            // Validação dos campos obrigatorios
            if (((((txtUseId.getText().isEmpty()) || (txtUseNome.getText().isEmpty())) || ((txtUseLogin.getText().isEmpty()))) || ((txtUseSenha.getText().isEmpty())))){
                JOptionPane.showMessageDialog(null, "Erro");
            } else {
                // Atualiza a tabela de usaurios com os dados do formulario
                // Estrutura para enviar uma mensagem de confirmação de cadastro
                int adicionado = pst.executeUpdate();
                // Linha de teste para o terminal o valor retornado deve ser 1, que significa a entrada de uma linha
                System.out.println(adicionado); 
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso");
                    txtUseId.setText(null);
                    txtUseNome.setText(null);
                    txtUseFone.setText(null);
                    txtUseLogin.setText(null);
                    txtUseSenha.setText(null);
                   // cmbUsePerfil.setSelectedItem(null);
                }
            }
        } catch (Exception e) {
           // JOptionPane.showMessageDialog(null, "Preencha todos os campos");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtUseId = new javax.swing.JTextField();
        txtUseNome = new javax.swing.JTextField();
        txtUseLogin = new javax.swing.JTextField();
        txtUseSenha = new javax.swing.JTextField();
        cmbUsePerfil = new javax.swing.JComboBox<>();
        txtUseFone = new javax.swing.JTextField();
        btnUseAdd = new javax.swing.JButton();
        btnUseRead = new javax.swing.JButton();
        btnUseUpdate = new javax.swing.JButton();
        btnUseDelete = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Usuários");

        jLabel1.setText("ID");

        jLabel2.setForeground(new java.awt.Color(255, 0, 0));
        jLabel2.setText("Nome");

        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("Login");

        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("Senha");

        jLabel5.setForeground(new java.awt.Color(255, 0, 0));
        jLabel5.setText("Perfil");

        jLabel6.setText("Fone");

        txtUseId.setText(" ");

        txtUseNome.setText(" ");

        txtUseLogin.setText(" ");

        txtUseSenha.setText(" ");

        cmbUsePerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Normal" }));

        txtUseFone.setText(" ");

        btnUseAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/create.png"))); // NOI18N
        btnUseAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseAdd.setPreferredSize(new java.awt.Dimension(30, 30));
        btnUseAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseAddActionPerformed(evt);
            }
        });

        btnUseRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/read.png"))); // NOI18N
        btnUseRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseRead.setPreferredSize(new java.awt.Dimension(30, 30));
        btnUseRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseReadActionPerformed(evt);
            }
        });

        btnUseUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/update.png"))); // NOI18N
        btnUseUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseUpdate.setPreferredSize(new java.awt.Dimension(30, 30));

        btnUseDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Delete.png"))); // NOI18N
        btnUseDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseDelete.setPreferredSize(new java.awt.Dimension(30, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtUseFone)
                    .addComponent(txtUseSenha)
                    .addComponent(txtUseId)
                    .addComponent(txtUseNome)
                    .addComponent(txtUseLogin)
                    .addComponent(cmbUsePerfil, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(141, Short.MAX_VALUE)
                .addComponent(btnUseAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUseRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUseUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnUseDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUseId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtUseNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUseLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtUseFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUseSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbUsePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUseAdd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUseRead, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUseUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUseDelete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnUseReadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseReadActionPerformed
        //Realizando a consulta
        consultar();
    }//GEN-LAST:event_btnUseReadActionPerformed

    private void btnUseAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseAddActionPerformed
        // realiza a adição do usuario
        adicionar();
    }//GEN-LAST:event_btnUseAddActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUseAdd;
    private javax.swing.JButton btnUseDelete;
    private javax.swing.JButton btnUseRead;
    private javax.swing.JButton btnUseUpdate;
    private javax.swing.JComboBox<String> cmbUsePerfil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JTextField txtUseFone;
    private javax.swing.JTextField txtUseId;
    private javax.swing.JTextField txtUseLogin;
    private javax.swing.JTextField txtUseNome;
    private javax.swing.JTextField txtUseSenha;
    // End of variables declaration//GEN-END:variables
}
