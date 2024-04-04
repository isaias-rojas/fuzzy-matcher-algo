package com.intuit.fuzzymatcher.domain;


import java.util.function.Function;
import java.util.stream.Stream;

import static com.intuit.fuzzymatcher.domain.MatchType.EQUALITY;
import static com.intuit.fuzzymatcher.domain.MatchType.NEAREST_NEIGHBORS;
import static com.intuit.fuzzymatcher.function.PreProcessFunction.*;
import static com.intuit.fuzzymatcher.function.TokenizerFunction.*;

/**
 * Enum to define different types of Element.
 * This is used only to categorize the data, and apply functions at different stages of match.
 * The functions, can be overridden from Element class using the appropriate setters at the time of creation.
 */
public enum ElementType {
    NAME,
    TEXT,
    ADDRESS,
    EMAIL,
    PHONE,
    NUMBER,
    PHONE_NUMBER,
    DATE,
    PATH,
    AGE,
    MODEL,
    OCCUPATION,
    RELEASE_DATE,
    GENDER;

    protected Function getPreProcessFunction() {
        switch (this) {
            case NAME:
                return namePreprocessing();
            case TEXT:
                return removeSpecialChars();
            case ADDRESS:
                return addressPreprocessing();
            case EMAIL:
                return removeDomain();
            case PHONE:
                return usPhoneNormalization();
            case NUMBER:
            case AGE:
                return numberPreprocessing();
            case PATH:
                return pathPrepFunction();
            case PHONE_NUMBER:
                return genericPhoneNumberPreprocessor();
            case MODEL:
                return removeSpecialChars();
            case OCCUPATION:
                return occupationNormalization();
            case RELEASE_DATE:
                return releaseDateNormalization();
            case GENDER:
                return genderNormalization();
            default:
                return none();
        }
    }

    protected Function getTokenizerFunction() {
        switch (this) {
            case NAME:
                return wordSoundexEncodeTokenizer();
            case TEXT:
                return wordTokenizer();
            case ADDRESS:
                return wordSoundexEncodeTokenizer();
            case EMAIL:
                return triGramTokenizer();
            case PHONE:
                return decaGramTokenizer();
            case PATH:
                return pathTokenizer();
            case PHONE_NUMBER:
                return phoneNumberTokenizer();
            case MODEL:
            case OCCUPATION:
                return wordTokenizer();
            case RELEASE_DATE:
                return releaseDateTokenizer();
            default:
                return valueTokenizer();
        }
    }

    protected MatchType getMatchType() {
        switch (this) {
            case NUMBER:
            case DATE:
            case AGE:
                return NEAREST_NEIGHBORS;
            default:
                return EQUALITY;
        }
    }
}