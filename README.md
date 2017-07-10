#Prova Finale (Ingegneria del Software) Group 15
`
#Lorenzo il magnifico

#RUNNING THE GAME

There are a few classes that you need to launch in order to play the game.

First of all, the class Server (package it.polimi.ingsw.manager) has to be runned.
By running this class a simple server is created waiting for clients to connect to it, to provide an username and be finally registered to it.
The server creates a user for each client connected to it.
Usernames are used by the server to identify uniquely a user. (this allows different users to have the same name, both in the same game or in different games)
Everytime the minimum amount of users is reached the server starts a timer and creates a game on a new thread.
When the time is over every user is asked to insert a name and a color and then the game can start.
The server will keep running waiting for other users to connect and allows multiple matches at the same time.

it's possible to create a client running different classes, this choice determines the type of connection and interface the player would use.

To play from command line interface the classes to be runned are CliRmiView and CliSocketView (package it.polimi.ingsw.Cli)
in order to establish respectively an Rmi and a Socket connection.

To play from graphical user interface, classes GuiRmiView and GuiSocketView will be used (package it.polimi.ingsw.Gui)
and they will also establish respectively an Rmi and a socket connection.

#CONNECTING AND DISCONNECTING A USER

When a user (it.polimi.ingsw.manager.User.java) stop its connection (i.e. by closing the console) the server waits for the timer (set for its action) to be over,
notifies the other players and lets them to continue the game.
While the user is disconnected it doesn't mature any progress in the game unless a mandatory choice occurs.
In that case the server will automatically choose randomly.

To be reconnected to the same game, the user will follow the normal procedure to be connected to the server.
User can reconnect only with the same type of connection used the first time it has been connected.
Providing the same username, the user will be identified and could wait for his turn.
All the other players are notified about its reconnection.
If a user tries to reconnect using the username of a connected user the server ask the username again.

#CONFIGURATION OF THE GAME

The game parameters can be manually configured updating a file called "config.json".
For this project it has been used json language and Gson library.

---BONUS---
For a complete configurability, we have standardized all possible bonus.
Example: ActivationZoneBonus (secondary effect of Preacher Card) could forbid to take bonus from either tower or council Palace. 

We have also created new bonuses in accord with possible bonus logic.
Example: we have created multFamilyMemberBonus which could multiply the value of a specific family member.


Every development card can be configured in these parameters:
	-Name
	-Period
	-Cost and alternativeCost (for venture cards)
	-Immediate Effect
	-Secondary Effect
	-Development Card Type
Also new cards can be added.

Every leader card can be configured in these parameters:
	-Name
	-Requirements
	-Effect
Also new leader cards can be added.

Excommunication Tiles can be configured in these parameters:
	-Period
	-Malus
Also new Excommunication Tiles can be added.

Personal Bonus Tile can be configured in these parameters:
	-Harvest bonus
	-Production bonus
	-Harvest activation condition 
	-Production activation condition
Also new PersonalBonusTiles can be added.

Other configurable objects are: 
	- councilPrivileges
	- faith points track
	- military points track
	- victory Points For Territory Card
	- victory Points For Character Card
	- military requirement for territory card
	- bonus of all position of board

Timers (timerBeforeStartGame and timerTurn) are configurable by inserting the number of seconds. 

#VIEW

A user can send input to the server only when it's its turn.
When a user sends a message to the server when it's not its turn the server replies with an alert message:
"Please wait your turn!". 

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

Many other alert messages can occur on Cli but they are intended to be a response to logic errors.
Everytime game rules are broken a message is displayed explaining the user the reason of its answer rejection.
Even if two users try to connect providing the same username an alert is sent.

#USING GUI

Graphical user interface consists in different windows.
The main window shows the board of the game, the personal boards of all the players with their statistics. By clicking on it it's possible to zoom in on every position of the board.
Then if you want to place a family member on that position, you have to click on "Place family member" button.
Finally to pass the turn you have to click on "Pass the turn" button.

When a family member is placed, in that position will appear the chosen family member.
Resources are updated immediately on the personal board of the player.
When the chosen position is a tower floor, the personal board will automatically take the card on it.

On the top right a chat has been implemented, Lorenzo (cpu) retrieves further information during the game by asking questions to the current player.
There are a few other windows appearing during the game that allow the user to make complementary actions (i.e. set username, name and color).
Whenever a message has to be sent from the server, an alert window is displayed to inform the user
(i.e "It's your turn", "Please wait your turn", "Resources are not enough", etc.).
When a Gui user disconnects, he can reconnect to the game using Cli.

#MODEL

The model has an hierarchical structure.
At the base we can find the classes that model the basic elements of the game:

-Board and its structure elements (package it.polimi.ingsw.BOARD)
-Players, Personal boards and all of their elements (package it.polimi.ingsw.GC15)
-Resources(every countable element displayed in the game) (package.it.polimi.ingsw.RESOURCE)
-Bonuses and advanced rule's bonuses (packages it.polimi.ingsw.BONUS it.polimi.ingsw.BONUS.ADVANCED)

A first level of abstraction is put in place by the classes we called Controllers (package it.polimi.ingsw.CONTROLLER).
A controller has a main boolean method called check and other private auxiliary methods.
Invoking check methods the game verify a condition imposed by the game rules.

The logic and sequencing of the game is managed by Handler classes.

Normal handlers are sets of instructions that use controllers and modify the basic model classes, they represent the atomic units of the game's logic.
They all have a main method called handle.
(packages it.polimi.ingsw.HANDLER it.polimi.ingsw.HANDLER.ADVANCED)

Any of the game handlers manages a game's stage (i.e. start game, start round, end round, end game).
Game handlers invoke appropriate normal handlers in the right order.
(package it.polimi.ingsw.HANDLER.GAME)

The gap between handlers is filled by the Game, the class at the top of the model's hierarchical structure.
The Game links the game stages and supply the primary execution skeleton.
A Game object is created when the first user connects to the server and starts its execution when all the players have chosen name and color.
(package it.polimi.ingsw.GC_15)

#CONTROLLER
The main controller classes are Manager and ConnectionManagerImpl classes (package it.polimi.ingsw.manager).

Manager (it.polimi.ingsw.manager.Manager.java) links model to ConnectionManagerImpl holding requests from model and calling the right methods in ConnectionManagerImpl.
ConnectionManagerImpl it's the only class that interfaces client with model and differentiates Rmi and Socket users.
Every method of this class acts differently depending on the connection type, for RMi users calls a remote method on the right Rmi client,
while for socket users creates an ActionSocket object (it.polimi.ingsw.manager.ActionSocket.java) and pass the needed parameters.

#TESTS
The project provide jUnit tests on model classes.
Classes composed only by attributes, get and set methods have been excluded.

In order to have a smarter way of creating tests and in some cases to simulate user response Mockito library has been used.

Main classes of the project (Controllers and Handlers) have been tested.
By testing them, minor classes have been consequentially tested.
For Example: testing TowerHandler allowed to test resources and family member behavior.

Tests made it sure model classes respected model dependencies.

Low coverage (45% by Sonar) is caused by the amount of View code, that is not tested.
All model packages have a very high coverage (90% or above).








#CONTRIBUTORS

Papale Michele - 827290 - michelepapale1996 on GitHub

Probo Giovanni - 829953 - Nontrovounnick on GitHub

Tommasi Giovanni - 830053 - Giogia on GitHub







