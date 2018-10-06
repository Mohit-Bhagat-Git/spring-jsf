package gui.admin.java;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.TreeNode;
import gui.admin.java.DocumentService;
 
@ManagedBean(name = "ttEditView")
@ViewScoped
public class EditView implements Serializable{
     
    private TreeNode root;
     
    private TreeNode root2;
     
    @ManagedProperty("#{documentService}")
    private DocumentService service;
     
    @PostConstruct
    public void init() {
        root = service.createDocuments();
        root2 = service.createDocuments();
    }
 
    public TreeNode getRoot() {
        return root;
    }
 
    public TreeNode getRoot2() {
        return root2;
    }
     
    public void setService(DocumentService service) {
        this.service = service;
    }
     
    public void onRowEdit(RowEditEvent event) {
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
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    } 
}