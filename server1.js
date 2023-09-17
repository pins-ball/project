const express = require('express');
const bodyParser = require('body-parser');
const MongoClient = require('mongodb').MongoClient;
const path = require('path');
const http = require('http');
const socketIO = require('socket.io');
const { ObjectId } = require('mongodb'); // ObjectId 추가

const app = express();
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

const databaseUrl = 'mongodb://127.0.0.1:27017/local';
let database;

MongoClient.connect(databaseUrl, { useUnifiedTopology: true }, (err, client) => {
  if (err) throw err;
  console.log('MongoDB에 연결되었습니다.');
  database = client.db('local');
});

app.use(bodyParser.urlencoded({ extended: true }));

const server = http.createServer(app);
const io = socketIO(server);

io.on('connection', (socket) => {
  console.log('새로운 클라이언트가 연결되었습니다.');

  socket.on('newMessage', (message) => {
    console.log('새로운 메시지:', message);

    database.collection('messages').insertOne({ message: message }, (err, result) => {
      if (err) {
        console.error('메시지 저장에 실패했습니다:', err);
      } else {
        console.log('메시지가 저장되었습니다.');
        io.emit('newMessage', message);
      }
    });
  });
});

app.get('/', (req, res) => {
  res.render('login');
});

app.get('/signup', (req, res) => {
  res.render('signup');
});



app.post('/login', (req, res) => {
  const username = req.body.username; // req.body.id -> req.body.username로 수정
  const password = req.body.password;

  database.collection('users1').findOne({ username: username, password: password }, (err, result) => {
    if (err) throw err;

    if (result) {
      res.render('chat', { username: username });
    } else {
      res.send('아이디 또는 비밀번호가 일치하지 않습니다.');
    }
  });
});

// 회원가입 요청 처리
app.post('/signup', (req, res) => {
  const username = req.body.id; // 'id'를 'username'으로 수정
  const password = req.body.password;
  const name = req.body.name;

  // MongoDB에 사용자 정보 저장
  database.collection('users1').insertOne({ username: username, password: password, name: name }, (err, result) => {
    if (err) {
      res.status(500).json({ message: '회원가입에 실패했습니다.' });
    } else {
      res.status(200).json({ message: '회원가입이 완료되었습니다.', username: username, name: name }); // 'id'를 'username'으로 수정
    }
  });
});

server.listen(3000, () => {
  console.log('서버가 시작되었습니다. http://localhost:3000');
});
