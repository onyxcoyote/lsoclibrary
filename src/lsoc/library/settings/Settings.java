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

import java.util.ArrayList;

/**
 * 
 * 
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class Settings
{
    private ArrayList<Setting> settingList;
    
    /**
     * Constructor
     */
    public Settings()
    {
        settingList = new ArrayList<>();
    }
    
    /**
     * Inserts setting.  If setting exists, remove previous setting with the same name and input this one
     * @param _setting 
     */
    public void InsertSettingWithReplace(Setting _setting)
    {
        if(DoesSettingExist(_setting.getSettingName()))
        {
            settingList.remove(_setting.getSettingName());
        }

        settingList.add(_setting);
    }
    
    /**
     * Removes setting by name
     * @param _settingName 
     */
    public void RemoveSetting(String _settingName)
    {
        Setting setting = GetSetting(_settingName);
        settingList.remove(setting);
    }
    
    /**
     * returns setting with matching settingname, if there are multiple, it returns the first one found
     * RETURNS NULL IF NONE FOUND
     * @param _settingName
     * @return 
     */
    public Setting GetSetting(String _settingName)
    {
        for(Setting setting : settingList)
        {
            if(setting.getSettingName().toLowerCase().equals(_settingName.toLowerCase()))
            {
                return setting;
            }
        }
        
        return null;
    }
    
    /**
     * Checks if setting exists (by setting name)
     * @param _settingName
     * @return 
     */
    public boolean DoesSettingExist(String _settingName)
    {
        Setting setting = GetSetting(_settingName);
        return (setting != null);
    }
    
}
