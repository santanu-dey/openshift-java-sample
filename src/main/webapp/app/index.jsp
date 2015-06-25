<!DOCTYPE html>
<html>
<head>
<title>InfoSpot App App</title>
  <!-- Include meta tag to ensure proper rendering and touch zooming -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <!-- Include jQuery Mobile stylesheets -->
  <link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
  <!-- Include the jQuery library -->
  <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
  <!-- Include the jQuery Mobile library -->
  <script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header">
    <h1>InfoSpot App</h1>
  </div>

  <div data-role="main" class="ui-content">
    <p>Welcome!</p>

<p>InfoSpot App needs to access information from MoF</p>
<br/>

  <div data-role="main" class="ui-content">

    <a href="http://test-4g-prod.apigee.net/v2.1/oauth/authorize?response_type=code&redirect_uri=http://localhost:8080/training/faces/app/return.jsp&client_id=Z4GHyW8o3JXUrkAkGetWyUYLw99uMAkC" class="ui-btn">Provide access to MoF data</a>
  </div>

  </div>

  <div data-role="footer">
    <h1>About | Contact Us | Rate </h1>
  </div>
</div> 

</body>
</html>
