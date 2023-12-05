
function Mudar() {
    elemento = document.getElementById("cadastrar");
    bloco = document.getElementById("container-bloco");
    bloco.style.display = "none";
    bloco2 = document.getElementById("container-registrar");
    bloco2.style.display = "flex";
}

function Voltar() {
    elemento = document.getElementById("voltar");
    bloco = document.getElementById("container-registrar");
    bloco.style.display = "none";
    bloco2 = document.getElementById("container-bloco");
    bloco2.style.display = "flex";
}

async function Confirmar() {
    let email = document.getElementById("email").value;
    let senha = document.getElementById("senha").value;

    let dado = await autenticarUsuario(email, senha);

    if (dado === -1) {
        alert("Senha ou email inválidos");
    } else {
        alert("Bem vindo de Volta");
        localStorage.setItem("appid", dado);
        window.location.href = "HomePage.html";
    }
}

async function Cadastrar() {
    
    let email = document.getElementById("email2").value;
    let senha = document.getElementById("senha2").value;
    let confimasenha = document.getElementById("confirmasenha").value;
    let nome = document.getElementById("nome").value;
    let date = document.getElementById("date").value;
    let termo = document.getElementById("termodeuso");

    if (email != "" && senha != "" && nome != "" && date != "" && confimasenha != "") {
        if (senha === confimasenha) {
            if (termo.checked) {
                let data = await inserirUsuario(nome, email, senha, formatarData(date))
                console.log(data)
                if (!data === true) {
                    alert("Cadastrado");
                    window.location.href = "HomePage.html";
                } else {
                    alert("Ocorreu um erro, realize novamente o cadastro");
                }
            } else {
                alert("Aceite os termos de uso");
            }
        } else {
            alert("Suas senhas estão diferentes");
        }
    } else {
        alert("Preencha todos os dados");
    }
}

function formatarData(data) {
    // Divide a data em dia, mês e ano
    const partes = data.split('-');
    
    // Reorganiza as partes da data no formato desejado
    const dataFormatada = partes[2] + '-' + partes[1] + '-' + partes[0];
    
    return dataFormatada;
}

async function autenticarUsuario(str1, str2) {
    const url = `http://localhost:4567/Index/login?email=${str1}&senha=${str2}`;

    try {
        const response = await fetch(url, {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        if (response.ok) {
            const data = await response.json();
            console.log(data);
            return data;
        } else {
            throw new Error('Erro na solicitação GET.');
        }
    } catch (error) {
        console.error('Erro:', error);
        throw -1; // Retorna -1 em caso de erro
    }
}


async function inserirUsuario(nome, email, senha, nasc) {
    const url = `http://localhost:4567/Index/registro?nome=${nome}&email=${email}&senha=${senha}&nasc=${nasc}`;
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        });

        if (response.ok) {
            const data = await response.json();
            console.log(data);
            return data;
        } else {
            throw new Error('Erro na solicitação POST.');
        }
    } catch (error) {
        console.error('Erro:', error);
        return false; // Retorna false em caso de erro
    }
}
