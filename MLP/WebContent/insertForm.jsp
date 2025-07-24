<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>회원가입</title>
  <link rel="stylesheet" href="login.css"> <!-- 필요시 회원가입 전용 CSS로 교체 -->
  <style>
    body {
      background: #f2f4f8;
      font-family: 'Arial', sans-serif;
    }
    .container {
      max-width: 800px;
      margin: 50px auto;
      background: #fff;
      padding: 40px;
      border-radius: 12px;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    h1 {
      text-align: center;
      margin-bottom: 30px;
    }
    .form-group {
      margin-bottom: 20px;
    }
    label {
      display: block;
      font-weight: bold;
      margin-bottom: 5px;
    }
    input, select {
      width: 100%;
      padding: 10px;
      font-size: 14px;
      border: 1px solid #ccc;
      border-radius: 5px;
    }
    .submit-btn {
      width: 100%;
      padding: 12px;
      background-color: #4A6CF7;
      color: white;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      cursor: pointer;
    }
    .submit-btn:hover {
      background-color: #3b56d4;
    }
    .required {
      color: red;
      margin-left: 4px;
    }
  </style>
</head>
<body>

<div class="container">
  <h1>회원가입</h1>
  <form action="member" method="post">
    <input type="hidden" name="command" value="insert">

    <div class="form-group">
      <label>조직 <span class="required">*</span></label>
      <input type="text" name="organization" placeholder="조직을 선택해 주세요." required>
    </div>

    <div class="form-group">
      <label>부서 <span class="required">*</span></label>
      <input type="text" name="department" placeholder="부서를 선택해 주세요." required>
    </div>

    <div class="form-group">
      <label>이름 <span class="required">*</span></label>
      <input type="text" name="name" placeholder="이름을 입력해 주세요." required>
    </div>

    <div class="form-group">
      <label>영문이름</label>
      <input type="text" name="ename" placeholder="영문이름을 입력해 주세요.">
    </div>

    <div class="form-group">
      <label>아이디 <span class="required">*</span></label>
      <input type="text" name="id" placeholder="로그인 아이디를 입력해 주세요." required>
    </div>

    <div class="form-group">
      <label>비밀번호 <span class="required">*</span></label>
      <input type="password" name="pw" placeholder="비밀번호를 입력해 주세요." required>
    </div>

    <div class="form-group">
      <label>비밀번호 확인 <span class="required">*</span></label>
      <input type="password" name="pw_check" placeholder="비밀번호를 확인해 주세요." required>
    </div>

    <div class="form-group">
      <label>이메일주소</label>
      <input type="email" name="email" placeholder="abc@example.com 형식으로 입력하세요.">
    </div>

    <div class="form-group">
      <label>선호언어 <span class="required">*</span></label>
      <select name="language" required>
        <option value="ko">한국어</option>
        <option value="en">English</option>
      </select>
    </div>

    <div class="form-group">
      <label>전화번호</label>
      <input type="text" name="phone" placeholder="010-1234-5678 형식으로 입력하세요.">
    </div>

    <div class="form-group">
      <button type="submit" class="submit-btn">회원가입</button>
    </div>
  </form>
</div>

</body>
</html>
