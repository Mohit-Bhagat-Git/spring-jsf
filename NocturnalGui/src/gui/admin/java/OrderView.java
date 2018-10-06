package gui.admin.java;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import gui.main.model.java.GuiOrder;
import gui.main.model.java.GuiSubOrder;

@ManagedBean(name = "orderView")
@SessionScoped
public class OrderView implements Serializable {

	/**
	 * 
	 */
	private TreeNode root;
	private static final long serialVersionUID = -3047011018104599861L;
	private List<GuiOrder> ordersList;
	private Set<GuiOrder> toUpdate = new HashSet<GuiOrder>();
	

	@ManagedProperty("#{orderService}")
	private OrderService service;
	
	@ManagedProperty("#{guiOrder}")
	private GuiOrder guiOrder;

	@PostConstruct
	public void init() {
		root = new DefaultTreeNode();
		ordersList = new LinkedList<GuiOrder>();
	}

	public String search(){
		root =  service.readAllOrdersTree(guiOrder);
		ordersList = service.readAllOrders(guiOrder);
		System.out.println(ordersList);
		
		return null;
		
	}
	
	public void saveAction() {
	    System.out.println("save button clicked");
		//get all existing value but set "editable" to false 
		for (GuiOrder order : ordersList){
			order.setEditable(false);
			System.out.println(order.getOrderStatus());
		}
		
		//return to current page
		
		
	}
	
	public void saveAll(){
		
		service.updateOrders(toUpdate);
		
		
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}

	public Set<GuiOrder> getToUpdate() {
		return toUpdate;
	}

	public void setToUpdate(Set<GuiOrder> toUpdate) {
		this.toUpdate = toUpdate;
	}

	public void editAction(GuiOrder order) {
	    System.out.println("edit action called");
		order.setEditable(true);
	}
	
	public GuiOrder getGuiOrder() {
		return guiOrder;
	}

	public void setGuiOrder(GuiOrder guiOrder) {
		this.guiOrder = guiOrder;
	}

	public List<GuiOrder> getOrdersList() {
		return ordersList;
	}

	public void setOrdersList(List<GuiOrder> ordersList) {
		this.ordersList = ordersList;
	}

	public OrderService getService() {
		return service;
	}

	public void setService(OrderService service) {
		this.service = service;
	}

	public void onRowEdit(RowEditEvent event) {
		TreeNode obj = (TreeNode) event.getObject();
		if(obj.getType()=="order"){
			System.out.println("main order edited");
			GuiOrder mainOrder = (GuiOrder) obj.getData();
			toUpdate.add(mainOrder);
		}
		
		if(obj.getType()=="subOrder"){
			System.out.println("sub order edited");
			TreeNode parent = obj.getParent();
			GuiOrder mainOrder = (GuiOrder) parent.getData();
			toUpdate.add(mainOrder);
		}
		
		FacesMessage msg = new FacesMessage("Document Edited", ((TreeNode) event.getObject()).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onRowCancel(RowEditEvent event) {
		FacesMessage msg = new FacesMessage("Edit Cancelled", ((TreeNode) event.getObject()).toString());
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public void onCellEdit(CellEditEvent event) {
		Object oldValue = event.getOldValue();
		Object newValue = event.getNewValue();

		if (newValue != null && !newValue.equals(oldValue)) {
			FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed",
					"Old: " + oldValue + ", New:" + newValue);
			FacesContext.getCurrentInstance().addMessage(null, msg);
		}
	}
}
