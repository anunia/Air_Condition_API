package tqs.project.air.airinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Arrays;
import java.util.List;

@Service
public class AirServiceImpl implements AirService {

    @Value("${breezometer.api}")
    private String url;

    @Value("${breezometer.key}")
    private String key;

    @Value("${breezometer.features}")
    private String features;

    @Autowired
    private AirRepository airRepository;

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    protected String sendGET(String lat, String lon) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(
                        this.url+
                        "lat="+lat +
                        "&lon="+lon +
                        "&key="+this.key+
                        "&features=" + this.features))
                .setHeader("User-Agent", "Java 11 HttpClient Bot")
                .build();
        System.out.println(this.url+
                        "lat="+lat +
                        "&lon="+lon +
                        "&key="+this.key+
                        "&features=" + this.features);
        
        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }

    @Override
    public AirRequest getAirQualityByLocal(double lat, double lon, String[] polutants, String[] health_recommendations) {
        AirRequest airRequest = this.airRepository.getData(lat, lon);
        if (airRequest == null){
            try {
                String getResult = sendGET("" + lat, "" + lon);
//               airRequest = new AirRequest(getResult);

                airRequest = this.airRepository.putData(lat, lon, getResult);
            } catch (Exception e){
                return null;
            }
        }

        List<String> allFeatures = Arrays.asList("co", "no2", "o3", "pm10", "pm25", "so2");
        List<String> featuresList = Arrays.asList(features);

        for (String feature: allFeatures) {
            if (!featuresList.contains(feature)) {
                airRequest.excludeAirMetric(feature);
            }
        }

        List<String> allRecom = Arrays.asList("general_population", "elderly", "lung_diseases", "heart_diseases", "active", "pregnant_women","children");
        List<String> recomList = Arrays.asList(health_recommendations);

        for (String recom: allRecom) {
            if (!recomList.contains(recom)) {
                airRequest.excludeAirMetric(recom);
                System.out.println(recom);
            }
        }

        return airRequest;
    }

    @Override
    public int getMisses() {
        return airRepository.getMiss();
    }

    @Override
    public int getHits() {
        return airRepository.getHit();
    }

    @Override
    public int getRequests() {
        return airRepository.getHit() + airRepository.getMiss();
    }

    @Override
    public void clearCache() {
        airRepository.clear();
    }

//	@Override
//	public AirRequest getAirQualityByLocal(double lon, double lat, String[] features) {
//		// TODO Auto-generated method stub
//		return null;
//	}



}
