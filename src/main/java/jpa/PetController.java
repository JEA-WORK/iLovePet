package jpa;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/jpa/Pet")
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
			String raca = valor(req, "raça", "");
			String descricao = valor(req, "descricao", "");
			String dono = valor(req, "dono", "");
			String telefone = valor(req, "telefone", "");
			
			if (op.equals("incluir")) {
				PetDao.inclui(cod, nome, apelido, raca, descricao, dono, telefone);
				msg = "Inclusão realizada com sucesso.";
			} else if (op.equals("alterar")) {
				PetDao.alterar(cod, nome);
				msg = "Alteração realizada com sucesso.";
			} else if (op.equals("excluir")) {
				PetDao.excluir(cod);
				msg = "Exclusão realizada com sucesso.";
			} else if (op.equals("")) {
				msg = "";
			} else {
				throw new IllegalArgumentException("Operação \"" + op + "\" não suportada.");
			}
			req.setAttribute("msg", msg);
			req.setAttribute("pets", PetDao.listar());
			
			req.getRequestDispatcher("ProfessorView.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace(resp.getWriter());
			}
		}
	}


