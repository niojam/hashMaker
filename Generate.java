package comHash;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

public class Generate {
    /**
     * Go input url and insert randomly generated nonce + previous content + current content to the web site textarea.
     * The output is correct SHA-256 hash.
     */
    public static void main(String[] args) {
        // Create a new instance of the html unit driver
        // Notice that the remainder of the code relies on the interface,
        // not the implementation.
        WebElement code;
        System.setProperty("webdriver.chrome.driver", "C:/Users/nikit/IdeaProjects/nonce/chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();

        // And now use this to visit Google
        driver.get("https://timestampgenerator.com/tools/sha256-generator");

        // Find the text input element by its name
        while (true) {
            WebElement element = driver.findElement(By.id("source"));
            element.clear();
            String newNonce = getNonce();
            element.sendKeys("cdac1f5a6b2d5fcd32bf9287702446dc0637644860809f1fa294e00eed00a254136e1e64afa85b" +
                    "0088db92435b897377337ccb5ccaa8c097d5e38249a486ba00" + newNonce);
            element.submit();
            String myHash = driver.findElement(By.id("copyResult")).getAttribute("innerHTML");
            System.out.println("SHA256 IS: " + driver.findElement(By.id("copyResult")).getAttribute("innerHTML")
                    + "NEW NONCE:" + newNonce);
            if (myHash.substring(0, 7).equals("0000000")) {
                break;
            }
        }
    }

    /**
     * @return Randomly generated 32 character long hex format string.
     */
    private static String getNonce() {
        StringBuilder nonce = new StringBuilder();
        String allChars = "abcdef0123456789";
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
            char c = allChars.charAt(random.nextInt(16));
            nonce.append(c);
        }
        return nonce.toString();
    }
}