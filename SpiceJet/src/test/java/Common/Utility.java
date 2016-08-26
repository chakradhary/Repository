package Common;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Utility {
	public static WebDriver driver;
	public static ExtentReports report;
	public static ExtentTest log;
	public static Logger logger;
	
	public static void leaving_message(String method_name,int line_number, boolean pass, String source) {
		String method_name1 = method_name.replace("_", " ");
		String className=Thread.currentThread().getStackTrace()[1].getClassName();
		if (pass) {
			logger.info(method_name1 + ": " + source + " Passed");
			//System.out.println(method_name1 + ": " + source + " Passed");
			log.log(LogStatus.PASS, className + ":" + line_number + "-" + method_name1 + ": " + source);
		} else {
			//System.out.println(method_name1 + ": " + source + " Failed");
			String screenShot = captureScreenShot(method_name1);
			logger.info(method_name1 + ": " + source + " Failed" + " Path: " + screenShot);
			log.log(LogStatus.FAIL, className + ":" + line_number + "-" + method_name1 + ": " + source, screenShot);
		}
	}
	
	public static String captureScreenShot(String screenShotName) {
		try {
			File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String dest = "C:\\selenium\\" + screenShotName + ".png";
			FileUtils.copyFile(src, new File(dest));
			return dest;
		} catch (IOException e) {
			return e.getMessage();
		}
	}

	public static void select_browser(String browser) {
		report = new ExtentReports("G:\\EclipseWorkspace\\SpiceJet\\NewTest.html");
		log = report.startTest("Spice Jet Search");
		String className=Thread.currentThread().getStackTrace()[1].getClassName();
		logger=Logger.getLogger(className);
		PropertyConfigurator.configure("G:\\EclipseWorkspace\\SpiceJet\\log4j.properties");
		
		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), true, browser);
		} else if (browser.equalsIgnoreCase("chrome")) {

		} else if (browser.equalsIgnoreCase("internetexplorer")) {

		} else {
			System.out.println("Browser not present in list");
		}
	}

	public static void navigate_to_url(String url) {
		select_browser("firefox");
		driver.get(url);
		leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), true, url);
	}

	public static boolean verify_element_is_present_by_id(String source) {
		try {
			if (driver.findElements(By.id(source)).size() > 0) {
				// leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),
				// true, source);
				return true;
			} else {
				leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				return false;
			}
		} catch (Exception e) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
			return false;
		}

	}

	public static boolean verify_element_is_present_by_name(String source) {
		try {
			if (driver.findElements(By.name(source)).size() > 0) {
				// leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),
				// true, source);
				return true;
			} else {
				leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				return false;
			}
		} catch (Exception e) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
			return false;
		}
	}

	public static boolean verify_element_is_present_by_xpath(String source) {
		try {
			if (driver.findElements(By.xpath(source)).size() > 0) {
				// leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),
				// true, source);
				return true;
			} else {
				leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				return false;
			}
		} catch (Exception e) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
			return false;
		}
	}

	public static boolean verify_element_is_enabled_by_name(String source) {
		try {
			if (driver.findElement(By.name(source)).isEnabled()) {
				// leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),true,
				// source);
				return true;
			} else {
				leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				return false;
			}

		} catch (Exception e) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
			return false;
		}
	}

	public static boolean verify_element_is_enabled_by_id(String source) {
		try {
			if (driver.findElement(By.id(source)).isEnabled()) {
				// leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),true,
				// source);
				return true;
			} else {
				leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				return false;
			}
		} catch (Exception e) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
			return false;
		}
	}

	public static boolean verify_element_is_enabled_by_xpath(String source) {
		try {
			if (driver.findElement(By.xpath(source)).isEnabled()) {
				// leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),true,
				// source);
				return true;
			} else {
				leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(),false, source);
				return false;
			}

		} catch (Exception e) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(),false, source);
			return false;
		}
	}

	public static void select_option_by_name(String source, String value) {
		if (verify_element_is_present_by_name(source)) {
			if (verify_element_is_enabled_by_name(source)) {
				try {
					Select select = new Select(driver.findElement(By.name(source)));
					select.selectByVisibleText(value);
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							select.getFirstSelectedOption().equals(value), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}

			}
		}
	}

	public static void select_option_by_xpath(String source, String value) {
		if (verify_element_is_present_by_xpath(source)) {
			if (verify_element_is_enabled_by_xpath(source)) {
				try {
					Select select = new Select(driver.findElement(By.xpath(source)));
					select.selectByVisibleText(value);
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							select.getFirstSelectedOption().equals(value), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}

			}
		}
	}

	public static void select_option_by_id(String source, String value) {
		if (verify_element_is_present_by_id(source)) {
			if (verify_element_is_enabled_by_id(source)) {
				try {
					Select select = new Select(driver.findElement(By.id(source)));
					select.selectByVisibleText(value);
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							select.getFirstSelectedOption().equals(value), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}

			}
		}
	}

	public static void type_value_field_by_name(String source, String value) {
		if (verify_element_is_present_by_name(source)) {
			if (verify_element_is_enabled_by_name(source)) {
				try {
					driver.findElement(By.name(source)).sendKeys(value);
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.name(source)).getText().equals(value), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void type_value_field_by_xpath(String source, String value) {
		if (verify_element_is_present_by_xpath(source)) {
			if (verify_element_is_enabled_by_xpath(source)) {
				try {
					driver.findElement(By.xpath(source)).sendKeys(value);
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.xpath(source)).getText().equals(value), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void type_value_field_by_id(String source, String value) {
		if (verify_element_is_present_by_id(source)) {
			if (verify_element_is_enabled_by_id(source)) {
				try {
					driver.findElement(By.id(source)).sendKeys(value);
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.id(source)).getText().equals(value), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void select_checkbox_by_name(String source) {
		if (verify_element_is_present_by_name(source)) {
			if (verify_element_is_enabled_by_name(source)) {
				try {
					driver.findElement(By.name(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.name(source)).isSelected(), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void select_checkbox_by_xpath(String source) {
		if (verify_element_is_present_by_xpath(source)) {
			if (verify_element_is_enabled_by_xpath(source)) {
				try {
					driver.findElement(By.xpath(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.xpath(source)).isSelected(), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void select_checkbox_by_id(String source) {
		if (verify_element_is_present_by_id(source)) {
			if (verify_element_is_enabled_by_id(source)) {
				try {
					driver.findElement(By.id(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.id(source)).isSelected(), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void select_radio_button_by_name(String source) {
		if (verify_element_is_present_by_name(source)) {
			if (verify_element_is_enabled_by_name(source)) {
				try {
					driver.findElement(By.name(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.name(source)).isSelected(), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void select_radio_button_by_xpath(String source) {
		if (verify_element_is_present_by_xpath(source)) {
			if (verify_element_is_enabled_by_xpath(source)) {
				try {
					driver.findElement(By.xpath(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.xpath(source)).isSelected(), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void select_radio_button_by_id(String source) {
		if (verify_element_is_present_by_id(source)) {
			if (verify_element_is_enabled_by_id(source)) {
				try {
					driver.findElement(By.id(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(),
							driver.findElement(By.id(source)).isSelected(), source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void click_button_by_name(String source) {
		if (verify_element_is_present_by_name(source)) {
			if (verify_element_is_enabled_by_name(source)) {
				try {
					driver.findElement(By.name(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), true, source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void click_button_by_xpath(String source) {
		if (verify_element_is_present_by_xpath(source)) {
			if (verify_element_is_enabled_by_xpath(source)) {
				try {
					driver.findElement(By.xpath(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), true, source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void click_button_by_id(String source) {
		if (verify_element_is_present_by_id(source)) {
			if (verify_element_is_enabled_by_id(source)) {
				try {
					driver.findElement(By.id(source)).click();
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), true, source);
				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void wait_util_element_present_by_name(String source) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(source)));
		if (!(verify_element_is_present_by_name(source))) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
		}
	}

	public static void wait_util_element_present_by_xpath(String source) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(source)));
		if (!(verify_element_is_present_by_xpath(source))) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(),false, source);
		}
	}

	public static void wait_util_element_present_by_id(String source) {
		WebDriverWait wait = new WebDriverWait(driver, 20);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(source)));
		if (!(verify_element_is_present_by_id(source))) {
			leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
		}
	}

	public static void select_option_from_list_by_name(String source, String value) {
		boolean selected = false;
		if (verify_element_is_present_by_name(source)) {
			if (verify_element_is_enabled_by_name(source)) {
				try {
					List<WebElement> list = driver.findElements(By.name(source));

					for (WebElement e : list) {
						if (e.getText().equalsIgnoreCase(value)) {
							e.click();
							selected = true;
							break;
						}
					}
					if (selected) {
						leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), true, source);
					} else {
						leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
					}

				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void select_option_from_list_by_id(String source, String value) {
		boolean selected = false;
		if (verify_element_is_present_by_id(source)) {
			if (verify_element_is_enabled_by_id(source)) {
				try {
					List<WebElement> list = driver.findElements(By.id(source));

					for (WebElement e : list) {
						if (e.getText().equalsIgnoreCase(value)) {
							e.click();
							selected = true;
							break;
						}
					}
					if (selected) {
						leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), true, source);
					} else {
						leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
					}

				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void select_option_from_list_by_xpath(String source, String value) {
		boolean selected = false;
		if (verify_element_is_present_by_xpath(source)) {
			if (verify_element_is_enabled_by_xpath(source)) {
				try {
					List<WebElement> list = driver.findElements(By.xpath(source));

					for (WebElement e : list) {
						if (e.getText().equalsIgnoreCase(value)) {
							e.click();
							selected = true;
							break;
						}
					}
					if (selected) {
						leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(), Thread.currentThread().getStackTrace()[1].getLineNumber(),true, source);
					} else {
						leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
					}

				} catch (Exception e) {
					leaving_message(Thread.currentThread().getStackTrace()[1].getMethodName(),Thread.currentThread().getStackTrace()[1].getLineNumber(), false, source);
				}
			}
		}
	}

	public static void driver_quit() {
		report.endTest(log);
		report.flush();
		driver.quit();
	}
}
