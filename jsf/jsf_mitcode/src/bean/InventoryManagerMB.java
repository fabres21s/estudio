package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.essecorp.notifyit.ejb.session.IAccessNotifyItDaoLocal;
import com.essecorp.notifyit.parametros.ejb.entity.Plantilla;

@ManagedBean
@ViewScoped
public class InventoryManagerMB implements Serializable {

    private static final long serialVersionUID = -1201944101993687165L;

    @EJB(lookup = "java:global/Notifyit-ejb/NotifyItSessionStatelessBean!com.essecorp.notifyit.ejb.session.IAccessNotifyItDaoLocal")
	private IAccessNotifyItDaoLocal mainPersistenceManager;
    
    private LazyDataModel<Plantilla> model;
 


     @PostConstruct
    public void init() {
        try {
            this.model = new LazyDataModel<Plantilla>(){
                private static final long    serialVersionUID    = 1L;
                @Override
                public List<Plantilla> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                    List<Plantilla> result = mainPersistenceManager.findPlantillas(first, pageSize, sortField, sortOrder.toString(), filters);
                    model.setRowCount(mainPersistenceManager.countPlantillas(filters));
                    return result;
                }
            };
        } catch(Exception exc) {
        	
        }
}

    public LazyDataModel<Plantilla> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Plantilla> model) {
        this.model = model;
    }
}