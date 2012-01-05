package at.fhhgb.auth.provider;
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


import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Provides convenience definitions for the authentication content providers.
 * 
 * @author thomaskaiser
 *
 */
public final class AuthDb {
	
	// no instantiation
	private AuthDb(){}
	
	public static final String AUTHORITY = "at.fhhgb.auth.provider.AuthDb";
	
	public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/");
	
	private static final String PATH_SUBJECT = "subject";
	private static final String PATH_MODE = "mode";
	private static final String PATH_FEATURE = "feature";
	private static final String PATH_FOR = "for";
	
	public interface SubjectColumns {
		/**
         * Last name of the subject. Type: TEXT
         */
        public static final String LAST_NAME = "lastname";
        
        /**
         * First name of the subject. Type: TEXT
         */
        public static final String FIRST_NAME = "firstname";
        
        /**
         * Gender of the subject. Type: TEXT <br>
         */
        public static final String GENDER = "gender";
        
        /**
         * Age of the subject. Type: Integer <br>
         */
        public static final String AGE = "age";
        
        /**
         * Email address. This will usually be used as a unique identifier.
         */
        public static final String EMAIL = "email";
	}
	
	public interface ModeColumns {
		/**
		 * Unique name of this mode. Every authentication mode that installs 
		 * itself should insert its mode with a unique name. 
		 */
		public static final String NAME = "name";
		/**
		 * What type this authentication mode is. For example, FACE_RECOGNITION, 
		 * SPEAKER_RECOGNITION, PASSWORD, PATTERN, ...
		 */
		public static final String TYPE = "type";
		/**
         * Name of this authentication mode for display purposes. Type: TEXT
         */
        public static final String DISPLAY_NAME = "displayname";
        /**
         * Name of the activity that handles authentication requests.
         */
        public static final String CLASS_NAME = "classname";
        /**
         * Package name of the application that handles authentication requests.
         */
        public static final String PACKAGE_NAME = "packagename";
	}
	
	public interface FeatureColumns {
		/**
		 * Foreign key to the mode table. {@link Mode._ID}.
		 */
		public static final String MODE_ID = "modeid";
		/**
		 * Foreign key to the subject table. {@link Subject._ID}.
		 */
		public static final String SUBJECT_ID = "subjectid";
		/**
		 * The actual feature, represented as TEXT. How the feature is represented as string
		 * is up the the implementations.
		 */
		public static final String REPRESENTATION = "representation";
	}
	

	/** Columns definitions for the subject table. */
	public static final class Subject implements BaseColumns, SubjectColumns {
		private Subject() {}
		
		/** Uri for this table. */
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_SUBJECT).build();
		
		/** Good practice to define mime types. */
        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of subjects.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.hgb.subject";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single subject.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.hgb.subject";
        
        public static Uri buildFeaturesForSubjectUri(int subjectId) {
        	return CONTENT_URI.buildUpon().appendPath(String.valueOf(subjectId)).appendPath(PATH_FEATURE).build();
        }
	}
	
	/** Columns definitions for the authentication modes table. */
	public static final class Mode implements BaseColumns, ModeColumns {
		private Mode() {}
		
		/** Uri for this table. */
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MODE).build();
		
		/** Good practice to define mime types. */
        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of authentication modes.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.hgb.mode";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single authentication mode.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.hgb.mode";
	}
	
	/** Columns definitions for the authentication modes table. */
	public static final class Feature implements BaseColumns, FeatureColumns {
		private Feature() {}
		
		/** Uri for this table. */
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_FEATURE).build();
		
		/** Good practice to define mime types. */
        /**
         * The MIME type of {@link #CONTENT_URI} providing a directory of features.
         */
        public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.hgb.feature";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single feature.
         */
        public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.hgb.feature";
        
        /** Build an Uri to query for all features of a subject. */
        public static final Uri buildFeaturesForSubjectUri(int subjectId) {
        	return CONTENT_URI.buildUpon().appendPath(PATH_FOR).appendPath(String.valueOf(subjectId)).build();
        }
	}
}
