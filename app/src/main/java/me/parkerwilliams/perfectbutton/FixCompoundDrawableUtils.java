package me.parkerwilliams.perfectbutton;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Simple Utils class to help in fixing the issue where Button's and TextView's compound drawables will un-center
 * text when either one of a left and right or top and bottom compound drawable is not there. The centering of the
 * text is achieved by adding a transparent drawable of the same bounds to the opposite side.
 * <p/>
 * Methods:
 * <ul>
 * <li>{@link #vertical(TextView)} fixes the top and bottom compound drawables</li>
 * <li>{@link #horizontal(TextView)} fixes the left and right compound drawables</li>
 * <p/>
 * Created by parker on 8/12/15.
 */
public final class FixCompoundDrawableUtils {
    private FixCompoundDrawableUtils() {
    }

    private static final int LEFT = 0;
    private static final int TOP = 1;
    private static final int RIGHT = 2;
    private static final int BOTTOM = 3;

    /**
     * Fixes the top and bottom compound drawables
     *
     * @param tv
     */
    public static void vertical(TextView tv) {
        Drawable[] drawables = tv.getCompoundDrawables();
        Drawable top = drawables[TOP];
        Drawable bottom = drawables[BOTTOM];
        if ((top != null && bottom != null) || (top == null && bottom == null)) {
            return;
        }
        if (top == null) {
            top = createPlaceholder(bottom);
        }
        if (bottom == null) {
            bottom = createPlaceholder(top);
        }
        tv.setCompoundDrawables(drawables[LEFT], top, drawables[RIGHT], bottom);
    }

    /**
     * Fixes the left and right compound drawables
     *
     * @param tv
     */
    public static void horizontal(TextView tv) {
        Drawable[] drawables = tv.getCompoundDrawables();
        Drawable left = drawables[LEFT];
        Drawable right = drawables[RIGHT];
        if ((left != null && right != null) || (left == null && right == null)) {
            return;
        }
        if (right == null) {
            right = createPlaceholder(left);

            // create a placeholder drawable of the same size on the right
        }
        if (left == null) {
            left = createPlaceholder(right);
        }
        tv.setCompoundDrawables(left, drawables[TOP], right, drawables[BOTTOM]);
    }

    private static Drawable createPlaceholder(Drawable original) {
        Drawable placeholder = new ColorDrawable();
        placeholder.setBounds(original.getBounds());
        return placeholder;
    }
}
