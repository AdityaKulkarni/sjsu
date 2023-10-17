const express = require('express');
const http = require('http');
const {Server} = require("socket.io");

const app = express();
const appServer = http.createServer(app);
const io = new Server(appServer, {
    cors: {
        origin: '*',
    }
});
io.listen(3001);
io.on('connection', (socket) => {
    console.log('a user connected');
    socket.on('disconnect', () => {
        console.log('user disconnected')
    });

    socket.on("join-room", (name) => {
        console.log(name + " connected");
    });

    socket.on("new-message", (msg) => {
        const message = JSON.parse(msg);
        console.log("new message");
        console.log("name: " + message.name + " message: " + message.message);
        io.emit("message-ack", message);
    });
});
appServer.listen(3000, () => console.log('Server started'));