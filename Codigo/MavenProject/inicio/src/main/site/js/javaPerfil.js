const input = document.querySelector(".dir input");
const preview = document.querySelector(".preview");

let corzinha = localStorage.getItem("premium");
if (corzinha) {
  const profile = document.querySelector(".profile");
  console.log(profile);
  profile.classList.add("amarelo");
}

input.style.opacity = 0;

input.addEventListener("change", updateImageDisplay);

function updateImageDisplay() {
  while (preview.firstChild) {
    preview.removeChild(preview.firstChild);
  }

  const curFiles = input.files;
  if (curFiles.length === 0) {
    const para = document.createElement("p");
    para.textContent = "No files currently selected for upload";
    preview.appendChild(para);
  } else {
    const list = document.createElement("ol");
    preview.appendChild(list);

    for (const file of curFiles) {
      const listItem = document.createElement("li");
      const para = document.createElement("p");
      if (validFileType(file)) {
        para.textContent = `File name ${file.name}, file size ${returnFileSize(
          file.size,
        )}.`;
        const image = document.createElement("img");
        image.src = URL.createObjectURL(file);

        listItem.appendChild(image);
        listItem.appendChild(para);
      } else {
        para.textContent = `File name ${file.name}: Not a valid file type. Update your selection.`;
        listItem.appendChild(para);
      }

      list.appendChild(listItem);
    }
  }
}

const fileTypes = [
  "image/apng",
  "image/bmp",
  "image/gif",
  "image/jpeg",
  "image/pjpeg",
  "image/png",
  "image/svg+xml",
  "image/tiff",
  "image/webp",
  "image/x-icon",
];

function validFileType(file) {
  return fileTypes.includes(file.type);
}

function returnFileSize(number) {
  if (number < 1024) {
    return `${number} bytes`;
  } else if (number >= 1024 && number < 1048576) {
    return `${(number / 1024).toFixed(1)} KB`;
  } else if (number >= 1048576) {
    return `${(number / 1048576).toFixed(1)} MB`;
  }
}

const appid = localStorage.getItem("appid");
user(appid);

async function user (appid){
  let userData = await getUsuario(appid);
  localStorage.setItem("premium", userData.assinatura);
  insereDados(userData);
}

function insereDados (str){
  const input_name = document.querySelector(".nome");
  const input_email = document.querySelector(".email");
  const input_date = document.querySelector(".date");
  // const input_senha = document.querySelector(".senha");
  // const input_confirma_senha = document.querySelector(".confirmar_senha");
  // input_senha.value = str.senha;
  // input_confirma_senha.value = str.senha;
  input_name.value = str.nome;
  input_email.value = str.email;
  input_date.value = formatarData(str.dataNasc);
}

function formatarData(inputData) {
  // Mapeia os nomes dos meses para números
  const meses = {
      jan: '01',
      fev: '02',
      mar: '03',
      abr: '04',
      mai: '05',
      jun: '06',
      jul: '07',
      ago: '08',
      set: '09',
      out: '10',
      nov: '11',
      dez: '12'
  };

  // Divide a entrada em partes usando espaços e vírgulas
  const partes = inputData.split(/[\s,]+/);

  // Obtém o ano, mês e dia
  const ano = partes[2];
  const mes = meses[partes[0].toLowerCase()];
  const dia = partes[1];

  // Formata a data no formato desejado
  const dataFormatada = `${ano}-${mes}-${dia.padStart(2, '0')}`;

  return dataFormatada;
}

const deletar = document.querySelector(".deletar");
const sim = document.querySelector(".sim");
const nao = document.querySelector(".nao");

deletar.addEventListener("click", async () =>{
  const certeza = document.querySelector(".certeza");
  certeza.style.display = "block";
});

