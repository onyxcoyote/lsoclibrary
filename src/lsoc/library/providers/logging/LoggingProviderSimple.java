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
package lsoc.library.providers.logging;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import lsoc.library.providers.logging.ILoggingProvider;
import static lsoc.library.providers.logging.LoggingProviderFlatFile.DATE_FORMAT_TO_SECONDS_FILENAME;
import lsoc.library.utilities.ExceptionUtilities;


/**
 * Simple implementation of ILoggingProvider if you don't need something more complex
 *  
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class LoggingProviderSimple implements ILoggingProvider
{
    
    public static String DATE_FORMAT_TO_SECONDS_LOGTEXT = "yyyy-MM-dd HH:mm:ss.SSS";
    private SimpleDateFormat df_logText = new SimpleDateFormat(DATE_FORMAT_TO_SECONDS_LOGTEXT);

    @Override
    public boolean LogMessage(String textToLog)
    {
        //this could be replaced with more complex logging like logging to a log file or database
        return LogMessage_NoDependency(textToLog);
    }

    @Override
    public boolean LogException(Exception ex)
    {
        //this could be replaced with more complex logging like logging to a log file or database
        return LogException_NoDependency(ex);
    }

    @Override
    public boolean LogException(Exception ex, String exDescription)
    {
        //this could be replaced with more complex logging like logging to a log file or database
        boolean status;
        status = LogMessage_NoDependency(exDescription);
        status = status & LogException_NoDependency(ex);
        
        return status;
    }

    /**
     * By necessity this should not do a more complex logging like database
     * @param ex
     * @return 
     */
    @Override
    public boolean LogMessage_NoDependency(String message)
    {
        try
        {
            String _timeStamp = df_logText.format(Calendar.getInstance().getTime());
            System.out.println(_timeStamp + " " + message);
        }
        catch(Exception inEx)
        {
            return false;
        }
        return true;
    }

    /**
     * By necessity this should not do a more complex logging like database
     * @param ex
     * @return 
     */
    @Override
    public boolean LogException_NoDependency(Exception ex)
    {
        try
        {
            String _timeStamp = df_logText.format(Calendar.getInstance().getTime());
            String exString = ExceptionUtilities.ExceptionToString(ex);        
            System.out.println(_timeStamp + " " + exString);
        }
        catch(Exception inEx)
        {
            return false;
        }
        return true;
    }
    
}
