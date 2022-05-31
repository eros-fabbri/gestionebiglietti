package it.prova.gestionebiglietti.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

import it.prova.gestionebiglietti.model.Biglietto;


//nel nome della classe vi è Biglietto in quanto è una classe specifica
public class UtilityBigliettoForm {

	public static Biglietto createBigliettoFromParams(String descrizioneInputParam, String destinazioneInputParam,
			String provenienzaInputParam, String prezzoInputParam, String dataInputParam) {

		Biglietto result = new Biglietto(descrizioneInputParam, destinazioneInputParam, provenienzaInputParam);

		if (NumberUtils.isCreatable(prezzoInputParam)) {
			result.setPrezzo(Integer.parseInt(prezzoInputParam));
		}
		result.setData(parseDateArrivoFromString(dataInputParam));

		return result;
	}

	public static boolean validateBigliettoBean(Biglietto articoloToBeValidated) {
		// prima controlliamo che non siano vuoti i parametri
		if (StringUtils.isBlank(articoloToBeValidated.getDescrizione())
				|| StringUtils.isBlank(articoloToBeValidated.getDestinazione())
				|| StringUtils.isBlank(articoloToBeValidated.getProvenienza())
				|| articoloToBeValidated.getPrezzo() < 1
				|| articoloToBeValidated.getData() == null) {
			return false;
		}
		return true;
	}

	public static Date parseDateArrivoFromString(String dataArrivoStringParam) {
		if (StringUtils.isBlank(dataArrivoStringParam))
			return null;

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(dataArrivoStringParam);
		} catch (ParseException e) {
			return null;
		}
	}

}
