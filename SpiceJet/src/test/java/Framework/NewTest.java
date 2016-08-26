package Framework;

import org.testng.annotations.Test;
import Common.Utility;
import ResourceBundle.OR;

public class NewTest {
  @Test
  public void f() {
	  Utility.navigate_to_url("http://www.spicejet.com/");
	  Utility.wait_util_element_present_by_xpath(OR.homePage.oneWay_xpath);
	  Utility.select_radio_button_by_xpath(OR.homePage.oneWay_xpath);
	  Utility.click_button_by_xpath(OR.homePage.from_button_xpath);
	  Utility.wait_util_element_present_by_xpath(OR.homePage.from_frame_xpath);
	  Utility.select_option_from_list_by_xpath(OR.homePage.from_list_xpath, "Hyderabad (HYD)");
	  Utility.wait_util_element_present_by_xpath(OR.homePage.to_frame_id);
	  Utility.select_option_from_list_by_xpath(OR.homePage.to_list_xpath, "Bengaluru (BLR)");
	  Utility.click_button_by_xpath(OR.homePage.find_flights_button_xpath);
	  Utility.driver_quit();
  }
}
