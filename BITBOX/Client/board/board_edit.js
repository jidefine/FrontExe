// 게시글을 데이터베이스에 저장하는 방식으로 변경 및 조회수 증가 기능 수정

const idx = location.search;
const index = idx.split("=")[1];

// 조회수 증가 및 데이터 출력
const viewPost = async () => {
    try {
        const response = await fetch(`/board/view?index=${index}`, { // 수정된 URL
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            throw new Error('게시글을 불러오는데 실패했습니다.');
        }
        const post = await response.json();

        // 조회수 증가
        if (document.referrer.split("/").pop() === "list.html") {
            await fetch(`/board/increase-views?index=${index}`, { // 수정된 URL
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
        }

        // 데이터 출력
        const viewFrm = document.querySelectorAll("#viewFrm > div");
        for (let i = 0; i < viewFrm.length; i++) {
            const id = viewFrm[i].id;
            viewFrm[i].innerHTML += " " + post[id];
        }
    } catch (error) {
        console.error('Error:', error);
    }
};

viewPost();

// 수정 버튼
const modifyBtn = document.querySelector("#modify");

const modifyBtnHandler = (e) => {
    location = `/board/modify.html${idx}`;
};

modifyBtn.addEventListener("click", modifyBtnHandler);

// 삭제 버튼
const deleteBtn = document.querySelector("#delete");

const deleteBtnHandler = async (e) => {
    try {
        const response = await fetch(`/board/delete?index=${index}`, { // 수정된 URL
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        });
        if (!response.ok) {
            throw new Error('게시글을 삭제하는데 실패했습니다.');
        }
        location.href = "/board/list.html";
    } catch (error) {
        console.error('Error:', error);
    }
};

deleteBtn.addEventListener("click", deleteBtnHandler);
