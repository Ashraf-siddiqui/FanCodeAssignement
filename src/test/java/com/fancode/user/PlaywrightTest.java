package com.fancode.user;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.imageio.ImageIO;

import org.sikuli.script.FindFailed;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class PlaywrightTest {

	private Playwright playwright;
	private Browser browser;
	private Page page;

	@BeforeClass
	public void setUp() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)); // Set headless to
																									// false for visual
																									// verification
		page = browser.newPage();
	}

	@Test
	public void testSearchForTomCruise() throws FindFailed, InterruptedException, IOException {

		page.navigate("https://search.yahoo.com/");

		page.waitForSelector("input[name='p']");

		page.fill("input[name='p']", "Tom Cruise");

		page.press("input[name='p']", "Enter");

		page.waitForSelector("h3");

		boolean isTomCruisePresent = page.textContent("body").contains("Tom Cruise");
		Assert.assertTrue(isTomCruisePresent, "Tom Cruise not found on the page!");

		Locator tomCruiseImage = page.locator("img[alt='Tom Cruise']").first();
		tomCruiseImage.waitFor(new Locator.WaitForOptions().setTimeout(10000));
		tomCruiseImage.screenshot(new Locator.ScreenshotOptions()
				.setPath(Paths.get("src/test/resources/tomcruise_element_screenshotActual.png")));

		BufferedImage image1 = ImageIO.read(new File("src/test/resources/tomcruise_element_screenshotActual.png"));
		BufferedImage image2 = ImageIO.read(new File("src/test/resources/tomcruise_element_screenshot.png"));

		boolean isSame = compareImages(image1, image2);

		if (isSame) {
			System.out.println("The images are identical!");
		} else {
			System.out.println("The images are different!");
		}

	}

	public static boolean compareImages(BufferedImage img1, BufferedImage img2) {
		if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
			return false;
		}

		// Compare every pixel
		for (int x = 0; x < img1.getWidth(); x++) {
			for (int y = 0; y < img1.getHeight(); y++) {
				if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
					return false; // Images are different
				}
			}
		}

		return true;
	}

	@AfterClass
	public void tearDown() {
		browser.close();
		playwright.close();
	}

}
