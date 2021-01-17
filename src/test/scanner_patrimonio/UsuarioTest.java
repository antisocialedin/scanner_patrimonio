package scanner_patrimonio;

import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Test;

import com.scanner_patrimonio.model.models.Usuario;
import com.scanner_patrimonio.model.service.UsuarioService;

import scanner_patrimonio.List;

public class UsuarioTest {
	
	//@Test(expected = Exception.class)
	public void salvarUsuarioBancoTest() {
		
		Usuario usuario = new Usuario();
		
		//usuario.setId(1);
		usuario.setUsername("edin");
		usuario.setPassword("123456");
		usuario.setEmail("eder@hotmail.com");
		usuario.setAtivo(false);
		usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.save(usuario);

		System.out.println("Gravando Registro do Usuario no Banco de dados");
		assertTrue(true);
	}
	
	//@Test(expected = Exception.class)
	public void alterarUsuarioBancoDadosTeste() {
			
		Usuario usuario = new Usuario();
				
		usuario.setId(1);
		
		//usuario.setUsername("Lucas da Silva brito");
		//usuario.setPassword("123456");
		//usuario.setEmail("roberto@carlos.com.br");
		//usuario.setAtivo(false);
		//usuario.setAdmin(false);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuario = usuarioService.findById(usuario.getId());
		
		System.out.println(usuario.toString());
		
		usuario.setEmail("brito.s@alumno.ifsp.edu.br");
		
		usuarioService.save(usuario);
		
		System.out.println("Alteracao do usuario no banco de dados");
		
		//assertTrue(true);
	}
		
	//@Test(expected = Exception.class)
	public void listarTodosUsuarioTabelaUsuario() {
		UsuarioService usuarioService = new UsuarioService();
		List<Usuario> listaUsuario = usuarioService.findAll();
		
		for(Usuario usuario : listaUsuario) {
			System.out.println(usuario.toString());
		}
	}
		
	//@Test
	public void excluirUsuarioDaTabela() {
		Usuario usuario = new Usuario();
		usuario.setId(2);
		
		UsuarioService usuarioService = new UsuarioService();
		
		usuarioService.delete(usuario);
	}
}
