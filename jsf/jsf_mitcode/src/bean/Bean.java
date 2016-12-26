package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.datatable.DataTable;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.essecorp.notifyit.ejb.session.IAccessNotifyItDaoLocal;
import com.essecorp.notifyit.parametros.ejb.entity.Plantilla;

@ManagedBean
@ViewScoped
public class Bean {

	private LazyDataModel<Plantilla> lazyDataModel;
	@EJB(lookup = "java:global/Notifyit-ejb/NotifyItSessionStatelessBean!com.essecorp.notifyit.ejb.session.IAccessNotifyItDaoLocal")
	private IAccessNotifyItDaoLocal mainPersistenceManager;

	@PostConstruct
	public void init() {
		lazyDataModel = new LazyDataModel<Plantilla>() {
			@Override
			public List<Plantilla> load(int first, int pageSize, String sortField, SortOrder sortOrder,
					Map<String, Object> filters) {

				lazyDataModel.setRowCount(mainPersistenceManager.countPlantillas(filters));

				List<Plantilla> data = new ArrayList<Plantilla>();
				// pageSize is scrollRows="20" in the datatable
				// data = userEJB.findAll(first, pageSize);
				data = mainPersistenceManager.findPlantillas(first, pageSize, sortField, sortOrder.toString(), filters);
				// findAll is using
				// query.setFirstResult(first).setMaxResults(pageSize).getResultList()

				// rowCount
				return data;
			}

		};
	}

	public void filter(DataTable table) {
		// ...
		System.out.println("daflkjdlkf");
		table.loadLazyData();
	}

	public LazyDataModel<Plantilla> getLazyDataModel() {
		return lazyDataModel;
	}

	public void setLazyDataModel(LazyDataModel<Plantilla> lazyDataModel) {
		this.lazyDataModel = lazyDataModel;
	}

	// setters and getters for userEJB
}

//


@Override
public List<Plantilla> findPlantillas(int first, int pageSize, String sortField, String sortOrder, Map<String, Object> filters) {
	
	
	String sql = "SELECT p FROM Plantilla p WHERE p.activo = :activo  AND p.nombre LIKE :nombre AND p.correoorigen LIKE :correoorigen";
	String sort = "nombre", order = "DESC";
	String nombre = "", correoorigen="";
	
	
	if (sortField != null && sortOrder != null) {
		 order = sortOrder.equals("ASCENDING") ? "ASC" : "DESC";
		sort = sortField;
		
	}
	if (filters.get("nombre") !=null) {
		nombre = filters.get("nombre").toString();
	}
	if (filters.get("correoorigen") !=null) {
		correoorigen = filters.get("correoorigen").toString();
	}
	
	
	sql = sql + " ORDER BY p."+sort+" "+order;
	List<Plantilla> plantillas = new ArrayList<Plantilla>();
	try {
		Query query = em.createQuery(sql);
		query.setParameter("activo", true);
		query.setParameter("nombre", "%"+nombre+"%");
		query.setParameter("correoorigen", "%"+correoorigen+"%");
		query.setFirstResult(first);
		query.setMaxResults(pageSize);
		plantillas = query.getResultList();
	}catch  (Exception exc) {
		exc.printStackTrace();
	}
	return plantillas;
}



@Override
public int countPlantillas(Map<String, Object> filters) {
	String sql = "SELECT COUNT(p) FROM Plantilla p WHERE p.activo = :activo  AND p.nombre LIKE :nombre AND p.correoorigen LIKE :correoorigen";
	String nombre ="", correoorigen ="";
	if (filters.get("nombre") !=null) {
		nombre = filters.get("nombre").toString();
	}
	if (filters.get("correoorigen") !=null) {
		correoorigen = filters.get("correoorigen").toString();
	}
	try {
		Query query = em.createQuery(sql);
		query.setParameter("activo", true);
		query.setParameter("nombre", "%"+nombre+"%");
		query.setParameter("correoorigen", "%"+correoorigen+"%");
		return Long.valueOf(query.getSingleResult().toString()).intValue();
		} catch (Exception exc) {
			exc.printStackTrace();
		}
		return 0;
}