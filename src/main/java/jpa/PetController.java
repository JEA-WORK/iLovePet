package jpa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Pet")
public class PetController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2235717712386984802L;

	private String valor(HttpServletRequest req, String param, String padrao) {
		String result = req.getParameter(param);
		if (result == null) {
			result = padrao;
		}
		return result;
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String msg;
			String op = valor(req, "operacao", "");
			String cod = valor(req, "cod", "");
			String nome = valor(req, "nome", "");
			String apelido = valor(req, "apelido", "");
			String raca = valor(req, "raca", "");
			String descricao = valor(req, "descricao", "");
			String dono = valor(req, "dono", "");
			String telefone = valor(req, "telefone", "");
			
			Pet pet = new Pet();
			
			pet.setCod(cod);
			pet.setNome(nome);
			pet.setApelido(apelido);
			pet.setRaca(raca);
			pet.setDescricao(descricao);
			pet.setTelefone(telefone);
			pet.setDono(dono);
			
			if (op.equals("incluir")) {
				PetDao.getInstance().inclui(pet);
				msg = "Inclusão realizada com sucesso.";
				
			} else if (op.equals("alterar")) {
				PetDao.getInstance().alterar(pet);
				msg = "Alteração realizada com sucesso.";
				
			} else if (op.equals("excluir")) {
				//int i = Integer.parseInt(cod);
				PetDao.getInstance().remove(pet);
				msg = "Exclusão realizada com sucesso.";
			} else if (op.equals("")) {
				msg = "";
			} else {
				throw new IllegalArgumentException("Operação \"" + op + "\" não suportada.");
			}
			req.setAttribute("msg", msg);
			req.setAttribute("pets", PetDao.getInstance().findAll());
			
			req.getRequestDispatcher("PetView.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			}
		}
	}


