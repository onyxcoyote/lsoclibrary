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

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import lsoc.library.utilities.ExceptionUtilities;

/**
 * Log to multiple ILoggingProviders simultaneously
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class LoggingProviderMultiplexer implements ILoggingProvider
{
    List<ILoggingProvider> providers;
    
    public static String DATE_FORMAT_TO_SECONDS_LOGTEXT = "yyyy-MM-dd HH:mm:ss.SSS";
    private SimpleDateFormat df_logText = new SimpleDateFormat(DATE_FORMAT_TO_SECONDS_LOGTEXT);
    
    public LoggingProviderMultiplexer(List<ILoggingProvider> _providers)
    {
        providers = _providers;
    }
    
    @Override
    public boolean LogMessage(String textToLog)
    {
        boolean result = true;
        for(ILoggingProvider prov : providers)
        {
            try
            {
                prov.LogMessage(textToLog);
            }
            catch(Exception logEx)
            {
                LogMessage_NoDependency(ExceptionUtilities.ExceptionToString(logEx));
                result = false;
            }
        }
        
        return result;        
    }

    @Override
    public boolean LogException(Exception ex)
    {
        boolean result = true;
        for(ILoggingProvider prov : providers)
        {
            try
            {
                prov.LogException(ex);
            }
            catch(Exception logEx)
            {
                LogMessage_NoDependency(ExceptionUtilities.ExceptionToString(logEx));
                result = false;
            }
        }
        
        return result;
    }

    @Override
    public boolean LogException(Exception ex, String exDescription)
    {
        boolean result = true;
        for(ILoggingProvider prov : providers)
        {
            try
            {
                prov.LogException(ex, exDescription);
            }
            catch(Exception logEx)
            {
                LogMessage_NoDependency(ExceptionUtilities.ExceptionToString(logEx));
                result = false;
            }
        }
        
        return result;
    }

    /**
     * By necessity this should not do a more complex logging like database
     * @param ex
     * @return 
     */
    @Override
    public boolean LogMessage_NoDependency(String message)
    {
        boolean result = true;
        for(ILoggingProvider prov : providers)
        {
            try
            {
                prov.LogMessage_NoDependency(message);
            }
            catch(Exception logEx)
            {
                System.err.println(ExceptionUtilities.ExceptionToString(logEx));
                result = false;
            }
        }
        
        return result;
    }

    /**
     * By necessity this should not do a more complex logging like database
     * @param ex
     * @return 
     */
    @Override
    public boolean LogException_NoDependency(Exception ex)
    {
        boolean result = true;
        for(ILoggingProvider prov : providers)
        {
            try
            {
                prov.LogMessage_NoDependency(ExceptionUtilities.ExceptionToString(ex));
            }
            catch(Exception logEx)
            {
                System.err.println(ExceptionUtilities.ExceptionToString(logEx));
                result = false;
            }
        }
        
        return result;
    }
    
}