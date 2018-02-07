# GameOfLife
Overview.

This program displays a simulation of “Conway’s Game of Life”, which is a ruleset for modelling population growth and decay, modelled by cells in a grid toggled on or off. The simulation maintains and displays a fixed number of generations, or, state of ‘alive’ and ‘dead cells’, prior to the current generation. Additionally, interaction is preserved over the bounds of the screen by screen-wrapping. Generate an initial state by dragging the mouse on the screen and press the spacebar to begin the simulation, and can be pressed at any time to stop the simulation. Additionally, the up and down keys can be used to change the color, left and right can be used to change the speed, ‘g’ can be used to draw a guiding grid, ‘p’ can be used to draw a ‘pulsar’, an initial starting state with interesting properties, and ‘b’ can be used to fill the screen with pulsars!

The Cell class.

The Cell class defines simple aspects of each cell, including information about its pixel position on the screen, as well as its position in terms of row and column, for quicker access in the two-dimensional boolean array associated with each ‘EvolutionState’ object.

The EvolutionState class.

The EvolutionState class maintains a two-dimensional array of booleans to keep track of alive and dead and cells. The purpose of this is for quick random access in retrieving whether a cell at a given position is alive or dead, for creating the next evolution state. Additionally, an ArrayList of Cell objects is maintained of all of the alive cells, to speed up the process of rendering only the alive cells, rather than iterating through the boolean array. Functions are implemented for adding a cell, removing a cell, changing the size of the state, and retrieving a clone of the evolution state.

The ColofulCellularAutomaton class.

What a name. This class maintains information about the dimensions of the screen in terms of number of ‘cellWidth’ units, the number of evolutions to be saved, the delay between each frame, boolean flags for if cells have just been input, and for drawing the pulsar or grid state. It contains a ListIterator of all the colors for use in toggling the screen color forwards and backwards, a LinkedList containing all evolution states, the associated current colors for the screen, a Timer for the game loop, and the current and previous EvolutionState. Complementary colors for the background and the color are retrieved using the ‘HSLColor’ class from Rob Camick, which is frankly full of a lot of algebra relating to colors that is over my head, but pretty neat nonetheless! Thanks to Rob Camick. If I were to have implemented this class currently, I would have omitted the timer in favor of a proper engine with an initialization, logic, and render function, rather than relying on the java default methods for this, because the java default methods for this lend themselves towards creating spaghetti code and is much less readable.

The ‘runLife’ method.

This contains the main logic associated with the Game of Life. The ruleset defines that for every given cell in the grid, its neighbors are defined as the 8 cells in proximity to it. In order for an alive cell to remain alive, these neighbors must be no less than the minimum (2), to avoid death by underpopulation and no greater than the maximum (3), to avoid death by overpopulation. A ‘dead’ cell may be considered alive if it has a desired number of neighbors surrounding it (3). I recommend changing these parameters for interesting results, I have achieved pseudo-maze like behavior with a ruleset of minimum (1), maximum (4) and birth value (3), with aesthetically pleasing implied lines! If the current number of saved evolutions exceeds the number of desired saved states, the first state is removed. This is the purpose of implementing a LinkedList of states, as removing the first element is faster in a LinkedList than an ArrayList. Then the current state is added to the state LinkedList, and a new EvolutionState is created based on this current state. The number of neighbors is found by calling the ‘getNumNeighbors’ method, then according to the method previously described, the remove or add cell method is called on this evolution state. The current state is then set to this updated state, and the graphics are redrawn by calling the ‘repaint’ method.

The ‘getNumNeighbors’ method.

This method returns the number of cells around the current cell. This is done by creating boolean flags for if the current cell is at the bounds of the screen based on its row and column value, and if so, sets the row and column position of its neighbor to the value wrapped around the screen. Then a two dimensional array of ints is created, representing the row and column position of each neighbor. Then these positions are iterated through, and the ‘cellIsAliveAt’ method is called for that row and column in the evolution state, and the evolution state returns the boolean value of that cell. If the cell is alive, a counter is incremented. That counter is then returned.

The ‘addNewCells’ method.

New cells are created by iterating through the screen in a nested for loop, and checking if the coordinates if the mouse click are contained within the current cell’s pixels. If so, a cell is created there. If the ‘drawPulsar’ flag is toggled on, then an ArrayList is created of the cells associated with this pattern, relative to the mouse position. Then the screen bounds are created by subtracting the size of the screen modulus the cell width from the size of the screen, effectively the screen bounds given that drawing will only occur for full-sized cells, disregarding pixel overhang outside of the grid. Finally, if these cells are contained within these bounds, the new cell is added to the current state.

The ‘drawAllCells’ method.

This method draws all of the cells in each evolution state, giving each a modified color, the newest state colored the brightest, and the oldest state fading into a darker tone. This is achieved by creating a constant different to change each state by, by dividing each of the color channels, red, green, and blue, by the total number of saved evolutions plus two. This inverse relationship allows for an even distribution of color tones, creating a smaller difference between each state’s color if there are many evolution states, and a larger difference if there are few. The original color cell color is stored as the starting color. Then, for each evolution state, except for the initial state, the color is set to this stored color, minus the difference for each color channel, and all cells are drawn by calling the draw method on the cell.

IO.

There are various io methods to handle special cases, such as one for resetting variables when the component is resized, or calling different draw methods in response to mouse input. Additionally, the program responds to input from the spacebar, arrow keys, and ‘G’, ‘P’ and ‘B’ in varying ways.
