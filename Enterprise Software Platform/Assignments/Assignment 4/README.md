# Applications Framework

For this assignment, I have developed a react app with nodejs as backend and an android app with springboot as backend.

## ReactJS with NodeJS
This application makes use of the [Socket.io](https://www.npmjs.com/package/socket.io) package which is a real time bidirectional and low latency communication library. The socket server runs on nodejs express server. React app connects to the socket port and sends/receives messages. This application is based on the pub-sub model.

### MVC
The V of this application is the ```chat.js``` component in the react app. The M_C resides on the nodejs backend. I have opted to put the M_C on the nodejs backend because this way, the react app doesnt need to worry about the type of object it's receiving. The backend will send appropriate object and react app will display it.

### Getting started
Make sure you have NodeJS installed. If not, you can download it from [here](https://nodejs.org/en/download).
Clone the repository

```shell
#First terminal
cd react
npm install
npm start
```

```shell
#Second terminal
cd node
npm install
node index.js
```

## Android with SpringBoot
This application has an android app as frontend and a sprintboot app as backend. This project uses Stomp Protocol for messaging and is based on pub-sub model.

### MVC
The MV of the app resides in the android application and the C resides in the SprintBoot app.

### Getting Started
For the android app, download and install [android studio](https://developer.android.com/studio). Clone and open the repo in android studio and run the app.

For SpringBoot, clone the repo and run following commands.
```shell
cd springboot
./gradlew bootRun
```

## Differences between the frameworks

| Framework    | Strengths                                     | Weaknesses                                   |
|--------------|----------------------------------------------|---------------------------------------------|
| Android      | 1. Native mobile app development            | 1. Steeper learning curve for beginners    |
|              | 2. Wide device compatibility                | 2. Fragmentation across Android versions   |
|              | 3. Access to device features (e.g., camera) | 3. Longer development time                |
|              | 4. Strong community and resources           | 4. Requires Java or Kotlin knowledge       |
| Node.js      | 1. Highly scalable for server applications  | 1. Single-threaded, not suitable for CPU-bound tasks |
|              | 2. Non-blocking I/O for high performance   | 2. Callback hell without proper structuring  |
|              | 3. Extensive package ecosystem (npm)       | 3. Lack of built-in structure for larger apps  |
|              | 4. JavaScript on both client and server    | 4. Not ideal for CPU-intensive operations |
| React.js     | 1. Component-based UI development           | 1. Steeper learning curve for newcomers     |
|              | 2. Virtual DOM for efficient updates       | 2. Need to manage state and data flow      |
|              | 3. Strong community and reusable components | 3. Requires a build setup (e.g., Webpack) |
|              | 4. Great for single-page applications      | 4. SEO challenges with client-side rendering |
| Spring Boot  | 1. Rapid development with pre-configured templates | 1. Heavier memory footprint compared to lightweight frameworks |
|              | 2. Strong support for microservices        | 2. Configuration can be complex            |
|              | 3. Robust security features                | 3. Learning curve for beginners            |
|              | 4. Integration with various data sources   | 4. Overhead for simple, small-scale apps   |

