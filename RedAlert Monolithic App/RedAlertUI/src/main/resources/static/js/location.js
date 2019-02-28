var x = document.getElementById("demo");

      function getLocation() {

          if (navigator.geolocation) {

              navigator.geolocation.getCurrentPosition(showPosition, showError);

          } else {

              x.innerHTML = "Geolocation is not supported by this browser.";

          }

      }

     

 

      function showPosition(position) {

          var lat = position.coords.latitude;

          document.getElementById("latitude").value=lat;

          var lon = position.coords.longitude;

          document.getElementById("longitude").value=lon;

          var latlon = new google.maps.LatLng(lat, lon)

          var mapholder = document.getElementById('mapholder')

 

          var myOptions = {

          center:latlon,zoom:14,

          mapTypeId:google.maps.MapTypeId.ROADMAP,

          mapTypeControl:false,

          navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}

          }

         

          var map = new google.maps.Map(document.getElementById("mapholder"), myOptions);

          var marker = new google.maps.Marker({position:latlon,map:map,title:"You are here!"});

          mapholder.style.height = '200px';

          mapholder.style.width = '100%';

      }

 

      function showMap(latd,longtd) {

          console.log("Latitude"+latd+"/n Longitude"+longtd);

          var lat = latd;

          var lon = longtd;

          var latlon = new google.maps.LatLng(lat, lon);

          var mapholder = document.getElementById('mapholder');

         

 

          var myOptions = {

          center:latlon,zoom:14,

          mapTypeId:google.maps.MapTypeId.ROADMAP,

          mapTypeControl:false,

          navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}

          }

         

          var map = new google.maps.Map(document.getElementById("mapholder"), myOptions);

          var marker = new google.maps.Marker({position:latlon,map:map,title:"You are here!"});


      
      }
      
      function showMapOnPost(latd,longtd,mapId) {

          console.log("Latitude"+latd+"/n Longitude"+longtd);

          var lat = latd;

          var lon = longtd;

          var latlon = new google.maps.LatLng(lat, lon);

          var mapholder = document.getElementById(mapId);

         

 

          var myOptions = {

          center:latlon,zoom:14,

          mapTypeId:google.maps.MapTypeId.ROADMAP,

          mapTypeControl:false,

          navigationControlOptions:{style:google.maps.NavigationControlStyle.SMALL}

          }

         

          var map = new google.maps.Map(document.getElementById(mapId), myOptions);

          var marker = new google.maps.Marker({position:latlon,map:map,title:"You are here!"});


      
      }

 

      function showError(error) {

          switch(error.code) {

              case error.PERMISSION_DENIED:

                  x.innerHTML = "User denied the request for Geolocation."

                  break;

              case error.POSITION_UNAVAILABLE:

                  x.innerHTML = "Location information is unavailable."

                  break;

              case error.TIMEOUT:

                  x.innerHTML = "The request to get user location timed out."

                 break;

              case error.UNKNOWN_ERROR:

                  x.innerHTML = "An unknown error occurred."

                  break;

          }

      }