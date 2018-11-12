/*
    This file is part of lsoclibrary.

    lsoclibrary is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    lsoclibrary is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with lsoclibrary.  If not, see <https://www.gnu.org/licenses/>.
 */
package lsoc.library.settings;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.ResultSet; //hmm
import java.sql.SQLException; //hmm

/**
 * Object for storing simple name/value pairs (both stored as string)
 * 
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class Setting
{
    private String settingName;
    private String settingValue;
    
    private String errorMessage = "";
    
    
    /**
     * constructor
     * @param _settingName
     * @param _settingValue 
     */
    public Setting(String _settingName, String _settingValue)
    {
        settingName = _settingName;
        settingValue = _settingValue;
    }
    
    /**
     * Constuctor
     * @param _settingName
     * @param _settingValue 
     */
    public Setting(String _settingName, double _settingValue)
    {
        settingName = _settingName;
        settingValue = Double.toString(_settingValue);
    }
    
    /**
     * Constuctor
     * @param _settingName
     * @param _settingValue 
     */
    public Setting(String _settingName, boolean _settingValue)
    {
        settingName = _settingName;
        settingValue = Boolean.toString(_settingValue);
    }
    
    /**
     * Constuctor
     * @param ex 
     */
    public Setting(Exception ex)
    {
        errorMessage = ex.toString();
        
        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String stackString = sw.toString();
        errorMessage += "|"+stackString;
    }
    
    /**
     * Constuctor
     * @param rs
     * @throws SQLException 
     */
    public Setting(ResultSet rs) throws SQLException
    {
        try
        {
            settingName =  rs.getString("settingname");
            settingValue =  rs.getString("settingvalue");
        }
        catch(Exception ex)
        {
            this.errorMessage = ex.toString();
        }
                    
    }

    /**
     * @return the settingName
     */
    public String getSettingName()
    {
        return settingName;
    }

    /**
     * @return the settingValue
     */
    public String getSettingValue()
    {
        return settingValue;
    }

    /**
     * @return the errorMessage
     */
    public String getErrorMessage()
    {
        return errorMessage;
    }
    
}
