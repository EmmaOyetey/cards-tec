//package org.example;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertTrue;
//
//public class WarTest {
//
//    private War warGame;
//
//    @BeforeEach
//    void setUp() {
//        warGame = new War();
//    }
//
//    @Test
//    @DisplayName("Each player should be dealt 26 cards at the start")
//    void testDealInitialHands() {
//        // Verify that each player's hand size is 26 after dealing
//        assertEquals(26, warGame.getBill().getHand().size());
//        assertEquals(26, warGame.getPlayerOne().getHand().size());
//    }
//
//
//}
//

//        @Test
//        @DisplayName("A 12-year-old should not be allowed to view an 18-rated film")
//        void isAllowedToView_18FilmValidAge_ReturnsTrue() {
//            boolean rating = film.isAllowedToView(12);
//            assertFalse(rating);
//        }
//
//        @Test
//        void isAllowedToView_18FilmInvalidAge_ReturnFalse() {
//            boolean rating = film.isAllowedToView(19);
//            assertTrue(rating);
//        }
//    }
//




//package org.example;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class CalculatorTest {
//
//    private Calculator calculator;
//
//    @BeforeEach
//    void setUp() {
//        //set calculator state back
//        calculator = new Calculator();
//    }
//    //method name_StateUnderTest_ExpectedBehaviour
//    @Test
//    void add_ValidInput_CorrectAnswer(){
//        int result = calculator.add(60, 20);
//        assertEquals(80, result);
//    };
//
////    @Test
////    void subtract_ValidInput_CorrectAnswer(){
////        int result = calculator.subtract(60, 20);
////        assertEquals(40, result);
////    };
//
//    @Test
//    void multiply_ValidInput_CorrectAnswer(){
//        int result = calculator.multiply(5, 10);
//        assertEquals(50, result);
//    };
//
//    @Test
//    void divide_ValidInput_CorrectAnswer(){
//        int result = calculator.divide(60, 2);
//        assertEquals(30, result);
//    };
//
//    @Test
//    @DisplayName("Two minus one equals 1")
//    void subtract_ValidInput_CorrectAnswer(){
//        int result = calculator.subtract(2,1);
//        assertEquals(1, result);
//    }
//}