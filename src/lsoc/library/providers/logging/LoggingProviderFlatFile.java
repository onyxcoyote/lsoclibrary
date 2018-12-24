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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import lsoc.library.providers.logging.ILoggingProvider;
import lsoc.library.utilities.ExceptionUtilities;


/**
 * Simple implementation of ILoggingProvider if you don't need something more complex
 *  
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class LoggingProviderFlatFile implements ILoggingProvider
{
    private static PrintStream logFile;
    public static String DATE_FORMAT_TO_SECONDS_FILENAME = "yyyy-MM-dd_HH-mm-ss-SSS";
    public static String DATE_FORMAT_TO_SECONDS_LOGTEXT = "yyyy-MM-dd HH:mm:ss.SSS";
    private SimpleDateFormat df_filename = new SimpleDateFormat(DATE_FORMAT_TO_SECONDS_FILENAME);
    private SimpleDateFormat df_logText = new SimpleDateFormat(DATE_FORMAT_TO_SECONDS_LOGTEXT);
    private static final String LOG_FILE_EXTENTION = ".lsoc.log";
    
    /**
     * 
     * @param fileName
     * @throws FileNotFoundException 
     */
    public LoggingProviderFlatFile(String fileName) throws FileNotFoundException, IOException
    {
        //todo: check for unsafe paths

        
        
        File newlog = new File(fileName+LOG_FILE_EXTENTION);
        if(newlog.exists())
        {
            //rename log file if it already exists
            String _timeStamp = df_filename.format(Calendar.getInstance().getTime());
            File newlogRenamed = new File(fileName+_timeStamp+LOG_FILE_EXTENTION);
            Files.move(newlog.toPath(), newlogRenamed.toPath());
        }
        else
        {
            //file should be created automatically if it doesn't exist
        }
               
        logFile = new PrintStream(new FileOutputStream(fileName+LOG_FILE_EXTENTION));
        this.LogMessage("Starting LoggingProviderFlatFile");
    }
    
    @Override
    public boolean LogMessage(String textToLog)
    {
        String _timeStamp = df_logText.format(Calendar.getInstance().getTime());
        logFile.append(_timeStamp + " " + textToLog+"\r\n");
        return true;
    }

    @Override
    public boolean LogException(Exception ex)
    {
        //this could be replaced with more complex logging like logging to a log file or database
        return LogMessage(ExceptionUtilities.ExceptionToString(ex));
    }

    @Override
    public boolean LogException(Exception ex, String exDescription)
    {
        //this could be replaced with more complex logging like logging to a log file or database
        return LogMessage(ExceptionUtilities.ExceptionToString(ex) + " " + exDescription);
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
            System.out.println(message);
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
            String exString = ExceptionUtilities.ExceptionToString(ex);        
            System.out.println(exString);
        }
        catch(Exception inEx)
        {
            return false;
        }
        return true;
    }
    
}
