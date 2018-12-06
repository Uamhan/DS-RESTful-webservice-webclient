<?xml version = "1.0"?>
<xsl:stylesheet xmlns:xsl = "http://www.w3.org/1999/XSL/Transform" 
   version = "1.0">
 
   <xsl:output method = "html" indent = "yes"/>
   <xsl:template match = "/">
      <html>
         <body>
            <xsl:apply-templates/>
         </body>
      </html>
   </xsl:template>
    
   <xsl:template match = "orders">
      <table border = "1" width = "100%">
         <tr>
         	<th>Customer ID</th>
         	<th>Customer Name</th>
         	
         	<th>Car ID</th>
         	<th>Make</th>
         	<th>Model</th>
         	<th>Price</th>
         	
         	<th>Order Start</th>
         	<th>Order Return</th>
         	
         	
         </tr>
         <xsl:for-each select = "order">
            <tr>

				<td>
                  <i><xsl:value-of select = "Customer/ID"/></i>
               </td>
               
               <td>
                  <i><xsl:value-of select = "Customer/Name"/></i>
               </td>

               <td>
                  <i><xsl:value-of select = "Car/CarID"/></i>
               </td>
               <td>
                  <i><xsl:value-of select = "Car/Make"/></i>
               </td>
               <td>
                  <i><xsl:value-of select = "Car/Model"/></i>
               </td>
               <td>
                  <i><xsl:value-of select = "Car/Price"/></i>
               </td>
               
               <td>
                  <i><xsl:value-of select = "StartDate"/></i>
               </td>
               
               <td>
                  <i><xsl:value-of select = "ReturnDate"/></i>
               </td>
  
            </tr>
         </xsl:for-each>
      </table>
   
   </xsl:template>
</xsl:stylesheet>