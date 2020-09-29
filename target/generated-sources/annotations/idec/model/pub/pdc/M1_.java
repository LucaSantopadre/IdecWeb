package idec.model.pub.pdc;

import idec.model.pub.pdc.M2;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:03")
@StaticMetamodel(M1.class)
public class M1_ { 

    public static volatile SingularAttribute<M1, Date> allaDataAdmin;
    public static volatile SingularAttribute<M1, Date> dallaDataAdmin;
    public static volatile ListAttribute<M1, M2> m2List;
    public static volatile SingularAttribute<M1, String> admin;
    public static volatile SingularAttribute<M1, Integer> m1Id;
    public static volatile SingularAttribute<M1, BigDecimal> m1IdVisual;
    public static volatile SingularAttribute<M1, String> m1Descr;
    public static volatile SingularAttribute<M1, String> m1Totale;

}