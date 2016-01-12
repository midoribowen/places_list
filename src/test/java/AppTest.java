import static org.fluentlenium.core.filter.FilterConstructor.*;
import org.fluentlenium.adapter.FluentTest;
import org.junit.ClassRule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends FluentTest {
  public WebDriver webDriver = new HtmlUnitDriver();
  public WebDriver getDefaultDriver() {
    return webDriver;
  }

  @ClassRule
  public static ServerRule server = new ServerRule();

  @Test
  public void rootTest() {
    goTo("http://localhost:4567/");
    assertThat(pageSource()).contains("Places We've Been");
  }

  @Test
  public void placesCreatedTest() {
    goTo("http://localhost:4567/");
    fill("#location").with("Irondale, Washington");
    submit(".btn");
    assertThat(pageSource()).contains("Your place has been saved");
  }

  @Test
  public void placeIsDisplayedTest() {
    goTo("http://localhost:4567/");
    fill("#location").with("Chennai, India");
    submit(".btn");
    click("a", withText("Go Back to Add Another!"));
    assertThat(pageSource()).contains("Chennai, India");
  }

  @Test
  public void multiplePlacesAreDisplayedTest() {
    goTo("http://localhost:4567/");
    fill("#location").with("Chennai, India");
    submit(".btn");
    click("a", withText("Go Back to Add Another!"));
    fill("#location").with("Irondale, Washington");
    submit(".btn");
    click("a", withText("Go Back to Add Another!"));
    assertThat(pageSource()).contains("Chennai, India");
    assertThat(pageSource()).contains("Irondale, Washington");
  }

}
