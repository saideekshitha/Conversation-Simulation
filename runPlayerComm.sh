#!/bin/bash
mvn clean install
# Check if Maven build was successful
if [ $? -eq 0 ]; then
  # Run the program
  echo "output for the players running in the same java process"
  java -cp target/conversation-0.0.1-SNAPSHOT.jar conversation.InitiateComm
  echo "output for every player running in a separate java process"
  java -cp target/conversation-0.0.1-SNAPSHOT.jar conversation.InitiateComm "separate-process"
else
  echo "Build failed. Exiting."
  exit 1
fi