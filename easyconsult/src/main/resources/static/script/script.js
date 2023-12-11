window.addEventListener('load',function(){

    var estate = 1;

    function changer(){
        var change = document.getElementById('container');
        if(estate==1){
            change.style.backgroundImage = 'url("img/peak.gif")';
        }else if(estate==2){
            change.style.backgroundImage = 'url("img/peak2.gif")';
        }else{
            change.style.backgroundImage = 'url("img/peak3.gif")';
        }
      
        estate = estate === 4 ? 1 : estate +1;
      
    }
   
    document.getElementById('icon').addEventListener('click', changer);
    
  
    
});
