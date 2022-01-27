package tqs.project.air.airinfo;


public interface AirService {
    AirRequest getAirQualityByLocal(double lon, double lat, String[] features, String[] health_recommendations);
    int getMisses();
    int getHits();
    int getRequests();
    void clearCache();
}
