/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package Admin;

import java.awt.Component;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.math.BigDecimal;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
public class EditKaryawan extends javax.swing.JDialog {

    private EntityManagerFactory emf;
    private EntityManager em;

    public EditKaryawan(JFrame parent, boolean modal) {

        super(parent, modal);
        initComponents();
        connect();
        setLocationRelativeTo(null);
        isiComboBoxDivisi();
        isiComboBoxJabatan();
//        isiComboBoxSupervisor();
//        isiComboBoxGajiPokok();

    }

    public void connect() {
        try {
            emf = Persistence.createEntityManagerFactory("TABDLPU");
            em = emf.createEntityManager();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Koneksi gagal: " + e.getMessage());
        }
    }

    public EditKaryawan(java.awt.Frame parent, boolean modal,
            String idKaryawan,
            String nama,
            String jenisKelamin,
            String tanggalLahir,
            String alamat,
            String noHp,
            String tanggalMasuk,
            String statusKerja,
            String divisi,
            String jabatan,
            String supervisor,
            String gajiPokok) {

        super(parent, modal);
        initComponents();

        connect();
        isiComboBoxDivisi();
        isiComboBoxJabatan();
//    isiComboBoxSupervisor();
//    isiComboBoxGajiPokok();

        txtIDKaryawan.setText(idKaryawan);
        txtNama.setText(nama);
        cmbKelamin.setSelectedItem(jenisKelamin);
        txtTanggalLahir.setText(tanggalLahir);
        txtAlamat.setText(alamat);
        txtNoHp.setText(noHp);
        txtTanggalMasuk.setText(tanggalMasuk);
        txtStatusKerja.setText(statusKerja);

        // ComboBox
        cmbDivisi_1.setSelectedItem(divisi);
        cmbJabatan_1.setSelectedItem(jabatan);

    }

