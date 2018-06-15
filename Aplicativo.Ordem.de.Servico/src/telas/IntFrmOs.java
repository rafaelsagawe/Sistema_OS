/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telas;

import java.sql.*;
import DAO.Conexao;
import javax.swing.JOptionPane;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author rafael
 */
public class IntFrmOs extends javax.swing.JInternalFrame {

    // Variaveis de acesso ao banco de dados
    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    // Linha da variavel para o raio boton
    private String tipo;

    /**
     * Creates new form IntFrmOs
     */
    public IntFrmOs() {
        initComponents();
        conexao = Conexao.conector();
    }

    // metodo de pesquisa inteligente
    private void pesquisarCliente() {
        String sql = "select id_cliente as ID, nome_cliente as Nome, fone_cliente as Telefone from tb_cliente where nome_cliente like ?";
        try {
            pst = conexao.prepareStatement(sql);
            // Passando o conteudo da caixa de pesquisa para o ?
            // Necessita da concatenação da instrução "%" para o sql 
            pst.setString(1, txtCliBusca.getText() + "%");
            rs = pst.executeQuery();
            // Linha que usa o rs2xml adicionado na biblioteca
            tblCliente.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    // Metodo para preencer os campos selecionando item da tabela
    public void setarCampos() {
        int setar = tblCliente.getSelectedRow();
        txtCliId.setText(tblCliente.getModel().getValueAt(setar, 0).toString());
        // Desativa o botão adcionar cliente, assim evita que o cliente ja cadatrado sejá novamente cadastrado
        //btnCliAdd.setEnabled(false);

    }

    // metodo de cadastro de OS
    private void emitirOS() {
        String sql = "insert into tb_os (tipo, situacao, equipamentos, defeito, servico, tecnico, valor, id_cliente) values (?,?,?,?,?,?,?,?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOSSitu.getSelectedItem().toString());
            pst.setString(3, txtEquip.getText());
            pst.setString(4, txtDef.getText());
            pst.setString(5, txtServ.getText());
            pst.setString(6, txtOSTec.getText());
            // substitui a virgula pelo ponto
            pst.setString(7, txtValorTotal.getText().replace(",", "."));
            pst.setString(8, txtCliId.getText());

            // Validação dos compos obrigatorios 
            if ((txtCliId.getText().isEmpty()) || (txtEquip.getText().isEmpty()) || (txtDef.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obreigatorios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS emetida com sucesso");
                    txtCliId.setText(null);
                    txtEquip.setText(null);
                    txtDef.setText(null);
                    txtServ.setText(null);
                    txtOSTec.setText(null);
                    txtValorTotal.setText(null);
                    txtCliBusca.setText(null);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }

    }

    //Metodo de pesquisa de OS
    private void pesquisarOS() {
        String numOS = JOptionPane.showInputDialog("Número da OS");
        String sql = "select * from tb_os where id_os = " + numOS;
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            if (rs.next()) {
                txtOS.setText(rs.getString(1));
                txtData.setText(rs.getString(2));
                // Setando os radio bottons
                String rbtTipo = rs.getString(3);
                if (rbtTipo.equals("OS")) {
                    rbtOS.setSelected(true);
                    tipo = "OS";
                } else {
                    rbtOrc.setSelected(true);
                    tipo = "Orçamento";
                }
                cboOSSitu.setSelectedItem(rs.getString(4));
                txtEquip.setText(rs.getString(5));
                txtDef.setText(rs.getString(6));
                txtServ.setText(rs.getString(7));
                txtOSTec.setText(rs.getString(8));
                txtValorTotal.setText(rs.getString(9));
                txtCliId.setText(rs.getString(10));
                // desativar areas apois busca
                btnAdicionarOs.setEnabled(false);
                txtCliBusca.setEnabled(false);
                tblCliente.setEnabled(false);

            } else {
                JOptionPane.showMessageDialog(null, "OS não cadastrada");
            }

        } catch (java.sql.SQLSyntaxErrorException e) {
            JOptionPane.showMessageDialog(null, "OS invalida");
            //System.out.println(e);

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, e2);
        }
    }
    
    // Metodo alterar OS
    private  void alterarOS(){
        String sql = "update tb_os set tipo=?, situacao=?, equipamentos=?, defeito=?, servico=?, tecnico=?, valor=? where id_os=?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, tipo);
            pst.setString(2, cboOSSitu.getSelectedItem().toString());
            pst.setString(3, txtEquip.getText());
            pst.setString(4, txtDef.getText());
            pst.setString(5, txtServ.getText());
            pst.setString(6, txtOSTec.getText());
            // substitui a virgula pelo ponto
            pst.setString(7, txtValorTotal.getText().replace(",", "."));
            pst.setString(8, txtOS.getText());

            // Validação dos compos obrigatorios 
            if ((txtCliId.getText().isEmpty()) || (txtEquip.getText().isEmpty()) || (txtDef.getText().isEmpty())) {
                JOptionPane.showMessageDialog(null, "Preencha todos os campos obreigatorios");
            } else {
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "OS atualizada com sucesso");
                    // Limpar os compos apos alteração
                    txtCliId.setText(null);
                    txtEquip.setText(null);
                    txtDef.setText(null);
                    txtServ.setText(null);
                    txtOSTec.setText(null);
                    txtValorTotal.setText(null);
                    txtCliBusca.setText(null);
                    txtData.setText(null);
                    // habilita os botoes 
                btnAdicionarOs.setEnabled(true);
                txtCliBusca.setEnabled(true);
                tblCliente.setEnabled(true);

                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
        
    }
    
    // metodo deletar os 
    private void deletarOS(){
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja remover este usuário?", "Atenção" ,JOptionPane.YES_NO_OPTION);
        if (confirma == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM tb_os WHERE id_os=? ";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtOS.getText());
                int apagado = pst.executeUpdate();
                if (apagado > 0 ){
                    JOptionPane.showMessageDialog(null, "OS removido");
                    txtCliId.setText(null);
                    txtEquip.setText(null);
                    txtDef.setText(null);
                    txtServ.setText(null);
                    txtOSTec.setText(null);
                    txtValorTotal.setText(null);
                    txtCliBusca.setText(null);
                    txtData.setText(null);
                    // habilita os botoes 
                btnAdicionarOs.setEnabled(true);
                txtCliBusca.setEnabled(true);
                tblCliente.setEnabled(true);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtOS = new javax.swing.JTextField();
        txtData = new javax.swing.JTextField();
        rbtOrc = new javax.swing.JRadioButton();
        rbtOS = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        cboOSSitu = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtCliBusca = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtCliId = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCliente = new javax.swing.JTable();
        jToolBar1 = new javax.swing.JToolBar();
        btnAnterior = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btnAdicionarOs = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btnPesquisarOS = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        btnAlterarOS = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        btnDeletarOS = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JToolBar.Separator();
        btnImprimirOS = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JToolBar.Separator();
        btnProximo = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        txtEquip = new javax.swing.JTextField();
        txtDef = new javax.swing.JTextField();
        txtServ = new javax.swing.JTextField();
        txtOSTec = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtValorTotal = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createEtchedBorder());
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Ordem de Serviço");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameOpened(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nº OS");

        jLabel2.setText("Data:");

        buttonGroup1.add(rbtOrc);
        rbtOrc.setText("Orçamento");
        rbtOrc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOrcActionPerformed(evt);
            }
        });

        buttonGroup1.add(rbtOS);
        rbtOS.setText("OS");
        rbtOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtOSActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rbtOrc)
                            .addComponent(rbtOS))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtOS)
                            .addComponent(txtData))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtOS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtOrc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rbtOS)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setText("Situação");

        cboOSSitu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Na Bancada", "Entrega Ok", "Orçamento REAPROVADO", "Aguardando Aprovação", "Aguardando Peças", "Abandonado pelo Cliente", "Retornou" }));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Cliente"));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/View.png"))); // NOI18N

        txtCliBusca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCliBuscaKeyReleased(evt);
            }
        });

        jLabel5.setText("ID");

        txtCliId.setEditable(false);

        tblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "Nome", "Telefone"
            }
        ));
        tblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblClienteMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblCliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCliBusca, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtCliBusca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtCliId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
        );

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setFloatable(false);

        btnAnterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Previous.png"))); // NOI18N
        btnAnterior.setFocusable(false);
        btnAnterior.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAnterior.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnAnterior);
        jToolBar1.add(jSeparator1);

        btnAdicionarOs.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/create.png"))); // NOI18N
        btnAdicionarOs.setFocusable(false);
        btnAdicionarOs.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdicionarOs.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAdicionarOs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAdicionarOsActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAdicionarOs);
        jToolBar1.add(jSeparator2);

        btnPesquisarOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/read.png"))); // NOI18N
        btnPesquisarOS.setFocusable(false);
        btnPesquisarOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnPesquisarOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnPesquisarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarOSActionPerformed(evt);
            }
        });
        jToolBar1.add(btnPesquisarOS);
        jToolBar1.add(jSeparator3);

        btnAlterarOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/update.png"))); // NOI18N
        btnAlterarOS.setFocusable(false);
        btnAlterarOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAlterarOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnAlterarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarOSActionPerformed(evt);
            }
        });
        jToolBar1.add(btnAlterarOS);
        jToolBar1.add(jSeparator4);

        btnDeletarOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Delete.png"))); // NOI18N
        btnDeletarOS.setFocusable(false);
        btnDeletarOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDeletarOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDeletarOS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeletarOSActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDeletarOS);
        jToolBar1.add(jSeparator5);

        btnImprimirOS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Text.png"))); // NOI18N
        btnImprimirOS.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImprimirOS.setFocusable(false);
        btnImprimirOS.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnImprimirOS.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnImprimirOS);
        jToolBar1.add(jSeparator6);

        btnProximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icones/Next.png"))); // NOI18N
        btnProximo.setFocusable(false);
        btnProximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnProximo.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(btnProximo);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Ordem de Serviço"));

        jLabel10.setText("Valor Total");

        txtValorTotal.setText("0");

        jLabel6.setText("Equipamento");

        jLabel7.setText("Defeito");

        jLabel8.setText("Serviço");

        jLabel9.setText("Tecnico");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtEquip)
                    .addComponent(txtDef)
                    .addComponent(txtServ)
                    .addComponent(txtOSTec)
                    .addComponent(txtValorTotal))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtDef, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtServ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtOSTec, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtValorTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cboOSSitu, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cboOSSitu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setBounds(0, 0, 395, 621);
    }// </editor-fold>//GEN-END:initComponents

    private void txtCliBuscaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCliBuscaKeyReleased
        // Metodo de pesquisa de clientes
        pesquisarCliente();
    }//GEN-LAST:event_txtCliBuscaKeyReleased

    private void tblClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblClienteMouseClicked
        // Motodo para setar o campo ID
        setarCampos();
    }//GEN-LAST:event_tblClienteMouseClicked


    private void rbtOrcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOrcActionPerformed
        // atribuindo um texto a variavel tipo
        tipo = "Orçamento";
    }//GEN-LAST:event_rbtOrcActionPerformed

    private void rbtOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtOSActionPerformed
        // TODO add your handling code here:
        tipo = "OS";
    }//GEN-LAST:event_rbtOSActionPerformed

    private void formInternalFrameOpened(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameOpened
        // Ao abrir marca radio botão
        rbtOrc.setSelected(true);
        tipo = "Orçamento";
    }//GEN-LAST:event_formInternalFrameOpened

    private void btnAdicionarOsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAdicionarOsActionPerformed
        // metodo de emitir a OS
        emitirOS();
    }//GEN-LAST:event_btnAdicionarOsActionPerformed

    private void btnPesquisarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarOSActionPerformed
        // Chama o metodo pesquisar OS
        pesquisarOS();
    }//GEN-LAST:event_btnPesquisarOSActionPerformed

    private void btnAlterarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarOSActionPerformed
        // TODO add your handling code here:
        alterarOS();
    }//GEN-LAST:event_btnAlterarOSActionPerformed

    private void btnDeletarOSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeletarOSActionPerformed
        // TODO add your handling code here:
        deletarOS();
    }//GEN-LAST:event_btnDeletarOSActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdicionarOs;
    private javax.swing.JButton btnAlterarOS;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnDeletarOS;
    private javax.swing.JButton btnImprimirOS;
    private javax.swing.JButton btnPesquisarOS;
    private javax.swing.JButton btnProximo;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboOSSitu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar.Separator jSeparator5;
    private javax.swing.JToolBar.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JRadioButton rbtOS;
    private javax.swing.JRadioButton rbtOrc;
    private javax.swing.JTable tblCliente;
    private javax.swing.JTextField txtCliBusca;
    private javax.swing.JTextField txtCliId;
    private javax.swing.JTextField txtData;
    private javax.swing.JTextField txtDef;
    private javax.swing.JTextField txtEquip;
    private javax.swing.JTextField txtOS;
    private javax.swing.JTextField txtOSTec;
    private javax.swing.JTextField txtServ;
    private javax.swing.JTextField txtValorTotal;
    // End of variables declaration//GEN-END:variables
}
