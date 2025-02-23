package ml.vladmikh.projects.find_timer.android.ui

sealed class Screen(val title: String) {
    object  TimeZonesScreen: Screen("TimeZones")
    object  FindTimeScreen: Screen("FindZoneScreen")

}