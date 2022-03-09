package tqs.project.air.airinfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@RestController
//@RequestMapping("/api")
public class AirRestController {
    @Autowired
    private AirService airService;

    @GetMapping("/breeze")
    public ResponseEntity<AirRequest> getAirRequest(@RequestParam double lon, @RequestParam double lat, @RequestParam String polutants, @RequestParam String health_recommendations){
        System.out.println(polutants + "\n====" + health_recommendations);
    	return ResponseEntity.ok(airService.getAirQualityByLocal(lat, lon, polutants.split(","), health_recommendations.split(",")));
    }

    @GetMapping("/api/cache")
    public ResponseEntity<HashMap<String, String>> getCache(){
        HashMap<String, String> cache = new HashMap<>();
        cache.put("Requests", ""+airService.getRequests());
        cache.put("Hits", ""+airService.getHits());
        cache.put("Misses", ""+airService.getMisses());
        return new ResponseEntity<>(cache, HttpStatus.OK);
    }

    @GetMapping("/clear")
    public void clearCache(){
        airService.clearCache();
    }
    
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
       request.logout();
       return "/";
    }
}
