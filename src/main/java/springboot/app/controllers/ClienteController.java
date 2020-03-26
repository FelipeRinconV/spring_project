package springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import springboot.app.models.dao.IClienteDao;
import springboot.app.models.entity.Cliente;

@Controller
public class ClienteController {

	@Autowired
	@Qualifier("clienteDaoJPA")
	private IClienteDao clienteDao;

	@RequestMapping(value = "/listar", method = RequestMethod.GET)
	public String listar(Model model) {

		model.addAttribute("titulo", "listado de clientes");
		model.addAttribute("clientes", clienteDao.finAll());
		return "listar";

	}

	@GetMapping(value = "/form")
	public String crear(Model model) {

		Cliente cliente = new Cliente();

		// (2)
		model.addAttribute("cliente", cliente);
		model.addAttribute("titulo", "Formulario de clientes");

		return "form";

	}

	@RequestMapping(value = "/form/{id}")
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model) {

		Cliente cliente = null;

		if (id > 0) {

			cliente = clienteDao.findOne(id);

		} else {

			return "redirec:/listar";
		}

		model.put("cliente", cliente);

		model.put("titulo", "Editar cliente");

		return "form";
	}

	@PostMapping(value = "/form")
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model) {

		if (result.hasErrors()) {
			// el objeto lo pasa de forma automatica si se llama igual que (2)
			model.addAttribute("titulo", "Formulario clientes");

			return "form";
		}

		clienteDao.save(cliente);

		return "redirect:listar";

	}
}
