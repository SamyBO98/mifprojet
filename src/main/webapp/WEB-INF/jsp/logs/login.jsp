<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container-fluid">
    <div class="d-flex justify-content-center">
        <span class="border bordel-dark rounded-lg">
            <div class="d-flex flex-column px-4">
                <h1 class="d-flex flex-row justify-content-center">Connexion</h1>
                <form method="post" action="">
                    <div class="d-flex flex-row ml-2"> Adresse e-mail </div>
                    <div class="d-flex flex-row justify-content-center"> <input type="email" name="email" required /> </div>
                    <br>
                    <div class="d-flex flex-row ml-2"> Mot de passe </div>
                    <div class="d-flex flex-row justify-content-center"> <input type="password" name="password" required /> </div>
                    <div class="d-flex flex-row justify-content-center py-4">
                        <button class="btn btn-secondary" type="submit" name="submit" value="Create My Account">Connexion</button>
                    </div>
                </form>
            </div>
        </span>
    </div>
</div>

