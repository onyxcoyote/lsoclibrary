/*
    This file is part of lsoclibrary, a library for java programs to simplify some difficult tasks.

    Copyright (C) 2018  no-reply@onyxcoyote.com

    This library is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with this library.  If not, see <https://www.gnu.org/licenses/>.
 */
package lsoc.library.http;

import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 * Flatten exceptions out into actual response codes
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class httpResponse
{
    public static int getResponseCode(HttpsURLConnection httpConn)
    {
        int responseCode = -1;
               
        
        try
        {
            responseCode = httpConn.getResponseCode(); //returns java.io.IOException: Server returned HTTP response code: 503 for URL: [URL]
        }
        catch(java.io.FileNotFoundException fnfe) //certain JVM versions throw exception on code 404 or 410
        {
            if(fnfe.getMessage().contains("HTTP response code: 401"))
            {
                responseCode = 401;
            }
            else if(fnfe.getMessage().contains("HTTP response code: 404"))
            {
                responseCode = 404;
            }
            else
            {
                responseCode = 418; //I'm a teapot (RFC 2324, RFC 7168)
            }
        }
        catch(java.io.IOException ioex)
        {
            /*if(ioex.getMessage().contains("HTTP response code: 503"))
            {
                responseCode = 503;
            }
            else */if(ioex.getMessage().contains("HTTP response code: 5"))
            {
                int ind = ioex.getMessage().indexOf("HTTP response code: ");
                String strCode = ioex.getMessage().subSequence(ind, ind+3).toString(); //this may need testing
                responseCode = Integer.parseInt(strCode);
            }
            else
            {
                responseCode = 518; //temporary 
            }
        }
        /*catch(Exception ex)
        {
            throw ex;
        }*/

        return responseCode;
    }
}
