/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package at.fhhgb.auth.lib.util;

import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

/**
 * Some useful utility methods that are used in multiple places.
 * @author thomaskaiser
 *
 */
public class UIUtils {
	
	/** 
	 * Simple error dialog with title and message and a single OK button. The given listener will be set on the OK button. 
	 * */
	public static AlertDialog showErrorDialog(Context context, String title, String message, DialogInterface.OnClickListener okButtonListener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle(title);
		builder.setMessage(message);
		builder.setPositiveButton("OK", okButtonListener);
		return builder.show();
	}

	/** Simple error dialog with title and message and a single OK button. */
	public static AlertDialog showErrorDialog(Context context, String title, String message) {
		DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		};
		return showErrorDialog(context, title, message, onClickListener);
	}

	/**
	 * Workaround for {@link PackageManager#resolveActivity(Intent, int)} that returns null instead
	 * of an activity chooser intent if no matching activities are found. If multiple actvities are found,
	 * it will return the best match.
	 * @param action The intent action string.
	 */
	public static ResolveInfo resolveActivity(Context context, String action) {
		List<ResolveInfo> activities = context.getPackageManager().queryIntentActivities(new Intent(action), 0);
		if (activities.size() > 0) {
			return activities.get(0);
		} else {
			return null;
		}
	}
}
