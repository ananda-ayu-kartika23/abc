/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import Admin.EditKaryawan;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class Karyawan extends javax.swing.JDialog {

    

    /**
     * Creates new form Karyawan
     */
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

    public Karyawan(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();

        loadTable();

    }

    private String safeGetValue(int row, int col) {
        Object val = jTable1.getValueAt(row, col);
        return (val == null) ? "" : val.toString();
    }

    

    // ======================== FIX UTAMA ADA DI SINI ========================
    private void loadTable() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Karyawan_1> query = em.createQuery("SELECT k FROM Karyawan_1 k", Karyawan_1.class);
            List<Karyawan_1> hasil = query.getResultList();

            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID Karyawan");
            model.addColumn("Nama Karyawan");
            model.addColumn("Jenis Kelamin");
            model.addColumn("Tgl Lahir");
            model.addColumn("Alamat");
            model.addColumn("No HP");
            model.addColumn("Tanggal Masuk");
            model.addColumn("Status Kerja");
            model.addColumn("Gaji Pokok");

            model.addColumn("Divisi");
            model.addColumn("Jabatan");
            model.addColumn("Admin");
            model.addColumn("Supervisor");

            for (Karyawan_1 k : hasil) {

                // ========== FIX UNTUK ADMIN ==================
                Admin admin = null;
                if (k.getIdAdmin() != null) {
                    admin = em.find(Admin.class, k.getIdAdmin());
                }
                String namaAdmin = (admin != null) ? admin.getNamaAdmin() : "-";

                // Supervisor relasi tetap aman
                String namaSupervisor = (k.getSupervisor() != null)
                        ? k.getSupervisor().getNamaKaryawan()
                        : "-";

                model.addRow(new Object[]{
                    k.getIdKaryawan(),
                    k.getNamaKaryawan(),
                    k.getJenisKelamin(),
                    k.getTanggalLahir(),
                    k.getAlamat(),
                    k.getNoHp(),
                    k.getTanggalMasuk(),
                    k.getStatusKerja(),
                    k.getGajiPokokk(),

                    (k.getIdDivisi() != null) ? k.getIdDivisi().getNamaDivisi() : "-",
                    (k.getIdJabatan() != null) ? k.getIdJabatan().getNamaJabatan() : "-",

                    // ADMIN YANG SUDAH FIX
                    namaAdmin,

                    // SUPERVISOR
                    namaSupervisor
                });
            }

            jTable1.setModel(model);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal load tabel: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnTambah = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnTambah.setText("Tambah");
        btnTambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahActionPerformed(evt);
            }
        });

        btnEdit.setText("Edit");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

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

        jLabel1.setText("DATA KARYAWAN");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnTambah)
                        .addGap(33, 33, 33)
                        .addComponent(btnEdit)))
                .addGap(32, 32, 32)
                .addComponent(btnHapus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambah)
                    .addComponent(btnEdit)
                    .addComponent(btnHapus))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        TambahKaryawan fk = new TambahKaryawan(new javax.swing.JFrame(), true);
        fk.setLocationRelativeTo(this);
        fk.setVisible(true);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed

        int row = jTable1.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data dulu pada tabel!");
            return;
        }

        EditKaryawan edit = new EditKaryawan(
                new javax.swing.JFrame(),
                true,
                safeGetValue(row, 0), // ID Karyawan
                safeGetValue(row, 1), // Nama
                safeGetValue(row, 2), // Jenis Kelamin
                safeGetValue(row, 3), // Tgl Lahir
                safeGetValue(row, 4), // Alamat
                safeGetValue(row, 5), // No HP
                safeGetValue(row, 6), // Tanggal Masuk
                safeGetValue(row, 7), // Status Kerja
                safeGetValue(row, 9), // Divisi
                safeGetValue(row, 10), // Jabatan
                safeGetValue(row, 12), // Supervisor
                safeGetValue(row, 8) // Gaji Pokok
        );

        edit.setLocationRelativeTo(this);
        edit.setVisible(true);
        loadTable();


    }//GEN-LAST:event_btnEditActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
                                              

    int baris = jTable1.getSelectedRow();
    if (baris == -1) {
        JOptionPane.showMessageDialog(this, "Pilih data terlebih dahulu!");
        return;
    }

    // Ambil ID dari tabel kolom 0
    String id = jTable1.getValueAt(baris, 0).toString();

    // Ambil parent JFrame dari panel saat ini (penting!)
    JFrame parent = (JFrame) javax.swing.SwingUtilities.getWindowAncestor(this);

    // Buka dialog hapus
    HapusKaryawan hk = new HapusKaryawan(parent, true);

    // Kirim ID dan callback untuk refresh tabel
    hk.setData(id, () -> {
        loadTable();   // refresh tabel setelah hapus
    });

    hk.setLocationRelativeTo(this);
    hk.setVisible(true);

                                          
    }//GEN-LAST:event_btnHapusActionPerformed

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
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Karyawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Karyawan dialog = new Karyawan(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    
   
}
