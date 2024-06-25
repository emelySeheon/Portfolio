/*
  By Logan Sullivan and Joseph Haugh
  Makes sure that the text being entered is a valid positive decimal number
 */

package DistributedAuction;

import javafx.scene.control.TextFormatter;
import java.text.DecimalFormat;
import java.text.ParsePosition;

public class DecimalTextVerifier {
    public static <V> TextFormatter getFormatter() {
        TextFormatter<V> tf = new TextFormatter<>(c -> {
            DecimalFormat df = new DecimalFormat("#");

            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Number newNumber = df.parse(c.getControlNewText(), parsePosition);

            if (newNumber == null || parsePosition.getIndex() < c.getControlNewText().length()) {
                return null;
            } else {
                c.setRange(0, Math.max(c.getControlText().length(), 0));
                c.setText("" + Math.abs(newNumber.doubleValue()));

                return c;
            }
        });

        return tf;
    }
}