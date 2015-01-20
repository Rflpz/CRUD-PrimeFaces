package ui.bean;

import dat.entity.Customer;
import dat.facade.CustomerFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "customerController")
@ViewScoped
public class CustomerController extends AbstractController<Customer> {

    @EJB
    private CustomerFacade ejbFacade;
    private DiscountCodeController discountCodeController;
    private MicroMarketController zipController;

    /**
     * Initialize the concrete Customer controller bean. The AbstractController
     * requires the EJB Facade object for most operations.
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
        discountCodeController = context.getApplication().evaluateExpressionGet(context, "#{discountCodeController}", DiscountCodeController.class);
        zipController = context.getApplication().evaluateExpressionGet(context, "#{microMarketController}", MicroMarketController.class);
    }

    public CustomerController() {
        // Inform the Abstract parent controller of the concrete Customer?cap_first Entity
        super(Customer.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        discountCodeController.setSelected(null);
        zipController.setSelected(null);
    }

    /**
     * Sets the "items" attribute with a collection of PurchaseOrder entities
     * that are retrieved from Customer?cap_first and returns the navigation
     * outcome.
     *
     * @return navigation outcome for PurchaseOrder page
     */
    public String navigatePurchaseOrderCollection() {
        if (this.getSelected() != null) {
            FacesContext.getCurrentInstance().getExternalContext().getRequestMap().put("PurchaseOrder_items", this.getSelected().getPurchaseOrderCollection());
        }
        return "/entity/purchaseOrder/index";
    }

    /**
     * Sets the "selected" attribute of the DiscountCode controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareDiscountCode(ActionEvent event) {
        if (this.getSelected() != null && discountCodeController.getSelected() == null) {
            discountCodeController.setSelected(this.getSelected().getDiscountCode());
        }
    }

    /**
     * Sets the "selected" attribute of the MicroMarket controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareZip(ActionEvent event) {
        if (this.getSelected() != null && zipController.getSelected() == null) {
            zipController.setSelected(this.getSelected().getZip());
        }
    }
}
