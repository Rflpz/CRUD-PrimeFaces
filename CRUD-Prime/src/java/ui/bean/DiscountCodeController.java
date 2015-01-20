package ui.bean;

import dat.entity.DiscountCode;
import dat.facade.DiscountCodeFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "discountCodeController")
@ViewScoped
public class DiscountCodeController extends AbstractController<DiscountCode> {

    @EJB
    private DiscountCodeFacade ejbFacade;

    /**
     * Initialize the concrete DiscountCode controller bean. The
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

    public DiscountCodeController() {
        // Inform the Abstract parent controller of the concrete DiscountCode?cap_first Entity
        super(DiscountCode.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
    }

    /**
     * Sets the "items" attribute with a collection of Customer entities that
     * are retrieved from DiscountCode?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for Customer page
     */
    public String navigateCustomerCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("Customer_items", this.getSelected().getCustomerCollection());
        }
        return "/entity/customer/index";
    }

}
