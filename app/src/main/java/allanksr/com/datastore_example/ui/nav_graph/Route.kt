package allanksr.com.datastore_example.ui.nav_graph

sealed class Route(val route: String) {
    object MainScreen : Route("main_screen")
    object DataStoreScreen : Route("store_screen")
    object DataProtoScreen : Route("proto_screen")
}