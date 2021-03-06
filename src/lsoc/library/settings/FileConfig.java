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
package lsoc.library.settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import lsoc.library.providers.logging.ILoggingProvider;

/**
 * Class for getting configuration settings from .property files
 * here are the required files and properties:
 * 
 *    metaconfig.properties   - meta config file points only to any SUB-property files to use, does not have other properties on its own
 *          configfile=exampleconfigfilename.properties
 *          OR
 *          configfile=$HOSTNAME.properties
 * 
 *    (hostname).properties   - config file for use with one hostname (e.g. mymachine.properties,  or  server1.properties)
 *          (propname)=(propvalue)
 * 
 *    (othername).properties  - config file for use with other purpose (e.g. prod.properties, or test.properties)
 *          (propname)=(propvalue)
 * 
 * 
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class FileConfig 
{
    
    /**
     * Generic logging provider
     */
    private ILoggingProvider loggingProvider;
    
    /**
     * 
     * Primary Configuration file (*.properties) for use in this instance of FileConfig
     */
    private String configFile;
    
    private boolean initialized = false;
    
    private boolean configFileCompiledIn = false;
    
    private static String META_CONFIG_PROPERTIES_FILE_NAME = "metaconfig.properties";
    private static String PROPERTYNAME_CONFIGFILE = "configfile";
    
    /**
     * Constructor.  After construction, it is recommended to Force Initialization with Initialize();
     * @param _loggingProvider
     */
    public FileConfig(ILoggingProvider _loggingProvider) 
    {
        loggingProvider = _loggingProvider;    
    }
    
    /**
     * 
     * @param _loggingProvider
     * @param _configFileCompiledIn - true if config file is compiled into JAR. false if using a separate file.
     */
    public FileConfig(ILoggingProvider _loggingProvider, boolean _configFileCompiledIn) 
    {
        loggingProvider = _loggingProvider;    
        configFileCompiledIn = _configFileCompiledIn;
    }
    
    /**
     * Initialize FileConfig object
     * @throws IOException
     * @throws Exception 
     */
    public void Initialize() throws IOException, Exception
    {
        //GET METACONFIG FILE
        // <editor-fold>
        File checkFile = new File(META_CONFIG_PROPERTIES_FILE_NAME);
        if(!checkFile.exists())
        {
            loggingProvider.LogMessage_NoDependency("file does not exist " + checkFile.getAbsolutePath() + " this file is required" ); //can't log to DB here or we likely end up with infinite loop
        }
        
        Properties metaprop=new Properties();
        FileInputStream inStream =  new FileInputStream(META_CONFIG_PROPERTIES_FILE_NAME);
        metaprop.load(inStream);
        inStream.close();
        // </editor-fold>

        
        //REQUIRED PROPERTIES
        // Metaconfig Property: configfile
        // <editor-fold>
        boolean configFileNameFound = false;
        try
        {
            configFile = metaprop.get(PROPERTYNAME_CONFIGFILE).toString();
            
            if(configFile.length() > 0)
            {
                configFileNameFound = true;
            }
        }
        catch(Exception ex)
        {

        }
        
        //was property found
        if(!configFileNameFound)
        {
            loggingProvider.LogMessage_NoDependency("metaconfig.properties: required property not found: " + PROPERTYNAME_CONFIGFILE); //can't log to DB here or we likely end up with infinite loop
            throw new Exception("metaconfig.properties: required property not found: " + PROPERTYNAME_CONFIGFILE);
        }
        else
        {
            loggingProvider.LogMessage_NoDependency("USING CONFIG FILE: " + configFile); //can't log to DB here or we likely end up with infinite loop
        }
        
        // </editor-fold>
        
        this.initialized = true;
    }
    
    
    
    /**
     * Get a config setting (Setting object) from .properties file
     * @param key
     * @return
     * @throws IOException 
     */
    public Setting getConfigSetting(String key) throws IOException, Exception
    {
        try
        {
            ForceInitialization();

            File checkFile = new File(configFile);
            if(!checkFile.exists())
            {
                loggingProvider.LogMessage_NoDependency("file does not exist " + checkFile.getAbsolutePath() + " this file is required" ); //can't log to DB here or we likely end up with infinite loop
            }
            
            Properties prop=new Properties();
            
            InputStream inStream;
            if(configFileCompiledIn)
            {
                //InputStream
                 inStream = getClass().getResourceAsStream(configFile); //get file compiled into JAR
            }
            else
            {
                //FileInputStream
                inStream =  new FileInputStream(configFile); //get setting from file
            }
            prop.load(inStream);
            inStream.close();

            String value = prop.get(key).toString();
            Setting setting = new Setting(key, value);
            return setting;
        }
        catch(Exception ex)
        {
            try
            {
                loggingProvider.LogException(ex, "Error getting SETTING -" + key + "- from file: " + configFile);
            }
            catch(Exception inEx)
            {
                try
                {
                    loggingProvider.LogException_NoDependency(inEx);
                }
                catch(Exception logEx2)
                {
                    //ignore error, otherwise could result in infinite loops
                }
            }

            throw ex;
        }
    }
    
    
    /**
     * Get a config setting (string) from .properties file
     * @param key
     * @return
     * @throws IOException 
     */
    public String getConfigString(String key) throws IOException, Exception
    {
        ForceInitialization();
        
        try
        {
            Properties prop=new Properties();
            InputStream inStream;
            if(configFileCompiledIn)
            {
                //InputStream
                 inStream = getClass().getResourceAsStream(configFile); //get file compiled into JAR
            }
            else
            {
                //FileInputStream
                inStream =  new FileInputStream(configFile); //get setting from file
            }
            prop.load(inStream);
            inStream.close();

            Object val = prop.get(key);
            if(val == null)
            {
                return null;
            }
            else
            {
                String strValue = prop.get(key).toString();
                return strValue;
            }
            
        }
        catch(Exception ex)
        {
            try
            {
                loggingProvider.LogException(ex, "Error getting setting STR -" + key + "- from file: " + configFile);
            }
            catch(Exception inEx)
            {
                //ignore logging error
            }

            throw ex;
        }
    }
    

    
    private void ForceInitialization() throws IOException, Exception
    {
        if(!this.initialized)
        {
            Initialize();
        }
    }    
    

}
