package idec.model.ditta;

import idec.model.ditta.Reg03Rigo;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:03")
@StaticMetamodel(RegDoc.class)
public class RegDoc_ { 

    public static volatile SingularAttribute<RegDoc, Date> dataScadDoc;
    public static volatile SingularAttribute<RegDoc, Reg03Rigo> reg03Rigo;
    public static volatile SingularAttribute<RegDoc, String> azzeraDoc;
    public static volatile SingularAttribute<RegDoc, String> numDoc;
    public static volatile SingularAttribute<RegDoc, Date> dataDoc;
    public static volatile SingularAttribute<RegDoc, String> monetaContabileDoc;
    public static volatile SingularAttribute<RegDoc, String> monetaDoc;
    public static volatile SingularAttribute<RegDoc, String> mezzoDiPagDoc;
    public static volatile SingularAttribute<RegDoc, Integer> regDocId;
    public static volatile SingularAttribute<RegDoc, String> pagatoDoc;
    public static volatile SingularAttribute<RegDoc, String> rifDoc;
    public static volatile SingularAttribute<RegDoc, String> tipoDoc;
    public static volatile SingularAttribute<RegDoc, Float> cambioDoc;
    public static volatile SingularAttribute<RegDoc, String> ggTraScadDoc;

}