/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class ListGaji extends javax.swing.JDialog {

    

    private EntityManagerFactory emf;
    private EntityManager em;

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("TABDLPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    public ListGaji(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();
        loadTable();

    }
    
    
 private void loadTable() {
    EntityManager em = emf.createEntityManager();
    try {
        TypedQuery<ListGaji_1> query = em.createQuery("SELECT g FROM ListGaji_1 g", ListGaji_1.class);
        List<ListGaji_1> hasil = query.getResultList();

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Gaji");
        model.addColumn("Karyawan");
        model.addColumn("Periode");
        model.addColumn("Gaji Pokok");
        model.addColumn("Tunjangan");
        model.addColumn("Bonus");
        model.addColumn("Lembur");
        model.addColumn("Total Hadir");
        model.addColumn("Total Cuti");
        model.addColumn("Total Alpha");
        model.addColumn("Total Potongan");
        model.addColumn("Total Gaji Bersih");

        for (ListGaji_1 g : hasil) {

            // AMBIL NAMA KARYAWAN
            String namaKaryawan = "-";
            if (g.getIdKaryawan() != null) {
                try {
                    namaKaryawan = em.createQuery(
                            "SELECT k.namaKaryawan FROM Karyawan_1 k WHERE k.idKaryawan = :id",
                            String.class
                    )
                    .setParameter("id", g.getIdKaryawan())
                    .getSingleResult();
                } catch (Exception e) {
                }
            }

            // AMBIL PERIODE
            String periode = "-";
            if (g.getIdPeriode() != null) {
                try {
                    Object[] p = em.createQuery(
                            "SELECT p.bulan_, p.tahun FROM Periode_1 p WHERE p.idPeriode = :id",
                            Object[].class
                    )
                    .setParameter("id", g.getIdPeriode())
                    .getSingleResult();

                    periode = p[0] + " " + p[1];
                } catch (Exception e) {
                }
            }

            model.addRow(new Object[]{
                g.getIdGaji(),
                namaKaryawan,
                periode,
                g.getGajiPokok(),
                g.getTunjangan(),
                g.getBonus(),
                g.getLembur(),
                g.getTotalHadir(),
                g.getTotalCuti(),
                g.getTotalAlpha(),
                g.getTotalPotongan(),
                g.getTotalGajiBersih()
            });
        }

        jTable1.setModel(model);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Gagal load tabel: " + e.getMessage());
        e.printStackTrace();
    } finally {
        em.close();
    }
}


    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 388, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(ListGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListGaji.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListGaji dialog = new ListGaji(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    
}
