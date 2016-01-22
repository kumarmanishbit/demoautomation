package pricing;

import java.util.ArrayList;
import java.util.List;

public class PricingMongoInsert {
   public static void main(String[] args) {
      List<Priceable> priceableList = new ArrayList<Priceable>();
      Priceable priceable = new Priceable(1, 1, 0, 0, 0, 0, 0, 0, 0);
      priceableList.add(priceable);
      priceableList.add(priceable);
      String mongoQuery = createMongoQuery(priceableList);
      System.out.println(mongoQuery);
   }

   private static String createMongoQuery(List<Priceable> priceableList) {
      StringBuilder mongoInsertStatement= new StringBuilder();
      mongoInsertStatement.append("use bucket_1;\n");
      mongoInsertStatement.append("db.priceables.remove({\"pm\":"+priceableList.get(0).getProcedureMappingId()+"});\n");
      mongoInsertStatement.append("db.priceables.insert([");
      
      for (Priceable priceable : priceableList) {
         mongoInsertStatement.append("\n{\"_id\" : NumberLong(\""+ priceable.getId()
          +"\"), \"pm\": NumberInt("+priceable.getProcedureMappingId()
          +"), \"pp\": NumberInt("+priceable.getProviderParticipation()
          +"), \"pn\": NumberInt("+priceable.getProviderNetwork()
          +"), \"loc\": ["+priceable.getLongitude()+", "+priceable.getLatitude()
          +"], \"amt\": NumberInt("+priceable.getAmount()
          +"), \"pl\": NumberInt("+priceable.getPriceLower()
          +"), \"pu\": NumberInt("+priceable.getPriceUppper()
          +"), \"spe\": false, \"pi\": NumberInt(0), \"pt\": \"rg\"},");
      }
      mongoInsertStatement.deleteCharAt(mongoInsertStatement.length()-1); 
      mongoInsertStatement.append("\n]);");
      return mongoInsertStatement.toString();
   }
}
