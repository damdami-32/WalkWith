<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Seller Page</title>
    <style>
        @font-face {
            font-family: "museum";
            src: url("../font/museum.ttf") format("truetype");
            font-weight: normal;
        }

        @font-face {
            font-family: "ebs";
            src: url("../font/ebs.ttf") format("truetype");
            font-weight: normal;
        }

        #banner{
            height: 9rem;
            max-width: 100%;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        #user{
            color: #A99A8F;
            margin-left: 17px;
            font-size: 15px;
            font-family: 'ebs';
        }

        #left-div{
            background-image: url("../img/Walkwith-div-left.png");
            background-repeat: no-repeat;
            width: 230px;
            height: 280px;
            border-radius: 10px;
            display: flex;
            align-items: center;
            margin-top: 5px;
            font-family: 'museum', serif;
        }

        #menu{
            list-style: none;
            color: white;
            font-size: 1.5rem;
            padding-top: 10px;
        }

        #menu > li{
            padding-bottom: 5px;
        }

        #right-div{
            background: RGB(242, 235, 226);
            width: 1200px;
            height: 670px;
            margin-left: 20px;
            border-radius: 10px;
            background-size: cover;
            padding-left: 4rem;
            padding-right: 4rem;
            padding-top: 2rem;
            padding-bottom: 1rem;
            font-family: 'ebs';
        }

        .info-head{
            background: #AC998B;
            font-size: 30px;
            font-family: 'ebs';
            color: white;
            padding-left: 15px;
            border-radius: 7px;
        }

        #right-div > a{
            color: #BAAA9C;
            display: right;
        }

        #customer-div {
            color: #AD9B8C;
            font-size: 1.5rem;
            font-family: 'ebs';
            display: flex;
            margin-bottom: 25px;
        }
        
        .ra {
            text-align: right;
            margin-top: 4px;
            color: RGB(172, 153, 139);
            margin-top: 7px;
            margin-right: 20px;
        }

        .info-menu{
            text-align: right;
            margin-right: 10px;
            display: flex;
            justify-content: center;
            font-family: 'museum', serif;
            flex-flow: column;
        }

        .info{
            margin-left: 10px;
        }

        #store-div{
            color: #AD9B8C;
            font-size: 1.5rem;
            font-family: 'ebs';
            display: flex;
            align-items: center;
        }
        
        .store-item{
            width: 180px;
            height: 200px;
            background-size: cover;
            border-radius: 10px;
        }

    </style>
</head>
<body>
    <div class="container">
        <div id="banner">
            <img id="banner-img" src="../img/Walkwith-logo.png">
        </div>
        <div id="user">
                <span>Pet�� �Բ��ϴ� User��</span>
        </div>
        <div class="container" style="display: flex;">
            <div id="left-div">
                <ul id="menu">
                    <li>Home</li>
                    <li>My Page</li>
                    <li>Reservation</li>
                    <li>Review</li>
                    <li>Market</li>
                </ul>
            </div>
            <div id="right-div">
                <div class="info-head">����� ����</div>
                <div class="ra"><a>����</a></div>
                <div id="customer-div">   
                    <div class="info-menu"  style="margin-left: 15px;">
                        <div>�̸�</div>
                        <div>ID</div>
                        <div>PW</div>
                        <div>����ó</div>
                        <div>E-Mail</div>
                    </div>
                    <div class="info">
                        <div>����</div>
                        <div>User123</div>
                        <div>********</div>
                        <div>010-1234-1234</div>
                        <div>adress@dongduk.ac.kr</div>
                    </div>
                </div>
                <div class="info-head">���� ����</div>
                <div class="ra">
                    <a style="margin-right: 20px;">�߰�</a>
                    <a>����</a>
                </div>
                <div id="store-div">
                    <img class="store-item" src="../img/Walkwith-market-Image.png"></img>
                    <div class="info-menu" style="margin-left: 25px;">
                        <div>�̸�</div>
                        <div>�ּ�</div>
                        <div>�����ð�</div>
                        <div>����</div>
                        <div>���ƿ�</div>
                    </div>
                    <div class="info">
                        <div>����</div>
                        <div>����� ���ϱ� ...</div>
                        <div>10:00 ~ 20:00</div>
                        <div>5.0</div>
                        <div>150</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
    crossorigin="anonymous"></script>
</body>
</html>