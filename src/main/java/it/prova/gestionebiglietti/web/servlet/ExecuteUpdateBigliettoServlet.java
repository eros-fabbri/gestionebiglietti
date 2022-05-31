package it.prova.gestionebiglietti.web.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestionebiglietti.model.Biglietto;
import it.prova.gestionebiglietti.service.MyServiceFactory;
import it.prova.gestionebiglietti.utility.UtilityBigliettoForm;


/**
 * Servlet implementation class ExecuteUpdateBigliettoServlet
 */
@WebServlet("/ExecuteUpdateBigliettoServlet")
public class ExecuteUpdateBigliettoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	// estraggo input
	   String destinazioneInputParam = request.getParameter("destinazione");
		String provenienzaInputParam = request.getParameter("provenienza");
		String prezzoInputStringParam = request.getParameter("prezzo");
		String dataStringParam = request.getParameter("data");
		String descrizioneStringParam = request.getParameter("descrizione");
		String bigliettoIdToParse = request.getParameter("bigliettoId");
		
		

		// preparo un bean (che mi serve sia per tornare in pagina
		// che per inserire) e faccio il binding dei parametri
		Biglietto bigliettoInstance = UtilityBigliettoForm.createBigliettoFromParams(descrizioneStringParam,
				destinazioneInputParam, provenienzaInputParam, prezzoInputStringParam, dataStringParam);

		if(!NumberUtils.isCreatable(bigliettoIdToParse)) {
			request.setAttribute("biglietto_modifica", bigliettoInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			System.out.println(bigliettoIdToParse);
			request.getRequestDispatcher("/biglietto/modifica.jsp").forward(request, response);
			return;
		}
		// se la validazione non risulta ok
		if (!UtilityBigliettoForm.validateBigliettoBean(bigliettoInstance)) {
			request.setAttribute("biglietto_modifica", bigliettoInstance);
			request.setAttribute("errorMessage", "Attenzione sono presenti errori di validazione");
			request.getRequestDispatcher("/biglietto/modifica.jsp").forward(request, response);
			return;
		}

		bigliettoInstance.setId(Long.parseLong(bigliettoIdToParse));
		// se sono qui i valori sono ok quindi posso creare l'oggetto da inserire
		// occupiamoci delle operazioni di business
		try {
			MyServiceFactory.getBigliettoServiceInstance().aggiorna(bigliettoInstance);
			request.setAttribute("listaBigliettiAttribute", MyServiceFactory.getBigliettoServiceInstance().listAll());
			request.setAttribute("successMessage", "Operazione effettuata con successo");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Attenzione si è verificato un errore.");
			request.getRequestDispatcher("/index.jsp").forward(request, response);
			return;
		}

		// andiamo ai risultati
		request.getRequestDispatcher("/biglietto/results.jsp").forward(request, response);
	}

}
