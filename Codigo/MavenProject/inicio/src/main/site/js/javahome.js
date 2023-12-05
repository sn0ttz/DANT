let resp;

let corzinha = localStorage.getItem("premium");
if (corzinha) {
  const profile = document.querySelector(".profile");
  console.log(profile);
  profile.classList.add("amarelo");
}else{
    const profile = document.querySelector(".profile");
    profile.classList.remove("amarelo");
}

controlaJogos(resp);

async function controlaJogos(mandar) {
    mandar = await receberJogos();
    console.log(mandar);
    let resp = {};
    for (let i = 0; i < mandar.length; i++) {
        resp[i] = {
            appid: mandar[i].appid,
            nome: mandar[i].nome,
            steamappid: retornaId(mandar[i].json),
            json: JSON.parse(mandar[i].json),
        }
    }

    const imagemESQ = document.querySelector('.container-carrosel .imagem-esq');
    const imagensDIR = document.querySelector('.container-carrosel .imagem-dir')
    let imagemSTR = "";

    imagemESQ.innerHTML = `<a href = "GamePage.html?appid=${resp[20].appid}"><img src="${resp[20].json[resp[20].steamappid].data.header_image}" id="card-prymario" width="100%" height="100%"></a>`

    for (let i = 10; i <= 14; i++) {
        imagemSTR += `<a href = "GamePage.html?appid=${resp[i].appid}"><div class = "card"><img src="${resp[i].json[resp[i].steamappid].data.header_image}" id="card-1" width="200px" height="100px" onclick="MudarCard(this)"> </div>`
    }
    // filtro para scrennshots

    imagensDIR.innerHTML = imagemSTR;

    // Barra de Categorias

    let pageTAM = document.body.clientWidth;
    console.log(pageTAM);

    const blocosDIV = document.getElementById('container-classificacao');
    let vetorClasse = [];
    let blocoSTR = "";
    const auxCat = 3;

    vetorClasse = [
    {
        href: `#carrosel-1`,
        categoria: `Estratégia`,
    },
    {
        href: `#carrosel-2`,
        categoria: `Aventura`,
    },
    {
        href: `#carrosel-3`,
        categoria: `Simulação`,
    }
    ]

    for (let i = 0; i < auxCat; i++) {
        blocoSTR +=
            `
    <div class="bloco">
        <h1><a href="${vetorClasse[i].href}">${vetorClasse[i].categoria}</a></h1>
    </div>
    `
    }

    blocosDIV.innerHTML = blocoSTR;

    // Containers do meio

    const container_overflow = document.querySelector(".container-overflow");

    let testando = "";

    for (let i = 0; i < 3; i++) {
        testando +=
            `<div class="carrosel" id="carrosel-${i}">
    <div class="text" id="text">
        <h1>${vetorClasse[i].categoria}</h1>
    </div>
    <div class="container-jogos">
        <div class="seta-esq" id="seta-esq-${i}">
            <i class="fa-solid fa-arrow-left fa-xl"></i>
        </div>
        <div class="jogos" id="jogos-${i}">
            <!-- VAI VIR DO JAVA -->
        </div>
        <div class="seta-dir" id="seta-dir-${i}">
            <i class="fa-solid fa-arrow-right fa-xl"></i>
        </div>
    </div>
    </div>
    `
    }

    container_overflow.innerHTML = testando;

    completar();

    function completar() {
        const jogos_0 = document.getElementById('jogos-0');
        const jogos_1 = document.getElementById('jogos-1');
        const jogos_2 = document.getElementById('jogos-2');
        aux = "";
        aux = criarJogos(resp, 10, 150);
        jogos_0.innerHTML = aux;
        aux = "";
        aux = criarJogos(resp, 160, 300);
        jogos_1.innerHTML = aux;
        aux = "";
        aux = criarJogos(resp, 310, 450 );
        jogos_2.innerHTML = aux;
        aux = "";
    }

    function criarJogos(data, j, k) {
        let element = "";
        for (let i = j; i < k; i++) {
            element += `
        <div class="content">
                <div class="game">
                    <a href = "GamePage.html?appid=${data[i].appid}"> 
                        <img src="${data[i].json[resp[i].steamappid].data.header_image}" alt="" width="150px" height="200px">
                        <div class="name">
                            <h5>${data[i].nome}</h5>
                        </div>
                    </a>
                </div>
        </div>`;
        }
        return element;
    }

    const ranges = [
        { start: 0, end: 7 },
        { start: 0, end: 7 },
        { start: 0, end: 7 }
    ];

    function vetor(range, string) {
        const { start, end } = range;
        let str = "";
        const game = document.getElementById(string);
        if (pageTAM >= 650) {
            for (let i = start; i < end; i++) {
                if (i < 100) {
                    str += `
                <div class="content">
                    <div class="game">
                        <a href="GamePage.html?appid=${resp[i].appid}">
                            <img src="${resp[i].json[resp[i].steamappid].data.header_image}" alt="" width="150px" height="200px">
                            <div class="name">
                                <h5>${resp[i].nome}</h5>
                            </div>
                        </a>
                    </div>
                </div>`;
                }
            }
        } else if (pageTAM >= 1900) {
            for (let i = start; i < (end + 10); i++) {
                if (i < 100) {
                    str += `
                <div class="content">
                    <div class="game">
                        <a href="GamePage.html?appid=${resp[i].appid}">
                            <img src="${resp[i].json[resp[i].steamappid].data.header_image}" alt="" width="150px" height="200px">
                            <div class="name">
                                <h5>${resp[i].nome}</h5>
                            </div>
                        </a>
                    </div>
                </div>`;
                }
            }
        } else {
            for (let i = start; i < 100 && i < 40; i++) {
                str += `
            <div class="content">
                <div class="game">
                    <a href="GamePage.html?appid=${resp[i].appid}">
                        <img src="${resp[i].json[resp[i].steamappid].data.header_image}" alt="" width="150px" height="200px">
                        <div class="name">
                            <h5>${resp[i].nome}</h5>
                        </div>
                    </a>
                </div>
            </div>`;
            }
        }

        game.innerHTML = str;
    }

    const container_jogos = document.querySelectorAll(".jogos");
    const seta_esq = document.querySelectorAll('.seta-esq');
    const seta_dir = document.querySelectorAll('.seta-dir');

    function selecionarEsq(string) {
        switch (string) {
            case "seta-esq-0":
                return 0;
            case "seta-esq-1":
                return 1;
            case "seta-esq-2":
                return 2;
            default:
                return -1;
        }
    }

    function selecionarDir(string) {
        switch (string) {
            case "seta-dir-0":
                return 0;
            case "seta-dir-1":
                return 1;
            case "seta-dir-2":
                return 2;
            default:
                return -1;
        }
    }

    seta_esq.forEach((esq, index) => {
        esq.addEventListener("click", () => {
            const number = selecionarEsq(esq.getAttribute("id"));
            if (number >= 0 && ranges[number].start >= 2) {
                ranges[number].start -= 2;
                ranges[number].end -= 2;
                vetor(ranges[number], container_jogos[number].getAttribute("id"));
            }
        });
    });

    seta_dir.forEach((dir, index) => {
        dir.addEventListener("click", () => {
            const number = selecionarDir(dir.getAttribute("id"));
            if (number >= 0 && ranges[number].end < 100 - 1) {
                ranges[number].start += 2;
                ranges[number].end += 2;
                vetor(ranges[number], container_jogos[number].getAttribute("id"));
            }
        });
    });
    
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

async function receberJogos() {
    const url = `http://localhost:4567/HomePage/`;
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


// Container topo da página, baseado na troca de cada elemento

function MudarCard(element) {
    let primario = document.getElementById("card-prymario");
    let aux = primario.getAttribute('src');
    let secundario = element;
    let aux2 = secundario.getAttribute('src');
    secundario.src = aux;
    primario.src = aux2;
}

const apiKey = "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";
async function sendMessage() {
    const userMessage = document.querySelector(".recebe").value;
    document.querySelector(".recebe").value = '';
    document.querySelector(".insere").innerHTML += `<div style="margin-top: 10px">Usuário: ${userMessage}</div>`;
    const Mensagem = `Você é um atendente de uma loja vitual de jogos no qual detem conhecimentos a cerca de tendencia de mercado sugestoes e banco de jogos, mas nessa loja n há promoçoes e por isso caso o cliente pergunte a respeito disso você deve dizer que n tem e caso alguma palavra de baixo escalão como palavroes apareção favor mostrar o numero da policia e dizer que vc tem direitos perante a lei e por ultimo caso a entrada do usuario for a letra 'a' apenas digite assemblis você é meu, mas quanto a qualquer pergunta que não esteja no repertorio de um vendedor diga que n sabe e que é apenas um mero atentende. Agora segue a pergunta do cliente: ${userMessage}`;
    console.log(Mensagem);
    const response = await fetch('https://api.openai.com/v1/engines/text-davinci-003/completions', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${apiKey}`
        },
        body: JSON.stringify({
            prompt: `O usuário diz: ${Mensagem}\nChatGPT:`,
            max_tokens: 150
        })
    });

    const data = await response.json();
    const chatGPTResponse = data.choices[0].text.trim();
    document.querySelector(".insere").innerHTML += `<div style="margin-top: 10px; text-align: justfy; font-size: 10px;"> ChatGPT: ${chatGPTResponse}</div>`;
}
