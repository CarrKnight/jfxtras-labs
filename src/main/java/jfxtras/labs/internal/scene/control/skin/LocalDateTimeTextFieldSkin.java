/**
 * LocalDateTimeTextFieldSkin.java
 *
 * Copyright (c) 2011-2014, JFXtras
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the organization nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package jfxtras.labs.internal.scene.control.skin;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javafx.scene.control.SkinBase;
import jfxtras.labs.scene.control.CalendarTextField;
import jfxtras.labs.scene.control.LocalDateTimeTextField;

/**
 * This skin reuses CalendarTextField
 * @author Tom Eugelink
 *
 */
public class LocalDateTimeTextFieldSkin extends SkinBase<LocalDateTimeTextField>
{
	// ==================================================================================================================
	// CONSTRUCTOR
	
	/**
	 * 
	 */
	public LocalDateTimeTextFieldSkin(LocalDateTimeTextField control)
	{
		super(control);
		construct();
	}

	/*
	 * construct the component
	 */
	private void construct()
	{
		// setup component
		createNodes();
		
		// basic control binding
		calendarTextField.getStyleClass().addAll(getSkinnable().getClass().getSimpleName());
		calendarTextField.getStyleClass().addAll(getSkinnable().getStyleClass());
		getSkinnable().styleProperty().bindBidirectional( calendarTextField.styleProperty() );

		// bind it up
		getSkinnable().localeProperty().bindBidirectional( calendarTextField.localeProperty() );
		getSkinnable().promptTextProperty().bindBidirectional( calendarTextField.promptTextProperty() );
		getSkinnable().parseErrorCallbackProperty().bindBidirectional( calendarTextField.parseErrorCallbackProperty() );
		DateTimeToCalendarHelper.syncLocalDateTime(calendarTextField.calendarProperty(), getSkinnable().localDateTimeProperty(), calendarTextField.localeProperty());
		
		// formatter(s) require special attention
		DateTimeToCalendarHelper.syncDateTimeFormatter(calendarTextField.dateFormatProperty(), getSkinnable().dateTimeFormatterProperty());
		DateTimeToCalendarHelper.syncDateTimeFormatters(calendarTextField.dateFormatsProperty(), getSkinnable().dateTimeFormattersProperty());
	}
	
	// ==================================================================================================================
	// DRAW
	
	/**
	 * construct the nodes
	 */
	private void createNodes()
	{
		// setup the grid so all weekday togglebuttons will grow, but the weeknumbers do not
		calendarTextField = new CalendarTextField().withDateFormat(SimpleDateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, getSkinnable().getLocale())); // TODO this is not right
		getChildren().add(calendarTextField);
		
		// setup CSS
        getSkinnable().getStyleClass().add(this.getClass().getSimpleName()); // always add self as style class, because CSS should relate to the skin not the control
	}
	private CalendarTextField calendarTextField = null;
}
