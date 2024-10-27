## RowdyKeys - Back to the Future Sound Visualizer 
RowdyKeys is a Java-based project that visualizes piano key sounds using a Java Swing GUI and a Spring Boot REST API. It includes a sound visualizer (BackToTheFutureVisualizer) that animates a vertical progress bar for each note when the associated sound is triggered. The app is designed to work cross-platform (Linux and Windows) and loads sound files dynamically from a resources folder.

# Project Structure
+ BackToTheFutureVisualizer (GUI): A Java Swing-based sound visualizer that polls the REST API, animating a   
  progress bar for each note.
+ Controller (REST API): A Spring Boot API handling data exchange and sound playback triggers.
+ Parser: Extracts note and instrument information from the API data to correctly match and play sound files.
+ PlayClip: Loads and plays audio files from resources using javax.sound.sampled classes.
+ HTML/JS Frontend: Provides a basic web UI for selecting and triggering notes.

# Features
+ Sound Visualizer: Displays a GUI with interactive vertical progress bars representing notes.
+ Cross-platform: Designed for both Linux and Windows using Java Swing.
+ Dynamic Sound Loading: Fetches and plays audio files from a resources folder (rowdyKeys).
+ REST API Integration: Uses Spring Boot for handling data transmission and sound-triggering commands from the frontend.

# Setup
Prerequisites
1 Java Development Kit (JDK) - Java 11 or higher
2 Apache Maven - For building the project
3 Spring Boot - Dependency included in the project
4 Audio Files - Place .wav files in src/main/resources/rowdyKeys.

# Installation Steps
1. Clone the repository:
   ```
    git clone https://github.com/austinar59/rowdy-24.git
   ```
2. Build the project:
   ```
    mvn clean install
   ```
3. Run the Application:
+ Start REST API:
   ```
   mvn spring-boot:run
   ```
+ Start the GUI Visualizer:
  ```
  java -cp target/RowdyKeys-1.0-SNAPSHOT.jar com.example.RestApi.BackToTheFutureVisualizer
  ```

# Usage
Running the Visualizer
1. Launch the Swing GUI by running BackToTheFutureVisualizer.
2. The GUI will poll the REST API every 500ms for new note data.
3. Progress bars for each note (C, D, E, etc.) will animate whenever the API sends a signal for a new note.
4. Close the visualizer window to stop the application.

Frontend Interaction
1. Open index.html in a browser to access the web interface.
2. Select a note on the virtual piano, and it sends a POST request to /sendData on the server.
3. The server plays the audio clip and triggers the GUI to animate the corresponding note bar.

# Contributors
This project was created by Austin Roche, Roman Lopez, Sebastian Gongore,  and Jonathan Berndt over the course of around 24 hours for the RowdyHacks X competition on October 26-7, 2024.

# Acknowledgements
All rights are reserved to the property owners of Back to the Future, and thanks to the makers of SpringInitializr for speeding up the process of creating a SpringBoot server.
