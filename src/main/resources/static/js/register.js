let selectedGender = '';

// 전역 함수로 selectGender 정의
function selectGender(gender) {
    selectedGender = gender;
    document.getElementById('gender').value = gender;
}

// 전역 함수로 register 정의
function register() {
    const emailField = document.getElementById('email');
    const passwordField = document.getElementById('password');
    const passwordConfirmField = document.getElementById('passwordConfirm');
    const nameField = document.getElementById('name');
    const birthdayField = document.getElementById('birthday');
    const genderField = document.getElementById('gender');

    // 각 필드가 null이 아닌지 확인
    if (!emailField || !passwordField || !passwordConfirmField || !birthdayField || !genderField || !nameField) {
        console.error("폼의 일부 요소를 찾을 수 없습니다.");
        alert("폼을 다시 확인해주세요.");
        return;
    }

    const email = emailField.value;
    const password = passwordField.value;
    const passwordConfirm = passwordConfirmField.value;
    const birthday = birthdayField.value;
    const gender = selectedGender;
    const name = nameField.value;

    if (password !== passwordConfirm) {
        alert("비밀번호가 일치하지 않습니다.");
        return;
    }

    if (birthday.length !== 8 || isNaN(birthday)) {
        alert("생년월일은 숫자 8자리로 입력해 주세요. (예: 19981011)");
        return;
    }
    const formattedBirthday = `${birthday.substring(0, 4)}-${birthday.substring(4, 6)}-${birthday.substring(6, 8)}`;

    const data = {
        email: email,
        password: password,
        birthday: formattedBirthday,
        gender: gender,
        name: name
    };

    fetch("/api/v1/member", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(data)
    })
        .then(response => {
            if (response.ok) {
                alert("회원가입이 완료되었습니다.");
                window.location.href = "/";
            } else {
                alert("회원가입에 실패했습니다.");
            }
        })
        .catch(error => console.error("Error:", error));
}