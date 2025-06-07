package ru.job4j.exam;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutionException;

class SpamCheckerTest {
    @Test
    public void whenThereIsNoSpamThenFalse() throws ExecutionException, InterruptedException {
        SpamChecker checker = new SpamChecker();
        List<Filter> filters = List.of(new Filter(List.of("hi", "hello")), new Filter(List.of("privet", "Privet")));
        assertThat(checker.isSpam(filters, "Dobriy den")).isFalse();
    }

    @Test
    public void whenThereIsSpamThenTrue() throws ExecutionException, InterruptedException {
        SpamChecker checker = new SpamChecker();
        List<Filter> filters = List.of(new Filter(List.of("hi", "hello")), new Filter(List.of("privet", "Privet")));
        assertThat(checker.isSpam(filters, "Vsem privetik")).isTrue();
    }
}