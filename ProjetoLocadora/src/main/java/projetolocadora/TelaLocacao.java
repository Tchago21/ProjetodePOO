package projetolocadora;

import connection.Conexao;
import dao.ClienteDAO;
import dao.CarroDAO;
import dao.LocacaoDAO;
import dao.PagamentoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import java.util.List;

/**
 *
 * @author Thiago Henrique
 */
public class TelaLocacao extends javax.swing.JFrame {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private CarroDAO carroDAO = new CarroDAO();
    private LocacaoDAO locacaoDAO = new LocacaoDAO();
    private PagamentoDAO pagamentoDAO = new PagamentoDAO();
    private List<Locacao> locacoes;
    private List<Cliente> clientes;
    private List<Carro> carrosDisponiveis;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TelaLocacao.class.getName());

    public TelaLocacao() {
        setResizable(false);
        initComponents();
        setDefaultCloseOperation(
        javax.swing.WindowConstants.DISPOSE_ON_CLOSE
    );

    lblValorDiaria.setText("R$ 0,00");
    lblValorTotal.setText("R$ 0,00");
    
    carregarClientes();
    carregarCarrosDisponiveis();
    carregarLocacoes();
    tabelaLocacoes.getColumnModel().getColumn(0).setPreferredWidth(160);
    tabelaLocacoes.getColumnModel().getColumn(1).setPreferredWidth(150);
    tabelaLocacoes.getColumnModel().getColumn(2).setPreferredWidth(30);
    tabelaLocacoes.getColumnModel().getColumn(3).setPreferredWidth(60);
    tabelaLocacoes.getColumnModel().getColumn(4).setPreferredWidth(100);
}
    private void carregarClientes() {

    comboCliente.removeAllItems();

    clientes = clienteDAO.listar();

    for (Cliente cliente : clientes) {

        comboCliente.addItem(
            cliente.getId() + " - " + cliente.getNome()
        );
    }
}
    private void carregarCarrosDisponiveis() {
        
    comboCarro.removeAllItems();

    carrosDisponiveis = carroDAO.listar();

    for (Carro carro : carrosDisponiveis) {

        if (carro.isDisponivel()) {

            comboCarro.addItem(
                carro.getId()
                + " - "
                + carro.getMarca()
                + " "
                + carro.getModelo()
                + " - "
                + carro.getPlaca()
            );
        }
    }

    if (comboCarro.getItemCount() > 0) {

        comboCarro.setSelectedIndex(0);

        atualizarValorDiaria();

    } else {

        lblValorDiaria.setText("R$ 0,00");
    }
}

    private void atualizarValorDiaria() {

    if (comboCarro.getSelectedItem() == null) {
        lblValorDiaria.setText("R$ 0,00");
        return;
    }

    String texto = comboCarro.getSelectedItem().toString();

    try {

        int id = Integer.parseInt(
            texto.split(" - ")[0]
        );

        for (Carro carro : carrosDisponiveis) {

            if (carro.getId() == id) {

                lblValorDiaria.setText(
                    String.format(
                        "R$ %.2f",
                        carro.getValorDiaria()
                    )
                );

                return;
            }
        }

    } catch (NumberFormatException e) {

        lblValorDiaria.setText("R$ 0,00");
    }
}
    private Carro obterCarroSelecionado() {

    if (comboCarro.getSelectedItem() == null) {
        return null;
    }

    String texto = comboCarro.getSelectedItem().toString();

    int id = Integer.parseInt(
        texto.split(" - ")[0]
    );

    for (Carro carro : carrosDisponiveis) {

        if (carro.getId() == id) {
            return carro;
        }
    }

    return null;
}
    
    private void carregarLocacoes() {

    locacoes = locacaoDAO.listar();

    DefaultTableModel modelo =
        (DefaultTableModel) tabelaLocacoes.getModel();

    modelo.setRowCount(0);

    for (Locacao locacao : locacoes) {

        String situacao =
            locacao.isDevolvida()
            ? "Devolvido"
            : "Em andamento";

        modelo.addRow(new Object[]{

            locacao.getCliente().getNome(),

            locacao.getCarro().getMarca()
                + " "
                + locacao.getCarro().getModelo()
                + " - "
                + locacao.getCarro().getPlaca(),

            locacao.getQuantidadeDias(),

            String.format(
                "R$ %.2f",
                locacao.getValorTotal()
            ),

            situacao
        });
    }
}
    private void limparCampos() {

    txtDias.setText("");

    lblValorDiaria.setText("R$ 0,00");
    lblValorTotal.setText("R$ 0,00");
}
    
    public void registrarDevolucao(int idLocacao) {

    String sql =
        "UPDATE locacao SET devolvida = true WHERE id = ?";

    try (Connection conexao = Conexao.conectar();
         PreparedStatement stmt =
             conexao.prepareStatement(sql)) {

        stmt.setInt(1, idLocacao);

        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        lblTituloCadastro = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboCliente = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        comboCarro = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtDias = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCalcular = new javax.swing.JButton();
        btnAlugar = new javax.swing.JButton();
        btnFechar = new javax.swing.JButton();
        lblTituloCadastro1 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabelaLocacoes = new javax.swing.JTable();
        lblValorDiaria = new javax.swing.JLabel();
        lblValorTotal = new javax.swing.JLabel();
        btnDevolver = new javax.swing.JButton();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTituloCadastro.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lblTituloCadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloCadastro.setText("Nova Locação");
        lblTituloCadastro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lblTituloCadastro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel1.setText("Cliente:");

        comboCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCliente.addActionListener(this::comboClienteActionPerformed);

        jLabel2.setText("Carro:");

        comboCarro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboCarro.addActionListener(this::comboCarroActionPerformed);

        jLabel3.setText("Quantidade de Dias:");

        txtDias.addActionListener(this::txtDiasActionPerformed);

        jLabel4.setText("Valor da Diária:");

        jLabel5.setText("Valor Total:");

        btnCalcular.setText("Calcular");
        btnCalcular.addActionListener(this::btnCalcularActionPerformed);

        btnAlugar.setText("Alugar");
        btnAlugar.addActionListener(this::btnAlugarActionPerformed);

        btnFechar.setText("Fechar");
        btnFechar.addActionListener(this::btnFecharActionPerformed);

        lblTituloCadastro1.setFont(new java.awt.Font("Segoe UI", 0, 22)); // NOI18N
        lblTituloCadastro1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTituloCadastro1.setText("Histórico de Locações");
        lblTituloCadastro1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));
        lblTituloCadastro1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        tabelaLocacoes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Cliente", "Carro", "Dias", "Valor", "Status"
            }
        ));
        jScrollPane4.setViewportView(tabelaLocacoes);

        lblValorDiaria.setText("R$ 0,00");

        lblValorTotal.setText("R$ 0,00");

        btnDevolver.setText("Registrar Devolução");
        btnDevolver.addActionListener(this::btnDevolverActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTituloCadastro, javax.swing.GroupLayout.DEFAULT_SIZE, 502, Short.MAX_VALUE)
            .addComponent(lblTituloCadastro1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane4)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboCarro, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblValorDiaria)
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnCalcular)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnAlugar)
                                        .addGap(8, 8, 8)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblValorTotal)
                                .addGap(43, 43, 43)))
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(175, 175, 175))))
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(btnDevolver)
                .addGap(18, 18, 18)
                .addComponent(btnFechar)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTituloCadastro, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboCarro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(lblValorDiaria)
                    .addComponent(lblValorTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalcular)
                    .addComponent(btnAlugar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTituloCadastro1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 249, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFechar)
                    .addComponent(btnDevolver))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiasActionPerformed

    private void btnCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalcularActionPerformed
    Carro carro = obterCarroSelecionado();

    if (carro == null) {

        JOptionPane.showMessageDialog(
            this,
            "Selecione um carro."
        );
        return;
    }

    try {

        int dias = Integer.parseInt(
            txtDias.getText()
        );

        if (dias <= 0) {

            JOptionPane.showMessageDialog(
                this,
                "A quantidade de dias deve ser maior que zero."
            );

            return;
        }

        double total =
            carro.getValorDiaria() * dias;

        lblValorTotal.setText(
            String.format(
                "R$ %.2f",
                total
            )
        );

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
            this,
            "Digite uma quantidade de dias válida.",
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnCalcularActionPerformed

    private void comboCarroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCarroActionPerformed
    if (carrosDisponiveis != null) {
        atualizarValorDiaria();
    }
    }//GEN-LAST:event_comboCarroActionPerformed

    private void btnAlugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlugarActionPerformed
    if (comboCliente.getSelectedIndex() == -1) {

        JOptionPane.showMessageDialog(
            this,
            "Selecione um cliente."
        );

        return;
    }

    Carro carro = obterCarroSelecionado();

    if (carro == null) {

        JOptionPane.showMessageDialog(
            this,
            "Selecione um carro disponível."
        );

        return;
    }

    try {

        int dias = Integer.parseInt(
            txtDias.getText()
        );

        if (dias <= 0) {

            JOptionPane.showMessageDialog(
                this,
                "A quantidade de dias deve ser maior que zero."
            );

            return;
        }

        int indiceCliente =
            comboCliente.getSelectedIndex();

        Cliente cliente =
            clientes.get(indiceCliente);

        Locacao locacao =
            new Locacao(
                cliente,
                carro,
                dias
            );

        locacaoDAO.inserir(locacao);

        carroDAO.atualizarDisponibilidade(
            carro.getId(),
            false
        );

        JOptionPane.showMessageDialog(
            this,
            "Locação realizada com sucesso!"
        );

        limparCampos();

        carregarCarrosDisponiveis();
        carregarLocacoes();

    } catch (NumberFormatException e) {

        JOptionPane.showMessageDialog(
            this,
            "Digite uma quantidade de dias válida.",
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );
    }
    }//GEN-LAST:event_btnAlugarActionPerformed

    private void btnFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecharActionPerformed
        dispose();
    }//GEN-LAST:event_btnFecharActionPerformed

    private void btnDevolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDevolverActionPerformed
        int linha = tabelaLocacoes.getSelectedRow();

    if (linha == -1) {

        JOptionPane.showMessageDialog(
            this,
            "Selecione uma locação na tabela."
        );

        return;
    }

    Locacao locacao = locacoes.get(linha);

    if (locacao == null) {

        JOptionPane.showMessageDialog(
            this,
            "Não foi possível encontrar a locação.",
            "Erro",
            JOptionPane.ERROR_MESSAGE
        );

        return;
    }

    if (locacao.isDevolvida()) {

        JOptionPane.showMessageDialog(
            this,
            "Esta locação já foi devolvida.",
            "Devolução",
            JOptionPane.INFORMATION_MESSAGE
        );

        return;
    }

    boolean pagamentoRealizado =
        pagamentoDAO.existePagamento(locacao.getId());

    if (!pagamentoRealizado) {

        JOptionPane.showMessageDialog(
            this,
            "Não é possível registrar a devolução.\n\n"
            + "O pagamento da locação ainda não foi realizado.",
            "Devolução não permitida",
            JOptionPane.WARNING_MESSAGE
        );

        return;
    }

    int resposta = JOptionPane.showConfirmDialog(
        this,
        "O pagamento foi realizado.\n\n"
        + "Deseja registrar a devolução do veículo?",
        "Confirmar devolução",
        JOptionPane.YES_NO_OPTION
    );

    if (resposta != JOptionPane.YES_OPTION) {
        return;
    }

    carroDAO.atualizarDisponibilidade(
        locacao.getCarro().getId(),
        true
    );

    locacaoDAO.registrarDevolucao(
        locacao.getId()
    );

    JOptionPane.showMessageDialog(
        this,
        "Devolução realizada com sucesso!\n\n"
        + "O veículo está novamente disponível."
    );

    carregarCarrosDisponiveis();
    carregarLocacoes();
    }//GEN-LAST:event_btnDevolverActionPerformed

    private void comboClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboClienteActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new TelaLocacao().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlugar;
    private javax.swing.JButton btnCalcular;
    private javax.swing.JButton btnDevolver;
    private javax.swing.JButton btnFechar;
    private javax.swing.JComboBox<String> comboCarro;
    private javax.swing.JComboBox<String> comboCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLabel lblTituloCadastro;
    private javax.swing.JLabel lblTituloCadastro1;
    private javax.swing.JLabel lblValorDiaria;
    private javax.swing.JLabel lblValorTotal;
    private javax.swing.JTable tabelaLocacoes;
    private javax.swing.JTextField txtDias;
    // End of variables declaration//GEN-END:variables
}
