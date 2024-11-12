async function fetchBoards() {
    try {
        const response = await fetch("/api/v1/board");  // getAllBoards API 엔드포인트로 수정
        if (!response.ok) throw new Error("Failed to fetch boards");

        const boards = await response.json();
        const contentContainer = document.querySelector(".content-card-list");

        // 기존 게시물 목록 제거
        contentContainer.innerHTML = '';

        boards.forEach(board => {
            const contentCard = document.createElement("div");
            contentCard.classList.add("content-card");

            contentCard.innerHTML = `
                    <div class="left-card">
                        <div class="profile-box">
                            <div class="profile-image"></div>
                            <div class="profile-info">
                                <p class="profile-name">${board.authorName}</p>
                                <p class="profile-ago">${timeAgo(board.createdAt)}</p>
                            </div>
                        </div>
                        <div class="content-title">${board.title}</div>
                        <div class="content-content">${board.content}</div>
                        <div class="like-box">
                            <i class="bi bi-suit-heart-fill"></i>
                            <p>${board.likeCount || 0}</p>
                        </div>
                    </div>
                    <div class="right-card"></div>
                `;

            contentContainer.appendChild(contentCard);
        });
    } catch (error) {
        console.error("Error fetching boards:", error);
    }
}

function timeAgo(dateTime) {
    const now = new Date();
    const postDate = new Date(dateTime);
    const diffInHours = Math.floor((now - postDate) / (1000 * 60 * 60));
    return diffInHours > 0 ? `${diffInHours}시간 전` : "방금 전";
}

document.addEventListener("DOMContentLoaded", fetchBoards);