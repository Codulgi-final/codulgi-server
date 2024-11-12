document.addEventListener("DOMContentLoaded", function () {
    const loginButton = document.querySelector("button");

    // 버튼 클릭 또는 Enter 키 입력 시 로그인 함수 실행
    async function handleLogin(event) {
        event.preventDefault(); // 기본 폼 동작 방지

        const email = document.querySelector("input[placeholder='아이디']").value;
        const password = document.querySelector("input[placeholder='비밀번호']").value;

        try {
            const response = await fetch("/api/v1/member/login", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify({
                    email: email,
                    password: password
                })
            });

            if (response.ok) {
                const data = await response.json();
                alert("로그인 성공!");
                // 성공 시 추가 작업 수행 (예: 토큰 저장 또는 리다이렉트)
            } else {
                const errorText = await response.text();
                alert(errorText); // 서버에서 보낸 에러 메시지를 그대로 표시
            }
        } catch (error) {
            console.error("Error:", error);
            alert("로그인 중 문제가 발생했습니다. 다시 시도해주세요.");
        }
    }

    // 로그인 버튼 클릭 이벤트 리스너
    loginButton.addEventListener("click", handleLogin);

    // Enter 키 입력 이벤트 리스너 추가
    document.addEventListener("keydown", function (event) {
        if (event.key === "Enter") {
            handleLogin(event);
        }
    });
});
