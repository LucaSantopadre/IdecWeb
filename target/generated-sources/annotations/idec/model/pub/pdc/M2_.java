package idec.model.pub.pdc;

import idec.model.pub.pdc.M1;
import idec.model.pub.pdc.M3;
import idec.model.pub.pdc.Mc;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:03")
@StaticMetamodel(M2.class)
public class M2_ { 

    public static volatile SingularAttribute<M2, String> cee;
    public static volatile ListAttribute<M2, Mc> mcList;
    public static volatile SingularAttribute<M2, Date> allaDataAdmin;
    public static volatile SingularAttribute<M2, Date> dallaDataAdmin;
    public static volatile SingularAttribute<M2, Character> chkOltreAnno;
    public static volatile SingularAttribute<M2, BigDecimal> m2IdVisual;
    public static volatile SingularAttribute<M2, Character> chkEntroAnno;
    public static volatile SingularAttribute<M2, String> m2Totale;
    public static volatile SingularAttribute<M2, String> admin;
    public static volatile SingularAttribute<M2, String> privato;
    public static volatile SingularAttribute<M2, M1> m1Id;
    public static volatile SingularAttribute<M2, Character> chkOltre5Anni;
    public static volatile SingularAttribute<M2, Integer> m2Id;
    public static volatile ListAttribute<M2, M3> m3List;
    public static volatile SingularAttribute<M2, Character> chkEntro5Anni;
    public static volatile SingularAttribute<M2, String> m2Descr;
    public static volatile SingularAttribute<M2, Integer> ceeMasCodIdex;

}