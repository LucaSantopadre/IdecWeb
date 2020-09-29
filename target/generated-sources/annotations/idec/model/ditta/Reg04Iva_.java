package idec.model.ditta;

import idec.model.ditta.Reg03Rigo;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:03")
@StaticMetamodel(Reg04Iva.class)
public class Reg04Iva_ { 

    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaRipartoAcq;
    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaPercDet;
    public static volatile SingularAttribute<Reg04Iva, Reg03Rigo> reg03Rigo;
    public static volatile SingularAttribute<Reg04Iva, Long> reg04IvaId;
    public static volatile SingularAttribute<Reg04Iva, String> ivaOp;
    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaImposta;
    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaImportoDet;
    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaPercNonDet;
    public static volatile SingularAttribute<Reg04Iva, String> ivaFun;
    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaPercDed;
    public static volatile SingularAttribute<Reg04Iva, String> ivaNatura;
    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaImportoNonDet;
    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaPerc;
    public static volatile SingularAttribute<Reg04Iva, BigDecimal> ivaImportoDed;

}