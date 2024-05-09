
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
