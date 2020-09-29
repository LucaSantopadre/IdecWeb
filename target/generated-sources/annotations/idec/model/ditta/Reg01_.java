package idec.model.ditta;

import idec.model.ditta.Reg03Rigo;
import java.math.BigInteger;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:02")
@StaticMetamodel(Reg01.class)
public class Reg01_ { 

    public static volatile SingularAttribute<Reg01, BigInteger> protocolloLibro;
    public static volatile SingularAttribute<Reg01, Integer> libro;
    public static volatile SingularAttribute<Reg01, Long> numRegId;
    public static volatile ListAttribute<Reg01, Reg03Rigo> reg03RigoList;
    public static volatile SingularAttribute<Reg01, String> reparto;
    public static volatile SingularAttribute<Reg01, Date> dataReg;

}