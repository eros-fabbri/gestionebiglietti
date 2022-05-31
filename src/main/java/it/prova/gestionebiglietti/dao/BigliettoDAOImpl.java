package it.prova.gestionebiglietti.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TypedQuery;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;

import it.prova.gestionebiglietti.model.Biglietto;

public class BigliettoDAOImpl implements BigliettoDAO {

	private EntityManager entityManager;

	@Override
	public List<Biglietto> list() throws Exception {
		return entityManager.createQuery("from Biglietto", Biglietto.class).getResultList();
	}

	@Override
	public Biglietto findOne(Long id) throws Exception {
		return entityManager.find(Biglietto.class, id);
	}

	@Override
	public void update(Biglietto input) throws Exception {
		input = entityManager.merge(input);
	}

	@Override
	public void insert(Biglietto input) throws Exception {
		if (input == null) {
			throw new Exception("Problema valore in input");
		}
		entityManager.persist(input);
	}

	@Override
	public void delete(Biglietto input) throws Exception {
		entityManager.remove(entityManager.merge(input));
	}

	@Override
	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<Biglietto> findByExample(Biglietto biglietto) {

		StringBuilder queryBuilder = new StringBuilder("select r from Biglietto r where r.id = r.id ");
		List<String> whereClauses = new ArrayList<String>();
		Map<String, Object> paramaterMap = new HashMap<String, Object>();

		if (StringUtils.isNotEmpty(biglietto.getDescrizione())) {
			whereClauses.add(" r.descrizione  like :descrizione ");
			paramaterMap.put("descrizione", "%" + biglietto.getDescrizione() + "%");
		}
		if (StringUtils.isNotEmpty(biglietto.getDestinazione())) {
			whereClauses.add(" r.destinazione like :destinazione ");
			paramaterMap.put("destinazione", "%" + biglietto.getDestinazione() + "%");
		}
		if (StringUtils.isNotEmpty(biglietto.getProvenienza())) {
			whereClauses.add(" r.provenienza like :provenienza ");
			paramaterMap.put("provenienza", "%" + biglietto.getProvenienza() + "%");
		}
		if (biglietto.getPrezzo() < 1) {
			whereClauses.add(" r.prezzo =:prezzo ");
			paramaterMap.put("prezzo", biglietto.getPrezzo());
		}
		if (StringUtils.isNotEmpty((CharSequence) biglietto.getData())) {
			whereClauses.add(" r.data like :data ");
			paramaterMap.put("data", "%" + biglietto.getProvenienza() + "%");
		}
		
		queryBuilder.append(!whereClauses.isEmpty() ? " and " : "");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		
		TypedQuery<Biglietto> typedQuery = entityManager.createQuery(queryBuilder.toString(), Biglietto.class);
	    return typedQuery.getResultList();

	}
}
