package ml.vladmikh.projects.find_timer.android.ui

import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.NavigationRail
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ml.vladmikh.projects.find_timer.android.MyApplicationTheme

val bottomNavigationItems = listOf(
     BottomItem(
         Screen.TimeZonesScreen.title,
         Icons.Filled.Place,
         "TimeZones"
     ),

    BottomItem(
        Screen.FindTimeScreen.title,
        Icons.Filled.Search,
        "Find Time"
    )
 )

// This function takes a function that can provide a top bar (toolbar on Android)
//and defaults to an empty composable
@Composable
fun MainScreen(actionBarFun: topBarFun = { EmptyComposable() }) {
    //If the state is true, a dialog box will appear on the screen.
    val createAddTimeZoneDialog = remember { mutableStateOf(false) }
    //The variable needed to switch screen with bottomNavigation
    val screenItemNumber = remember{ mutableIntStateOf(0) }
    // Hold the state containing a list of current time zone strings
    val currentTimeZone = remember { SnapshotStateList<String>() }

    MyApplicationTheme {
        Scaffold (
            topBar = {
                actionBarFun(screenItemNumber.value)
            },

            floatingActionButton = {
                if (screenItemNumber.value == 0) {
                    FloatingActionButton(
                        modifier = Modifier.padding(16.dp),
                        shape = FloatingActionButtonDefaults.largeShape,
                        containerColor = MaterialTheme.colorScheme.primary,
                        onClick = {createAddTimeZoneDialog.value = true}){

                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add Timezone"
                        )
                    }
               }
            },

            bottomBar = {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.primary
                ) {
                    bottomNavigationItems.forEachIndexed { i, bottomNavigationItem ->

                        NavigationBarItem(
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = Color.White,
                                selectedTextColor = Color.White,
                                unselectedIconColor = Color.Black,
                                unselectedTextColor = Color.Black,
                                indicatorColor = MaterialTheme.colorScheme.primary
                            ),
                            label = {
                                Text(
                                    bottomNavigationItem.route,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            icon = {
                                Icon(
                                    bottomNavigationItem.icon,
                                    contentDescription = bottomNavigationItem.iconDescription)
                            },
                            selected = screenItemNumber.intValue == i,
                            onClick = {
                                screenItemNumber.intValue = i
                            }
                        )
                    }
                }
            }
        ) {
            padding ->
            Box( modifier = Modifier.padding(padding)){

                if (createAddTimeZoneDialog.value) {
                    AddTimeZoneDialog(

                        onAdd = { newTimezones ->
                            createAddTimeZoneDialog.value = false
                            for (zone in newTimezones) {

                                if (!currentTimeZone.contains(zone)) {
                                    currentTimeZone.add(zone)
                                }
                            }
                        },
                        onDismiss = {

                            createAddTimeZoneDialog.value = false
                        },
                    )
                }

                when (screenItemNumber.intValue) {

                    0 -> TimeZoneScreen(currentTimeZone)
                }
            }
        }
    }
}
