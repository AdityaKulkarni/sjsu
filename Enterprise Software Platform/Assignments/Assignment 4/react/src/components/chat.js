import { useEffect, useState } from "react";
import socket from "../socket/socket";

const Chat = ({name}) => {
    const [message, setMessage] = useState("");
    const [messages, setMessages] = useState([]);

    useEffect(() => {
        socket.on("message-ack", (msg) => {
            console.log(name, message);
            setMessages(prevMessages => {
                if(prevMessages && !prevMessages.find(m => m.name === msg.name && m.message === msg.message)){
                    return [...prevMessages, {name: msg.name, message: msg.message}]
                }
                return prevMessages
            });
        });
    }, []);

    useEffect(() => {
        console.log(JSON.stringify(messages));
    }, [messages]);

    const handleMessageChange = (e) => {
        setMessage(e.target.value);
    }

    const handleSendMessage = () => {
        socket.emit("new-message", JSON.stringify({name, message}));
        setMessage("");
    }

    return (
        <div style={{minHeight: "100vh"}}>
            <div>Messages</div>
            <div  style={{ flex: 1,  height: "90vh" }}>
                {messages.map((message, index) => {
                    return <div key={index}>
                        {message.name}:
                        {message.message}
                    </div>
                })}
            </div>
            <div style={{
                flex: 1,
                flexDirection: "row",
                position: "relative",
                bottom: 0,
                left: 0,
                right: 0,
                margin: "16px",
            }}>
                <input style={{flex: 1}} type="text" placeholder="Enter your message" value={message} onChange={handleMessageChange}/>
                <button onClick={handleSendMessage}>Send</button>
            </div>
        </div>
    );
}

export default Chat;