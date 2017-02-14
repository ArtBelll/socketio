<html>
<head>
  <link href="res/bootstrap.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <script src="res/socket.io/socket.io.js"></script>
  <script src="res/moment.min.js"></script>

  <title>Demo Chat</title>

  <style>
    body {
      padding:20px;
    }
    #console {
      height: 400px;
      overflow: auto;
    }
    .username-msg {color:orange;}
    .connect-msg {color:green;}
    .disconnect-msg {color:red;}
    .send-msg {color:#888}
  </style>

  <script>
    var userName = 'user' + Math.floor((Math.random()*1000)+1);

    var socket =  io.connect('http://192.168.1.113:8999');

    socket.on('connect', function() {
      output('<span class="connect-msg">Client has connected to the server!</span>');
    });

    socket.on('chatevent', function(data) {
      output('<span class="username-msg">' + data.userName + ':</span> ' + data.message);
    });

    socket.on('disconnect', function() {
      output('<span class="disconnect-msg">The client has disconnected!</span>');
    });

    function sendDisconnect() {
      socket.disconnect();
    }

    function sendMessage() {
      var message = $('#msg').val();
      $('#msg').val('');

      var jsonObject = {userName: userName,
        message: message};
      socket.emit('chatevent', jsonObject);
    }

    function output(message) {
      var currentTime = "<span class='time'>" +  moment().format('HH:mm:ss.SSS') + "</span>";
      var element = $("<div>" + currentTime + " " + message + "</div>");
      $('#console').prepend(element);
    }

    $(document).keydown(function(e){
      if(e.keyCode == 13) {
        $('#send').click();
      }
    });
  </script>


</head>
<body>

<h1>Netty-socketio Demo Chat</h1>

<br/>

<div id="console" class="well">
</div>

<form class="well form-inline" onsubmit="return false;">
  <input id="msg" class="input-xlarge" type="text" placeholder="Type something..."/>
  <button type="button" onClick="sendMessage()" class="btn" id="send">Send</button>
  <button type="button" onClick="sendDisconnect()" class="btn">Disconnect</button>
</form>



</body>
</html>
