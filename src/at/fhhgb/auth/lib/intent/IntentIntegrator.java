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
package at.fhhgb.auth.lib.intent;



/**
 * Provides some definitions to work with the authentication framework.
 * This may be used as a utility class to easily start an auth workflow.
 * 
 * @author thomaskaiser
 *
 */
public final class IntentIntegrator {
	
	private static final String BASE_PACKAGE = "at.fhhgb.auth.intent";
	
	public class Actions {
		/**
		 * This is the action that will trigger the authentication workflow,
		 * showing first a list of registered users and then a list of
		 * registered authentication methods.
		 */
		public static final String ACTION_START_AUTH = BASE_PACKAGE
				+ ".START_AUTH";

		/**
		 * The auth framework will show a list of all intent that provide this
		 * action as potential auth methods that the user may select.
		 */
		public static final String ACTION_PROVIDE_AUTH_METHOD = BASE_PACKAGE
				+ ".AUTH_METHOD";

		/**
		 * Triggers a user selection or creation workflow
		 */
		public static final String ACTION_GET_USER = BASE_PACKAGE + ".GET_USER";
		
		/**
		 * This is the base action used to handle a specific authentication request with a specified
		 * auth method.
		 * <p> Append a "." and the name of a {@link AuthModes} to resolve an activity that will handle
		 * a specific authentication request, i.e. start an activity with the action 
		 * "{@link Actions#DO_AUTH}. {@link AuthModes#PASSWORD}" and set the user's URI as data</p>
		 */
		public static final String DO_AUTH = BASE_PACKAGE + ".DO_AUTH";
	}
	
	
	public class Extras {
		/** A user Id. Data type: LONG */
		public static final String EXTRA_USER_ID = "userid";
		/** Email of a user. Type: String */
		public static final String EXTRA_USER_EMAIL = "email";
		/** Unique name of authentication type. */
		public static final String EXTRA_AUTH_TYPE = "authtype";
		/** String array. */
		public static final String EXTRA_MULTI_MODAL_MODES = "modes";
		/** Boolean with a binary decision: yes/no. */
		public static final String EXTRA_RESULT = "result";
		/** Double with a confidence value between 0.0 and 1.0. */
		public static final String EXTRA_RESULT_CONFIDENCE = "confidence";
	}
	
	/** Constants for identifying different types of authentication modes. */
	public class AuthModes {
		public static final String FACE_RECOGNITION = "facerecognition";
		public static final String PASSWORD = "password";
		public static final String MULTI_MODAL = "multimodal";
	}
}
