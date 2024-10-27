# Piano Web App 
This project is a simple web-based piano that allows users to play notes by clicking keys on a virtual keyboard. Each key triggers an API call to a Java RESTful Spring Boot server, which processes the request and plays the corresponding audio file.

# Project Overview
This application is designed to simulate a virtual piano using a web server interface. When a piano key is clicked, it sends a signal to an API built with Java Spring Boot. The API then plays an audio file corresponding to the note selected, providing a musical experience through a simple web interface.

# Features
+ Interactive web-based piano keyboard
+ API integration using Java Spring Boot
+ Plays corresponding audio files for each note
+ Cross-platform compatibility

# Technologies Used
+ Frontend: HTML, CSS, JavaScript
+ Backend: Java REST API with Spring Boot
+ Audio Processing: Server-side handling of audio files

# Getting Started
+ Prerequisites
+ Java 11+ installed
+ Dependencies for Spring Boot

# Installation
1. Clone this repository:
```
git clone 
```
2. Set up and start the API:
  + Navigate to the API directory:
    ```
    cd piano-web-app/api
    ```
 + Build and run the API using Maven or Gradle:
   ```
    mvn spring-boot:run
   ```
3. Start the Web Server
   ```
   cd piano-web-app/frontend
   npm start
   ```
# Usage
1. Open the web app in your browser (e.g., http://localhost:3000 if using a local server).
2. Click on the piano keys to send API requests and hear the corresponding notes.

Each key press triggers an API call to the Java Spring Boot server, which plays an audio file representing the selected note.
