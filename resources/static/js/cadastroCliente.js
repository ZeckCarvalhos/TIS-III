
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

{
    db.collection("clientes").get().then(
        function(listaClientes) {
            listaClientes.forEach(
                function(doc) {
                    // doc.data() is never undefined for query doc snapshots
                    var cliente = doc.data();
                    console.log(doc.id, " => ", cliente.nome);
                });
        });
}

//Função para cadastrar clientes
function cadastrarCliente(){
    var name = document.getElementById("form-nome").value;
    var RG = document.getElementById("form-rg").value;
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
}