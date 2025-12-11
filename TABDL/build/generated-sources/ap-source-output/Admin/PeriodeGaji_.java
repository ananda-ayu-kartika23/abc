package Admin;

import java.util.Date;
import javax.annotation.processing.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="org.eclipse.persistence.internal.jpa.modelgen.CanonicalModelProcessor", date="2025-12-01T20:36:14", comments="EclipseLink-2.7.12.v20230209-rNA")
@StaticMetamodel(PeriodeGaji.class)
public class PeriodeGaji_ { 

    public static volatile SingularAttribute<PeriodeGaji, Integer> idPeriodeGaji;
    public static volatile SingularAttribute<PeriodeGaji, Integer> tahun;
    public static volatile SingularAttribute<PeriodeGaji, String> bulan;
    public static volatile SingularAttribute<PeriodeGaji, Date> tanggalGajian;

}