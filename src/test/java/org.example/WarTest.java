//package org.example;
//
//import org.example.game.War;
//import org.junit.Test;
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
//    @DisplayName("BothPlayersAreDealt26Cards") {
//        assertEquals(26, warGame.getBill().getHand().size());
//        assertEquals(26, warGame.getPlayerOne().getHand().size());
//    }
//
//    @Test
//    public void testAddCardsToHand() {
//
//
//        // Add some cards to the cardsInPlay list
//        Card card1 = new Card("Hearts", 10); // A card that Bill wins
//        Card card2 = new Card("Diamonds", 5); // A card played by playerOne
//        cardsInPlay.add(card1);
//        cardsInPlay.add(card2);
//
//        // Call the method to add cards to Bill's hand
//        warGame.addCardsToHand(bill);
//
//        // Check if the length of Bill's hand increased by 2 and playerOne's hand decreased by 1
//        assertEquals(27, bill.getHand().size());
//        assertEquals(25, playerOne.getHand().size());
//    }
//
//
//
//
//}

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