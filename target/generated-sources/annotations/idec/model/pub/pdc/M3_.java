package idec.model.pub.pdc;

import idec.model.pub.pdc.M2;
import idec.model.pub.pdc.M4;
import idec.model.pub.pdc.Mc;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:02")
@StaticMetamodel(M3.class)
public class M3_ { 

    public static volatile SingularAttribute<M3, String> cee;
    public static volatile ListAttribute<M3, Mc> mcList;
    public static volatile SingularAttribute<M3, Date> allaDataAdmin;
    public static volatile ListAttribute<M3, M4> m4List;
    public static volatile SingularAttribute<M3, Date> dallaDataAdmin;
    public static volatile SingularAttribute<M3, Character> chkOltreAnno;
    public static volatile SingularAttribute<M3, Character> chkEntroAnno;
    public static volatile SingularAttribute<M3, Integer> ceeMasCod;
    public static volatile SingularAttribute<M3, String> admin;
    public static volatile SingularAttribute<M3, String> privato;
    public static volatile SingularAttribute<M3, Integer> ceeMas2Cod;
    public static volatile SingularAttribute<M3, String> ceeMas2CodDes;
    public static volatile SingularAttribute<M3, Character> chkOltre5Anni;
    public static volatile SingularAttribute<M3, M2> m2id;
    public static volatile SingularAttribute<M3, Integer> m3Id;
    public static volatile SingularAttribute<M3, Character> chkEntro5Anni;
    public static volatile SingularAttribute<M3, Integer> cgTipoMasCod;

}