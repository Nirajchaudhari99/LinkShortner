import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class LinkShortenerCLI {
 public static void main(String[] args) {
 LinkShortener shortener = new LinkShortener();
 BufferedReader reader = new BufferedReader(new
InputStreamReader(System.in));
 System.out.println("Welcome to the Link Shortener System!");
 System.out.println("Options:");
 System.out.println("1. Shorten a URL");
 System.out.println("2. Shorten a URL with a custom code");
 System.out.println("3. Expand a short URL");
 System.out.println("4. Save mappings to file");
 System.out.println("5. Load mappings from file");
 System.out.println("6. Exit");
 while (true) {
 try {
 System.out.print("\nEnter your choice: ");
 String choice = reader.readLine();
 switch (choice) {
 case "1" -> {
 // Shorten a URL
System.out.print("Enter the long URL: ");
String longURL = reader.readLine();
String shortURL = shortener.shortenURL(longURL,
null);
 System.out.println("Shortened URL: " + shortURL);
 }
 case "2" -> {
 // Shorten a URL with a custom code
System.out.print("Enter the long URL: ");
String longURL = reader.readLine();
System.out.print("Enter the custom code: ");
String customCode = reader.readLine();
String shortURL = shortener.shortenURL(longURL,
customCode);
 System.out.println(shortURL);
 }
 case "3" -> {
 // Expand a short URL
System.out.print("Enter the short URL: ");
String shortInput = reader.readLine();
String originalURL =
shortener.expandURL(shortInput);
 System.out.println("Original URL: " +
originalURL);
 }
 case "4" -> {
 // Save mappings to file
System.out.print("Enter the file name to save mappings: ");
 String saveFileName = reader.readLine();
try {
 shortener.saveToFile(saveFileName);
System.out.println("Mappings saved successfully!");
 } catch (IOException e) {
 System.out.println("Error saving mappings to file: " + e.getMessage());
 }
 }
 case "5" -> {
 // Load mappings from file
System.out.print("Enter the file name to load mappings: ");
 String loadFileName = reader.readLine();
try {
 shortener.loadFromFile(loadFileName);
System.out.println("Mappings loaded successfully!");
 } catch (IOException | ClassNotFoundException e)
{
 System.out.println("Error loading mappings from file: " + e.getMessage());
 }
 }
 case "6" -> {
 // Exit
System.out.println("Exiting the application.Goodbye!");
 return;
 }
 default -> // Invalid input
 System.out.println("Invalid choice. Please try again.");
 }
 } catch (IOException e) {
 System.out.println("Error reading input. Please try again.");
 }
 }
 }
}
