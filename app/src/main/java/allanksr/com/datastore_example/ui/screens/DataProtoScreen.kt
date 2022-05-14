package allanksr.com.datastore_example.ui.screens

import allanksr.com.datastore_example.ui.MainViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import allanksr.com.datastore_example.R
import allanksr.com.datastore_example.common.toast
import allanksr.com.datastore_example.domain.model.DataStoreProto
import allanksr.com.datastore_example.ui.model.ProtoStore
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.Q)
@Composable
fun DataProtoScreen(
    navController: NavController,
    viewModel: MainViewModel = hiltViewModel(),
) {

    val storedString = remember {
        mutableStateOf(
            DataStoreProto(
                someName = "",
                someNumber = 0,
                someBoolean = false,
                someLong = 0L,
                someFloat = 0f
            )
        )
    }
    LaunchedEffect(key1 = true) {
        viewModel.getProtoValue().collectLatest {
            storedString.value = it
        }
        Log.d("LaunchedEffect", "storedString: ${storedString.value}")
    }
    val focusManager = LocalFocusManager.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            },
        verticalArrangement = Arrangement.SpaceBetween,
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.Top,
                    content = {
                        Icon(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(30.dp)
                                .clickable {
                                    navController.popBackStack()
                                },
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.description_back),
                            tint = MaterialTheme.colors.primary
                        )
                    }
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top,
                    content = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = stringResource(R.string.proto_screen),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp,
                                color = MaterialTheme.colors.onBackground
                            )
                        )
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 40.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Top,
                    content = {
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            text = stringResource(R.string.store_proto_info),
                            textAlign = TextAlign.Center,
                            style = TextStyle(
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 15.sp,
                                color = MaterialTheme.colors.onBackground
                            )
                        )
                    }
                )
            }
        }
    )
    TextFieldProtoCompose(
        storedString = storedString,
        focusManager = focusManager,
        screenWidth = screenWidth,
        viewModel = viewModel
    )
}

@Composable
fun TextFieldProtoCompose(
    storedString: MutableState<DataStoreProto>,
    focusManager: FocusManager,
    screenWidth: Dp,
    viewModel: MainViewModel
) {
    val focusRequester = remember { FocusRequester() }
    val context = LocalContext.current

    val storeValue = remember {
        mutableStateOf(ProtoStore())
    }
    LaunchedEffect(key1 = storeValue.value.storeNow) {
        if (storeValue.value.storeNow) {
            viewModel.setProtoValue(
                DataStoreProto(
                    someName = storeValue.value.someName,
                    someNumber = storeValue.value.someNumber,
                    someBoolean = storeValue.value.someBoolean,
                    someLong = storeValue.value.someLong,
                    someFloat = storeValue.value.someFloat
                )
            ).collectLatest {
                context.toast(it)
                storedString.value = DataStoreProto(
                    someName = it
                )
            }
            storeValue.value = ProtoStore(storeNow = false)
        }
    }
    var textField by rememberSaveable { mutableStateOf("") }

    Column(
        content = {
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center,
                content = {
                    Row(
                        content = {
                            TextField(
                                modifier = Modifier
                                    .focusRequester(focusRequester)
                                    .height(100.dp)
                                    .width(screenWidth / 1.5f),
                                value = textField,
                                onValueChange = {
                                    if (it.length < 50)
                                        textField = it
                                },
                                label = {
                                    Text(
                                        text = stringResource(R.string.store_proto_info),
                                        style = TextStyle(
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 15.sp,
                                            color = MaterialTheme.colors.onBackground
                                        )
                                    )
                                },
                                textStyle = TextStyle(
                                    color = MaterialTheme.colors.onBackground,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                ),
                            )
                        }
                    )
                    Row(
                        modifier = Modifier
                            .width(screenWidth / 1.5f)
                            .padding(top = 150.dp),
                        content = {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                text = storedString.value.someName.ifEmpty { stringResource(R.string.no_data_stored) },
                                textAlign = TextAlign.Center,
                                style = TextStyle(
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 15.sp,
                                    color = MaterialTheme.colors.onBackground
                                )
                            )
                        }
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 250.dp),
                        horizontalArrangement = Arrangement.Center,
                        content = {
                            if (!storeValue.value.storeNow) {
                                Button(
                                    onClick = {
                                        if (textField.isNotEmpty()) {
                                            storeValue.value = ProtoStore(
                                                storeNow = true,
                                                someName = textField,
                                                someNumber = textField.length,
                                                someBoolean = false,
                                                someLong = 0L,
                                                someFloat = 0f
                                            )
                                            textField = ""
                                        }
                                        focusManager.clearFocus()
                                    },
                                    content = {
                                        Text(
                                            modifier = Modifier
                                                .padding(
                                                    start = 5.dp,
                                                    top = 5.dp,
                                                    end = 5.dp,
                                                    bottom = 5.dp,
                                                ),
                                            text = stringResource(R.string.store_object),
                                            style = TextStyle(
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 15.sp,
                                                color = MaterialTheme.colors.onBackground
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    )

                    if (storeValue.value.storeNow) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 250.dp),
                            horizontalArrangement = Arrangement.Center,
                            content = {
                                CircularProgressIndicator(
                                    color = MaterialTheme.colors.onBackground,
                                    strokeWidth = ProgressIndicatorDefaults.StrokeWidth
                                )
                            }
                        )
                    }
                }
            )
        }
    )
}