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
    
   <xsl:template match = "cars">
      <table border = "1" width = "100%">
         <tr>
         	<th>Car ID</th>
         	<th>Make</th>
         	<th>Model</th>
         	<th>Price</th>
         </tr>
         <xsl:for-each select = "car">
            <tr>
            
            	<td>
                  <i><xsl:value-of select = "CarID"/></i>
               </td>
            
               <td>
                  <i><xsl:value-of select = "Make"/></i>
               </td>
               
               <td>
                  <xsl:value-of select = "Model"/>
               </td>
               
               <td>
                  <xsl:value-of select = "Price"/>
               </td>
            </tr>
         </xsl:for-each>
      </table>
   
   </xsl:template>
</xsl:stylesheet>