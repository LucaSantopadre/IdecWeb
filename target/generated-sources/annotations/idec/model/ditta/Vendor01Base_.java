package idec.model.ditta;

import idec.model.ditta.Vendor02Sede;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2017-12-29T17:30:03")
@StaticMetamodel(Vendor01Base.class)
public class Vendor01Base_ { 

    public static volatile SingularAttribute<Vendor01Base, String> vendor01Cognome;
    public static volatile SingularAttribute<Vendor01Base, String> vendor01Denominazione;
    public static volatile ListAttribute<Vendor01Base, Vendor02Sede> vendor02SedeList;
    public static volatile SingularAttribute<Vendor01Base, Integer> vendor01Id;
    public static volatile SingularAttribute<Vendor01Base, String> vendor01Conto;
    public static volatile SingularAttribute<Vendor01Base, String> vendor01Nome;
    public static volatile SingularAttribute<Vendor01Base, String> vendor01CodFisc;
    public static volatile SingularAttribute<Vendor01Base, String> vendor01Codice;
    public static volatile SingularAttribute<Vendor01Base, String> vendor01PartIva;

}