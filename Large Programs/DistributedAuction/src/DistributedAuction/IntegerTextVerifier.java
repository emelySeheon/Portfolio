/*
  By Logan Sullivan and Joseph Haugh
  Makes sure that the text being entered is a valid positive integer
 */

package DistributedAuction;

import javafx.scene.control.TextFormatter;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.math.RoundingMode;

public class IntegerTextVerifier {
    public static <V> TextFormatter getFormatter() {
        TextFormatter<V> tf = new TextFormatter<>(c -> {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setRoundingMode(RoundingMode.HALF_UP);
            nf.setParseIntegerOnly(true);

            if (c.getControlNewText().isEmpty()) {
                return c;
            }

            ParsePosition parsePosition = new ParsePosition(0);
            Number newNumber = nf.parse(c.getControlNewText(), parsePosition);

            if (newNumber == null) {
                return null;
            } else {
                c.setRange(0, Math.max(c.getControlText().length(), 0));
                c.setText("" + Math.abs(newNumber.intValue()));

                return c;
            }
        });

        return tf;
    }
}