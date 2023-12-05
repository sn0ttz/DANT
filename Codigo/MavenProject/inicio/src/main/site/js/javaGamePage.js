const url = document.location.search;
const searchURL = new URLSearchParams(url);
let appid = searchURL.get('appid');
appid = parseInt(appid);

let corzinha = localStorage.getItem("premium");
if (corzinha) {
  const profile = document.querySelector(".profile");
  console.log(profile);
  profile.classList.add("amarelo");
}else{
    const profile = document.querySelector(".profile");
    profile.classList.remove("amarelo");
}

let teste;
dadosJogo(teste);

async function dadosJogo(mandar) {

    mandar = await receberJogo(appid);
    let resp = {};
    resp = {
        appid: mandar.appid,
        nome: mandar.nome,
        steamappid: retornaId(mandar.json),
        json: JSON.parse(mandar.json),
    }

    const nomeDoGame = document.querySelector(".container-esq .texto");
    let nomeSTR = `<h1>${resp.nome}</h1>`
    nomeDoGame.innerHTML = nomeSTR;

    const cima = document.querySelector(".cima");
    const baixo = document.querySelector(".baixo");
    let strBAIXO = "";

    cima.innerHTML = `<img src="${resp.json[resp.steamappid].data.header_image}" width="100%" height="400px" id="card-primario">`

    const container_direita = document.querySelector(".container-dir");

    let DireitaStr = `
    <div class="descricao">
        <h3>${resp.json[resp.steamappid].data.about_the_game}</h3>
    </div>
    `
    container_direita.innerHTML = DireitaStr;

    const container_baixo = document.querySelector(".container-baixo");

    container_baixo.innerHTML = `<h3>${resp.json[resp.steamappid].data.pc_requirements.minimum}</h3>`

    for (let i = 0; i < 3; i++) {
        strBAIXO += `
    <div class="card">
        <img src="${resp.json[resp.steamappid].data.screenshots[i].path_thumbnail}" width="100%" height="100px" onclick="MudarCard(this)">
    </div>
    `
    }

    baixo.innerHTML = strBAIXO;
    let testando = await receberForum(resp.appid);
    let varTipoDeEspaço = 3;
    const tipos_botoes = document.querySelectorAll(".navegador-forum .button");

    tipos_botoes.forEach(element => {
        element.addEventListener("click", () => {
            let numero = parseInt(element.classList[1]);
            varTipoDeEspaço = numero;
            final(numero);
        })
    })

    const enviarmensagem = document.querySelector(".label label input");
    enviarmensagem.addEventListener("click", async ()=> {
        let postagem = document.querySelector(".label label textarea").value;
        let usuarioId = localStorage.getItem("appid");
        console.log(usuarioId);
        let data = await mandarForum(postagem, resp.appid, usuarioId, varTipoDeEspaço);
        if (data >= 0) {
            alert("Salvo com sucesso");
            localStorage.setItem("forumDeleta", data); 
        } else {
            alert("Tente Novamente");
        }
    })

    // abrir e fechar comentários

    const rosto = document.querySelector(".forum");
    const seta = document.querySelector(".container-seu-comentario");
    let contador = 0;

    rosto.addEventListener("click", () => {

        const game = document.querySelector(".container-dados");
        const forum = document.querySelector(".container-tudo-forum");

        if (contador == 0) {
            game.style.display = "none";
            forum.style.display = "block";
            seta.style.display = "block";
            contador++;
        }
        else {
            game.style.display = "block";
            forum.style.display = "none";
            seta.style.display = "none";
            contador--;
        }

    });

    // abrir e fechar os subcomentarios

    const respostas = document.querySelectorAll(".respostas h6");

    respostas.forEach(resp => {
        resp.addEventListener("click", () => {
            const numero = resp.classList[0];
            const campo_preencher = document.querySelector(`.campo_preencher.number-${numero}`);

            if (resp.classList.contains("open")) {
                resp.classList.remove("open");
                campo_preencher.style.display = "none";
            } else {
                resp.classList.add("open");
                campo_preencher.style.display = "block";
            }
        });
    });


    function final(numero) {

        const totalContainers = testando.length;
        let contador_Tipos = 0;
        let novoVet = [];

        for (let i = 0, j = 0; i < totalContainers; i++) {
            if (testando[i].categoriaID === numero) {
                novoVet[j++] = testando[i].postagem;
                contador_Tipos++;
            }
        }

        let str = "";
        str = criarContainer(contador_Tipos);
        const inserir_comentarios = document.querySelector(".container-forum");
        inserir_comentarios.innerHTML = str;

        for (let i = 0; i < contador_Tipos; i++) {

            str = criarComentario(i, novoVet);
            const container = document.querySelector(`.container-comentarios.number-${i}`);
            container.innerHTML = str;

        }
    }

    function criarContainer(number) {
        let insere = "";
        for (let i = 0; i < number; i++) {
            insere += `
            <div class="container-comentarios number-${i}"></div>`;
        }
        return insere;
    }

    function criarComentario(i, novo) {
        let str = "";
        str += `
        <div class="comment">
            <div class="imagem-perfil">
                <i class="fa-solid fa-user fa-2xl"></i>
            </div>
            <div class="descricao-comment">
                <div class="texto">
                    <p>${novo[i]}</p>
                </div>
                <div class="respostas">
                    <h6 class="number-${i}">responder</h6>
                    <div class="abrir number-${i}">
                        <i class="fa-solid fa-plus fa-xl"></i>
                    </div>
                </div>
                <div class="campo_preencher number-${i}">
                    <label>
                        <textarea style="resize: none;">Insira seu comentário</textarea>
                        <input type="submit">
                    </label>
                </div>
            </div>
        </div>`;

        return str;
    }

    return resp;
}

function retornaId(str) {
    let resp = "";
    let isParsingID = false;

    for (let i = 0; i < str.length; i++) {
        if (str[i] === '"' && !isParsingID) {
            isParsingID = true;
        } else if (str[i] === '"' && isParsingID) {
            break;
        } else if (isParsingID) {
            resp += str[i];
        }
    }

    return resp;
}

async function receberJogo(id) {
    const url = `http://localhost:4567/GamePage/game?id=${id}`;
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            throw new Error("Deu pau GET");
        }
    } catch (error) {
        console.log(error);
    }
}

function MudarCard(element) {
    let primario = document.getElementById("card-primario");
    let aux = primario.getAttribute('src');
    let secundario = element;
    let aux2 = secundario.getAttribute('src');
    secundario.src = aux;
    primario.src = aux2;
}

async function receberForum(id) {
    const url = `http://localhost:4567/ForumPage?id=${id}`;
    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            throw new Error("Deu pau GET");
        }
    } catch (error) {
        console.log(error);
    }
}

async function mandarForum(postagem, forumid, userid, categoria) {
    const url = `http://localhost:4567/ForumPage/new?postagem=${postagem}&forumID=${forumid}&userID=${userid}&categoria=${categoria}`;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        });
        if (response.ok) {
            const data = await response.json();
            return data;
        } else {
            throw new Error("Deu pau POST");
        }
    } catch (error) {
        console.log(error);
    }
}