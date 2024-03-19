let boardsStr = localStorage.getItem("boards");

if (boardsStr === null) {
  const listStr = JSON.stringify([]);
  localStorage.setItem("boards", listStr);
  boardsStr = listStr;
}

const boardsObj = JSON.parse(boardsStr);

const template = (index, objValue) => {
  return `
  <tr>
  <td>${index + 1}</td>
  <td><a href="../../board/view.html?index=${objValue.index}">${objValue.subject}</a></td>
  <td>${objValue.writer}</td>
  <td>${objValue.date}</td>
  <td>${objValue.views}</td>
  </tr>
  `;
};

const tbody = document.querySelector("tbody");

for (let i = 0; i < boardsObj.length; i++) {
  tbody.innerHTML += template(i, boardsObj[i]);
} 

const generateBoardHTML = (posts) => {
  let html = "";
  posts.forEach((post, index) => {
    html += `
      <tr>
        <td>${index + 1}</td>
        <td><a href="../../board/view.html?index=${post.index}">${post.subject}</a></td>
        <td>${post.writer}</td>
        <td>${post.date}</td>
        <td>${post.views}</td>
      </tr>
    `;
  });
  return html;
};

// 게시판의 HTML을 업데이트하는 함수
const updateBoardHTML = (posts) => {
  const tbody = document.querySelector("tbody");
  tbody.innerHTML = generateBoardHTML(posts);
};

// 서버로부터 게시글 목록을 가져와서 화면에 표시하는 함수
const fetchPostsFromDB = () => {
  // AJAX나 fetch를 사용하여 서버에 요청을 보내서 게시글 목록을 가져옵니다.
  // 여기에서는 WebSocket을 사용하여 서버로부터 데이터를 가져오는 방식으로 가정합니다.
  const websocket = new WebSocket("ws://localhost:9990"); // 서버의 WebSocket 엔드포인트를 지정해야 합니다.

  // WebSocket 연결이 열릴 때의 이벤트 핸들러
  websocket.onopen = () => {
    const message = {
      cmd: "fetchPosts"
    };
    // 서버로 게시글 목록을 요청합니다.
    websocket.send(JSON.stringify(message));
  };

  // 서버로부터 게시글 목록을 받았을 때의 이벤트 핸들러
  websocket.onmessage = (event) => {
    const posts = JSON.parse(event.data);
    // 받아온 게시글 목록을 사용하여 게시판의 HTML을 업데이트합니다.
    updateBoardHTML(posts);
  };
};

// 초기화 시 서버로부터 게시글 목록을 가져와서 화면에 표시합니다.
fetchPostsFromDB();
