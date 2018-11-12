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
package lsoc.library.providers.logging;

/**
 * Generic logging provider interface
 * 
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 * 
 */
public interface ILoggingProvider
{
    /**
     * Log arbitrary text
     * @param textToLog
     * @return 
     */
    public boolean LogMessage(String textToLog);
    
    /**
     * Log exception
     * @param ex
     * @return 
     */
    public boolean LogException(Exception ex);
    
    /**
     * Log exception with an additional message
     * @param ex
     * @param exDescription
     * @return 
     */
    public boolean LogException(Exception ex, String exDescription);
    
    /**
     * For logging when there is no dependencies available. Also can be used for logging failures of the logging provider.
     * E.g. for use when database driver has not yet been loaded, so we don't try logging to the database
     * @param message
     * @return 
     */
    public boolean LogMessage_NoDependency(String message);
    
    /**
     * For logging when there is no dependencies available. Also can be used for logging failures of the logging provider.
     * E.g. for use when database driver has not yet been loaded, so we don't try logging to the database
     * @param ex
     * @return 
     */
    public boolean LogException_NoDependency(Exception ex);
    
}
