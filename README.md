#Prova Finale (Ingegneria del Software)
`
#Lorenzo il magnifico

#RUNNING THE GAME

There are a few classes that you need to launch in order to play the game.

First of all, the class Server (package it.polimi.ingsw.manager) has to be runned.
By running this class a simple server is created waiting for clients to connect to it, provide an username and be finally registered to it.
The server creates a user for each client connected to it.
Everytime the mininum amount of users is reached the server starts a timer, creates a game and waits for other users.
When the time is over every user is asked to insert a name and a color and then the game starts.
it's possible to create a client running different classes, this choice determines the type of connection and interface the player would use.

To play from command line interface the classes to be runned are CliRmiView and CliSocketView (package it.polimi.ingsw.Cli)   in order to establish respectively an Rmi and a Socket connection.

To play from graphical user interface, classes GuiRmiView and GuiSocketView will be used (package it.polimi.ingsw.Gui)
and they will also use respectively Rmi and socket connections.


#CONNECTING AND DISCONNECTING A USER

When a user stop its connection (i.e. by closing the Gui window or the command line) the server waits for the timer set for its action to be over, then let the other players to continue the game.
While the user is disconnected it doesn't mature any progress in the game unless a mandatory choice occurs.
In that case the server will automatically choose randomly.

To be reconnected to the same game, the user will follow the normal procedure to be connected to the server.
Providing the same username, the user will be identifyied and could wait for his turn.

#USING CLI

Every question and game details are displayed on command line and the user interacts typing directly on it.
There are only two types of input accepted by command line: words and numbers.
The words are used only for the username and name, while all the other questions that occur have a standard multiple choice structure as follows:

Choose something:
1) option
2) another option
3) back to the previous multiple choice

When a wrong input is provided a message appears "The input must be an integer! Try again!".

#USING GUI

#MODEL

#TESTS

#CONFIGURATION OF THE GAME

#CONTRIBUTORS

#proposte?

Papale Michele - numero matricola - username github

Probo Giovanni - numero matricola - username github

Tommasi Giovanni - 830053 - Giogia on GitHub

//DEVO AGGIUNGERE FRAMMENTI DI CODICE CHE SPIEGHINO







