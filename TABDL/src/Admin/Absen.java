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
public class Absen extends javax.swing.JDialog {

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

    public Absen(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        connect();
//        autoAlpha();
        loadTable();
    }
    
//    public Absen() {
//    emf = Persistence.createEntityManagerFactory("TABDLPU");
//    autoAlpha();
//}
    
//    private void autoAlpha() {
//     if (emf == null) {
//        System.out.println("EMF belum diinisialisasi!");
//        return;
//    }
//    EntityManager em = emf.createEntityManager();
//
//    try {
//        em.getTransaction().begin();
//
//        java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
//
//        // Ambil semua karyawan dari entity Karyawan_1
//        TypedQuery<Karyawan_1> qKaryawan = em.createQuery(
//                "SELECT k FROM Karyawan_1 k", Karyawan_1.class);
//        List<Karyawan_1> semuaKaryawan = qKaryawan.getResultList();
//
//        for (Karyawan_1 k : semuaKaryawan) {
//
//            // Cek apakah karyawan sudah absen hari ini
//            TypedQuery<Long> qCheck = em.createQuery(
//                    "SELECT COUNT(a) FROM Absensi a WHERE a.idKaryawan.idKaryawan = :id AND a.tanggal = :tgl",
//                    Long.class);
//            qCheck.setParameter("id", k.getIdKaryawan());
//            qCheck.setParameter("tgl", today);
//
//            Long jumlah = qCheck.getSingleResult();
//
//            // Jika belum absen → buat absensi ALPHA
//            if (jumlah == 0) {
//                Absensi alpha = new Absensi();
//                alpha.setIdAbsensi(null); // mengikuti auto increment/sequence
//                alpha.setIdKaryawan(k);
//                alpha.setTanggal(today);
//                alpha.setWaktuMasuk(null);
//                alpha.setWaktuKeluar(null);
//                alpha.setStatusKehadiran("ALPHA");
//                alpha.setTotalWaktuKerja(null);
//
//                em.persist(alpha);
//            }
//        }
//
//        em.getTransaction().commit();
//
//    } catch (Exception e) {
//        em.getTransaction().rollback();
//        JOptionPane.showMessageDialog(this, "Auto Alpha gagal: " + e.getMessage());
//    } finally {
//        em.close();
//    }
//}

    
    
private void loadTable() {
    EntityManager em = emf.createEntityManager();
    try {
        TypedQuery<Absensi> query = em.createQuery("SELECT a FROM Absensi a", Absensi.class);
        List<Absensi> hasil = query.getResultList();

        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID Absensi");
        model.addColumn("Nama Karyawan");
        model.addColumn("Tanggal");
        model.addColumn("Waktu Masuk");
        model.addColumn("Waktu Keluar");
        model.addColumn("Status");
        model.addColumn("Total Waktu Kerja");

        for (Absensi a : hasil) {
            model.addRow(new Object[]{
                a.getIdAbsensi(),
               (null != a.getIdKaryawan()) ? a.getIdKaryawan().getNamaKaryawan() : "-",
                a.getTanggal(),
                a.getWaktuMasuk(),
                a.getWaktuKeluar(),
                a.getStatusKehadiran(),
                a.getTotalWaktuKerja()
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 691, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(97, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
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
            java.util.logging.Logger.getLogger(Absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Absen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Absen dialog = new Absen(new javax.swing.JFrame(), true);
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
