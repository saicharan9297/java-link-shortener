import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LinkShortener {
    private static final String CHAR_SET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int SHORT_URL_LENGTH = 6;
    private Map<String, String> urlMap;  
    private Map<String, String> reverseMap;  
    private Random random;

    
    public LinkShortener() {
        urlMap = new HashMap<>();
        reverseMap = new HashMap<>();
        random = new Random();
    }

    
    public String shortenUrl(String longUrl) {
        if (reverseMap.containsKey(longUrl)) {
            return reverseMap.get(longUrl);  
        }
        
        String shortUrl;
        do {
            shortUrl = generateShortUrl();
        } while (urlMap.containsKey(shortUrl)); 
        
        urlMap.put(shortUrl, longUrl);
        reverseMap.put(longUrl, shortUrl);
        return shortUrl;
    }

   
    public String expandUrl(String shortUrl) {
        return urlMap.getOrDefault(shortUrl, "URL not found");  
    }

   
    private String generateShortUrl() {
        StringBuilder shortUrl = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int index = random.nextInt(CHAR_SET.length());
            shortUrl.append(CHAR_SET.charAt(index));
        }
        return shortUrl.toString();
    }

    
    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();

        
        String longUrl1 = "https://www.youtube.com/watch?v=QmmRpHILXqIl";
        String shortUrl1 = linkShortener.shortenUrl(longUrl1);
        System.out.println("Shortened URL: " + shortUrl1);
        System.out.println("Expanded URL: " + linkShortener.expandUrl(shortUrl1));

        String longUrl2 = "https://web.telegram.org/a/";
        String shortUrl2 = linkShortener.shortenUrl(longUrl2);
        System.out.println("Shortened URL: " + shortUrl2);
        System.out.println("Expanded URL: " + linkShortener.expandUrl(shortUrl2));
    }
}
