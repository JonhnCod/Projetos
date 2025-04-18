package sisrh.soap;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.xml.ws.WebServiceContext;

import sisrh.banco.Banco;
import sisrh.dto.Empregado;
import sisrh.dto.Empregados;
import sisrh.seguranca.Autenticador;

@WebService
@SOAPBinding(style = Style.RPC)
public class ServicoEmpregado {
	
	@Resource
	WebServiceContext context;

	@WebMethod(action = "listar")
	public Empregados listar() throws Exception {
		
		Autenticador.autenticarUsuarioSenha(context);

		Empregados empregados = new Empregados();
		
		List<Empregado> lista = Banco.listarEmpregados();
		for(Empregado emp: lista) {
			empregados.getEmpregados().add(emp);
		}
		return empregados;
	}
	
	@WebMethod(action = "listarAtivos")
	public Empregados listarAtivos() throws Exception {
		
		Autenticador.autenticarUsuarioSenha(context);
		
		Empregados empregadosAtivos = new Empregados();
		
		List<Empregado> listaAtivos = Banco.listarEmpregadosFiltrado(true);
		for(Empregado empAtv: listaAtivos) {
			empregadosAtivos.getEmpregados().add(empAtv);
		}
		return empregadosAtivos;
		
	}
	
	@WebMethod(action = "listarInativos")
	public Empregados listarInativos() throws Exception {
		

		Autenticador.autenticarUsuarioSenha(context);
		
		Empregados empregadosInativos = new Empregados();
		
		List<Empregado> listaAtivos = Banco.listarEmpregadosFiltrado(false);
		for(Empregado empAtv: listaAtivos) {
			empregadosInativos.getEmpregados().add(empAtv);
		}
		return empregadosInativos;
	}
}
