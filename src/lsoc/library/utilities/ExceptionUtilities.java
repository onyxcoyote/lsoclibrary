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
package lsoc.library.utilities;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 *
 * @author onyxcoyote <no-reply@onyxcoyote.com>
 */
public class ExceptionUtilities
{
    public static String ExceptionToString(Exception ex)
    {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }
}
