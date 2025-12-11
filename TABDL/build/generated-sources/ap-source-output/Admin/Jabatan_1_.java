package Admin;

import Admin.Karyawan_1;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-01T20:36:14", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Jabatan_1.class)
public class Jabatan_1_ { 

    public static volatile CollectionAttribute<Jabatan_1, Karyawan_1> karyawanCollection;
    public static volatile SingularAttribute<Jabatan_1, String> namaJabatan;
    public static volatile SingularAttribute<Jabatan_1, String> levelJabatan;
    public static volatile SingularAttribute<Jabatan_1, Integer> idJabatan;

}