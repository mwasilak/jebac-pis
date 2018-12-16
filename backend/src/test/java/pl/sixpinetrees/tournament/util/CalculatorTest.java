package pl.sixpinetrees.tournament.util;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by maciej on 30.03.17.
 */
@SpringBootTest
public class CalculatorTest {

    @Test
    public void log2ceil() throws Exception {
        assertThat(Calculator.log2ceil(1)).isEqualTo(0);
        assertThat(Calculator.log2ceil(2)).isEqualTo(1);
        assertThat(Calculator.log2ceil(3)).isEqualTo(2);
        assertThat(Calculator.log2ceil(4)).isEqualTo(2);
        assertThat(Calculator.log2ceil(5)).isEqualTo(3);
        assertThat(Calculator.log2ceil(8)).isEqualTo(3);
        assertThat(Calculator.log2ceil(9)).isEqualTo(4);
        assertThat(Calculator.log2ceil(16)).isEqualTo(4);
        assertThat(Calculator.log2ceil(17)).isEqualTo(5);
        assertThat(Calculator.log2ceil(32)).isEqualTo(5);
        assertThat(Calculator.log2ceil(33)).isEqualTo(6);
        assertThat(Calculator.log2ceil(64)).isEqualTo(6);
    }

    @Test
    public void pow2N() throws Exception {
        assertThat(Calculator.pow2N(0)).isEqualTo(1);
        assertThat(Calculator.pow2N(1)).isEqualTo(2);
        assertThat(Calculator.pow2N(2)).isEqualTo(4);
        assertThat(Calculator.pow2N(3)).isEqualTo(8);
        assertThat(Calculator.pow2N(4)).isEqualTo(16);
        assertThat(Calculator.pow2N(5)).isEqualTo(32);
        assertThat(Calculator.pow2N(6)).isEqualTo(64);
    }

}