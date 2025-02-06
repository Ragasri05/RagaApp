- Android Manifest file defines the structure and metadata of our application, its components and applications.
- it is like an instruction mannual for android to understand what the app  does and what is needs and how it interacts with ither apps.
- if the app needs special access, it must be declared in the android manifext file.
- it has intent filters and permissions which determines how our app can communicate with other applications.
```
<?xml version="1.0" encoding="utf-8"?>

<!-- the main component of android manifest file is manifest-->

<manifest
<!-- this is declared to use android specific attributes -->
    xmlns:android="http://schemas.android.com/apk/res/android"
<!-- this is declared to use developer tools declared by android.
    xmlns:tools="http://schemas.android.com/tools">
<!-- to access the internet -->
<!-- used for retrofit and firebase -->
    <uses-permission android:name="android.permission.INTERNET" />
<!-- allows the app to read files from the phones storage -->
<!-- used only upto Android 10 and API 29 -->
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
<!-- allows to save files to storage -->
<!-- used only upto Android 10 and API 29 -->
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
<!-- we can full access to all files on the storage -->
<!-- PLay store restricts apps from using this unless absolutely necessary. -->
    <uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE" />
<!-- allows the app to check if the phone is connected to internet. -->
    <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
<!-- allows the app to check the wifi status. whether connected or not. -->
    <uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />

    <application
<!-- android will save the user's data and restore it when the app is reinstalled -->
        android:allowBackup="true"
<!-- Controls which data can be backed up or restored. -->
<!-- uses an xml file -->
        android:dataExtractionRules="@xml/data_extraction_rules"
<!-- specifies what files should be backed up. -->
        android:fullBackupContent="@xml/backup_rules"
<!-- app icon -->
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:networkSecurityConfig="@xml/network_security_config"
        android:roundIcon="@mipmap/ic_launcher_round"
<!-- supports right to left languages like arabic -->
        android:supportsRtl="true"
<!-- sets teh app theme -->
        android:theme="@style/Theme.FoodOrderApp"
<!-- specifies which android version this app is optimised for -->
        tools:targetApi="31">


        <activity
            android:name=".FeedBack"
            android:exported="false" />
        <activity
            android:name=".SampleRetrofit"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name=".DatabaseToMenu"
            android:exported="false" />
        <!--
        <activity
            android:name=".CustomerMenu"
            android:exported="false" />
        -->
        <activity
            android:name=".Customer"
            android:exported="true">
            <intent-filter android:autoVerify="true">
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />

                <data android:scheme="https" />
                <data android:host="www.customer.com" />
                <data android:path="/getthemenu" />
            </intent-filter>
            <intent-filter>
                <action android:name="android.intent.action.VIEW" />

                <category android:name="android.intent.category.DEFAULT" />
                <category android:name="android.intent.category.BROWSABLE" />

                <data android:host="www.customer.com" />
                <data android:scheme="http" />
                <data android:path="/getthemenu" />
            </intent-filter>
        </activity>
        <!--
        <activity
            android:name=".Update"
            android:exported="false" />
        -->
        <activity
            android:name=".GetMenu"
            android:exported="false" />
        <activity
            android:name=".RegisterScreen"
            android:exported="false" />
        <activity
            android:name=".LoginScreen"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <service android:name=".ServiceProvider" /> <!-- declaring Content Provider -->
        <provider
            android:name=".ContentProvider"
            android:authorities="com.example.foodorderapp.provider"
            android:enabled="true"
            android:exported="true"></provider>
    </application>

</manifest>
```
### Manifest :
- the main componet of android manifest file is manifest.
- it must have application element within it.
### User permission.
- if the app wants to read files or access the internet.. to do such special tasks, the app has to ask for permisssion in android manifest file.
### Application:
- application tag has important settings for your app 
- manifest file must contain only one application node.
- it has meta data about the app.
- it acts as a container for activity service and provider.
### Actvity:
- it is contained within the application element.
- any activity which is not specified in application tag will not run and won't be visible to the system.
- it has characteristsics like label, name, launchmode, etc...
- Exported: indicates whether it is accessible by other apps using intent.
    #### Intent filter:
  - it specifies the type of intent to which an activity or provider or a service can sed a response.
