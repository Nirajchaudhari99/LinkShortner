
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Random;

public class LinkShortener {
    private HashMap<String, String> urlMap; // Short-to-long URL mappings
    private HashMap<String, String> reverseMap; // Long-to-short URL mappings
    private final String domain; // Domain for short URLs
    private final String chars; // Characters used for hash generation
    private final Random random;
    private final int shortCodeLength; // Length of generated short codes

    public LinkShortener() {
        this.urlMap = new HashMap<>();
        this.reverseMap = new HashMap<>();
        this.domain = "http://short.ly/"; // Custom domain
        this.chars ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        this.random = new Random();
        this.shortCodeLength = 6; // Default length for generated short codes
 }
 // Generate a random short code
 private String generateShortCode() {
 StringBuilder code = new StringBuilder();
 for (int i = 0; i < shortCodeLength; i++) {
 code.append(chars.charAt(random.nextInt(chars.length())));
 }
 return code.toString();
 }
 // Shorten a long URL with optional custom code
 public String shortenURL(String longURL, String customCode) {
 if (longURL == null || longURL.isEmpty()) {
 return "Error: Invalid URL!";
 }
 // Check if the long URL already has a short URL
 if (reverseMap.containsKey(longURL)) {
 return domain + reverseMap.get(longURL);
 }
 // Use custom code if provided, else generate a new one
 String shortCode = (customCode != null && !customCode.isEmpty())
? customCode : generateShortCode();
 // Check for duplicates in custom codes
 if (urlMap.containsKey(shortCode)) {
 return "Error: Custom code already exists!";
 }
 // Store mappings
 urlMap.put(shortCode, longURL);
 reverseMap.put(longURL, shortCode);
 return domain + shortCode;
 }
 // Expand a short URL back to the original URL
 public String expandURL(String shortURL) {
 if (shortURL == null || shortURL.isEmpty() ||
!shortURL.startsWith(domain)) {
 return "Error: Invalid short URL!";
 }
 String shortCode = shortURL.replace(domain, "");
 return urlMap.getOrDefault(shortCode, "Error: URL not found!");
 }
 // Save mappings to a file
 public void saveToFile(String fileName) throws IOException {
 try (ObjectOutputStream oos = new ObjectOutputStream(new
FileOutputStream(fileName))) {
 oos.writeObject(urlMap) ;
 oos.writeObject(reverseMap) ;
 }
 }
 // Load mappings from a file
 @SuppressWarnings("unchecked")
 public void loadFromFile(String fileName) throws IOException,
ClassNotFoundException {
 try (ObjectInputStream ois = new ObjectInputStream(new
FileInputStream(fileName))) {
 this.urlMap = (HashMap<String, String>) ois.readObject();
 this.reverseMap = (HashMap<String, String>) ois.readObject();
 }
 }
}