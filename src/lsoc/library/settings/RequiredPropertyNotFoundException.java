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

/**
 * Required property was not found
 * 
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class RequiredPropertyNotFoundException extends Exception
{
    public RequiredPropertyNotFoundException()
    {
    }

    public RequiredPropertyNotFoundException(String errorText)
    {
        super(errorText);
    }
}
