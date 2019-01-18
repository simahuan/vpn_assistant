package android.izy.app.dialog;

import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Encapsulate dialog behavior with blur effect for app using
 * {@link android.app.DialogFragment}.
 * <p/>
 * All the screen behind the dialog will be blurred except the action bar.
 */
public class BlurDialogFragment extends DialogFragment {

	/**
	 * Log cat
	 */
	private static final String TAG = BlurDialogFragment.class.getSimpleName();

	/**
	 * Bundle key used to start the blur dialog with a given scale factor
	 * (float).
	 */
	public static final String BUNDLE_KEY_DOWN_SCALE_FACTOR = "bundle_key_down_scale_factor";

	/**
	 * Bundle key used to start the blur dialog with a given blur radius (int).
	 */
	public static final String BUNDLE_KEY_BLUR_RADIUS = "bundle_key_blur_radius";

	/**
	 * Engine used to blur.
	 */
	private BlurDialogEngine mBlurEngine;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mBlurEngine = new BlurDialogEngine(getActivity());
	}

	@Override
	public void onResume() {
		super.onResume();
		mBlurEngine.onResume(getRetainInstance());
	}

	@Override
	public void onDismiss(DialogInterface dialog) {
		super.onDismiss(dialog);
		mBlurEngine.onDismiss();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		mBlurEngine.onDestroy();
	}

	@Override
	public void onDestroyView() {
		if (getDialog() != null) {
			getDialog().setDismissMessage(null);
		}
		super.onDestroyView();
	}

	/**
	 * Set the blur radius used by the
	 * {@link fr.tvbarthel.lib.blurdialogfragment.BlurDialogEngine}
	 *
	 * @param radius
	 *            down scaled factor used to reduce the size of the source
	 *            image. Range : [1,infinity)
	 */
	public void setBlurRadius(int radius) {
		if (radius > 0) {
			mBlurEngine.setBlurRadius(radius);
		}
	}

	/**
	 * Set the down scale factor used by the
	 * {@link fr.tvbarthel.lib.blurdialogfragment.BlurDialogEngine}
	 *
	 * @param factor
	 *            down scaled factor used to reduce the size of the source
	 *            image. Range : ]0,infinity)
	 */
	public void setDownScaleFactor(float factor) {
		if (factor > 0) {
			mBlurEngine.setDownScaleFactor(factor);
		}
	}

}
