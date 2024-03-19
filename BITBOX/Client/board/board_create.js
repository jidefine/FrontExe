// boardWrite.html에서 작성 완료 버튼 요소를 선택합니다.
const boardWriteBtn = document.querySelector(".board_write_btn");

// 작성 완료 버튼 클릭 시 실행될 함수를 정의합니다.
const handleWriteComplete = async () => {
    try {
        // boardWrite.html에서 해당하는 입력 요소들의 값을 가져옵니다.
        const foodType = document.getElementById("foodType").value;
        const title = document.getElementById("title").value;
        const author = document.getElementById("author").value;
        const location = document.getElementById("location").value;
        const content = document.getElementById("content").value;

        // Fetch API를 사용하여 서버로 데이터를 전송합니다.
        const response = await fetch("/board/create", {
            method: 'POST', // POST 메서드로 데이터 전송
            headers: {
                'Content-Type': 'application/json' // JSON 형식으로 데이터 전송
            },
            body: JSON.stringify({ foodType, title, author, location, content }) // 데이터를 JSON 형식으로 변환하여 전송
        });

        // 서버로부터의 응답을 처리합니다.
        if (response.ok) {
            alert("게시글 작성이 완료되었습니다."); // 성공적으로 작성된 경우 알림창 표시
            window.location.href = "/index.html"; // 작성 완료 후 게시판 페이지로 이동
        } else {
            throw new Error('게시글을 작성하는데 실패했습니다.'); // 실패 시 에러 처리
        }
    } catch (error) {
        console.error('Error:', error); // 에러 발생 시 콘솔에 에러 메시지 출력
        alert(error.message); // 사용자에게 에러 메시지 표시
    }
};

// 작성 완료 버튼에 클릭 이벤트 리스너를 추가합니다.
boardWriteBtn.addEventListener("click", handleWriteComplete);

// // 게시글을 데이터베이스에 저장하는 방식으로 수정

// const writeFrm = document.querySelector("#writeFrm");

// const submitHandler = async (e) => {
//     e.preventDefault();
//     const subject = e.target.subject.value;
//     const writer = e.target.writer.value;
//     const content = e.target.content.value;

//     try {
//         const response = await fetch("/board/create", { 
//             method: 'POST',
//             headers: {
//                 'Content-Type': 'application/json'
//             },
//             body: JSON.stringify({ subject, writer, content })
//         });
//         if (!response.ok) {
//             throw new Error('게시글을 작성하는데 실패했습니다.');
//         }
//         const result = await response.json();
//         location.href = `/board/view.html?index=${result.index}`;
//     } catch (error) {
//         console.error('Error:', error);
//         alert(error.message);
//     }
// };

// writeFrm.addEventListener("submit", submitHandler);
