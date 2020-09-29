package idec.model.ditta;

import idec.model.ditta.Reg01;
import idec.model.ditta.Reg03RigoPK;
import idec.model.ditta.Reg04Iva;
import idec.model.ditta.RegDoc;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:03")
@StaticMetamodel(Reg03Rigo.class)
public class Reg03Rigo_ { 

    public static volatile SingularAttribute<Reg03Rigo, String> localita;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> importoConto;
    public static volatile SingularAttribute<Reg03Rigo, RegDoc> regDoc;
    public static volatile SingularAttribute<Reg03Rigo, Reg03RigoPK> reg03RigoPK;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> percIrapDeducibile;
    public static volatile SingularAttribute<Reg03Rigo, Reg04Iva> reg04Iva;
    public static volatile SingularAttribute<Reg03Rigo, Reg01> reg01NumRegId;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> percIva;
    public static volatile SingularAttribute<Reg03Rigo, String> funzConto;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> avere;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> ivaDetraibile;
    public static volatile SingularAttribute<Reg03Rigo, Boolean> pagatoDoc;
    public static volatile SingularAttribute<Reg03Rigo, String> vendor;
    public static volatile SingularAttribute<Reg03Rigo, String> ivaNatura;
    public static volatile SingularAttribute<Reg03Rigo, String> funzImmobProdotto;
    public static volatile SingularAttribute<Reg03Rigo, String> conto;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> ripPerc;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> dare;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> percInerente;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> percIiDeducibile;
    public static volatile SingularAttribute<Reg03Rigo, String> opIva;
    public static volatile SingularAttribute<Reg03Rigo, String> accertamento;
    public static volatile SingularAttribute<Reg03Rigo, String> funIva;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> nonDetraibile;
    public static volatile SingularAttribute<Reg03Rigo, String> fV;
    public static volatile SingularAttribute<Reg03Rigo, String> centroCosto;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> percIvaDeducibile;
    public static volatile SingularAttribute<Reg03Rigo, String> codAtt;
    public static volatile SingularAttribute<Reg03Rigo, BigDecimal> percDetIva;
    public static volatile SingularAttribute<Reg03Rigo, String> rateo;
    public static volatile SingularAttribute<Reg03Rigo, String> subconto;

}