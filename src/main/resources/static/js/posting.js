async function publishPost() {
    // 제목과 내용 입력값 가져오기
    const title = document.querySelector(".title-input").value;
    const content = document.querySelector(".content-input").value;

    // 빈 값 체크
    if (!title || !content) {
        alert("제목과 내용을 입력하세요.");
        return;
    }

    try {
        const response = await fetch("/board", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: title,
                content: content
            })
        });

        if (response.ok) {
            const data = await response.json();
            alert("게시물이 성공적으로 발행되었습니다!");
            window.location.href = "/"; // 발행 후 메인 페이지로 이동
        } else {
            const errorText = await response.text();
            alert("게시물 발행 실패: " + errorText);
        }
    } catch (error) {
        console.error("Error:", error);
        alert("게시물 발행 중 오류가 발생했습니다.");
    }
}