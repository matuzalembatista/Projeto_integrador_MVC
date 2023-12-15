window.addEventListener('load',function(){
   

  const regex = /^[a-zA-Z]{6,}[^\w\s][0-9]{4}$/;

document.getElementById("usuarioCAD").addEventListener('blur', function(){
 var entradaUser = document.getElementById('usuarioCAD').value;
 entradaUser.toLowerCase().trim();
 if(!regex.test(entradaUser)){  
  document.getElementById('usuarioCAD').value = "";
  mostrarAlerta("Preencha corretamente o campo usuario!", "Usuario deve conter no minimo 6 letras um caracter e 4 números","warning","Ok");
  
 }

})








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
    document.getElementById('button').addEventListener('mouseover', ValidaSenha);
   
    
  function ValidaSenha(){
    var senha = document.getElementById('senha').value;
    var confirmSenha = document.getElementById('confirmSenha').value;
    
    if(senha != confirmSenha ){
       mostrarAlerta("Senhas não coincidem", "","warning","Ok");
    }
  }

  
  function ValidaUsuarioCAD(){
    var message = document.getElementById('message');

  
        if(message.textContent.trim()){
            mostrarAlerta("Usuario existente", "","warning","Ok");
        
    }
  }
  ValidaUsuarioCAD();
    
 

  function mostrarAlerta(titulo, texto,icone,button) {
   
    Swal.fire({
       
      title: titulo,
      text: texto,
      icon: icone, 
      confirmButtonColor: '#5FBDFF',
      confirmButtonText: button,
    
     
    })
}




});
