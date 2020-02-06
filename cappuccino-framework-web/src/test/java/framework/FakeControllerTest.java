package framework;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FakeController.class)
public class FakeControllerTest
{
	@Autowired
	MockMvc mockMvc;

	@Test
	void testPage1() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/fake/page1"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	void testPage1Html() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.get("/fake/page1"))
				.andExpect(MockMvcResultMatchers.xpath("//div[@id='cappuccino-page-breadcrumbs']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//div[@id='cappuccino-page-breadcrumbs']")
						.string(Matchers.equalToCompressingWhiteSpace("Home")))

				.andExpect(MockMvcResultMatchers.xpath("//li[@id='breadcrumb-li-0']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//li[@id='breadcrumb-li-0']")
						.string(Matchers.equalToCompressingWhiteSpace("Home")))

				.andExpect(MockMvcResultMatchers.xpath("//div[@id='title-section']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//div[@id='title-section']")
						.string(Matchers.equalToCompressingWhiteSpace("Title             Subtitle")))

				.andExpect(MockMvcResultMatchers.xpath("//h1[@id='main-title']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//h1[@id='main-title']")
						.string(Matchers.equalToCompressingWhiteSpace("Title")))

				.andExpect(MockMvcResultMatchers.xpath("//h2[@id='sub-title']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//h2[@id='sub-title']")
						.string(Matchers.equalToCompressingWhiteSpace("Subtitle")))

				.andExpect(MockMvcResultMatchers.xpath("//div[@id='form-section']").exists())

				.andExpect(MockMvcResultMatchers.xpath("//form[@id='my-form']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//form[@id='my-form']/@action")
						.string(Matchers.equalToCompressingWhiteSpace("/hello")))

				.andExpect(MockMvcResultMatchers.xpath("//form[@id='my-form']/@method")
						.string(Matchers.equalToCompressingWhiteSpace("POST")))

