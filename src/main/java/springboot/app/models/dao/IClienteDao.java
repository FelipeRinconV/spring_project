package springboot.app.models.dao;

import java.util.List;

import springboot.app.models.entity.Cliente;

public interface IClienteDao {
	
	
	
	public List<Cliente> finAll();
	
	public void save(Cliente cliente);
	
	
	public Cliente findOne(Long id);

}
