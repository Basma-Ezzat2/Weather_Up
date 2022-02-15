package com.example.weatherup.ui.settings

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.preference.*
import com.example.weatherup.R
import com.example.weatherup.ui.map.Maps
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class SettingsFragment : PreferenceFragmentCompat() {
    var changeLanguage=false
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.prefrences)
        val shared= context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val locationMap: SwitchPreferenceCompat?=findPreference("CUSTOM_LOCATION")
        val editor = shared?.edit()

        editor?.putBoolean("firstTime", true)
        editor?.apply()
        locationMap?.onPreferenceClickListener= Preference.OnPreferenceClickListener {
            if (shared != null) {
                if(shared.getBoolean("CUSTOM_LOCATION", true)) {
                    //Toast.makeText(it.context,"Map is opening ", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this.context, Maps::class.java)
                    intent.putExtra("MAP", true)
                    startActivity(intent)
                }
            }
            true
        }
        val language: ListPreference?=findPreference("LANGUAGE_SYSTEM")
        language?.onPreferenceClickListener= Preference.OnPreferenceClickListener {
            changeLanguage=true
            if (shared != null) {
                if(shared.getString("LANGUAGE_SYSTEM", "en").equals("en")) { setLocale(this.requireActivity(), "ar") } else if(shared.getString("LANGUAGE_SYSTEM", "en").equals("ar")) { setLocale(this.requireActivity(), "en") }
            }

            true
        }
    }
    override fun onPause() {
        if (changeLanguage){
            CoroutineScope(Dispatchers.Default).launch {
                restart()
            }
            changeLanguage=false
        }
        super.onPause()
    }
    fun restart(){
        val intent = requireActivity().intent
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                or Intent.FLAG_ACTIVITY_NO_ANIMATION)
        requireActivity().overridePendingTransition(0, 0)
        requireActivity().finish()
        requireActivity().overridePendingTransition(0, 0)
        startActivity(intent)
    }
}




