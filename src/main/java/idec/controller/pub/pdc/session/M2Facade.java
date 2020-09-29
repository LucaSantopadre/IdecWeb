/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.pub.pdc.session;

import idec.model.pub.pdc.M2;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luca
 */
@Stateless
public class M2Facade extends AbstractFacade<M2> {

    @PersistenceContext(unitName = "idec_IdecWeb_war_09.01.01PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public M2Facade() {
        super(M2.class);
    }
    
}
