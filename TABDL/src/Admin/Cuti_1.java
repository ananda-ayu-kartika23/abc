/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ANANDA AYU KARTIKA S
 */
@Entity
@Table(name = "cuti")
@NamedQueries({
    @NamedQuery(name = "Cuti_1.findAll", query = "SELECT c FROM Cuti_1 c"),
    @NamedQuery(name = "Cuti_1.findByIdCuti", query = "SELECT c FROM Cuti_1 c WHERE c.idCuti = :idCuti"),
    @NamedQuery(name = "Cuti_1.findByIdKaryawan", query = "SELECT c FROM Cuti_1 c WHERE c.idKaryawan = :idKaryawan"),
    @NamedQuery(name = "Cuti_1.findByIdAdmin", query = "SELECT c FROM Cuti_1 c WHERE c.idAdmin = :idAdmin"),
    @NamedQuery(name = "Cuti_1.findBySupervisor", query = "SELECT c FROM Cuti_1 c WHERE c.supervisor = :supervisor"),
    @NamedQuery(name = "Cuti_1.findByTanggalMulai", query = "SELECT c FROM Cuti_1 c WHERE c.tanggalMulai = :tanggalMulai"),
    @NamedQuery(name = "Cuti_1.findByTanggalSelesai", query = "SELECT c FROM Cuti_1 c WHERE c.tanggalSelesai = :tanggalSelesai"),
    @NamedQuery(name = "Cuti_1.findByAlasan", query = "SELECT c FROM Cuti_1 c WHERE c.alasan = :alasan"),
    @NamedQuery(name = "Cuti_1.findByStatusCuti", query = "SELECT c FROM Cuti_1 c WHERE c.statusCuti = :statusCuti")})
public class Cuti_1 implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_cuti")
    private Integer idCuti;
    @Column(name = "id_karyawan")
    private Integer idKaryawan;
    @Column(name = "id_admin")
    private Integer idAdmin;
    @Column(name = "supervisor")
    private Integer supervisor;
    @Column(name = "tanggal_mulai")
    @Temporal(TemporalType.DATE)
    private Date tanggalMulai;
    @Column(name = "tanggal_selesai")
    @Temporal(TemporalType.DATE)
    private Date tanggalSelesai;
    @Column(name = "alasan")
    private String alasan;
    @Column(name = "status_cuti")
    private String statusCuti;

    public Cuti_1() {
    }

    public Cuti_1(Integer idCuti) {
        this.idCuti = idCuti;
    }

    public Integer getIdCuti() {
        return idCuti;
    }

    public void setIdCuti(Integer idCuti) {
        this.idCuti = idCuti;
    }

    public Integer getIdKaryawan() {
        return idKaryawan;
    }

    public void setIdKaryawan(Integer idKaryawan) {
        this.idKaryawan = idKaryawan;
    }

    public Integer getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(Integer idAdmin) {
        this.idAdmin = idAdmin;
    }

    public Integer getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Integer supervisor) {
        this.supervisor = supervisor;
    }

    public Date getTanggalMulai() {
        return tanggalMulai;
    }

    public void setTanggalMulai(Date tanggalMulai) {
        this.tanggalMulai = tanggalMulai;
    }

    public Date getTanggalSelesai() {
        return tanggalSelesai;
    }

    public void setTanggalSelesai(Date tanggalSelesai) {
        this.tanggalSelesai = tanggalSelesai;
    }

    public String getAlasan() {
        return alasan;
    }

    public void setAlasan(String alasan) {
        this.alasan = alasan;
    }

    public String getStatusCuti() {
        return statusCuti;
    }

    public void setStatusCuti(String statusCuti) {
        this.statusCuti = statusCuti;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCuti != null ? idCuti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cuti_1)) {
            return false;
        }
        Cuti_1 other = (Cuti_1) object;
        if ((this.idCuti == null && other.idCuti != null) || (this.idCuti != null && !this.idCuti.equals(other.idCuti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Admin.Cuti_1[ idCuti=" + idCuti + " ]";
    }

    void setIdKaryawan(Karyawan_1 kry) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setIdAdmin(Admin adminObj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    void setSupervisor(Karyawan_1 supervisorObj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
