<head>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% String code = request.getParameter("code"); %>
<style>
#response, #data-response {
  border: 2px dashed #666;
  margin: 20px;
  padding: 10px;
  font-family: "Arial";
}
</style>

<!-- Include meta tag to ensure proper rendering and touch zooming -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Include jQuery Mobile stylesheets -->
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<!-- Include the jQuery library -->
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<!-- Include the jQuery Mobile library -->
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>


 <Script>
 var url = "http://test-4g-prod.apigee.net/v2.1/oauth/accesstoken";
 var params = "client_id=Z4GHyW8o3JXUrkAkGetWyUYLw99uMAkC&client_secret=iQ5WifRHg2Al1I32&code=<%=code%>&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Ftraining%2Ffaces%2Fapp%2Freturn.jsp&grant_type=authorization_code";
 var xhr = new XMLHttpRequest();
 xhr.open("POST", url, true);
 var accesstokenresponse='';

 //Send the proper header information along with the request
 xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

 xhr.onreadystatechange = function() {
     if (xhr.readyState == XMLHttpRequest.DONE ) {
        if(xhr.status == 200){
        	accesstokenresponse = JSON.parse(xhr.responseText);
            document.getElementById("response").innerHTML = accesstokenresponse.access_token;

            var xmlhttp = new XMLHttpRequest();
          
            xmlhttp.open("GET","http://test-4g-prod.apigee.net/v2/customers/12345",true);
            xmlhttp.setRequestHeader("Authorization", "Bearer "+accesstokenresponse.access_token);
            xmlhttp.onreadystatechange = function() {
            	if (xmlhttp.readyState == XMLHttpRequest.DONE ) {
                    if(xmlhttp.status == 200){
                    	dataresponse = JSON.parse(xmlhttp.responseText);
                    	f = {
                                brace: 0
                            };
                    	formattedResponse = JSON.stringify(dataresponse, null, 2).replace(/({|}[,]*|[^{}:]+:[^{}:,]*[,{]*)/g, function (m, p1) {
                    	    var rtnFn = function() {
                    	            return '<div style="text-indent: ' + (f['brace'] * 20) + 'px;">' + p1 + '</div>';
                    	        },
                    	        rtnStr = 0;
                    	    if (p1.lastIndexOf('{') === (p1.length - 1)) {
                    	        rtnStr = rtnFn();
                    	        f['brace'] += 1;
                    	    } else if (p1.indexOf('}') === 0) {
                    	        f['brace'] -= 1;
                    	        rtnStr = rtnFn();
                    	    } else {
                    	        rtnStr = rtnFn();
                    	    }
                    	    return rtnStr;
                    	});
                    	document.getElementById("data-response").innerHTML = formattedResponse;
                        }else {
    		               alert('Error was returned');
    		            }
            	}


                };


           
            xmlhttp.send();

			
            
        }else {
            alert('Error was returned');
         }
     }
 }
 
 xhr.send(params);
 
 
</Script>

<title>InfoSpot App </title>
</head>

<body>
 <div data-role="page" id="return">
   <div data-role="header">
    <h1>InfoSpot App </h1>
  </div>
  
<br/>
<p>	Access Token received from Server</p>
<br/>
 <div id="response"></div>
<br/>
<p>Data response obtained from Server</p>
<br/>
 <div id="data-response"></div>
 
 <div data-role="footer">
    <h1>About | Contact Us | Rate </h1>
  </div>
  
 </div>
 
</body>
 