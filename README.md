#Prova Finale (Ingegneria del Software)
`
#Lorenzo il magnifico

#RUNNING THE GAME

There are a few classes that you need to launch in order to play the game.

First of all, the class Server (package it.polimi.ingsw.manager) has to be runned.
By running this class a simple server is created waiting for clients to connect to it, to provide an username and be finally registered to it.
The server creates a user for each client connected to it.
usernames are used by the server to identify uniquely a user. 
Everytime the mininum amount of users is reached the server starts a timer and creates a game on a new thread.
When the time is over every user is asked to insert a name and a color and then the game can start.
The server will keep running waiting for other users to connect.

it's possible to create a client running different classes, this choice determines the type of connection and interface the player would use.

To play from command line interface the classes to be runned are CliRmiView and CliSocketView (package it.polimi.ingsw.Cli)
in order to establish respectively an Rmi and a Socket connection.

To play from graphical user interface, classes GuiRmiView and GuiSocketView will be used (package it.polimi.ingsw.Gui)
and they will also establish respectively an Rmi and a socket connection.

#CONNECTING AND DISCONNECTING A USER

When a user stop its connection (i.e. by closing the Gui window or the command line) the server waits for the timer set for its action to be over,
then let the other players to continue the game.
While the user is disconnected it doesn't mature any progress in the game unless a mandatory choice occurs.
In that case the server will automatically choose randomly.

To be reconnected to the same game, the user will follow the normal procedure to be connected to the server.
Providing the same username, the user will be identified and could wait for his turn.

#USING CLI

Every question and game details are displayed on command line and the user interacts typing directly on it.
There are only two types of input accepted by command line: words and numbers.
The words are used only for the username and name, while all the other questions that occur have a standard multiple choice structure as follows:

Choose something:
1) option
2) another option
3) back to the previous multiple choice

When a wrong input is provided a message appears "The input must be an integer! Try again!".
Then it's possible to type a new input.

Many other alert messages can occur on cli but they are intended to be a response to logic errors.
Everytime game rules are broken a messagge is displayed explaining the user the reason of its answer rejection.
Even if two users try to connect providing the same username a message is sent.

#USING GUI

Graphical user interface consists in different windows.
The main window shows the board of the game, the personal boards of the players with all the statistics. It also lets the user perform an action and zoom in on different points of the board.
On the top right a chat has been implemented, players can send message to each other and a sort of narrator called Lorenzo dispenses further information during the game.

There are a few other windows appearing during the game that allow the user to make complementary actions (i.e. set username, name and color).

#MODEL

The model has an hierarchical structure.
At the base we can find the classes that model the basic elements of the game:

-Board and its structure elements (package it.polimi.ingsw.BOARD)
-Players, Personal boards and all of their elements (package it.polimi.ingsw.GC15)
-Resources(every countable element displayed in the game) (package.it.polimi.ingsw.RESOURCE)
-Bonuses and advanced bonuses (packages it.polimi.ingsw.BONUS it.polimi.ingsw.BONUS.ADVANCED)

A first level of abstraction is put in place by the classes we called controllers.
A controller has a main boolean method called check and other private auxiliary methods.
Invoking check methods the game verify a condition imposed by the game rules.

The logic and sequencing of the game is managed by handler classes.

Normal handlers are sets of instructions that use controllers and modify the basic model classes, they represent the atomic units of the game's logic.
They all have a main method called handle.
(packages it.polimi.ingsw.HANDLER it.polimi.ingsw.HANDLER.ADVANCED)

Any of the game handlers represent a game's stage and invoke appropriate normal handlers in the right order.
(package it.polimi.ingsw.HANDLER.GAME)

The gap between handlers is filled by the manager, the class at the top of the model's hierarchical structure.
The manager links the game stages and supply the primary execution skeleton.
(package it.polimi.ingsw.manager)

#TESTS
The project provide jUnit tests on model classes.
Classes composed only by attributes, get and set methods have been excluded.

In order to have a smarter way of creating tests and in some cases to simulate user response Mockito library has been used.

//aggiungere dettagli e esempi


Testing controller classes made it clear if the basic model classes followed the behavior we expected it to have when rules would have been applied on it.

#CONFIGURATION OF THE GAME

The game parameters can be manually configured updating a file called "config.json".
 

#CONTRIBUTORS

Papale Michele - numero matricola - username github

Probo Giovanni - numero matricola - username github

Tommasi Giovanni - 830053 - Giogia on GitHub

//DEVO AGGIUNGERE FRAMMENTI DI CODICE CHE SPIEGHINO







