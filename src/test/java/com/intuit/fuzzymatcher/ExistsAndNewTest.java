package com.intuit.fuzzymatcher;

import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;
import com.intuit.fuzzymatcher.domain.Token;
import com.intuit.fuzzymatcher.function.TokenizerFunction;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.stream.Collectors;

public class ExistsAndNewTest {
  @Test
  public void itShouldNormalizeOccupation() {
    String value = "Software Developer!";
    Element element = new Element.Builder().setType(ElementType.OCCUPATION).setValue(value).createElement();
    Assert.assertEquals("software developer", element.getPreProcessedValue());
  }

  @Test
  public void itShouldNormalizeReleaseDate() {
    String value = "18/03/2022";
    Element element = new Element.Builder().setType(ElementType.RELEASE_DATE).setValue(value).createElement();
    Assert.assertEquals("2022-03-18", element.getPreProcessedValue());
  }

  @Test
  public void itShouldNormalizeGender() {
    String value = "Male";
    Element element = new Element.Builder().setType(ElementType.GENDER).setValue(value).createElement();
    Assert.assertEquals("masculino", element.getPreProcessedValue());
  }

  @Test
  public void testReleaseDateTokenizer() {
    String value = "18-03-2022";
    Element<String> element = new Element.Builder<String>()
            .setType(ElementType.RELEASE_DATE)
            .setValue(value)
            .createElement();
    List<String> expectedTokens = List.of("2022", "03", "18");
    List<String> actualTokens = TokenizerFunction.releaseDateTokenizer().apply(element)
            .map(Token::getValue)
            .collect(Collectors.toList());
    Assert.assertEquals(expectedTokens, actualTokens);
  }

  @Test
  public void testGenderTokenizer() {
    String value = "male";
    Element<String> element = new Element.Builder<String>()
            .setType(ElementType.GENDER)
            .setValue(value)
            .createElement();
    String expectedToken = "2";
    String actualToken = TokenizerFunction.genderTokenizer().apply(element)
            .map(Token::getValue)
            .findFirst()
            .orElse("");
    Assert.assertEquals(expectedToken, actualToken);
  }
}
