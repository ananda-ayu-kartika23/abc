package Admin;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-01T20:36:14", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Absensi.class)
public class Absensi_ { 

    public static volatile SingularAttribute<Absensi, Integer> idKaryawan;
    public static volatile SingularAttribute<Absensi, Date> waktuMasuk;
    public static volatile SingularAttribute<Absensi, String> statusKehadiran;
    public static volatile SingularAttribute<Absensi, Date> waktuKeluar;
    public static volatile SingularAttribute<Absensi, Date> tanggal;
    public static volatile SingularAttribute<Absensi, Integer> idAbsensi;
    public static volatile SingularAttribute<Absensi, Date> totalWaktuKerja;

}