				.andExpect(MockMvcResultMatchers.xpath("//span[@id='name-label-hint']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//span[@id='name-label-hint']")
						.string(Matchers.equalToCompressingWhiteSpace("Enter your first name")))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='name']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='name']/@type").string("text"))

				.andExpect(MockMvcResultMatchers.xpath("//span[@id='surname-label-hint']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//span[@id='surname-label-hint']")
						.string(Matchers.equalToCompressingWhiteSpace("Please specify your last name or surname")))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='surname']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='surname']/@type").string("text"))

				.andExpect(MockMvcResultMatchers.xpath("//textarea[@id='comments']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//textarea[@id='comments']")
						.string(Matchers.equalToCompressingWhiteSpace("")))

				.andExpect(MockMvcResultMatchers.xpath("//legend[@id='hobbiesLegend']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//legend[@id='hobbiesLegend']")
						.string(Matchers.equalToCompressingWhiteSpace("Hobbies                                                                                       Check one or more hobbies")))

				.andExpect(MockMvcResultMatchers.xpath("//span[@id='hobbies-label-hint']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//span[@id='hobbies-label-hint']")
						.string(Matchers.equalToCompressingWhiteSpace("Check one or more hobbies")))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies']/@type").string("checkbox"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies']/@checked").string("checked"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies-reading']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies-reading']/@type").string("checkbox"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies-gaming']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies-gaming']/@type").string("checkbox"))

				.andExpect(MockMvcResultMatchers.xpath("//legend[@id='acceptCookiesLegend']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//legend[@id='acceptCookiesLegend']")
						.string(Matchers.equalToCompressingWhiteSpace("Accept cookies")))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='acceptCookies']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='acceptCookies']/@type").string("radio"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='acceptCookies-no']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='acceptCookies-no']/@type").string("radio"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='submit']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='submit']/@type").string("submit"));
	}

	@Test
	void postPage1() throws Exception
	{
		mockMvc.perform(MockMvcRequestBuilders.post("/fake/page1"))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.model().errorCount(4))
				.andExpect(MockMvcResultMatchers.model().attributeHasFieldErrors(CappuccinoPageTO.CAPPUCCINO_FORM_OBJECT_KEY, "name", "surname", "comments", "acceptCookies"))
				.andExpect(MockMvcResultMatchers.xpath("//form[@id='my-form']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//form[@id='my-form']/@action")
						.string(Matchers.equalToCompressingWhiteSpace("/hello")))

				.andExpect(MockMvcResultMatchers.xpath("//form[@id='my-form']/@method")
						.string(Matchers.equalToCompressingWhiteSpace("POST")))

				.andExpect(MockMvcResultMatchers.xpath("//div[@id='form-error-summary']").exists())

				.andExpect(MockMvcResultMatchers.xpath("//h3[@id='error-summary-heading-5']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//h3[@id='error-summary-heading-5']")
						.string(Matchers.equalToCompressingWhiteSpace("Complete the missing information")))

				.andExpect(MockMvcResultMatchers.xpath("//li[@id='name0_errorLink']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//li[@id='name0_errorLink']")
						.string(Matchers.equalToCompressingWhiteSpace("Please enter your first name")))

				.andExpect(MockMvcResultMatchers.xpath("//a[@id='name0_errorAnchorLink']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//a[@id='name0_errorAnchorLink']")
						.string(Matchers.equalToCompressingWhiteSpace("Please enter your first name")))

				.andExpect(MockMvcResultMatchers.xpath("//a[@id='name0_errorAnchorLink']/@href")
						.string(Matchers.equalToCompressingWhiteSpace("#name")))

				.andExpect(MockMvcResultMatchers.xpath("//li[@id='surname1_errorLink']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//li[@id='surname1_errorLink']")
						.string(Matchers.equalToCompressingWhiteSpace("Please enter your surname")))

				.andExpect(MockMvcResultMatchers.xpath("//a[@id='surname1_errorAnchorLink']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//a[@id='surname1_errorAnchorLink']")
						.string(Matchers.equalToCompressingWhiteSpace("Please enter your surname")))

				.andExpect(MockMvcResultMatchers.xpath("//a[@id='surname1_errorAnchorLink']/@href")
						.string(Matchers.equalToCompressingWhiteSpace("#surname")))

				.andExpect(MockMvcResultMatchers.xpath("//li[@id='comments2_errorLink']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//li[@id='comments2_errorLink']")
						.string(Matchers.equalToCompressingWhiteSpace("Please enter your comments")))

				.andExpect(MockMvcResultMatchers.xpath("//a[@id='comments2_errorAnchorLink']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//a[@id='comments2_errorAnchorLink']")
						.string(Matchers.equalToCompressingWhiteSpace("Please enter your comments")))

				.andExpect(MockMvcResultMatchers.xpath("//a[@id='comments2_errorAnchorLink']/@href")
						.string(Matchers.equalToCompressingWhiteSpace("#comments")))

				.andExpect(MockMvcResultMatchers.xpath("//li[@id='acceptCookies3_errorLink']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//li[@id='acceptCookies3_errorLink']")
						.string(Matchers.equalToCompressingWhiteSpace("Please accept cookies")))

				.andExpect(MockMvcResultMatchers.xpath("//a[@id='acceptCookies3_errorAnchorLink']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//a[@id='acceptCookies3_errorAnchorLink']")
						.string(Matchers.equalToCompressingWhiteSpace("Please accept cookies")))

				.andExpect(MockMvcResultMatchers.xpath("//a[@id='acceptCookies3_errorAnchorLink']/@href")
						.string(Matchers.equalToCompressingWhiteSpace("#acceptCookies")))

				.andExpect(MockMvcResultMatchers.xpath("//span[@id='name-label-hint']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//span[@id='name-label-hint']")
						.string(Matchers.equalToCompressingWhiteSpace("Enter your first name")))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='name']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='name']/@type").string("text"))

				.andExpect(MockMvcResultMatchers.xpath("//span[@id='surname-label-hint']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//span[@id='surname-label-hint']")
						.string(Matchers.equalToCompressingWhiteSpace("Please specify your last name or surname")))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='surname']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='surname']/@type").string("text"))

				.andExpect(MockMvcResultMatchers.xpath("//textarea[@id='comments']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//textarea[@id='comments']")
						.string(Matchers.equalToCompressingWhiteSpace("")))

				.andExpect(MockMvcResultMatchers.xpath("//legend[@id='hobbiesLegend']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//legend[@id='hobbiesLegend']")
						.string(Matchers.equalToCompressingWhiteSpace("Hobbies                                                                                       Check one or more hobbies")))

				.andExpect(MockMvcResultMatchers.xpath("//span[@id='hobbies-label-hint']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//span[@id='hobbies-label-hint']")
						.string(Matchers.equalToCompressingWhiteSpace("Check one or more hobbies")))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies']/@type").string("checkbox"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies']/@checked").string("checked"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies-reading']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies-reading']/@type").string("checkbox"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies-gaming']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='hobbies-gaming']/@type").string("checkbox"))

				.andExpect(MockMvcResultMatchers.xpath("//legend[@id='acceptCookiesLegend']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//legend[@id='acceptCookiesLegend']")
						.string(Matchers.equalToCompressingWhiteSpace("Accept cookies")))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='acceptCookies']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='acceptCookies']/@type").string("radio"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='acceptCookies-no']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='acceptCookies-no']/@type").string("radio"))

				.andExpect(MockMvcResultMatchers.xpath("//input[@id='submit']").exists())
				.andExpect(MockMvcResultMatchers.xpath("//input[@id='submit']/@type").string("submit"));
	}
}
