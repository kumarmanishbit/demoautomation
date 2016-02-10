package com.castlight.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.castlight.beans.Priceable;
import com.castlight.beans.ProviderLocation;
import com.castlight.beans.ProviderLocationMapper;
import com.castlight.beans.SourceExcel;
import com.castlight.dao.ProviderLocationDao;

public class ProcessPricing {

	public String process(List<SourceExcel> sourceExcel, List<Long> procedureMappingIdList, long providerNetworkId) {
		List<Priceable> priceableList = new ArrayList<Priceable>();
		for (Long procedureMappingId : procedureMappingIdList) {
			priceableList.addAll(getPricable(sourceExcel, providerNetworkId,procedureMappingId));
		}
		return createMongoQuery(priceableList);
	}

	public String createMongoQuery(List<Priceable> priceableList) {
		StringBuilder mongoInsertStatement = new StringBuilder();
		mongoInsertStatement.append("use bucket_1;\n");
		mongoInsertStatement
				.append("db.priceables.remove({\"pm\":" + priceableList.get(0).getProcedureMappingId() + "});\n");
		mongoInsertStatement.append("db.priceables.insert([");

		for (Priceable priceable : priceableList) {
			mongoInsertStatement.append("\n{\"_id\" : NumberLong(\"" + priceable.getId() + "\"), \"pm\": NumberInt("
					+ priceable.getProcedureMappingId() + "), \"pp\": NumberInt(" + priceable.getProviderParticipation()
					+ "), \"pn\": NumberInt(" + priceable.getProviderNetwork() + "), \"loc\": ["
					+ priceable.getLongitude() + ", " + priceable.getLatitude() + "], \"amt\": NumberInt("
					+ priceable.getAmount() + "), \"pl\": NumberInt(" + priceable.getPriceLower()
					+ "), \"pu\": NumberInt(" + priceable.getPriceUppper()
					+ "), \"spe\": false, \"pi\": NumberInt(0), \"pt\": \"rg\"},");
		}
		mongoInsertStatement.deleteCharAt(mongoInsertStatement.length() - 1);
		mongoInsertStatement.append("\n]);");
		return mongoInsertStatement.toString();
	}

	public List<Priceable> getPricable(List<SourceExcel> sourceExcel, Long providerNetworkId , Long procedureMappingId) {

		List<Priceable> priceableObjects = new ArrayList<>();
		SourceExcel rowExcel = null;

		Long id = 95011546172l;

		Priceable priceable = null;

		Iterator<SourceExcel> iterator = sourceExcel.iterator();

		ProviderLocationDao providerLocationDao = new ProviderLocationDao();

		ProviderLocation providerLocation = null;

		while (iterator.hasNext()) {

			id++;

			rowExcel = iterator.next();

			providerLocation = providerLocationDao.getProviderLocationById(rowExcel.getProviderLocationId());
			
			if(providerLocation==null)
			{
				providerLocation = ProviderLocationMapper.getInstance().getLocationNameTOID().get(rowExcel.getId());
			}
			
			String[] prices = rowExcel.getEstimated_Price().split("-");

			float priceLower = 0.0f, priceUppper = 0.0f;
			if (prices.length > 1) {
				priceLower = Float.parseFloat(prices[0].replace("$", ""));
				priceUppper = Float.parseFloat(prices[1].replace("$", ""));
			} else {
				priceLower = Float.parseFloat(prices[0].replace("$", ""));
				priceUppper = priceLower;
			}

			float amount = priceUppper + priceLower;
			priceable = new Priceable(id, procedureMappingId, rowExcel.getProviderParticipaionId(),
					providerNetworkId, providerLocation.getLongitude(), providerLocation.getLatitude(), amount,
					priceLower, priceUppper);
			priceableObjects.add(priceable);

		}

		return priceableObjects;
	}

}
