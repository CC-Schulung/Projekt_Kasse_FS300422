package project_kassenbon_hsin.view;

import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.text.NumberFormatter;

public class MyNumberFormatter extends NumberFormatter {

	public MyNumberFormatter(NumberFormat format) {
		// TODO Auto-generated constructor stub
		super(format);
	}

	public Object stringToValue(String text) throws ParseException {
		if (text.equals("")) {
			return null;
		} else {
			return super.stringToValue(text);
		}

	}

}