    private void isiComboBoxDivisi() {

        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Divisi_1> query = em.createQuery("SELECT d FROM Divisi_1 d", Divisi_1.class);

            List<Divisi_1> hasil = query.getResultList();

            if (!hasil.isEmpty()) {
                for (Divisi_1 d : hasil) {
                    cmbDivisi_1.addItem(d);
                }
            }

            // Tidak memilih data apapun saat awal
            cmbDivisi_1.setSelectedIndex(-1);

        } finally {
            em.close();
        }
        isiComboBoxJabatan();
        setAdminOtomatis();
    }

    private void isiComboBoxJabatan() {
        EntityManager em = emf.createEntityManager();
        try {
            cmbJabatan_1.removeAllItems();
            TypedQuery<Jabatan_1> query;

            // Ambil divisi yang sedang dipilih
            Divisi_1 divisi = (Divisi_1) cmbDivisi_1.getSelectedItem();

            if (divisi == null) {
                // jika belum memilih divisi → kosongkan jabatan
                return;
            }

            int idDivisi = divisi.getIdDivisi();

            // FILTER BERDASARKAN ID DIVISI
            if (idDivisi == 1) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan = 1",
                        Jabatan_1.class
                );

            } else if (idDivisi == 2) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (2, 3)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 3) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (4,5,6,7)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 4) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (8,9)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 5) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (12,13)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 6) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (10)",
                        Jabatan_1.class
                );

            } else if (idDivisi == 7) {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j WHERE j.idJabatan IN (11)",
                        Jabatan_1.class
                );

            } else {
                query = em.createQuery(
                        "SELECT j FROM Jabatan_1 j",
                        Jabatan_1.class
                );
            }

            // Masukkan hasil ke combobox jabatan
            List<Jabatan_1> list = query.getResultList();
            for (Jabatan_1 j : list) {
                cmbJabatan_1.addItem(j);
            }

        } finally {
            em.close();
        }
        setSupervisorOtomatis();
        setAdminOtomatis();

    }

    private void setSupervisorOtomatis() {
        try {
            Jabatan_1 jabatan = (Jabatan_1) cmbJabatan_1.getSelectedItem();
            if (jabatan == null) {
                txtSupervisor.setText("");
                return;
            }

            int idJabatan = jabatan.getIdJabatan();
            int supervisorId = 0;

            // Aturan dari kamu:
            if (idJabatan >= 6 && idJabatan <= 13) {
                supervisorId = 5;  // Supervisor Produksi
            } else if (idJabatan >= 2 && idJabatan <= 5) {
                supervisorId = 1;  // Manager
            } else if (idJabatan == 1) {
                supervisorId = 0;  // Manager tidak punya supervisor
            }

            txtSupervisor.setText(String.valueOf(supervisorId));

        } catch (Exception e) {
            System.out.println("Error atur supervisor: " + e.getMessage());
        }
    }

    private void setAdminOtomatis() {
        try {
            Divisi_1 divisi = (Divisi_1) cmbDivisi_1.getSelectedItem();
            if (divisi == null) {
                txtAdmin.setText("");
                return;
            }

            int idDivisi = divisi.getIdDivisi();
            int idAdmin;

            // Aturan admin
            if (idDivisi == 1 || idDivisi == 2 || idDivisi == 4) {
                idAdmin = 1; // contoh admin 1
            } else if (idDivisi == 3) {
                idAdmin = 2;
            } else if (idDivisi == 5 || idDivisi == 6 || idDivisi == 7) {
                idAdmin = 2;
            } else {
                idAdmin = 0; // tidak ada admin
            }

            // jika admin valid
            if (idAdmin > 0) {
                txtAdmin.setText(String.valueOf(idAdmin));
            } else {
                txtAdmin.setText("");
            }

        } catch (Exception e) {
            System.out.println("Error set admin: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtIDKaryawan = new javax.swing.JTextField();
        txtNama = new javax.swing.JTextField();
        cmbKelamin = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtTanggalLahir = new javax.swing.JTextField();
        txtAlamat = new javax.swing.JTextField();
        txtNoHp = new javax.swing.JTextField();
        txtTanggalMasuk = new javax.swing.JTextField();
        txtStatusKerja = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        cmbDivisi_1 = new javax.swing.JComboBox<>();
        cmbJabatan_1 = new javax.swing.JComboBox<>();
        txtSupervisor = new javax.swing.JTextField();
        txtGajiPokok = new javax.swing.JTextField();
        txtAdmin = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("DATA KARYAWAN");

        jLabel2.setText("ID Karyawan");

        txtIDKaryawan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIDKaryawanActionPerformed(evt);
            }
        });

        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        cmbKelamin.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "P", "L" }));

        jLabel3.setText("Nama");

        jLabel4.setText("Jenis Kelamin");

        jLabel5.setText("Tempat Tanggal Lahir");

        jLabel6.setText("Alamat");

        jLabel7.setText("No Hp");

        jLabel8.setText("Tanggal Masuk");

        jLabel9.setText("Status Kerja");

        jLabel10.setText("Divisi");

        jLabel11.setText("Jabatan");

        jLabel12.setText("Supervisor");

        txtNoHp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNoHpActionPerformed(evt);
            }
        });

        jLabel13.setText("Gaji Pokok");

        jButton1.setText("Edit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cmbDivisi_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbDivisi_1ActionPerformed(evt);
            }
        });

        cmbJabatan_1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbJabatan_1ActionPerformed(evt);
            }
        });

        txtSupervisor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSupervisorActionPerformed(evt);
            }
        });

        txtGajiPokok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGajiPokokActionPerformed(evt);
            }
        });

        jLabel14.setText("Admin");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(jLabel1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(76, 76, 76)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(txtNama, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                                                .addComponent(txtIDKaryawan)
                                                .addComponent(cmbKelamin, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(txtTanggalLahir, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtAlamat, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtNoHp, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtTanggalMasuk, javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(txtStatusKerja, javax.swing.GroupLayout.Alignment.TRAILING))
                                            .addComponent(cmbDivisi_1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(cmbJabatan_1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtSupervisor, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtGajiPokok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))))))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtIDKaryawan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(cmbKelamin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtTanggalLahir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtAlamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtNoHp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(txtTanggalMasuk, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(txtStatusKerja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbDivisi_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cmbJabatan_1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtSupervisor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGajiPokok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addGap(33, 33, 33)
                .addComponent(jButton1)
                .addGap(29, 29, 29))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        // Validasi input
        if (txtIDKaryawan.getText().equals("")
                || txtNama.getText().equals("")
                || cmbKelamin.getSelectedItem() == null
                || txtTanggalLahir.getText().equals("")
                || txtAlamat.getText().equals("")
                || txtNoHp.getText().equals("")
                || txtTanggalMasuk.getText().equals("")
                || txtStatusKerja.getText().equals("")
                || cmbDivisi_1.getSelectedItem() == null
                || cmbJabatan_1.getSelectedItem() == null
                || txtSupervisor.getText().equals("")
                || txtAdmin.getText().equals("")
                || txtGajiPokok.getText().equals("")) {

            JOptionPane.showMessageDialog(this, "Isi Semua Data!");
            return;
        }

        try {

            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();

            // Cari data lama berdasarkan ID karyawan
            int idK = Integer.parseInt(txtIDKaryawan.getText());
            Karyawan_1 k = em.find(Karyawan_1.class, idK);

            if (k == null) {
                JOptionPane.showMessageDialog(this, "Data karyawan tidak ditemukan!");
                return;
            }

            // SET DATA BARU
            k.setNamaKaryawan(txtNama.getText());
            k.setJenisKelamin(cmbKelamin.getSelectedItem().toString().charAt(0));
            k.setTanggalLahir(java.sql.Date.valueOf(txtTanggalLahir.getText()));
            k.setAlamat(txtAlamat.getText());
            k.setNoHp(txtNoHp.getText());
            k.setTanggalMasuk(java.sql.Date.valueOf(txtTanggalMasuk.getText()));
            k.setStatusKerja(txtStatusKerja.getText());

// Gaji pokok (AMAN)
            String rawGaji = txtGajiPokok.getText().trim().replaceAll("[^0-9.]", "");
            if (rawGaji.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Gaji Pokok tidak boleh kosong!");
                return;
            }
            BigDecimal gaji = new BigDecimal(rawGaji);
            k.setGajiPokokk(gaji);

            // RELASI OBJEK
            Divisi_1 div = (Divisi_1) cmbDivisi_1.getSelectedItem();
            Jabatan_1 jab = (Jabatan_1) cmbJabatan_1.getSelectedItem();

            k.setIdDivisi(div);
            k.setIdJabatan(jab);

            // Supervisor
            int supId = Integer.parseInt(txtSupervisor.getText());
            Karyawan_1 supervisor = em.find(Karyawan_1.class, supId);
            k.setSupervisor(supervisor);

            // Admin
            int adminId = Integer.parseInt(txtAdmin.getText());
            Admin admin = em.find(Admin.class, adminId);
            k.setIdAdmin(admin);

            em.persist(k);
            em.getTransaction().commit();

            JOptionPane.showMessageDialog(this, "Data berhasil diperbarui!");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            e.printStackTrace();
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void cmbDivisi_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbDivisi_1ActionPerformed
        Divisi_1 d = (Divisi_1) cmbDivisi_1.getSelectedItem();
        if (d != null) {
            isiComboBoxJabatan();
            setAdminOtomatis();
        }
    }//GEN-LAST:event_cmbDivisi_1ActionPerformed

    private void txtSupervisorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSupervisorActionPerformed

    }//GEN-LAST:event_txtSupervisorActionPerformed

    private void cmbJabatan_1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJabatan_1ActionPerformed

        setSupervisorOtomatis();


    }//GEN-LAST:event_cmbJabatan_1ActionPerformed

    private void txtGajiPokokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGajiPokokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGajiPokokActionPerformed

    private void txtIDKaryawanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIDKaryawanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIDKaryawanActionPerformed

    private void txtNoHpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNoHpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNoHpActionPerformed

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
            java.util.logging.Logger.getLogger(EditKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditKaryawan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                EditKaryawan dialog = new EditKaryawan(new javax.swing.JFrame(), true);
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
    private javax.swing.JComboBox<Divisi_1> cmbDivisi_1;
    private javax.swing.JComboBox<Jabatan_1> cmbJabatan_1;
    private javax.swing.JComboBox<String> cmbKelamin;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtAdmin;
    private javax.swing.JTextField txtAlamat;
    private javax.swing.JTextField txtGajiPokok;
    private javax.swing.JTextField txtIDKaryawan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNoHp;
    private javax.swing.JTextField txtStatusKerja;
    private javax.swing.JTextField txtSupervisor;
    private javax.swing.JTextField txtTanggalLahir;
    private javax.swing.JTextField txtTanggalMasuk;
    // End of variables declaration//GEN-END:variables

}
