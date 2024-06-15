


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Dice Roll</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <link href="https://unpkg.com/nes.css@latest/css/nes.min.css" rel="stylesheet" />
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
</head>
<body>

    <header class="sticky">
        <div class="nes-container">
            <div class="nav-brand">
                <a href="">
                    <h1>
                        <i class="snes-jp-logo brand-logo"></i>
                        Dice Game
                    </h1>
                </a>
                <p>TP1 Exercice 2.</p>
            </div>
            <div class="social-button">
                 <i class="nes-octocat animate"></i>
            </div>
        </div>
    </header>

<div class="nes-container">
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form action="${pageContext.request.contextPath}/Game" method="post">
                <div class="nes-field">
                    <label for="diceNumber">Numéro du dé</label>
                    <input type="number" class="nes-input" id="diceNumber" name="diceNumber" min="1" max="3" placeholder="Enter dice number">
                </div>
                <br>
                <button type="submit" class="nes-btn is-primary" ${game.isOver == true ? "disabled" : ""}>Lancer le dé</button>
            </form>
            <div class="mt-3">
                <c:forEach var="dice" items="${game.dices}">
                    <p class="dice">
                            ${dice.value == 0 ? "?" : dice.value}
                        <span class="badge badge-${dice.rolled ? 'success' : 'secondary'}">${dice.rolled ? 'Rolled' : 'Not Rolled'}</span>
                    </p>
                </c:forEach>
            </div>
            <section class="showcase">
                <section class="nes-container with-title">
                    <h3 class="title">Score</h3>
                    <p class="card-text">Current Score: ${game.user.score}</p>
                    <p class="card-text">Best Score: ${game.user.bestscore}</p>
                </section>
            </section>

                     <c:if test="${game.isOver == true}">
                         <dialog class="nes-dialog" id="dialog-default">
                             <form method="dialog">
                                 <p class="title">Game Over !</p>
                                 <c:choose>
                                     <c:when test="${game.isWon == true}">
                                         <p>Game won GG's</p>
                                     </c:when>
                                     <c:otherwise>
                                         <c:forEach var="message" items="${messages}">
                                             <p>
                                                     ${message}
                                             </p>
                                         </c:forEach>
                                     </c:otherwise>
                                 </c:choose>
                                 <menu class="dialog-menu">
                                     <button class="nes-btn">Cancel</button>
                                     <button id="restart-button" class="nes-btn is-primary" >Restart</button>
                                 </menu>
                             </form>
                         </dialog>
                         <br>
            <div style="display: flex; justify-content: space-between;">
                         <form action="${pageContext.request.contextPath}/leaderboard" method="post">
                             <button type="submit" class="nes-btn is-warning" >Check Leaderboard</button>
                         </form>
                         <form action="${pageContext.request.contextPath}/disconnect" method="post">
                             <button type="submit" class="nes-btn is-error" >Disconnect</button>
                         </form>
                        <form action="${pageContext.request.contextPath}/reinit" method="post">
                            <button type="submit" class="nes-btn is-primary" >Restart</button>
                        </form>
            </div>
                   </c:if>

                </div>
            </div>
            <ul>
                <c:forEach var="message" items="${messages}">
                    <div class="alert alert-danger mt-3" role="alert">
                            ${message}
                    </div>
                </c:forEach>
            </ul>
        </div>
    </div>
</div>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<style>
    body {
        font-family: 'Press Start 2P', system-ui;
        font-weight: 400;
        font-style: normal;
    }
    .dice {
        font-size: 50px;
        text-align: center;
        color: #333;
        font-family: 'Courier New', Courier, monospace;
    }
    .badge {
        font-size: 30%;
        padding: .1em .2em;
        color: #fff;
        background-color: #007bff;
        border: 2px solid #333;
        border-radius: 5px;
    }


    .social-button {

        position: absolute;

        top: 10px;

        right: 30px;

    }
     .badge-success {
        background-color: #28a745;
    }
    .badge-secondary {
        background-color: #6c757d;
    }
    .card {
        width: 80%;
        margin: auto;
    }

    .card button {
        display: inline-block;
        margin-right: 10px;
    }

    @keyframes dice-roll {
        0% { transform: rotate(0deg); }
        50% { transform: rotate(180deg); }
        100% { transform: rotate(360deg); }
    }
</style>
    <script>
        //display a dialog-default when the game ends
        var isGameOver = ${game.isOver};
        if (isGameOver) {
            document.getElementById('dialog-default').showModal();
        }
        //send a post request to our reinit servlet and refresh the page once the user clicks
        //on restart button
        var restartButton = document.getElementById('restart-button');
        if (restartButton) {
            restartButton.addEventListener('click', function() {
                fetch('${pageContext.request.contextPath}/reinit', { method: 'POST' })
                    .then(response => {
                        window.location.href = '${pageContext.request.contextPath}/Game';
                    })
            });
        }
    </script>
</body>
</html>