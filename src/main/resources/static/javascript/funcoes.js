// Toast
function myFunction() {
  // Get the snackbar DIV
  var x = document.getElementById("snackbar");

  // Add the "show" class to DIV
  x.className = "show";

  // After 3 seconds, remove the show class from DIV
  setTimeout(function(){ x.className = x.className.replace("show", ""); }, 3000);  
}

// Google Maps
var lat = '';
var lng = '';
var address = {cep} // or {endereço};

geocoder.geocode( { 'address': address}, function(results, status) {
  if (status == google.maps.GeocoderStatus.OK) {
     lat = results[0].geometry.location.lat();
     lng = results[0].geometry.location.lng();
  } else {
     alert("Não foi possivel obter localização: " + status);
  }
});
alert('Latitude: ' + lat + ' Logitude: ' + lng);