package Admin;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-01T20:36:14", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Cuti_1.class)
public class Cuti_1_ { 

    public static volatile SingularAttribute<Cuti_1, Integer> idAdmin;
    public static volatile SingularAttribute<Cuti_1, Date> tanggalMulai;
    public static volatile SingularAttribute<Cuti_1, Date> tanggalSelesai;
    public static volatile SingularAttribute<Cuti_1, Integer> idKaryawan;
    public static volatile SingularAttribute<Cuti_1, String> statusCuti;
    public static volatile SingularAttribute<Cuti_1, Integer> idCuti;
    public static volatile SingularAttribute<Cuti_1, Integer> supervisor;
    public static volatile SingularAttribute<Cuti_1, String> alasan;

}