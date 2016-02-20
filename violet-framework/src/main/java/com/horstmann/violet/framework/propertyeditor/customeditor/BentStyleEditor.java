/*
 Violet - A program for editing UML diagrams.

 Copyright (C) 2007 Cay S. Horstmann (http://horstmann.com)
 Alexandre de Pellegrin (http://alexdp.free.fr);

 This program is free software; you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation; either version 2 of the License, or
 (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program; if not, write to the Free Software
 Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.horstmann.violet.framework.propertyeditor.customeditor;

import com.horstmann.violet.framework.propertyeditor.CustomPropertyEditorSupport;
import com.horstmann.violet.framework.property.BentStyle;

/**
 * A property editor for the BentStyle type.
 */
public class BentStyleEditor extends CustomPropertyEditorSupport
{

    /**
     * Default constructor
     * 
     */
    public BentStyleEditor()
    {
        super(NAMES, VALUES);
    }

    /** bent styme labels */
    public static final String[] NAMES =
    {
        resourceBundle.getString("bent_style.auto"),
        resourceBundle.getString("bent_style.straight"),
        resourceBundle.getString("bent_style.free"),
        resourceBundle.getString("bent_style.hv"),
        resourceBundle.getString("bent_style.vh"),
        resourceBundle.getString("bent_style.hvh"),
        resourceBundle.getString("bent_style.vhv")
    };

    /** bent style technical values */
    public static final Object[] VALUES =
    {
        BentStyle.AUTO,
        BentStyle.STRAIGHT,
        BentStyle.FREE,
        BentStyle.HV,
        BentStyle.VH,
        BentStyle.HVH,
        BentStyle.VHV
    };
}
