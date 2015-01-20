package ui.bean;

import dat.entity.PurchaseOrder;
import dat.facade.PurchaseOrderFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.annotation.PostConstruct;

@ManagedBean(name = "purchaseOrderController")
@ViewScoped
public class PurchaseOrderController extends AbstractController<PurchaseOrder> {

    @EJB
    private PurchaseOrderFacade ejbFacade;
    private CustomerController customerIdController;
    private ProductController productIdController;

    /**
     * Initialize the concrete PurchaseOrder controller bean. The
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
        customerIdController = context.getApplication().evaluateExpressionGet(context, "#{customerController}", CustomerController.class);
        productIdController = context.getApplication().evaluateExpressionGet(context, "#{productController}", ProductController.class);
    }

    public PurchaseOrderController() {
        // Inform the Abstract parent controller of the concrete PurchaseOrder?cap_first Entity
        super(PurchaseOrder.class);
    }

    /**
     * Resets the "selected" attribute of any parent Entity controllers.
     */
    public void resetParents() {
        customerIdController.setSelected(null);
        productIdController.setSelected(null);
    }

    /**
     * Sets the "selected" attribute of the Customer controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareCustomerId(ActionEvent event) {
        if (this.getSelected() != null && customerIdController.getSelected() == null) {
            customerIdController.setSelected(this.getSelected().getCustomerId());
        }
    }

    /**
     * Sets the "selected" attribute of the Product controller in order to
     * display its data in a dialog. This is reusing existing the existing View
     * dialog.
     *
     * @param event Event object for the widget that triggered an action
     */
    public void prepareProductId(ActionEvent event) {
        if (this.getSelected() != null && productIdController.getSelected() == null) {
            productIdController.setSelected(this.getSelected().getProductId());
        }
    }
}
