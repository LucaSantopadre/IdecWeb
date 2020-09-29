package idec.model.pub.pdc;

import idec.model.pub.pdc.M2;
import idec.model.pub.pdc.M3;
import idec.model.pub.pdc.M4;
import idec.model.pub.pdc.M5;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:03")
@StaticMetamodel(Mc.class)
public class Mc_ { 

    public static volatile SingularAttribute<Mc, Integer> sezioneF24;
    public static volatile SingularAttribute<Mc, Character> scadenzario;
    public static volatile SingularAttribute<Mc, Date> allaDataAdmin;
    public static volatile SingularAttribute<Mc, Date> dallaDataAdmin;
    public static volatile SingularAttribute<Mc, String> admin;
    public static volatile SingularAttribute<Mc, String> privato;
    public static volatile SingularAttribute<Mc, Integer> ceeMas3Cod;
    public static volatile SingularAttribute<Mc, Character> ammortamento;
    public static volatile SingularAttribute<Mc, Character> cespite;
    public static volatile SingularAttribute<Mc, Character> segno;
    public static volatile SingularAttribute<Mc, Character> fix;
    public static volatile SingularAttribute<Mc, Character> annoObbligatorio;
    public static volatile SingularAttribute<Mc, Integer> cgTipoMasCod;
    public static volatile SingularAttribute<Mc, Character> ceePadre;
    public static volatile SingularAttribute<Mc, Character> variazioneFiscale;
    public static volatile SingularAttribute<Mc, Integer> mcId;
    public static volatile SingularAttribute<Mc, String> ceeContoCod;
    public static volatile SingularAttribute<Mc, Integer> ceeMasCod;
    public static volatile SingularAttribute<Mc, Character> codObbl;
    public static volatile SingularAttribute<Mc, Integer> ceeMas2Cod;
    public static volatile SingularAttribute<Mc, Integer> ceeMas4Cod;
    public static volatile SingularAttribute<Mc, M4> m4id;
    public static volatile SingularAttribute<Mc, M5> m5id;
    public static volatile SingularAttribute<Mc, M2> m2id;
    public static volatile SingularAttribute<Mc, Character> codAtt;
    public static volatile SingularAttribute<Mc, M3> m3id;
    public static volatile SingularAttribute<Mc, Character> detrazioni;
    public static volatile SingularAttribute<Mc, String> ceeContoCodDes;
    public static volatile SingularAttribute<Mc, String> descrizioneAggiuntiva;

}