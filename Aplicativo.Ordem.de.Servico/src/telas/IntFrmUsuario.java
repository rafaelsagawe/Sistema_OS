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
    /**
    * Os metodos criados abaixo fazem parte do GRUD do formulario de usurario
    * G
    * R
    * U
    * D
    */
    
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
            if (((((txtUseId.getText().isEmpty()) || (txtUseNome.getText().isEmpty())) || ((txtUseLogin.getText().isEmpty()))) || ((txtUseSenha.getText().isEmpty())))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos vermelhos.");
            } else {
                // Atualiza a tabela de usaurios com os dados do formulario
                // Estrutura para enviar uma mensagem de confirmação de cadastro
                int adicionado = pst.executeUpdate();
                // Linha de teste para o terminal o valor retornado deve ser 1, que significa a entrada de uma linha
                System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário adicionado com sucesso.");
                    txtUseId.setText(null);
                    txtUseNome.setText(null);
                    txtUseFone.setText(null);
                    txtUseLogin.setText(null);
                    txtUseSenha.setText(null);
                    // cmbUsePerfil.setSelectedItem(null);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // metodo alterar dados do usuário
    private void alterar() {
        String sql = "update tb_usuario set usuario=?, fone=?, login=?, senha=?, perfil=? where id_user=?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtUseNome.getText());
            pst.setString(2, txtUseFone.getText());
            pst.setString(3, txtUseLogin.getText());
            pst.setString(4, txtUseSenha.getText());
            pst.setString(5, cmbUsePerfil.getSelectedItem().toString());
            pst.setString(6, txtUseId.getText());

            if (((((txtUseId.getText().isEmpty()) || (txtUseNome.getText().isEmpty())) || ((txtUseLogin.getText().isEmpty()))) || ((txtUseSenha.getText().isEmpty())))) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos vermelhos.");
            } else {
                // Atualiza a tabela de usaurios com os dados do formulario
                // Estrutura para enviar uma mensagem de confirmação de cadastro
                int adicionado = pst.executeUpdate();
                // Linha de teste para o terminal o valor retornado deve ser 1, que significa a entrada de uma linha
                System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário alterado com sucesso.");
                    txtUseId.setText(null);
                    txtUseNome.setText(null);
                    txtUseFone.setText(null);
                    txtUseLogin.setText(null);
                    txtUseSenha.setText(null);
                    // cmbUsePerfil.setSelectedItem(null);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // metodo de remoção de usuário
    private void remover(){
        // Estrutura de confirmação de remoção
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção" ,JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM tb_usuario WHERE id_user=? ";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUseId.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0 ){
                    JOptionPane.showMessageDialog(null, "Usuáriuo removido");
                    txtUseId.setText(null);
                    txtUseNome.setText(null);
                    txtUseFone.setText(null);
                    txtUseLogin.setText(null);
                    txtUseSenha.setText(null);
                }
                    
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
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
        jToolBar1 = new javax.swing.JToolBar();
        btnAnterior = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnUseAdd = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnUseRead = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnUseUpdate = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnUseDelete = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnProximo = new javax.swing.JButton();
        txtUseSenha1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
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

        cmbUsePerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Normal" }));

        jToolBar1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jToolBar1.setFloatable(false);

        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Previous.png"))); // NOI18N
        btnAnterior.setFocusable(false);
        btnAnterior.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAnterior.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAnterior);
        jToolBar1.add(jSeparator4);

        btnUseAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/create.png"))); // NOI18N
        btnUseAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseAdd.setPreferredSize(new java.awt.Dimension(30, 30));
        btnUseAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseAddActionPerformed(evt);
            }
        });
        jToolBar1.add(btnUseAdd);
        jToolBar1.add(jSeparator1);

        btnUseRead.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/read.png"))); // NOI18N
        btnUseRead.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseRead.setPreferredSize(new java.awt.Dimension(30, 30));
        btnUseRead.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseReadActionPerformed(evt);
            }
        });
        jToolBar1.add(btnUseRead);
        jToolBar1.add(jSeparator2);

        btnUseUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/update.png"))); // NOI18N
        btnUseUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseUpdate.setPreferredSize(new java.awt.Dimension(30, 30));
        btnUseUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseUpdateActionPerformed(evt);
            }
        });
        jToolBar1.add(btnUseUpdate);
        jToolBar1.add(jSeparator3);

        btnUseDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Delete.png"))); // NOI18N
        btnUseDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUseDelete.setPreferredSize(new java.awt.Dimension(30, 30));
        btnUseDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUseDeleteActionPerformed(evt);
            }
        });
        jToolBar1.add(btnUseDelete);
        jToolBar1.add(jSeparator5);

        btnProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Next.png"))); // NOI18N
        btnProximo.setFocusable(false);
        btnProximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProximo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnProximo);

        jLabel7.setForeground(new java.awt.Color(255, 0, 0));
        jLabel7.setText("Confirmação");

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
                    .addComponent(txtUseId)
                    .addComponent(txtUseNome)
                    .addComponent(txtUseLogin)
                    .addComponent(cmbUsePerfil, 0, 351, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtUseSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtUseSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                    .addComponent(txtUseSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(txtUseSenha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cmbUsePerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnUseUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseUpdateActionPerformed
        // Realiza alteração do usuário
        alterar();
    }//GEN-LAST:event_btnUseUpdateActionPerformed

    private void btnUseDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUseDeleteActionPerformed
        // Realiza remoção de usuário
        remover();
    }//GEN-LAST:event_btnUseDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnProximo;
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
    private javax.swing.JLabel jLabel7;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField txtUseFone;
    private javax.swing.JTextField txtUseId;
    private javax.swing.JTextField txtUseLogin;
    private javax.swing.JTextField txtUseNome;
    private javax.swing.JTextField txtUseSenha;
    private javax.swing.JTextField txtUseSenha1;
    // End of variables declaration//GEN-END:variables
}
