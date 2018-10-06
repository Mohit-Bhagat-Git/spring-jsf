package gui.admin.java;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import gui.main.java.DBObjectMapper;
import gui.main.model.java.GuiOrder;
import gui.main.model.java.GuiSubOrder;
import nocturnal.host.dao.OrderDAOImpl;
import nocturnal.host.model.Order;
import nocturnal.host.model.SubOrder;

@ManagedBean
@NoneScoped
public class OrderService {

	private String hql = "FROM Order o where ";
	public StringBuilder sb;

	private StringBuilder prepareHQLQuery(GuiOrder obj) {

		boolean firstSearchKey = true;
		if (obj.getOrderId() != null && obj.getOrderId() != 0) {
			if (firstSearchKey) {
				sb.append("o.orderId=" + obj.getOrderId());
				firstSearchKey = false;
			} else {
				sb.append(" and o.orderId=" + obj.getOrderId());
			}
		}

		if (obj.getCustomerId() != null && obj.getCustomerId() != 0) {
			if (firstSearchKey) {
				sb.append("o.customer.customerId=" + obj.getCustomerId());
				firstSearchKey = false;
			} else {
				sb.append(" and o.customer.customerId=" + obj.getCustomerId());
			}
		}

		if (obj.getOrderStatus() != null && !obj.getOrderStatus().isEmpty()) {
			if (firstSearchKey) {
				sb.append("o.orderStatus='" + obj.getOrderStatus() + "'");
				firstSearchKey = false;
			} else {
				sb.append(" and orderStatus='" + obj.getOrderStatus() + "'");
			}
		}

		if (obj.getFromDate() != null) {
			String formattedDate = getFormattedDate(obj.getFromDate());
			if (firstSearchKey) {
				sb.append("o.orderDateTime>='" + formattedDate + "'");
				firstSearchKey = false;
			} else {
				sb.append(" and o.orderDateTime>='" + formattedDate + "'");
			}
		}

		if (obj.getToDate() != null) {
			String formattedDate = getFormattedDate(obj.getFromDate());
			if (firstSearchKey) {
				sb.append("o.orderDateTime>='" + formattedDate + "'");
				firstSearchKey = false;
			} else {
				sb.append(" and o.orderDateTime>='" + formattedDate + "'");
			}
		}

		return sb;
	}

	private String getFormattedDate(Date fromDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = sdf.format(fromDate);
		return formattedDate;
	}

	public List<Order> search(String hql) {

		try {
			List<Order> list = null;
			OrderDAOImpl dao = new OrderDAOImpl();
			// Needed to show data to presentation.

			list = dao.readAll(hql);
			return list;
		} catch (Exception e) {

		}

		return null;
	}

	public List<GuiOrder> readAllOrders(GuiOrder guiOrder) {
		DBObjectMapper dbMapper = new DBObjectMapper();
		List<Order> list = null;
		List<GuiOrder> guiOrderList = null;
		sb = new StringBuilder(hql);
		sb = prepareHQLQuery(guiOrder);
		if (!hql.equals(sb.toString())) {
			list = search(sb.toString());
			guiOrderList = new LinkedList<GuiOrder>();
		}
		try {
			for (Order main : list) {
				GuiOrder guiObject = dbMapper.hostToGui_Order(main);
				guiOrderList.add(guiObject);
			}

			return guiOrderList;
		}

		catch (Exception e) {

		}
		return null;
	}

	public TreeNode readAllOrdersTree(GuiOrder obj) {
		TreeNode root = null;
		DBObjectMapper dbMapper = new DBObjectMapper();
		List<Order> list = null;
		sb = new StringBuilder(hql);
		sb = prepareHQLQuery(obj);
		if (!hql.equals(sb.toString())) {
			list = search(sb.toString());
		}
		try {
			root = new DefaultTreeNode("root", null);
			for (Order main : list) {
				GuiOrder guiOrder = dbMapper.hostToGui_Order(main);

				// root = new CheckboxTreeNode("root",guiOrder, null);
				TreeNode mainOrder = new DefaultTreeNode("order", guiOrder, root);
				for (GuiSubOrder guiSubOrder : guiOrder.getGuiSubOrderSet()) {
					TreeNode subOrder = new DefaultTreeNode("subOrder", guiSubOrder, mainOrder);
				}

			}
			return root;
		} catch (Exception e) {
			return null;
		}
	}

	public void updateOrders(Set<GuiOrder> toUpdate) {

		Set<Order> hostToUpdate = new HashSet<Order>();
		OrderDAOImpl dao = new OrderDAOImpl();

		for (GuiOrder obj : toUpdate) {
			Order hostResult = dao.readSingleRecord(obj.getOrderId());
			// check for status change
			hostResult = (Order) applyValues(hostResult, obj);
			for (GuiSubOrder sub : obj.getGuiSubOrderSet()) {
			 hostResult = applySubValues(hostResult, sub);
			}
			hostToUpdate.add(hostResult);
		}
		
		//save into db
		dao.bulkUpdateOrder(hostToUpdate);

	}

	private Order applySubValues(Order hostResult, GuiSubOrder obj) {
		Order to = (Order) hostResult;
		GuiSubOrder from = (GuiSubOrder) obj;

		for (SubOrder host : to.getSubOrderSet()) {
			if (from.getOrderId().equals(host.getSubOrderId())) {
				host.setSubOrderStatus(from.getOrderStatus());
				host.setSubOrderStatusReason(from.getSubOrderStatusReason());

				// Changing order completion time.
				if (from.getOrderStatus().equals("C")) {
					if (from.getOrderCompletionTime() != null) {
						to.setOrderCompletionTime(from.getOrderCompletionTime());
					} else {
						to.setOrderCompletionTime(new Date());
					}
				}
			}
		}
		return to;
	}

	private Object applyValues(Order obj, GuiOrder obj2) {

		Order to = (Order) obj;
		GuiOrder from = (GuiOrder) obj2;

		// Changing order status and status reason.
		to.setOrderStatus(from.getOrderStatus());
		to.setOrderStatusReason(from.getOrderStatusReason());

		// Changing order completion time.
		if (from.getOrderStatus().equals("C")) {
			if (from.getOrderCompletionTime() != null) {
				to.setOrderCompletionTime(from.getOrderCompletionTime());
			} else {
				to.setOrderCompletionTime(new Date());
			}
		}
		return to;

	}

}
