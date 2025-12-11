package Admin;

import Admin.Karyawan_1;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-01T20:36:14", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(Divisi_1.class)
public class Divisi_1_ { 

    public static volatile CollectionAttribute<Divisi_1, Karyawan_1> karyawanCollection;
    public static volatile SingularAttribute<Divisi_1, String> namaDivisi;
    public static volatile SingularAttribute<Divisi_1, String> lokasiDivisi;
    public static volatile SingularAttribute<Divisi_1, Integer> idDivisi;

}