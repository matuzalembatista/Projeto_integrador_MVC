window.addEventListener('load', function(){

    function validaUsuarioLog(){
        var erro = document.getElementById('erro');
        var login = document.getElementById('login')
      
        if(erro.textContent.trim()){
          mostrarAlerta("Usuario ou senha incorreto", "","info","Ok");
          login.value ="";
        }
      
       }
      validaUsuarioLog();


      var estate = 1;

    function changer(){
        var change = document.getElementById('container');
    
        if(estate==1){
            change.style.backgroundImage = 'url("img/peak.gif")';
        }else{
            change.style.backgroundImage = 'url("img/peak2.gif")';
        

        }
      
        estate = estate === 3? 1 : estate +1;
      
    }
   document.getElementById('icon').addEventListener('click', changer);
})
document.getElementById('btnLogin').addEventListener('mouseover',validaUsuarioLog);

function mostrarAlerta(titulo, texto,icone,button) {
   
    Swal.fire({
       
      title: titulo,
      text: texto,
      icon: icone, 
      confirmButtonColor: '#5FBDFF',
      confirmButtonText: button,
    
     
    })


}
