
let strFOOTER = `
<div class="container-footer">
<div class="redes-sociais">
    <ul>
        <li><a href="bobo.html"><i class="fa-brands fa-instagram fa-2xl"></i></a></li>
        <li><a href="bobo.html"><i class="fa-brands fa-youtube fa-2xl"></i></a></li>
        <li><a href="bobo.html"><i class="fa-brands fa-x-twitter fa-2xl"></i></a></li>
    </ul>
    <div class = "cima"><a href = "#container-navegar"><i class="fa-solid fa-chevron-up fa-2xl"></i></a></div>
</div>
<div class="recursos">
    <h3>Informações Relevantes</h3>
    <div class="uls">
        <ul>
            <li><a href="#">Sobre nós</a></li>
            <li><a href="#">Contato</a></li>
            <li><a href="#">Termos de Uso</a></li>
            <li><a href="#">Política de Privacidade</a></li>
            <li><a href="#">Relatórios de Segurança</a></li>
        </ul>
        <ul>
            <li><a href="#">Suporte Técnico</a></li>
            <li><a href="#">Carreiras</a></li>
            <li><a href="#">Parcerias</a></li>
        </ul>
    </div>
</div>
<hr>
<div class="quem-somos">
    <div class="imagem">
        <img src="content/dant2.png" width = "10%">
    </div>
    <p>Na D.A.N.T, nossa paixão pelo mundo dos jogos online é o que nos move e nos define. Somos mais do que uma empresa de distribuição de jogos; somos entusiastas, inovadores e construtores de comunidades virtuais. Nossa missão é fornecer uma plataforma de jogos online excepcional que oferece diversão, entretenimento e conexões para jogadores em todo o mundo. Buscamos criar uma experiência de jogo envolvente e emocionante, proporcionando acesso a uma ampla variedade de títulos, desde os mais recentes lançamentos até clássicos atemporais.
    </p>
</div>
<hr>
<div class="copiright">
    <h4>Copyright © 2023 D.A.N.T: Todos os direitos reservados.</h4>
</div>
</div>
`

let strHEADER = `
<nav>
<div class="container-navegar" id = "container-navegar">
    <div class="hamburguer">
        <i class="fa-solid fa-bars fa-xl"></i>
        <input type="checkbox" class="fake">
        <ul>
            <li><a href="HomePage.html">HOME PAGE</a></li>
            <li><a href="Search.html">SEARCH PAGE</a></li>
            <li><a href="Perfil.html">PERFIL</a></li>
        </ul>
    </div>
    <div class="esquerda">
        <div class="imagem">
            <a href="HomePage.html">
                <img src="content/dant1.png" alt="" width="60%" height="100%">
            </a>
        </div>
        <div class="links">
            <ul>
                <li><a href="HomePage.html">HOME PAGE</a></li>
                <li><a href="Search.html">SEARCH PAGE</a></li>
                <li><a href="Perfil.html">ASINAR PREMIUM</a></li>
            </ul>
        </div>
    </div>
    <div class="direita">
        <a href="Perfil.html">
            <div class="profile">
                <i class="fa-solid fa-user fa-xl"></i>
            </div>
        </a>
    </div>
</div>
</nav>
`

document.querySelector("header").innerHTML = strHEADER;
document.querySelector("footer").innerHTML = strFOOTER;