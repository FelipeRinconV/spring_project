package springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import springboot.app.models.entity.Cliente;

@Repository("clienteDaoJPA")
public class ClienteDaoImpl implements IClienteDao {

	public static final String LISTAR_TODOS = "listarTodos";

	@PersistenceContext
	private EntityManager em;

	@Transactional(readOnly = true)
	public List<Cliente> finAll() {
                                           
		TypedQuery<Cliente> query = em.createNamedQuery(Cliente.LISTAR_TODOS, Cliente.class);
        
		return query.getResultList();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	

	@Override
	@Transactional
	public void save(Cliente cliente) {

		em.persist(cliente);

	}

	@Override
	public Cliente findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Cliente.class, id);
	}

}
