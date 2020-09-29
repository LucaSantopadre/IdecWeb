package idec.model.ditta;

import idec.model.ditta.Pc2048PK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:03")
@StaticMetamodel(Pc2048.class)
public class Pc2048_ { 

    public static volatile SingularAttribute<Pc2048, String> siglaNazione;
    public static volatile SingularAttribute<Pc2048, Integer> comune;
    public static volatile SingularAttribute<Pc2048, Integer> associaCodice;
    public static volatile SingularAttribute<Pc2048, String> ceeCodiceNome;
    public static volatile SingularAttribute<Pc2048, String> ceeCodiceCognome;
    public static volatile SingularAttribute<Pc2048, Character> annoObbligatorio;
    public static volatile SingularAttribute<Pc2048, String> ragioneSociale;
    public static volatile SingularAttribute<Pc2048, Pc2048PK> pc2048PK;
    public static volatile SingularAttribute<Pc2048, Character> privato;
    public static volatile SingularAttribute<Pc2048, Long> partIva;
    public static volatile SingularAttribute<Pc2048, String> fiscCod;

}