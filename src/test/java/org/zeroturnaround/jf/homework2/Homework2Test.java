package org.zeroturnaround.jf.homework2;

import org.hamcrest.Matcher;
import org.junit.Test;
import org.zeroturnaround.jf.homework2.testhelper.Maps;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.Collections.emptyMap;
import static java.util.stream.Stream.of;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class Homework2Test {

  private Homework2 homework = new Homework2();

  @Test(expected = NoSuchFileException.class)
  public void throwsNoSuchFileExceptionWhenFileDoesNotExist() throws Exception {
    homework.getWords("noSuchFile.txt");
  }

  @Test
  public void worksWithEmptyFile() throws Exception {
    assertThat(homework.getWords("empty.txt"), is(emptyMap()));
  }

  @Test
  public void worksWithEmptyInput() {
    assertWords(of(), is(emptyMap()));
  }

  @Test
  public void worksWithEmptyStringInput() {
    assertWords(of(""), is(emptyMap()));
  }

  @Test
  public void worksWithSpaceInput() {
    assertWords(of("   "), is(emptyMap()));
  }

  @Test
  public void ignoresSingleCharWords() {
    assertWords(of("a b c"), is(emptyMap()));
  }

  @Test
  public void returnsSingleWord() {
    assertWords(of("lorem"), is(Maps.of("lorem", 1L)));
  }

  @Test
  public void returnsTwoSameWords() {
    assertWords(of("lorem lorem"), is(Maps.of("lorem", 2L)));
  }

  @Test
  public void returnsTwoDifferentWords() {
    assertWords(of("lorem ipsum"), is(Maps.of(
            "lorem", 1L,
            "ipsum", 1L
    )));
  }

  @Test
  public void returnsFiveWords() {
    assertWords(of("lorem ipsum dolor sit amet"), is(Maps.of(
            "lorem", 1L,
            "ipsum", 1L,
            "dolor", 1L,
            "sit",   1L,
            "amet",  1L
    )));
  }

  @Test
  public void ignoresCase() {
    assertWords(of("lorem Lorem LOREM"), is(Maps.of("lorem", 3L)));
  }

  @Test
  public void ignoresPunctuation() {
    assertWords(of("lorem, lorem"), is(Maps.of("lorem", 2L)));
  }

  @Test
  public void worksWithNonLatinCharacters() {
    assertWords(of("öötöö"), is(Maps.of("öötöö", 1L)));
  }

  @Test
  public void breaksWordsByPunctuations() {
    assertWords(of("more-or-less"), is(Maps.of(
            "more", 1L,
            "or",   1L,
            "less", 1L
    )));
  }

  @Test
  public void worksWithNumbers() {
    assertWords(of("Sections 1.10.32"), is(Maps.of(
            "sections", 1L,
            "10",       1L,
            "32",       1L
    )));
  }

  @Test
  public void isSortedInDescendingOrder() {
    assertWords(of("lorem ipsum ipsum"), is(Maps.of(
            "ipsum", 2L,
            "lorem", 1L
    )));
  }

  @Test
  public void ignoresThe6thWord() {
    assertWords(of("lorem lorem lorem lorem lorem lorem ipsum ipsum ipsum ipsum ipsum " +
            "dolor dolor dolor dolor sit sit sit amet amet consectetur"), is(Maps.of(
            "lorem", 6L,
            "ipsum", 5L,
            "dolor", 4L,
            "sit",   3L,
            "amet",  2L
    )));
  }

  @Test
  public void testREADMEmd() throws IOException {
    Map<String, Long> expected = new LinkedHashMap<>();
    expected.put("the", 28L);
    expected.put("of", 21L);
    expected.put("lorem", 19L);
    expected.put("ipsum", 19L);
    expected.put("it", 11L);

    Map<String, Long> actual = homework.getWords("lorem-ipsum.txt");
    assertEquals(expected, actual);
  }


  private void assertWords(Stream<String> words, Matcher<Map<String, Long>> matcher) {
    assertThat(homework.getWords(words), matcher);
  }

}
