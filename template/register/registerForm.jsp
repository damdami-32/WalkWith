<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>회원가입</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="register_view.css">
</head>
<body>
    <div class="container">
        <div id="banner" class="d-flex align-items-center justify-content-center">
            <img id="banner-logo" src="../img/Walkwith-logo.png" alt="Walkwith Logo">
        </div>
        <div class="container d-flex content-container">
            <div id="right-div" class="d-flex justify-content-center align-items-center">
                <form name="form" method="POST" action="/user/register" class="register-form">
                <h1 class="text-center">회원가입</h1>
                    <div id="text" class="position-relative">
                        <input type="text" id="ID" name="userId" maxlength="9" placeholder="아이디" class="form-control">
                    </div>
                    <div id="text" class="position-relative">
                        <input type="password" id="PW" name="password" placeholder="비밀번호" class="form-control">
                    </div>
                    <div id="text" class="position-relative">
                        <input type="name" id="name" name="name" placeholder="이름" class="form-control">
                    </div>
                    <div id="text" class="position-relative">
                        <input type="phone" id="phon" name="phone" placeholder="전화번호" class="form-control">
                    </div>
                    <div id="text" class="position-relative">
                        <input type="email" id="email" name="email" placeholder="이메일" class="form-control">
                    </div>                    
                    <div>
                       <input type="button" id="btn" style=" margin-top: 25px; width: 100%;" value="회원가입" onclick="userCreate('/user/register')" class="btn btn-primary">
                    </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</body>
</html>
