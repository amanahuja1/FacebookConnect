<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>

   <body>
      <h2>Successfully posted Quote with responseId below</h2>
      <table>
         <tr>
            <td>ResponseId from Facebook</td>
            <td>${result}</td>
         </tr>
      </table>  
   </body>
   
</html>