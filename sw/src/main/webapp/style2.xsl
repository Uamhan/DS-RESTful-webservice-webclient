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
    
   <xsl:template match = "customers">
      <table border = "1" width = "100%">
         <tr>
         	<th>Customer ID</th>
         	<th>Name</th>
         </tr>
         <xsl:for-each select = "customer">
            <tr>
            
            	<td>
                  <i><xsl:value-of select = "ID"/></i>
               </td>
            
               <td>
                  <i><xsl:value-of select = "Name"/></i>
               </td>
            </tr>
         </xsl:for-each>
      </table>
   
   </xsl:template>
</xsl:stylesheet>