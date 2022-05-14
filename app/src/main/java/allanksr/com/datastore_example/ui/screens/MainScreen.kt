package allanksr.com.datastore_example.ui.screens

import allanksr.com.datastore_example.R
import allanksr.com.datastore_example.ui.nav_graph.Route
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun MainScreen(
    navController: NavController,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 5.dp,
                        top = 5.dp,
                        end = 5.dp,
                        bottom = 5.dp,
                    ),
                text = stringResource(R.string.app_name),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp,
                    color = MaterialTheme.colors.onBackground,
                    textAlign = TextAlign.Center
                )
            )
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
                content = {
                    Button(
                        onClick = { navController.navigate(Route.DataStoreScreen.route) },
                        content = {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 5.dp,
                                        top = 5.dp,
                                        end = 5.dp,
                                        bottom = 5.dp,
                                    ),
                                text = stringResource(R.string.store_screen),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colors.onBackground
                                )
                            )
                        }
                    )
                }
            )
            Box(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
                content = {
                    Button(
                        onClick = { navController.navigate(Route.DataProtoScreen.route) },
                        content = {
                            Text(
                                modifier = Modifier
                                    .padding(
                                        start = 5.dp,
                                        top = 5.dp,
                                        end = 5.dp,
                                        bottom = 5.dp,
                                    ),
                                text = stringResource(R.string.proto_screen),
                                style = TextStyle(
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colors.onBackground
                                )
                            )
                        }
                    )
                }
            )
        }
    )
}
