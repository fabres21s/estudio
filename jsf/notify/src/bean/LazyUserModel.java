package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.essecorp.notifyit.ejb.session.IAccessNotifyItDaoLocal;
import com.essecorp.notifyit.parametros.ejb.entity.Plantilla;

public class LazyUserModel extends LazyDataModel<Plantilla> {
	/**
	* 
	*/
	private static final long serialVersionUID = 5942809554423928521L;
	private IAccessNotifyItDaoLocal mainPersistenceManager;
	private int lastPageSize = -1;
	
	public LazyUserModel(IAccessNotifyItDaoLocal mainPersistenceManager) {
		this.mainPersistenceManager = mainPersistenceManager;
	}

	@Override
	public List<Plantilla> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		
	
		
		this.setRowCount(mainPersistenceManager.countPlantillas( filters));

		List<Plantilla> data = new ArrayList<Plantilla>();
		// pageSize is scrollRows="20" in the datatable
		// data = userEJB.findAll(first, pageSize);
		data = mainPersistenceManager.findPlantillas(first, pageSize, sortField, sortOrder.toString(), filters);
		// findAll is using
		// query.setFirstResult(first).setMaxResults(pageSize).getResultList()

		
		
		// rowCount
		return data;
	}

	

}