# Conversation Simulation

This project demonstrates a simple simulation of two `Player` instances communicating with each other. The communication can be done either within the same Java process or in separate processes using socket programming.

## Project Structure

- **`Player`**: Represents a player who can send and receive messages.
- **`PlayerComm`**: Manages the communication between two players using sockets.
- **`InitiateComm`**: Initializes and starts players either in the same Java process or in separate processes.

## Building the Project

To build the project, ensure you have [Maven](https://maven.apache.org/) installed. Run the following command to clean and build the project:

```bash
mvn clean install

## Running the Simulation

### Running in the Same Java Process

To run the simulation with both players in the same Java process, execute the following command:

```bash
java -cp target/conversation-0.0.1-SNAPSHOT.jar conversation.InitiateComm

## Running in Separate Java Processes

To run the simulation with each player in a separate Java process, execute the following command:

```bash
java -cp target/conversation-0.0.1-SNAPSHOT.jar conversation.InitiateComm "separate-process"

## Automated Build and Execution Script

The project includes a Bash script to automate the build and execution process. Save the following script as `run.sh` and make it executable with `chmod +x run.sh`.

```bash
#!/bin/bash
mvn clean install
# Check if Maven build was successful
if [ $? -eq 0 ]; then
  # Run the program
  echo "Output for the players running in the same Java process"
  java -cp target/conversation-0.0.1-SNAPSHOT.jar conversation.InitiateComm
  echo "Output for every player running in a separate Java process"
  java -cp target/conversation-0.0.1-SNAPSHOT.jar conversation.InitiateComm "separate-process"
else
  echo "Build failed. Exiting."
  exit 1
fi
## To run the script, use:

```bash
./run.sh
## Description of Components

### Player

The `Player` class simulates a player that can send and receive messages. It maintains a message counter to track the number of messages sent.

### PlayerComm

The `PlayerComm` class manages the communication between two players using sockets. Depending on the argument provided (`Player1` or `Player2`), it either starts a server or connects as a client.

### InitiateComm

The `InitiateComm` class initializes the players and starts them either in the same process or in separate processes, based on the command-line arguments.

## Notes

- Ensure that `target/conversation-0.0.1-SNAPSHOT.jar` is the correct path to the built JAR file.
- Modify the port number and other configuration details if needed.
- The project uses basic socket communication and threading, which is suitable for simple simulations but may need adjustments for more complex scenarios.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Maven for project management.
- Java's socket and threading libraries for enabling inter-process communication.

