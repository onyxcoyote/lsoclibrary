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
package lsoc.library.utilities;

import java.net.InetAddress;
import java.net.URL;

import lsoc.library.providers.logging.ILoggingProvider;

/**
 *
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class NetworkUtilities
{
    /**
     * Tries to get IP from URL.  If an exception occurs, it returns null
     * @param _strUrl  should be in the form:  http://url.tld  or  https://url.tld
     * @param loggingProvider
     * @return 
     */
    public static String GetIPFromURL(String _strUrl, ILoggingProvider loggingProvider)
    {
        try
        {
            URL url = new URL(_strUrl);
            String host = url.getHost();
            InetAddress address = InetAddress.getByName(host);
            return address.getHostAddress();
        }
        catch(Exception ex)
        {
            loggingProvider.LogException(ex, "lsoc Error getting IP from URL");
            return null;
        }
    }
}
