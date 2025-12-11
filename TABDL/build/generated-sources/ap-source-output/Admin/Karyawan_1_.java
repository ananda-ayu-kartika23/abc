package Admin;

import Admin.Divisi_1;
import Admin.Jabatan_1;
import Admin.Karyawan_1;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-01T20:36:14", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Karyawan_1.class)
public class Karyawan_1_ { 

    public static volatile SingularAttribute<Karyawan_1, Date> tanggalMasuk;
    public static volatile SingularAttribute<Karyawan_1, Integer> idAdmin;
    public static volatile SingularAttribute<Karyawan_1, String> namaKaryawan;
    public static volatile SingularAttribute<Karyawan_1, Date> tanggalLahir;
    public static volatile SingularAttribute<Karyawan_1, BigDecimal> gajiPokokk;
    public static volatile SingularAttribute<Karyawan_1, String> alamat;
    public static volatile CollectionAttribute<Karyawan_1, Karyawan_1> karyawanCollection;
    public static volatile SingularAttribute<Karyawan_1, String> password;
    public static volatile SingularAttribute<Karyawan_1, Integer> idKaryawan;
    public static volatile SingularAttribute<Karyawan_1, Character> jenisKelamin;
    public static volatile SingularAttribute<Karyawan_1, String> noHp;
    public static volatile SingularAttribute<Karyawan_1, Divisi_1> idDivisi;
    public static volatile SingularAttribute<Karyawan_1, String> statusKerja;
    public static volatile SingularAttribute<Karyawan_1, Jabatan_1> idJabatan;
    public static volatile SingularAttribute<Karyawan_1, Karyawan_1> supervisor;
    public static volatile SingularAttribute<Karyawan_1, String> username;

}