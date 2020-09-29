/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package idec.controller.ditta.reg.session;

import idec.model.ditta.Reg03Rigo;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Luca
 */
@Stateless
public class Reg03RigoFacade extends AbstractFacade<Reg03Rigo> {

    @PersistenceContext(unitName = "idec_IdecWeb_war_09.01.01PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public Reg03RigoFacade() {
        super(Reg03Rigo.class);
    }
    
}
