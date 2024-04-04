package com.intuit.fuzzymatcher;

import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.ElementType;
import com.intuit.fuzzymatcher.domain.Token;
import com.intuit.fuzzymatcher.function.TokenizerFunction;
import org.junit.Assert;
import org.junit.Test;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.intuit.fuzzymatcher.domain.ElementType.ADDRESS;
import static com.intuit.fuzzymatcher.domain.ElementType.PHONE;
import static com.intuit.fuzzymatcher.function.TokenizerFunction.valueTokenizer;
import static com.intuit.fuzzymatcher.function.TokenizerFunction.wordTokenizer;

public class ExistsAndNewTest {

  @Test
  public void testAgePreprocessing() {
    String value = "I am 25 years old.";
    Element element = new Element.Builder().setType(ElementType.AGE).setValue(value).createElement();
    Assert.assertEquals("25", element.getPreProcessedValue());
  }

  @Test
  public void testModelPreprocessing() {
    String value = "Smartphone! Model X";
    Element element = new Element.Builder().setType(ElementType.MODEL).setValue(value).createElement();
    Assert.assertEquals("smartphone model x", element.getPreProcessedValue());
  }

  @Test
  public void testOccupationNormalization() {
    String value = "Software Developer!";
    Element element = new Element.Builder().setType(ElementType.OCCUPATION).setValue(value).createElement();
    Assert.assertEquals("software developer", element.getPreProcessedValue());
  }

  @Test
  public void testReleaseDateNormalization() {
    String value = "18/03/2022";
    Element element = new Element.Builder().setType(ElementType.RELEASE_DATE).setValue(value).createElement();
    Assert.assertEquals("2022-03-18", element.getPreProcessedValue());
  }

  @Test
  public void testGenderNormalization() {
    String value = "Male";
    Element element = new Element.Builder().setType(ElementType.GENDER).setValue(value).createElement();
    Assert.assertEquals("male", element.getPreProcessedValue());
  }

  @Test
  public void testAgeTokenizer() {
    String value = "25";
    Element elem = new Element.Builder().setType(ElementType.AGE).setValue(value).createElement();
    Assert.assertEquals(1, valueTokenizer().apply(elem).count());
    Assert.assertEquals("25", valueTokenizer().apply(elem).findFirst().get().getValue() );
  }

  @Test
  public void testModelTokenizer(){
    String value = "Smartphone Model X";
    Element elem = new Element.Builder().setType(ElementType.MODEL).setValue(value).createElement();
    Assert.assertEquals(3, wordTokenizer().apply(elem).count());
  }

  @Test
  public void testOccupationTokenizer(){
    String value = "Software Developer";
    Element elem = new Element.Builder().setType(ElementType.MODEL).setValue(value).createElement();
    Assert.assertEquals(2, wordTokenizer().apply(elem).count());
  }

  @Test
  public void testReleaseDateTokenizer() {
    String value = "18-03-2022";
    Element<String> element = new Element.Builder<String>().setType(ElementType.RELEASE_DATE).setValue(value).createElement();
    List<String> expectedTokens = List.of("2022", "03", "18");
    List<String> actualTokens = TokenizerFunction.releaseDateTokenizer().apply(element).map(Token::getValue).collect(Collectors.toList());
    Assert.assertEquals(expectedTokens, actualTokens);
  }
}
