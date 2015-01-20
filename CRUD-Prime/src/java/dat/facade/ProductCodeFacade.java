/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.facade;

import dat.entity.ProductCode;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Rflpz
 */
@Stateless
public class ProductCodeFacade extends AbstractFacade<ProductCode> {
    @PersistenceContext(unitName = "CRUD-PrimePU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ProductCodeFacade() {
        super(ProductCode.class);
    }
    
}
