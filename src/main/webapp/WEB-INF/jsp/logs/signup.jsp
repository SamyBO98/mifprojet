<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
    <div class="d-flex justify-content-center">
        <span class="border bordel-dark rounded-lg">
            <div class="d-flex flex-column px-4">
                <h1 class="d-flex flex-row">Inscription</h1>
                <form method="post" action="">
                    <div class="d-flex flex-row ml-2"> Adresse e-mail </div>
                    <div class="d-flex flex-row justify-content-center"> <input type="email" name="email" required /> </div>
                    <div class="d-flex flex-row ml-2"> Mot de passe </div>
                    <div class="d-flex flex-row justify-content-center"> <input type="password" name="password" required /> </div>
                    <div class="d-flex flex-row ml-2">  Confirmation mot de passe </div>
                    <div class="d-flex flex-row justify-content-center"> <input type="password" name="passwordconfirm" required /> </div>
                    <div class="d-flex flex-row ml-2"> Nom </div>
                    <div class="d-flex flex-row justify-content-center"> <input type="text" name="name" required /> </div>
                    <div class="d-flex flex-row ml-2"> Prénom </div>
                    <div class="d-flex flex-row justify-content-center"> <input type="text" name="firstname" required /> </div>
                    <div class="d-flex flex-row justify-content-end py-4">
                        <button class="btn btn-secondary" type="submit" name="submit" value="Create My Account">Inscription</button>
                    </div >
                </form>
            </div>
        </span>
    </div>
</div>
</body>
</html>