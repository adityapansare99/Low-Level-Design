import java.util.*;
import java.util.logging.Logger;

class RecommendationService {
    private static final Logger log = Logger.getLogger(RecommendationService.class.getName());
    private final CacheService cacheService = new CacheService();
    
    public List<String> getRecommendedItems(String userId) {
        try {
            return this.fetchLiveRecommendations(userId);
        } catch (Exception ex) {
            log.warning("Live service failed, falling back to cache");
            return cacheService.getCachedRecommendations(userId);
        }
    }

    public List<String> fetchLiveRecommendations(String userId) {
        return List.of("movie-1", "movie-2"); 
    }
}

class CacheService {
    public List<String> getCachedRecommendations(String userId) {
        return List.of("cached-movie-1", "cached-movie-2");
    }
}

public class CachedData{
    
}