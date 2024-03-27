package com.intuit.fuzzymatcher;

import com.intuit.fuzzymatcher.domain.Element;
import com.intuit.fuzzymatcher.domain.Token;
import com.intuit.fuzzymatcher.function.PreProcessFunction;
import com.intuit.fuzzymatcher.function.TokenizerFunction;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;
import org.junit.Test;

import static com.intuit.fuzzymatcher.domain.ElementType.PATH;
import static com.intuit.fuzzymatcher.domain.ElementType.PHONE_NUMBER;
import static com.intuit.fuzzymatcher.function.PreProcessFunction.*;
import static com.intuit.fuzzymatcher.function.TokenizerFunction.*;

public class PhoneAndPathTest {
    @Test
    public void itShouldPreprocessPath_ConvertToLowercase() {
        String path = "C:\\Users\\John\\Documents";
        String preprocessedPath = PreProcessFunction.pathPrepFunction().apply(path);
        Assert.assertEquals("c:/users/john/documents", preprocessedPath);
    }

    @Test
    public void itShouldPreprocessPath_ReplaceBackslashesWithForwardSlashes() {
        String path = "path\\to\\file.txt";
        String preprocessedPath = PreProcessFunction.pathPrepFunction().apply(path);
        Assert.assertEquals("path/to/file.txt", preprocessedPath);
    }

    @Test
    public void itShouldPreprocessPath_HandleMixedSlashes() {
        String path = "path/to\\file.txt";
        String preprocessedPath = PreProcessFunction.pathPrepFunction().apply(path);
        Assert.assertEquals("path/to/file.txt", preprocessedPath);
    }

    @Test
    public void itShouldTokenizePath_SplitByForwardSlashesAndDots() {
        String path = "path/to/file.txt";
        Element<String> element = new Element.Builder<String>()
                .setType(PATH)
                .setValue(path)
                .createElement();

        List<String> tokens = TokenizerFunction.pathTokenizer()
                .apply(element)
                .map(Token::getValue)
                .collect(Collectors.toList());

        Assert.assertEquals(List.of("path", "to", "file", "txt"), tokens);
    }

    @Test
    public void itShouldTokenizePath_HandleMultipleDots() {
        String path = "path/to/file.name.with.dots.txt";
        Element<String> element = new Element.Builder<String>()
                .setType(PATH)
                .setValue(path)
                .createElement();

        List<String> tokens = TokenizerFunction.pathTokenizer()
                .apply(element)
                .map(Token::getValue)
                .collect(Collectors.toList());

        Assert.assertEquals(List.of("path", "to", "file", "name", "with", "dots", "txt"), tokens);
    }

    @Test
    public void itShouldPreprocessAndTokenizePath_AndroidManifestScenario() {
        String path = "Android/android-L-preview/AndroidManifest.xml";
        Element<String> element = new Element.Builder<String>()
                .setType(PATH)
                .setValue(path)
                .createElement();

        String preprocessedPath = element.getPreProcessedValue();
        Assert.assertEquals("android/android-l-preview/androidmanifest.xml", preprocessedPath);

        List<String> tokens = TokenizerFunction.pathTokenizer()
                .apply(element)
                .map(Token::getValue)
                .collect(Collectors.toList());

        Assert.assertEquals(List.of("android", "android-l-preview", "androidmanifest", "xml"), tokens);
    }

    @Test
    public void itShouldPreprocessPath_HandleTrailingSlash() {
        String path = "path/to/directory/";
        String preprocessedPath = PreProcessFunction.pathPrepFunction().apply(path);
        Assert.assertEquals("path/to/directory/", preprocessedPath);
    }

    @Test
    public void itShouldTokenizePath_HandleSingleSegment() {
        String path = "file.txt";
        Element<String> element = new Element.Builder<String>()
                .setType(PATH)
                .setValue(path)
                .createElement();

        List<String> tokens = TokenizerFunction.pathTokenizer()
                .apply(element)
                .map(Token::getValue)
                .collect(Collectors.toList());

        Assert.assertEquals(List.of("file", "txt"), tokens);
    }

    @Test
    public void itShouldPreprocessPhoneNumber_HandleLeadingZero() {
        String phoneNumber = "+1 (055) 123-4567";
        String preprocessedPhoneNumber = PreProcessFunction.genericPhoneNumberPreprocessor().apply(phoneNumber);
        Assert.assertEquals("10551234567", preprocessedPhoneNumber);
    }

    @Test
    public void itShouldPreprocessPhoneNumber_HandleShortPhoneNumber() {
        String phoneNumber = "123-4567";
        String preprocessedPhoneNumber = PreProcessFunction.genericPhoneNumberPreprocessor().apply(phoneNumber);
        Assert.assertEquals("1234567", preprocessedPhoneNumber);
    }

    @Test
    public void itShouldTokenizePhoneNumber_HandleEmptyString() {
        String phoneNumber = "";
        Element<String> element = new Element.Builder<String>()
                .setType(PHONE_NUMBER)
                .setValue(phoneNumber)
                .createElement();

        List<String> tokens = TokenizerFunction.phoneNumberTokenizer()
                .apply(element)
                .map(Token::getValue)
                .collect(Collectors.toList());

        Assert.assertEquals(List.of(""), tokens);
    }
}
