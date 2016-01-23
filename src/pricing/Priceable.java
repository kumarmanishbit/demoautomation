package pricing;

public class Priceable {

   private Integer id;
   private Integer procedureMappingId;
   private Integer providerParticipationId;
   private Integer providerNetwork;
   private Integer latitude, longitude;
   private Integer amount, priceLower, priceUppper;
   
   public Priceable(Integer id, Integer procedureMappingId, Integer providerParticipation, Integer providerNetwork,
            Integer latitude, Integer longitude, Integer amount, Integer priceLower, Integer priceUppper) {
      this.id = id;
      this.procedureMappingId = procedureMappingId;
      this.providerParticipationId = providerParticipation;
      this.providerNetwork = providerNetwork;
      this.latitude = latitude;
      this.longitude = longitude;
      this.amount = amount;
      this.priceLower = priceLower;
      this.priceUppper = priceUppper;
   }
   
   public Integer getId() {
      return id;
   }
   
   public Integer getProcedureMappingId() {
      return procedureMappingId;
   }
   
   public Integer getProviderParticipation() {
      return providerParticipationId;
   }
   
   public Integer getProviderNetwork() {
      return providerNetwork;
   }
   
   public Integer getLatitude() {
      return latitude;
   }
   
   public Integer getLongitude() {
      return longitude;
   }
   
   public Integer getAmount() {
      return amount;
   }
   
   public Integer getPriceLower() {
      return priceLower;
   }
   
   public Integer getPriceUppper() {
      return priceUppper;
   }
}