// 필요한 모듈 불러오기
const express = require('express');
const bodyParser = require('body-parser');
const MongoClient = require('mongodb').MongoClient;
const path = require('path');
const http = require('http');
const socketIO = require('socket.io');

// Express 앱 생성
const app = express();

// 뷰 엔진 설정
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

// MongoDB 연결 정보
const databaseUrl = 'mongodb://127.0.0.1:27017/local';

// MongoDB 클라이언트 생성
let database;
MongoClient.connect(databaseUrl, { useUnifiedTopology: true }, (err, client) => {
  if (err) throw err;
  console.log('MongoDB에 연결되었습니다.');

  database = client.db('local');
});

// Body-parser 미들웨어 설정
app.use(bodyParser.urlencoded({ extended: true }));

// HTTP 서버 생성
const server = http.createServer(app);
const io = socketIO(server);

// 소켓 연결 시
io.on('connection', (socket) => {
  console.log('새로운 클라이언트가 연결되었습니다.');

  // 클라이언트로부터의 새로운 메시지 이벤트 처리
  socket.on('newMessage', (data) => {
    console.log('새로운 메시지:', data);

    const message = data.message;
    const name = data.name;

    // 메시지를 MongoDB에 저장
    database.collection('messages').insertOne({ message: message, name: name }, (err, result) => {
      if (err) {
        console.error('메시지 저장에 실패했습니다:', err);
      } else {
        console.log('메시지가 저장되었습니다.');
        // 모든 클라이언트에게 새로운 메시지를 전송
        io.emit('newMessage', { message: message, name: name });
      }
    });
  });

  // 클라이언트로부터의 새로운 방 생성 이벤트 처리
  socket.on('createRoom', (data) => {
    console.log('새로운 방 생성:', data);

    const roomName = data.roomName;

    // 방을 MongoDB에 저장
    database.collection('rooms').insertOne({ roomName: roomName }, (err, result) => {
      if (err) {
        console.error('방 생성에 실패했습니다:', err);
      } else {
        console.log('방이 생성되었습니다.');
        // 클라이언트에게 방 생성 완료를 알림
        socket.emit('roomCreated', { roomName: roomName });
      }
    });
  });

  // 클라이언트로부터의 방 접속 이벤트 처리
  socket.on('joinRoom', (data) => {
    console.log('방 접속:', data);

    const roomName = data.roomName;

    // 클라이언트를 해당 방에 조인
    socket.join(roomName);

    // 클라이언트에게 방 접속 완료를 알림
    socket.emit('roomJoined', { roomName: roomName });
  });

  // 클라이언트로부터의 새로운 메시지 이벤트 처리 (특정 방에 속한 클라이언트에게만 전송)
  socket.on('newRoomMessage', (data) => {
    console.log('새로운 방 메시지:', data);

    const message = data.message;
    const name = data.name;
    const roomName = data.roomName;

    // 메시지를 MongoDB에 저장
    database.collection('roomMessages').insertOne({ message: message, name: name, roomName: roomName }, (err, result) => {
      if (err) {
        console.error('방 메시지 저장에 실패했습니다:', err);
      } else {
        console.log('방 메시지가 저장되었습니다.');
        // 특정 방에 속한 클라이언트에게 새로운 메시지를 전송
        io.to(roomName).emit('newRoomMessage', { message: message, name: name, roomName: roomName });
      }
    });
  });
});

// 로그인 화면 렌더링
app.get('/', (req, res) => {
  res.render('login');
});

// 회원가입 화면 렌더링
app.get('/signup', (req, res) => {
  res.render('signup');
});

// 방 생성 화면 렌더링
app.get('/room', (req, res) => {
  // MongoDB에서 모든 방을 가져옴
  database.collection('rooms').find().toArray((err, rooms) => {
    if (err) {
      console.error('방 목록을 가져오지 못했습니다:', err);
      res.status(500).send('방 목록을 가져오지 못했습니다.');
    } else {
      // room.ejs 파일을 렌더링하면서 rooms 변수를 전달
      res.render('room', { rooms: rooms });
    }
  });
});


// 채팅 화면 렌더링
app.get('/chat', (req, res) => {
  res.render('chat');
});

// 로그인 요청 처리
app.post('/login', (req, res) => {
  const id = req.body.id;
  const password = req.body.password;

  // MongoDB에서 사용자 정보 조회
  database.collection('users1').findOne({ id: id, password: password }, (err, result) => {
    if (err) throw err;

    if (result) {
      // 로그인 성공
      res.render('room', { name: result.name }); // 사용자 이름 전달
    } else {
      // 로그인 실패
      res.send('아이디 또는 비밀번호가 일치하지 않습니다.');
    }
  });
});

// 회원가입 요청 처리
app.post('/signup', (req, res) => {
  const id = req.body.id;
  const password = req.body.password;
  const name = req.body.name;

  // MongoDB에 사용자 정보 저장
  database.collection('users1').insertOne({ id: id, password: password, name: name }, (err, result) => {
    if (err) {
      res.status(500).json({ message: '회원가입에 실패했습니다.' });
    } else {
      console.log('회원가입이 완료되었습니다');
      res.status(200).json({ id: id, name: name });
    }
  });
});
// 방 생성 요청 처리
app.post('/create-room', (req, res) => {
  const roomName = req.body.roomName;
  const name = req.body.name;

  // 방을 MongoDB에 저장
  database.collection('rooms').insertOne({ roomName: roomName, name: name }, (err, result) => {
    if (err) {
      console.error('방 생성에 실패했습니다:', err);
      res.status(500).json({ message: '방 생성에 실패했습니다.' });
    } else {
      console.log('방이 생성되었습니다.');
      res.sendStatus(200);
    }
  });
});
//깃허브 추가되는지 확인 부분 리얼 확인부분
//깃허브 브랜치 chatingprogramnode
//깃허브 chatting에서 test폴더로 가져오고 다시 올려보기

// 서버 시작
server.listen(3000, () => {
  console.log('서버가 시작되었습니다. http://localhost:3000');
});