sim.addEventListener("click", async () => {
  let retorno = await deletarUsuario(appid);
  console.log(retorno);
  if (retorno === true) {
    alert("Foi deletado");
    localStorage.removeItem("appid");
    window.location.href = "index.html";
  }
  else{
    alert("Erro: tente novamente");
    const certeza = document.querySelector(".certeza");
    certeza.style.display = "none";
  }
})

nao.addEventListener("click", () => {
  const certeza = document.querySelector(".certeza");
  certeza.style.display = "none";
})

const salvar = document.querySelector(".salvar");
const quero = document.querySelector(".quero");
const nao_quero = document.querySelector(".nao_quero");

salvar.addEventListener("click", async () =>{
  const salvarei = document.querySelector(".desejo_salvar");
  salvarei.style.display = "block";
});

quero.addEventListener("click", async () => {
  const input_name = document.querySelector(".nome").value;
  const input_email = document.querySelector(".email").value;
  const input_senha = document.querySelector(".senha").value;
  const input_date = document.querySelector(".date").value;

  let retorno = (await atualizaUsuario(input_name,input_email,input_senha,input_date,appid));
  console.log(retorno);
  if (retorno === true) {
    alert("Salvado com sucesso");
    const salvarei = document.querySelector(".desejo_salvar");
    salvarei.style.display = "none";
  }
  else{
    alert("Erro: tente novamente");
    const salvarei = document.querySelector(".desejo_salvar");
    salvarei.style.display = "none";
  }
})

nao_quero.addEventListener("click", () => {
  const salvarei = document.querySelector(".desejo_salvar");
  salvarei.style.display = "none";
})

const premium = document.querySelector(".premium");
const desejo = document.querySelector(".desejo");
const nao_desejo = document.querySelector(".nao_desejo");

premium.addEventListener("click", async () =>{
  const premiumei = document.querySelector(".assinar_premium");
  premiumei.style.display = "block";
});

desejo.addEventListener("click", async () => {
  
  let retorno = await atualizarPremium(appid);

  if (retorno === true) {
    alert("Salvado com sucesso");
    localStorage.setItem("premium", true);
    const premiumei = document.querySelector(".assinar_premium");
    premiumei.style.display = "none";
  }
  else{
    alert("Erro: tente novamente");
    const premiumei = document.querySelector(".assinar_premium");
    premiumei.style.display = "none";
  }
})

nao_desejo.addEventListener("click", () => {
  const premiumei = document.querySelector(".assinar_premium");
  premiumei.style.display = "none";
})

async function deletarUsuario(appid) {

  const url = `http://localhost:4567/UserPage/delete?id=${appid}`;

  try {
    const response = await fetch(url, {
      method: 'DELETE',
      headers: {
        'Content-Type': 'application/json'
      },
    });

    if (response.ok) {
      const data = await response.json();
      console.log(data);
      return data;
    } else {
      throw new Error('Erro na solicitação DELETE.');
    }
  } catch (error) {
    throw error;
  }
}

async function getUsuario(appid) {
  const url = `http://localhost:4567/UserPage/user?id=${appid}`;

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
      throw new Error('Erro na solicitação GET.');
    }
  } catch (error) {
    throw error;
  }
}

async function atualizaUsuario(nome, email, senha, nasc, id) {

  const url = `http://localhost:4567/UserPage/update?nome=${nome}&email=${email}&senha=${senha}&nasc=${nasc}&id=${id}`;

  try {
    const response = await fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        "Accept": "*/*"
      },
    });

    if (response.ok) {
      const data = await response.json();
      return data;
    } else {
      throw new Error('Erro na solicitação PUT.');
    }
  } catch (error) {
    throw error;
  }
}

async function atualizarPremium(id) {

  const url = `http://localhost:4567/UserPage/assinatura?id=${id}`;

  try {
    const response = await fetch(url, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        "Accept": "*/*"
      },
    });

    if (response.ok) {
      const data = await response.json();
      return data;
    } else {
      throw new Error('Erro na solicitação PUT.');
    }
  } catch (error) {
    throw error;
  }
}