package ba.unsa.etf.rpr;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

class ExpressionEvaluatorTest {
    String function;
    ExpressionEvaluator ee = new ExpressionEvaluator();

    /**
     * Check if test gives valid expression
     * @throws Exception
     */
    @Test
    void validanIzrazTacanRezultat() throws Exception {
        function = "( 1 + ( ( 2 + 15 ) * ( 4 * 1 ) ) )";
        assertEquals(69, ee.evaluate(function).doubleValue());
    }

    /**
     * check if test gives invalid expression
     * @throws Exception
     */
    @Test
    void validanIzrazPogresanRezultat() throws Exception {
        function = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        assertNotEquals(69, ee.evaluate(function).doubleValue());
    }

    @Test
    void nevalidanOperator() throws Exception {
        function = "( 1 ! ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        assertThrows(RuntimeException.class, () -> {  ee.evaluate(function).doubleValue();  } );

    }

    @Test
    void prazanStringKaoIzraz() throws Exception {
        function = "";
        //Function<String, Double> functionf = ee.evaluate();
        assertThrows(RuntimeException.class, () -> {  ee.evaluate(function).doubleValue();  } );

    }

    /**
     * deliberately wrong String (throws exception)
     * @throws Exception
     */
    @Test
    void namjernoLosTekst() throws Exception {
        function = "Namjerno los tekst";
        //Function<String, Double> functionf = ee.evaluate();
        assertThrows(RuntimeException.class, () -> {  ee.evaluate(function).doubleValue();  } );

    }

    @Test
    void izrazBezRazmaka() throws Exception {
        function = "(1+((2+3)*(4*5)))";
        assertThrows(RuntimeException.class, () -> {  ee.evaluate(function).doubleValue();  } );

    }

    @Test
    void tacanIzrazSaJednomDecimalom() throws Exception {
        function = "( 1 + ( ( 2 + 15 ) * ( 5 / 2 ) ) )";
        assertEquals(43.5, ee.evaluate(function).doubleValue());
    }

    @Test
    void tacanIzrazSaDvijeDecimale() throws Exception {
        function = "( 1 + ( ( 2 + 15 ) * ( 5 / 4 ) ) )";
        assertEquals(22.25, ee.evaluate(function).doubleValue());
    }

    @Test
    void tacanIzrazSaNDecimala() throws Exception {
        function = "( 1 / ( ( 2 + 1 ) * ( 1 / 1 ) ) )";
        assertEquals(0.33D, ee.evaluate(function).doubleValue());
    }

    @Test
    void tacanIzrazSaNegativnimBrojem() throws Exception {
        function = "( 1 - ( ( 2 + 1 ) * ( 1 / 1 ) ) )";
        assertEquals(-2, ee.evaluate(function).doubleValue());
    }







}