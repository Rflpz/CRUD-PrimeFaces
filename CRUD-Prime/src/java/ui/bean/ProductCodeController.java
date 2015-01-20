package ui.bean;

import dat.entity.ProductCode;
import dat.facade.ProductCodeFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "productCodeController")
@ViewScoped
public class ProductCodeController extends AbstractController<ProductCode> {

    @EJB
    private ProductCodeFacade ejbFacade;

    /**
     * Initialize the concrete ProductCode controller bean. The
     * AbstractController requires the EJB Facade object for most operations.
     * <p>
     * In addition, this controller also requires references to controllers for
     * parent entities in order to display their information from a context
     * menu.
     */
    @PostConstruct
    @Override
    public void init() {
        super.setFacade(ejbFacade);
        FacesContext context = FacesContext.getCurrentInstance();
    }

    public ProductCodeController() {
        // Inform the Abstract parent controller of the concrete ProductCode?cap_first Entity
        super(ProductCode.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Product entities that are
     * retrieved from ProductCode?cap_first and returns the navigation outcome.
     *
     * @return navigation outcome for Product page
     */
    public String navigateProductCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Product_items", this.getSelected().getProductCollection());
        }
        return "/entity/product/index";
    }

}
