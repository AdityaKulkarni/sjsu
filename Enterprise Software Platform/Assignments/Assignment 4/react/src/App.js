import logo from './logo.svg';
import './App.css';
import socket from './socket/socket';
import { useState } from 'react';
import Chat from './components/chat';

function App() {

  const [name, setName] = useState("");
  const [connected, setConnected] = useState(socket.connected);

  const handleNameChange = (e) => {
    setName(e.target.value);
  }

  const handleEnterRoom = () => {
    if(socket.connected){
      socket.disconnect();
    }
    socket.connect();
    socket.emit("join-room", name);
    setConnected(true);
  }

  return (
    <div className="App">
      {
          connected ? <Chat name={name}/> : <><img src={logo} className="App-logo" alt="logo" />
          <p>
            <input type="text" placeholder="Enter your name" onChange={handleNameChange} value={name}/>
          </p>
          <button disabled={!name} onClick={handleEnterRoom}>Enter in chat room</button></>
        }
    </div>
  );
}

export default App;
