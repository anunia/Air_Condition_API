package tqs.project.air.airinfo.recommendations;

import java.util.Objects;

public abstract class Recommendations {
    private String name;
    private String recommendation;
    
    public Recommendations(String name, String recommendation) {
        this.name = name;
        this.recommendation = recommendation;
    }

    public String getName() {
        return name;
    }

    public String getRecommendation() {
        return recommendation;
    }
    
     @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Recommendations)) return false;
        Recommendations recom = (Recommendations) o;
        
        return Objects.equals(name, recom.name) &&
                Objects.equals(recommendation, recom.recommendation);
    }
       
    @Override
    public int hashCode() {
        return Objects.hash(name, recommendation);
    }
}
