
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyDCyX10q3N6XKYZ3Kdo1Oqy2IfH0Qdc2n8",
    authDomain: "fast-beauty.firebaseapp.com",
    databaseURL: "https://fast-beauty.firebaseio.com",
    projectId: "fast-beauty",
    storageBucket: "fast-beauty.appspot.com",
    messagingSenderId: "664954501605"
  };
  firebase.initializeApp(config);
    

var db = firebase.firestore();
//mudando os settings do db para um formato aceitável.
const settings = {/* your settings... */ timestampsInSnapshots: true};
db.settings(settings);

var auth = firebase.auth();
var provider = new firebase.auth.FacebookAuthProvider();

var rgPattern = /^MG-\d{0,2}\.{0,1}\d{3}\.\d{3}$/;
var cpfPattern = /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/;
var nomePattern = /^([A-ZÀ-Ú]{1}[a-zà-ú]{1,15}( {0,1}))+$/;
var enderecoPattern = /^[A-Za-z]{1,7}\.{0,1} ([a-zA-Zà-úÀ-Ú]( {0,1}))+\,{0,1}( {0,1})\d[0-9]{1,4}$/;
var emailPattern = /^\S{2,61}@\S{2,12}(\.\S{2,10})(\.\S{2,4}){0,1}$/;
var senhaPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,}$/;

//Função para verificar se o cliente já está cadastrado
function verificaCadastro(){
    db.collection("clientes").get().then(
        function(listaClientes) {
            var jaExiste = false;
            listaClientes.forEach(
                function(doc) {
                    var cliente = doc.data();
                    if(doc.id == document.getElementById("form-cpf").value){
                        jaExiste = true;
                        break;
                    }
                });
            if(jaExiste == true){
                alert("CPF já cadastrado");
            }
            else{
                cadastrarCliente();
            }
    });
}

//Função para cadastrar clientes
function cadastrarCliente(){
    var name = document.getElementById("form-nome").value;
    var RG = "MG-" + document.getElementById("form-rg").value;
    var cpf = document.getElementById("form-cpf").value;
    var ENDERECO = document.getElementById("form-endereco").value;
    var EMAIL = document.getElementById("form-email").value;
    var SENHA = document.getElementById("form-password").value;
    
    /*
    auth.signInWithPopup(provider).then(
        function(resultado){
            var token = result.credential.accessToken;
            var user = result.user;
            console.log("Usuário " + user.name + " com o token " + token);

        }
    ).catch(
        function(erro){
            console.log(erro);
        }
    );
    */
    db.collection("clientes").doc(cpf).set({
        nome: name,
        rg: RG,
        endereco: ENDERECO,
        email: EMAIL,
        senha: SENHA
    })
    
    alert("Cadastro realizado com sucesso!");
}

//Função para verificar se os campos foram preenchidos corretamente.
function verificaCampos(){
    var nome = document.getElementById("form-nome").value;
    var rg = "MG-" + document.getElementById("form-rg").value;
    var cpf = document.getElementById("form-cpf").value;
    var endereco = document.getElementById("form-endereco").value;
    var email = document.getElementById("form-email").value;
    var senha = document.getElementById("form-password").value;
    
    //Verificará a validade dos campos.
    if(cpf && nome && rg && endereco && email && senha){
        var aviso = "Favor preencher corretamente os seguintes campos: \n";
        if(nomePattern.test(nome) == false){
            aviso += "Fornecer seu nome completo corretamente. \n";
        }
        else if(rgPattern.test(rg) == false){
            aviso += "Fornecer seu rg corretamente. \n";
        }
        else if(cpfPattern.test(cpf) == false || cpf == ""){
            aviso += "Fornecer seu cpf corretamente. \n";
        }
        else if(enderecoPattern.test(endereco) == false){
            aviso += "Fornecer seu endereço (via, número). \n";
        }
        else if(emailPattern.test(email) == false){
            aviso += "Fornecer seu e-mail corretamente. (ex.: example@example.com) \n";
        }
        else if(senhaPattern.test(senha) == false){
            aviso += "Fornecer uma senha que tenha ao menos 6 caracteres, contendo ao menos uma letra maiúscula, uma letra minúscula e um número.";
        }
        
        //Envia um alerta identificando os campos preenchidos incorretamente.
        if(aviso != "Favor preencher corretamente os seguintes campos: \n"){
            alert(aviso);
        }
        else if(aviso == "Favor preencher corretamente os seguintes campos: \n"){ //Verifica se os campos de confirmação são iguais.
            var confirmaEmail = document.getElementById("form-confirmEmail").value;
            var confirmaSenha = document.getElementById("form-confirmPassword").value;
            
            if(confirmaEmail != email){
                aviso = "Campo confirmação de e-mail inválido."
                alert(aviso);
            }
            else if(confirmaSenha != senha){
                aviso = "Campo confirmação de senha inválido."
                alert(aviso);
            }
            else{
                verificaCadastro();
            }
        }
    }
    else{
        alert("Favor preencher todos os campos obrigatórios")
    }
}