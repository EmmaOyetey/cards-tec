
Blurb:



User Class:
This class represents a generic user and serves as a blueprint for creating user objects in your game.
It serves as a base class for the UserInteraction class, allowing the latter to inherit its properties and potentially extend its functionality.
This class is currently not fully utilised but provides organisation, structure and potential extensibility to the codebase. 
It serves as a placeholder/foundation for future enhancements or refactorings;  additional features or behaviors for users.

UserInteraction Class:
This class extends the User class and represents the interaction between a user and the game.
It has additional methods and functionality specific to interacting with a user's hand of cards.
Methods include; removeFromHand, drawACard, shuffleHand, removeCardByIndex, and getHand 
It has a constructor to initialize the hand ArrayList, ensuring that each instance of UserInteraction has an empty hand when created.
The class follows the principles of Object-Oriented Programming (OOP) by encapsulating related functionality into a single class (UserInteraction), 
inheriting from a base class (User), and providing methods to manipulate its internal state (hand).It abstracts away the complexity of managing user data and interactions, 
providing a simplified interface for interacting with users and their hands of cards.
Inheritance: By inheriting from the User class, UserInteraction inherits its properties and behaviors, promoting code reuse and extensibility.

Game Class
The Game class serves as an abstract template, providing as a blueprint for concrete game implementations.
It encapsulates common functionality, including the methods printTitle() and printRules(). 
It defines a standard interface for those that extend it; BlackJack, Old Maid and War; declaring abstract methods play() and playAgain(), while allowing for variation in gameplay and replayability.
The classes War, OldMaid, and Blackjack extend the Game class, inheriting its common functionality and enabling code reuse, as well as the ability to extend and specialize behavior in subclasses.
demonstarting Polymorphism with each subclass providing its own implementation of these methods. 
The play() method n this class defines the main game loop and controls the flow of gameplay. Subclasses override this method to implement the specific logic and rules of each game.
The playAgain() method prompts the user to decide whether to play the game again after a round or session has ended. Subclasses can customize this behavior as needed.
This class promotes code reuse, encapsulation, and polymorphism, making it easier to develop and maintain a variety of card games with consistent structure and behavior.

